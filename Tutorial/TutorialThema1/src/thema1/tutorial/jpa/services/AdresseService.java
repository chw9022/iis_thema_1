package thema1.tutorial.jpa.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import thema1.tutorial.jpa.entities.Adresse;

/**
 * Session Bean implementation class AdresseService
 */
@Stateless
@LocalBean
public class AdresseService implements AdresseServiceLocal {

    @PersistenceContext
    EntityManager em;
    /**
     * Default constructor. 
     */
    public AdresseService() {
        // TODO Auto-generated constructor stub
    }
    
    public Adresse create(Adresse a){
        em.persist(a);
        return a;
    }
    
    public List<Adresse> selectAll(){
        TypedQuery<Adresse> query = em.createQuery("SELECT a FROM Adresse a", Adresse.class);
        return query.getResultList();
    }
    
    public Adresse selectOneById(int id){
        Adresse a = em.find(Adresse.class, id);
        return a;
    }

    @Override
    public void delete(Adresse a) {
        a = em.contains(a) ? a : em.merge(a);
        em.remove(a);        
    }
}
