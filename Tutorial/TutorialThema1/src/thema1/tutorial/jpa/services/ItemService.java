package thema1.tutorial.jpa.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import thema1.tutorial.jpa.entities.Item;

/**
 * Session Bean implementation class ItemService
 */
@Stateless
@LocalBean
public class ItemService implements ItemServiceLocal {

    @PersistenceContext
    EntityManager em;
    /**
     * Default constructor. 
     */
    public ItemService() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Item insert(Item o) {
        em.persist(o);
        return o;
    }
}
