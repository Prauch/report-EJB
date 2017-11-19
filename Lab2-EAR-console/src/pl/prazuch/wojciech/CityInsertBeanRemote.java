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
public interface CityInsertBeanRemote {
    
    public void insertCity(City city);
    
}
