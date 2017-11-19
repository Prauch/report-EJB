/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prazuch.wojciech;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Stateless(mappedName = "cityUpdateBean1")
public class CityUpdateBean implements CityUpdateBeanRemote {

    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void updateCityById(String attribute, String value, int id) {
        
        City city = em.find(City.class, id);
        
        if(attribute.equals("name")){
            city.setName(value);
        }
        else if(attribute.equals("populationSize")){
            city.setPopulationSize(Integer.parseInt(value));
        }else if(attribute.equals("language")){
            city.setPostalCode(value);
        }
        else{
            DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            Date date;
            try {
                date = fmt.parse(value);
                city.setFoundingDate(date);
            } catch (ParseException ex) {
                Logger.getLogger(CityUpdateBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        em.merge(city);
    }

    @Override
    public void updateCityByAttribute(String attribute, String value, String attributeToCompare, String comparator, String valueToCompare) {
        
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        // create delete
        CriteriaUpdate<City> update = cb.createCriteriaUpdate(City.class);
        
        // set the root class
        Root root = update.from(City.class);
        
        update.set(attribute, value);
        
        Expression<String> expression;
        expression = root.get(attributeToCompare);
        
        if(comparator.equals("like")){
        update.where(cb.like(expression, valueToCompare));
        }
        else{
        update.where(cb.equal(expression, valueToCompare));
        }
        

        this.em.createQuery(update).executeUpdate();
        
    }

    @Override
    public void updateCityByAllRecords(String attribute, String value) {
        
        StringBuilder sb = new StringBuilder();
        sb.append("Update City c Set c.");
        sb.append(attribute);
        sb.append(" ");
        sb.append("= '");
        sb.append(value);
        sb.append("'");
        
        em.createQuery(sb.toString()).executeUpdate();
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
