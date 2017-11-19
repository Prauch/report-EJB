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
public interface CityDeleteBeanRemote {
    
    public void deleteRecordByID(int id);
    
    public void deleteAllRecords();
    
    public void deleteAllCitiesWithCriteria(String attribute, String comparator, String expressionToCompare);
    
    
    
}
