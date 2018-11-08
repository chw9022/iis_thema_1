package thema1.tutorial.jpa.services;

import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import thema1.tutorial.jpa.entities.Orders;

/**
 * Session Bean implementation class OrderService
 */
@Stateless
@LocalBean
public class OrderService implements OrderServiceLocal {

    @PersistenceContext
    EntityManager em;

    /**
     * Default constructor.
     */
    public OrderService() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void insert(Orders o) {
        em.persist(o);
    }

    @Override
    public List<Orders> selectAll() {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o", Orders.class);
        List<Orders> result = query.getResultList();
        return result;
    }

    @Override
    public Orders selectById(int id) {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE id = :id", Orders.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Orders update(Orders o) {
        em.flush();
        em.merge(o);
        return o;
    }

    @Override
    public void delete(Orders o) {
        o = em.contains(o) ? o : em.merge(o);
        em.remove(o);
    }

    @Override
    public List<Orders> selectInTimerange(Date start, Date end){
        TypedQuery<Orders> query = em.createQuery("Select o FROM Orders o WHERE generated BETWEEN :start AND :end", Orders.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }
}
