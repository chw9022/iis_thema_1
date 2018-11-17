package thema1.tutorial.jpa.servlets.customer;

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

import thema1.tutorial.jpa.entities.Adresse;
import thema1.tutorial.jpa.entities.Customer;
import thema1.tutorial.jpa.entities.Orders;
import thema1.tutorial.jpa.services.CustomerServiceLocal;

/**
 * Servlet implementation class CreateCustomer
 */
@WebServlet("/CreateCustomer")
public class CreateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	CustomerServiceLocal customerService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	    final PrintWriter writer = response.getWriter();
	    
        // create new Customers
        Customer c1 = new Customer("Max", "Mustermann", "max@mustermann.de");
        
        Orders o = new Orders(new Date(Calendar.getInstance().getTime().getTime()));
        List<Orders> olist = new ArrayList<>();
        olist.add(o);
        c1.setOrders(olist);
        
        Adresse a = new Adresse("Hauptstrasse", "3", "85049", "Ingolstadt");
        c1.setAdresse(a);
        
        customerService.insert(c1);
        writer.println("Created Customers:");
        writer.println(c1.toString());
        
        // select all Customers from table
        writer.println("Current Table Content:");
        List<Customer> result = customerService.selectAll();
        result.forEach((customer) -> {
            writer.println(customer.toString());
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
