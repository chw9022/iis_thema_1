package thema1.tutorial.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import thema1.tutorial.jpa.entities.Customer;
import thema1.tutorial.jpa.entities.Orders;

public class TestCustomer {

    @Test
    public void testEquals() {
        Customer c1 = new Customer();
        Customer c2 = new Customer();
        c1.setId(1);
        c2.setId(1);
        assertEquals(c1,c2);
    }
    
    @Test
    public void testHash()
    {
        Customer c1 = new Customer();
        Customer c2 = new Customer();
        c1.setId(1);
        c2.setId(1);
        assertEquals(c1.hashCode(),c2.hashCode());
    }
    
    @Test
    public void testToString()
    {
        Customer c1 = new Customer("first","last","email@address");
        Customer c2 = new Customer("first","last","email@address");
        c1.setId(1);
        c2.setId(1);
        assertEquals(c1.toString(),c2.toString());
    }

}
