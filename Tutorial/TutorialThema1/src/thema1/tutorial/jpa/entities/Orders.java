package thema1.tutorial.jpa.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    @Column(nullable=false)
	private Date generated;
    @ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Item> items;
    @ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Supplier supplier;
	private static final long serialVersionUID = 1L;

	public Orders() {
		super();
	}
	
	public Orders(Date generated) {
        super();
        this.generated = generated;
    }

    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public Date getGenerated() {
		return this.generated;
	}

	public void setGenerated(Date generated) {
		this.generated = generated;
	}   
	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}   
	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
    @Override
    public String toString() {
        return "Order [id=" + id + ", generated=" + generated + ", items=" + items + ", supplier=" + supplier + "]";
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
        if (!(obj instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }
   
}
