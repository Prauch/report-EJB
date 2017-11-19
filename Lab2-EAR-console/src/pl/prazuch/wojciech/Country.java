/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prazuch.wojciech;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author wojciechprazuch
 */

@Entity
@Table
@NamedQueries({@NamedQuery(name = "Country.findAllRecords", 
        query = "SELECT c from Country c")})
public class Country implements Serializable {
    /**
     * A primary key for the Country class
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * A name of the country
     */
    @Column()
    private String name;
    
    /**
     * A continent a country lies within
     */
    @Column()
    private String continent;
    
    /**
     * A population size of given country
     */
    @Column()
    private int populationSize;
    
    /**
     * The official language in the country
     */
    @Column()
    private String language;

    public String getName() {
        return name;
    }
    
    /**
     * A list of cities in the country
     */
    @OneToMany(mappedBy = "country", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<City> cities = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * An overriden hashCode for Country class
     * @return returns a hash which is an int, to indicate whether objects are of same type
     */
    @Override    
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.id);
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
        final Country other = (Country) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * Description of a given Country entity
     * @return returns a string consisting of the id, name, continent, language, population size of given Country
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId());
        sb.append(" ");
        sb.append(this.getName());
        sb.append(" ");
        sb.append(this.getContinent());
        sb.append(" ");
        sb.append(this.getLanguage());
        sb.append(" ");
        sb.append(this.getPopulationSize());
        
        return sb.toString();
    }
    
    
    
}
