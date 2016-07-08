/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.dto;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import java.io.Serializable;
import java.util.List;

/**
* Esta clase el la respuesta que retorna el servicio de seguridad cla cual 
* contiene la autenticacion de la BD, el token, la persona Natural que se 
* logeo dado el mail, y una lista de grupos con los perfiles del usuario
*
* @author  Julio
* @version 1.0
* @since   2016-05-01
*/
public class RespuestaAutenticacion implements Serializable{
    
    private boolean autheticated;
    private String token;
    private PersonaNatural personaNatural;
    private List <String> listaGrupos;
    
   /**
   * Getter de la clase RespuestaAutenticacion el cual retorna un boolean con la autenticación de la BD
   * @return retorna boolean con la autenticacion sobre la BD
   */
    public boolean isAutheticated() {
        return autheticated;
    }

    /**
    * Setter de la clase RespuestaAutenticacion el cual setea el boolean de la autenticacion
    * @param autheticated un boolean con el estatus de la autenticacion en la BD
    */
    public void setAutheticated(boolean autheticated) {
        this.autheticated = autheticated;
    }

   /**
   * Getter de la clase RespuestaAutenticacion el cual retorna el token de la autenticacion
   * @return retorna String con el token generado por Utils de JWT
   */
    public String getToken() {
        return token;
    }

    /**
    * Setter de la clase RespuestaAutenticacion el cual setea el token de la autenticacion
    * @param token un String con el token de la autenticacion
    */
    public void setToken(String token) {
        this.token = token;
    }

   /**
   * Getter de la clase RespuestaAutenticacion el cual retorna la persona Natural dado el mail del usuario
   * @return retorna personaNatural del usuario 
   */
    public PersonaNatural getPersonaNatural() {
        return personaNatural;
    }

    /**
    * Setter de la clase RespuestaAutenticacion el cual setea la persona natural del usuario
    * @param personaNatural un objeto de tipo PersonaNatural con la información del usuario
    */
    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }

   /**
   * Getter de la clase RespuestaAutenticacion el cual retorna la lista de perfiles del usuario
   * @return retorna List <String> del usuario 
   */
    public List <String> getListaGrupos() {
        return listaGrupos;
    }

    /**
    * Setter de la clase RespuestaAutenticacion el cual setea el listado de perfiles del usuario
    * @param List <String> una lista de grupos del usuario
    */
    public void setListaGrupos(List <String> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }
    
    
    
}
