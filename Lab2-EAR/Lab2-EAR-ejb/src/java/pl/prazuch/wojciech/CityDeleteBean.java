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
@Stateless(mappedName = "cityDeleteBean1")
public class CityDeleteBean implements CityDeleteBeanRemote {

    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void deleteRecordByID(int id) {

        City city = em.find(City.class, id);
        em.remove(city);
        
    }

    @Override
    public void deleteAllRecords() {
        
        em.createQuery("DELETE FROM City c").executeUpdate();

    }

    @Override
    public void deleteAllCitiesWithCriteria(String attribute, String comparator, String expressionToCompare) {
        
        Expression<String> expression;
        
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        // create delete
        CriteriaDelete<City> delete = cb.createCriteriaDelete(City.class);
        
        // set the root class
        Root root = delete.from(City.class);
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
