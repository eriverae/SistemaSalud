/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.dto;

import java.io.Serializable;

/**
* Esta clase es un objeto de credenciales, compuesto por usuario y contraseña, 
* se implementa cuando estamos consumiendo el servicio de seguridad para generar
* el la autenticación
*
* @author  Julio
* @version 1.0
* @since   2016-05-01
*/
public class Credenciales implements Serializable{
    
    private String usuario;
    private String password;

   /**
   * Getter de la clase Credenciales el cual retorna el mail del usuario
   * @return retorna String con en el mail de usuario.
   */
    public String getUsuario() {
        return usuario;
    }

    /**
    * Setter de la clase Credenciales el cual setea el mail del usuario
    * @param usuario para setear el mail del usuario de la clase
    */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

   /**
   * Getter de la clase Credenciales el cual retorna la contraseña del usuario
   * @return retorna String con la contraseña del usuario.
   */
    public String getPassword() {
        return password;
    }

    /**
    * Setter de la clase Credenciales el cual setea el password del usuario
    * @param password para setear el password de la clase
    */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
