package thema1.tutorial.jpa.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Adresse
 *
 */
@Entity

public class Adresse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    @Column(nullable=false)
	private String street;
    @Column(nullable=false)
	private String houseNumber;
    @Column(nullable=false)
	private String postalCode;
    @Column(nullable=false)
	private String city;
	private static final long serialVersionUID = 1L;

	public Adresse() {
		super();
	}   
	
	
	public Adresse(String street, String houseNumber, String postalCode, String city) {
        super();
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }


    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}   
	public String getHouseNumber() {
		return this.houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}   
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}   
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}


    @Override
    public String toString() {
        return "Adresse [id=" + id + ", street=" + street + ", houseNumber=" + houseNumber + ", postalCode="
                + postalCode + ", city=" + city + "]";
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
        if (!(obj instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }
   
	
}
