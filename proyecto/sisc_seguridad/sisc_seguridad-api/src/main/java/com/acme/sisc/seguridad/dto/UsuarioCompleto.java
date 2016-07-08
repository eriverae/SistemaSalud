/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.dto;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.agenda.entidades.Usuario;

/**
* Esta clase es un objeto de UsuarioCompleto, compuesto por Usuario y su listado de perfiles, 
* se implementa cuando estamos consumiendo el servicio de seguridad para generar
* el la autenticación
*
* @author  Julio
* @version 1.0
* @since   2016-05-18
*/
public class UsuarioCompleto {
    private Usuario usuario;
    private java.util.List<Grupo> grupos;

   /**
   * Getter de la clase UsuarioCompleto el cual retorna el usuario
   * @return retorna Usuario con el objeto usuario.
   */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
    * Setter de la clase UsuarioCompleto el cual setea el objeto usuario
    * @param Usuario objeto usuario de la clase
    */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   /**
   * Getter de la clase UsuarioCompleto el cual retorna la lista de perfiles del usuario
   * @return retorna java.util.List<Grupo> del usuario 
   */
    public java.util.List<Grupo> getGrupos() {
        return grupos;
    }

    /**
    * Setter de la clase UsuarioCompleto el cual setea el listado de perfiles del usuario
    * @param java.util.List<Grupo> una lista de grupos del usuario
    */
    public void setGrupos(java.util.List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    
}
