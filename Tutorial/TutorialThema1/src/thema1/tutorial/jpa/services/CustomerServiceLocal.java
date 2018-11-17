package thema1.tutorial.jpa.services;

import java.util.List;

import javax.ejb.Local;

import thema1.tutorial.jpa.entities.Customer;

@Local
public interface CustomerServiceLocal {
    public Customer insert(Customer c);
    public List<Customer> selectAll();
}
