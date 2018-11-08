package thema1.tutorial.jpa.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity

public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private float price;
	private String description;
	private String color;
	private static final long serialVersionUID = 1L;

	public Item() {
		super();
	}   
	
	
	public Item(String name, float price, String description, String color) {
        super();
        this.name = name;
        this.price = price;
        this.description = description;
        this.color = color;
    }


    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}


    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + ", color="
                + color + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Item)) {
            return false;
        }
        Item other = (Item) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }
   
	
}
