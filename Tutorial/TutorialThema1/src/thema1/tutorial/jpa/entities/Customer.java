package thema1.tutorial.jpa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Customer
 *
 */
@Entity

public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String firstname;
	@Column(nullable=false)
	private String lastname;
	@Column(nullable=false)
	private String email;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Adresse adresse;
	@OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Orders> orders;
	private static final long serialVersionUID = 1L;

	public Customer() {
		super();
	}   
	
	public Customer(String firstname, String lastname, String email) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
	
    @Override
    public String toString() {
        return "Customer [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
                + ", adresse=" + adresse + ", orders=" + orders + "]";
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
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    public int getId() {
		return this.id;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}   
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}   
	public List<Orders> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
   
}
