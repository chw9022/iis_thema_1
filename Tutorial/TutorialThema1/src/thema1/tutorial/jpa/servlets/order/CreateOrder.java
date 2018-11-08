package thema1.tutorial.jpa.servlets.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thema1.tutorial.jpa.entities.Item;
import thema1.tutorial.jpa.entities.Orders;
import thema1.tutorial.jpa.entities.Supplier;
import thema1.tutorial.jpa.services.OrderServiceLocal;

/**
 * Servlet implementation class CreateOrder
 */
@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	OrderServiceLocal orderService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();

        // create new Order
        Orders o = new Orders(new Date(Calendar.getInstance().getTime().getTime()));
        
        Item item = new Item("Klobrille", Float.parseFloat("1.15"), "super zum sauber machen", "blau" );
        List<Item> itemlist = new ArrayList<>();
        itemlist.add(item);
        o.setItems(itemlist);
        
        Supplier sup = new Supplier("DHL");
        o.setSupplier(sup);
        
        orderService.insert(o);
        writer.println("Created Order:");
        writer.println(o.toString());

        // select all Orders from table
        writer.println("Current Table Content:");
        List<Orders> result = orderService.selectAll();
        result.forEach((order) -> {
            writer.println(order.toString());
        });
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
