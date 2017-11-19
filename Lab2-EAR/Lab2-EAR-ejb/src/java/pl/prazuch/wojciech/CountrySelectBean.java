/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prazuch.wojciech;

import java.util.List;
import javax.ejb.EJB;
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
@Stateless(mappedName = "countrySelectBean1")
public class CountrySelectBean implements CountrySelectBeanRemote {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Country selectCountryByID(int id) {
        
        return em.find(Country.class, id);
        
    }

    @Override
    public List<Country> selectAllCountries() {
        return em.createNamedQuery("Country.findAllRecords").getResultList();
    }

    @Override
    public List<Country> selectAllCountriesWithCriteria(String attribute, String comparator, String expressionToCompare) {
        Expression<String> expression;
        
        Root<Country> root;
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        
        root = cq.from(Country.class);
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
