/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prazuch.wojciech;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author wojciechprazuch
 */
@Remote
public interface CitySelectBeanRemote {
    
    public City selectCityByID(int id);
    
    
    public List<City> selectAllCities();
    
    
    public List<City> selectAllCitiesWithCriteria(String attribute, String comparator, String expressionToCompare);
    
}
