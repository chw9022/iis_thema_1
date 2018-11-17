package thema1.tutorial.jpa.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import thema1.tutorial.jpa.entities.Customer;

/**
 * Session Bean implementation class CustomerService
 */
@Stateless
@LocalBean
public class CustomerService implements CustomerServiceLocal {

    @PersistenceContext
    EntityManager em;
    
    public CustomerService() {
    }
    
    @Override
    public Customer insert(Customer c){
        em.persist(c);
        return c;
    }
    
    @Override
    public List<Customer> selectAll(){
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> result = query.getResultList();
        return result;
    }
}
