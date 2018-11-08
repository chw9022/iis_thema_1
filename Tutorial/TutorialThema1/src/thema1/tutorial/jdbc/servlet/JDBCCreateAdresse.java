package thema1.tutorial.jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class CreateAdresse
 */
@WebServlet("/CreateAdresse")
public class JDBCCreateAdresse extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(lookup = "java:jboss/datasources/Shop")
    private DataSource dataSource;

    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        Connection con = null;

        try {
            con = dataSource.getConnection();

            createWithNativeSQL(con, writer);
            writer.println();
            createWithPreparedStatement(con, writer);
            
            writer.close();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            writer.println("failed to connect.");
            writer.close();
        }
    }
    
    private void createWithNativeSQL(Connection con, PrintWriter writer){
        writer.println("Insert plain SQL");
        String sql_plain = "INSERT INTO Adresse VALUES(0, 'Ingolstadt', '5', '85055', 'Esplanade')";
        writer.println("Try to insert: " + sql_plain);
        
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql_plain);
            writer.println("inserted succesfully");
        } catch(SQLException e){
            writer.println(e);
            writer.println("failed to insert.");
        }
    }
    
    private void createWithPreparedStatement(Connection con, PrintWriter writer){
        writer.println("Insert Prepared Statement:");
        String sql_prepared = "INSERT INTO Adresse VALUES (?, ?, ?, ?, ?)";
        writer.println(sql_prepared);

        try{
            PreparedStatement pstmt = con.prepareStatement(sql_prepared);
            pstmt.setInt(1, 1);                             // Index starts with 1 not 0
            pstmt.setString(2, "München");
            pstmt.setString(3, "116");
            pstmt.setString(4, "809");
            pstmt.setString(5, "Frankfurter Ring");
            pstmt.executeUpdate();
            writer.println("inserted succesfully");
        } catch(SQLException e){
            writer.println(e);
            writer.println("failed to insert.");
        }
    }

}
