package thema1.tutorial.jpa.services;

import java.util.List;

import javax.ejb.Local;

import thema1.tutorial.jpa.entities.Adresse;

@Local
public interface AdresseServiceLocal {
    public Adresse create(Adresse a);
    public List<Adresse> selectAll();
    public Adresse selectOneById(int id);
    public void delete(Adresse a);
}
