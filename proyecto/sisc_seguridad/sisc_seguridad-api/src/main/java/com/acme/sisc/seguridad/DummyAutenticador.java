/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

/**
 *
 * @author Erika
 */
public class DummyAutenticador implements IAutenticador {

    
    @Override
    public boolean autenticar(String usuario, String password) {
        return true;
    }
    
}
