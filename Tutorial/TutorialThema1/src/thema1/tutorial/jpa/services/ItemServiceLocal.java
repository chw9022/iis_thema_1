package thema1.tutorial.jpa.services;

import javax.ejb.Local;

import thema1.tutorial.jpa.entities.Item;

@Local
public interface ItemServiceLocal {

    Item insert(Item o);

}
