package thema1.tutorial.jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * Servlet implementation class SelectAdresse
 */
@WebServlet("/SelectAdresse")
public class JDBCSelectAdresse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Resource(lookup = "java:jboss/datasources/Shop")
    private DataSource dataSource;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        Connection con = null;
        
        try {
            con = dataSource.getConnection();
            writer.println("Connection erfolgreich: " + con.isValid(10));
            
            selectWithNativeSQL(con, writer);
            writer.println();
            selectWithPreparedStatement(con, writer);
            
            writer.close();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            writer.println("failed to connect.");
            writer.close();
        }
	}

	private void selectWithNativeSQL(Connection con, PrintWriter writer){
        writer.println("Select with plain SQL");
        String sql_plain = "SELECT * FROM Adresse WHERE id = 0";
        writer.println("Try to select: " + sql_plain);
        
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql_plain);
            while(rs.next()){
                writer.println("Result:");
                writer.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
        } catch(SQLException e){
            writer.print(e);
            writer.println("failed to select.");
        }
	}

	private void selectWithPreparedStatement(Connection con, PrintWriter writer){
        // prepared Statement
        writer.println("Select with Prepared Statement:");
        String sql_prepared = "SELECT * FROM Adresse WHERE id=?";
        writer.println(sql_prepared);
        
        try{
            PreparedStatement pstmt = con.prepareStatement(sql_prepared);
            pstmt.setInt(1, 1);
            pstmt.executeQuery();
            ResultSet rs = pstmt.getResultSet();
            while(rs.next()){
                writer.println("Result:");
                writer.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
        } catch(SQLException e){
            writer.println("failed to select.");
        }
	}
}
