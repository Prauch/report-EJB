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
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

/**
 *
 * @author wojciechprazuch
 */
@Stateless(mappedName = "countryDeleteBean1")
public class CountryDeleteBean implements CountryDeleteBeanRemote {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void deleteRecordByID(int id) {

        Country country = em.find(Country.class, id);
        em.remove(country);
        
    }

    @Override
    public void deleteAllRecords() {

        em.createQuery("DELETE FROM Country c").executeUpdate();
        
    }

    @Override
    public void deleteAllCountriesWithCriteria(String attribute, String comparator, String expressionToCompare) {

        Expression<String> expression;
        
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        // create delete
        CriteriaDelete<Country> delete = cb.createCriteriaDelete(Country.class);
        
        // set the root class
        Root root = delete.from(Country.class);
        expression = root.get(attribute);
        
        if(comparator.equals("like")){
        delete.where(cb.like(expression, expression));
        }
        else{
        delete.where(cb.equal(expression, expressionToCompare));
        }
        

        this.em.createQuery(delete).executeUpdate();

        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
