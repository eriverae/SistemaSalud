/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.dto;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.agenda.entidades.Acceso;

/**
 *
 * @author Julio
 */
public class GrupoCompleto {
    
    private Grupo grupo;
    private java.util.List<Acceso> accesos;

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public java.util.List<Acceso> getAccesos() {
        return accesos;
    }

    public void setAccesos(java.util.List<Acceso> accesos) {
        this.accesos = accesos;
    }
    
    
    
}
