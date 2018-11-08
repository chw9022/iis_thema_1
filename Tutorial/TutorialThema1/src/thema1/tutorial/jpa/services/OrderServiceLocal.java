package thema1.tutorial.jpa.services;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import thema1.tutorial.jpa.entities.Orders;

@Local
public interface OrderServiceLocal {
    void insert(Orders o);
    List<Orders> selectAll();
    Orders update(Orders o);
    Orders selectById(int id);
    void delete(Orders o);
    List<Orders> selectInTimerange(Date start, Date end);
}
