/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prazuch.wojciech;

import javax.ejb.Remote;

/**
 *
 * @author wojciechprazuch
 */
@Remote
public interface CityUpdateBeanRemote {
    
    public void updateCityById(String attribute, String value, int id);
    
    public void updateCityByAttribute(String attribute, String value, String attributeToCompare, String comparator, String valueToCompare);
    
    public void updateCityByAllRecords(String attribute, String value);
    
    
    
}
