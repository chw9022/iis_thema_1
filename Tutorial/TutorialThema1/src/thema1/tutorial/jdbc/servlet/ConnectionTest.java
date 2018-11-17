package thema1.tutorial.jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ConnectionTest
 */
@WebServlet("/ConnectionTest")
public class ConnectionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Resource(lookup="java:jboss/datasources/Shop")
	private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        PrintWriter writer = response.getWriter();
        Connection con = null;
        try {
            // get a Connection
            con = dataSource.getConnection();
            // print if connection is valid
            writer.println("Connection erfolgreich: " + con.isValid(10));
            // close writer and connection
            writer.close();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
