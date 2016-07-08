/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

/**
* Interface IAutenticador, define el metodo autenticar que implementan las clases BaseDatosAutenticador y DummyAutenticador
*
* @author  Erika
* @version 1.0
* @since   2016-04-27
*/
public interface IAutenticador {
     public boolean autenticar(String usuario, String password);
}
