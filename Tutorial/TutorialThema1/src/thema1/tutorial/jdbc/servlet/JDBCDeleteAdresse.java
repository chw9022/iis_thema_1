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
 * Servlet implementation class DeleteAdresse
 */
@WebServlet("/DeleteAdresse")
public class JDBCDeleteAdresse extends HttpServlet {
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
            
            deleteWithNativeSQL(con, writer);
            writer.println();
            deleteWithPreparedStatement(con, writer);
            
            writer.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            writer.println("failed to connect.");
            writer.close();
        }
	}
	
	private void deleteWithNativeSQL(Connection con, PrintWriter writer){
        //plain sql string
        writer.println("Delete with plain SQL");
        String sql_plain = "DELETE FROM Adresse WHERE id = 0";
        writer.println("Try to delete: " + sql_plain);
        
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql_plain);
            // Select to check if changes were done
            String sql_update = "SELECT * FROM Adresse";
            try{
                ResultSet rs = stmt.executeQuery(sql_update);
                writer.println("Result:");
                while(rs.next()){
                    writer.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                }
            } catch(SQLException e){
                writer.print(e);
                writer.println("failed to select result.");
            }
            writer.println("deleted succesfully");
        } catch(SQLException e){
            writer.println("failed to delete.");
        }
	}
	
	private void deleteWithPreparedStatement(Connection con, PrintWriter writer){        
        writer.println("Delete with Prepared Statement:");
        String sql_prepared = "DELETE FROM Adresse WHERE id = ?";
        writer.println(sql_prepared);
        
        try{
            PreparedStatement pstmt = con.prepareStatement(sql_prepared);
            pstmt.setInt(1, 1);
            pstmt.executeUpdate();
            String sql_update = "SELECT * FROM Adresse";
            try{
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql_update);
                writer.println("Result:");
                while(rs.next()){
                    writer.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                }
            } catch(SQLException e){
                writer.print(e);
                writer.println("failed to select result.");
            }
        } catch(SQLException e){
            writer.println("failed to delete.");
        }
	}

}
