/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.dto;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.agenda.entidades.Acceso;

/**
* La clase grupo completo esta compuesta por dos objetos del negocio (Grupo) y (Acceso),
* se implementa para devolver los accesos que tiene el perfil a crear
*
* @author  Julio
* @version 1.0
* @since   2016-05-22
*/
public class GrupoCompleto {
    
    private Grupo grupo;
    private java.util.List<Acceso> accesos;

   /**
   * Getter de la clase GrupoCompleto el cual retorna el objeto Grupo
   * @return retorna Grupo
   */
    public Grupo getGrupo() {
        return grupo;
    }

    /**
    * Setter de la clase GrupoCompleto el cual setea el objeto grupo
    * @param grupo para setear el grupo de la clase
    */
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

   /**
   * Getter de la clase GrupoCompleto el cual retorna una lista de objetos de Acceso
   * @return retorna java.util.List<Acceso> del la clase
   */
    public java.util.List<Acceso> getAccesos() {
        return accesos;
    }

    /**
    * Setter de la clase GrupoCompleto el cual setea la lista de accesos del grupo
    * @param accesos para setear los objtos de la lista de Acceso
    */
    public void setAccesos(java.util.List<Acceso> accesos) {
        this.accesos = accesos;
    }
    
    
    
}
