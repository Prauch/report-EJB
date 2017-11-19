/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prazuch.wojciech;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

/**
 *
 * @author wojciechprazuch
 */
@Stateless(mappedName = "countryUpdateBean1")
public class CountryUpdateBean implements CountryUpdateBeanRemote {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void updateCountryById(String attribute, String value, int id) {

        Country country = em.find(Country.class, id);
        
        if(attribute.equals("name")){
            country.setName(value);
        }
        else if(attribute.equals("populationSize")){
            country.setPopulationSize(Integer.parseInt(value));
        }else if(attribute.equals("language")){
            country.setLanguage(value);
        }
        else{
            country.setContinent(value);
        }
        
        em.merge(country);
        
    }

    @Override
    public void updateCountryByAllRecords(String attribute, String value) {

        StringBuilder sb = new StringBuilder();
        sb.append("Update Country c Set c.");
        sb.append(attribute);
        sb.append(" ");
        sb.append("= '");
        sb.append(value);
        sb.append("'");
        
        em.createQuery(sb.toString()).executeUpdate();
        
    }

    @Override
    public void updateCountryByAttribute(String attribute, String value, String attributeToCompare, String comparator, String valueToCompare) {

        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        // create delete
        CriteriaUpdate<Country> update = cb.createCriteriaUpdate(Country.class);
        
        // set the root class
        Root root = update.from(Country.class);
        
        update.set(attribute, value);
        
        Expression<String> expression;
        expression = root.get(attributeToCompare);
        
        if(comparator.equals("like")){
        update.where(cb.like(expression, valueToCompare));
        }
        else{
        update.where(cb.equal(expression, valueToCompare));
        }
        

        em.getTransaction().begin();
        this.em.createQuery(update).executeUpdate();
        em.getTransaction().commit();

    }

    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
