/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

/**
 *
 * @author desarrollador
 */
public interface IAutenticador {
    public boolean autenticar(String user,String password);
}
