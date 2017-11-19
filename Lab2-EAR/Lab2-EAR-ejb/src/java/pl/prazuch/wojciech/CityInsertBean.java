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
@Stateless(mappedName = "cityInsertBean1")
public class CityInsertBean implements CityInsertBeanRemote {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void insertCity(City city) {

        em.merge(city);

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
