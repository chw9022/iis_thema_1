package thema1.tutorial.jpa.servlets.apmanaged;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import thema1.tutorial.jpa.entities.Orders;

/**
 * Servlet implementation class DeleteOrder
 */
@WebServlet(name = "AmDeleteOrder", urlPatterns = { "/AmDeleteOrder" })
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @PersistenceUnit
    EntityManagerFactory emf;

    @Resource
    private UserTransaction utx;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();
        
        // create Entity Manager from Factory
        EntityManager em = emf.createEntityManager();
        
        // create new object
        Orders o = new Orders(new Date(Calendar.getInstance().getTime().getTime()));

        try {
            utx.begin();                            // begin transaction
            em.persist(o);                          // save object
            em.remove(o);;
            //query to get all results
            TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o", Orders.class);
            List<Orders> result = query.getResultList();
            utx.commit();                           // run transaction
            writer.println("Created Order:");       // print
            writer.println(o.toString());
            writer.println("Current Table Content:");
            result.forEach((order) -> {
                writer.println(order.toString());
            });
        } catch (Exception e) {
            try {
                utx.rollback();                     // rollback if transaction failes
            } catch (Exception e1) {
                e.printStackTrace();
            }
            throw new ServletException(e.getMessage());
        } finally {
            em.close();
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
