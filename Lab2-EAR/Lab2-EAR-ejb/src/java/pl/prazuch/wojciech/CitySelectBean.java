/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prazuch.wojciech;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

/**
 *
 * @author wojciechprazuch
 */
@Stateless(mappedName = "citySelectBean1")
public class CitySelectBean implements CitySelectBeanRemote {

    @PersistenceContext
    private EntityManager em;
    
    
    @Override
    public City selectCityByID(int id) {
        
        return em.find(City.class, id);
        
    }

    @Override
    public List<City> selectAllCities() {
        return em.createNamedQuery("City.findAllRecords").getResultList();
        
    }

    @Override
    public List<City> selectAllCitiesWithCriteria(String attribute, String comparator, String expressionToCompare) {
        
        Expression<String> expression;
        
        Root<City> root;
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<City> cq = cb.createQuery(City.class);
        
        root = cq.from(City.class);
        cq.select(root);
        expression = root.get(attribute);
        if(comparator.equals("like")){
        cq.where(cb.like(expression, expressionToCompare));
        }
        else{
        cq.where(cb.equal(expression, expressionToCompare));
        }
        return em.createQuery(cq).getResultList();
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
