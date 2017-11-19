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
public interface CountryUpdateBeanRemote {
    
    public void updateCountryById(String attribute, String value, int id);
    
    public void updateCountryByAllRecords(String attribute, String value);
    
    public void updateCountryByAttribute(String attribute, String value, String attributeToCompare, String comparator, String valueToCompare);
    
    
}
