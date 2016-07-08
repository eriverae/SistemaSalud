/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

/**
* Esta clase contiene un metodo Dummy para retornar un true en la autenticación
*
* @author  Erika
* @version 1.0
* @since   2016-05-01
*/
public class DummyAutenticador implements IAutenticador {

    /**
    * Metodo autenticar retorna siempre true, es un dummy para la autenticación
    * @param usuario String es el mail del usario
    * @param password String es la contraseña del usuario
    * @return retorna boolean por defecto true
    */
    @Override
    public boolean autenticar(String usuario, String password) {
        return true;
    }
    
}
