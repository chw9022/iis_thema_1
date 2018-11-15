package thema1.tutorial.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import thema1.tutorial.jpa.entities.Orders;

public class TestOrders {

    @Test
    public void testEquals() {
        Orders o1 = new Orders();
        Orders o2 = new Orders();
        o1.setId(1);
        o2.setId(1);
        assertEquals(o1,o2);
    }
    
    @Test
    public void testHash()
    {
        Orders o1 = new Orders();
        Orders o2 = new Orders();
        o1.setId(1);
        o2.setId(1);
        assertEquals(o1.hashCode(),o2.hashCode());
    }

}
