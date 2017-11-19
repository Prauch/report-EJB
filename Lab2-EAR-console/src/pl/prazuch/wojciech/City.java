/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prazuch.wojciech;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author wojciechprazuch
 */

@Entity
@Table
@NamedQueries({@NamedQuery(name = "City.findAllRecords", 
        query = "SELECT c from City c")})
public class City implements Serializable{
    
    /**
     * A primary key for the City Entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * A name of the city
     */
    @Column(length = 60, nullable = false, name = "cityName")
    private String name;
    
    /**
     * A postal code for the city
     */
    @Column(length = 60, nullable = false)
    private String postalCode;
    
    /**
     * A population in a given city
     */
    @Column()
    private int populationSize;
    
    /**
     * A date of founding the given city
     */
    @Column()
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date foundingDate;
    
    /**
     * A foreign key indicating in which Country a City exists
     */
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    

    /**
     * Overriden hashCode for City class
     * @return returns a hash which is an integer, to indicate whether objects are of same type
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
        
    }

    /**
     * An overriden equals method
     * @param obj an object to be compared with
     * @return whether the passed object is the same as the one, whose method is being executed
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final City other = (City) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * Description of a given City entity
     * @return returns a string consisting of the id, name, postal code, population size of given City
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId());
        sb.append(" ");
        sb.append(this.getName());
        sb.append(" ");
        sb.append(this.getPostalCode());
        sb.append(" ");
        sb.append(this.getPopulationSize());
        
        return sb.toString();
    }
    
    
    
    
}
