/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.prazuch.wojciech;

import javax.naming.NamingException;
import pl.prazuch.wojciech.View.CRUDMenu;


/**
 *
 * @author wojciechprazuch
 */
public class Main {
    
    public static void main(String[] args) throws NamingException{
        
        CRUDMenu menu;
        
        do{
            menu = new CRUDMenu();
        }
        while(!menu.isUserDoneWithDatabase());
    }
    
}
