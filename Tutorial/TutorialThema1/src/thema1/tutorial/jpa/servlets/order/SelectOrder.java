package thema1.tutorial.jpa.servlets.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thema1.tutorial.jpa.entities.Orders;
import thema1.tutorial.jpa.services.OrderServiceLocal;

/**
 * Servlet implementation class SelectOrder
 */
@WebServlet("/SelectOrder")
public class SelectOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    OrderServiceLocal orderService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();

        // create some orders
        createOrders(writer);

        // select some in daterange
        writer.println("Select Orders between 2018-05-01 and 2018-06-30");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            start = new java.sql.Date(sdf.parse("2018-05-00").getTime());
            end = new java.sql.Date(sdf.parse("2018-06-30").getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        List<Orders> result = orderService.selectInTimerange(start, end);
        writer.println("Selected Result:");
        result.forEach((order) -> {
            writer.println(order.toString());
        });
        
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    private void createOrders(PrintWriter writer) {

        //create some static dates
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        Date date3 = null;
        Date date4 = null;
        try {
            date1 = new java.sql.Date(sdf.parse("2018-04-02").getTime());
            date2 = new java.sql.Date(sdf.parse("2018-05-02").getTime());
            date3 = new java.sql.Date(sdf.parse("2018-06-02").getTime());
            date4 = new java.sql.Date(sdf.parse("2018-07-02").getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // create orders
        Orders o1 = new Orders(date1);
        Orders o2 = new Orders(date2);
        Orders o3 = new Orders(date3);
        Orders o4 = new Orders(date4);

        // insert Orders
        writer.println("Inserted Orders:");
        orderService.insert(o1);
        writer.println(o1.toString());
        orderService.insert(o2);
        writer.println(o2.toString());
        orderService.insert(o3);
        writer.println(o3.toString());
        orderService.insert(o4);
        writer.println(o4.toString());
    }
}
