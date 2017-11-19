/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prazuch.wojciech;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wojciechprazuch
 */
@Stateless(mappedName = "countryInsertBean1")
public class CountryInsertBean implements CountryInsertBeanRemote {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void insertCountry(Country country) {
        
        em.merge(country);
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
