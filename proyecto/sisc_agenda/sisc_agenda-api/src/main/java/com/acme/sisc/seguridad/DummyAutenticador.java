/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desarrollador
 */
public class DummyAutenticador implements  IAutenticador{

    @Override
    public boolean autenticar(String user, String password) {
        
         Logger.getLogger(ProxyAutenticador.class.getName()).log(
                    Level.INFO,"IAutenticador: DummyAutenticador...... ");
       
        return true;
    }
    
}
