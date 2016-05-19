/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.dto;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.agenda.entidades.Usuario;

/**
 *
 * @author Julio
 */
public class UsuarioCompleto {
    private Usuario usuario;
    private java.util.List<Grupo> grupos;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public java.util.List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(java.util.List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    
}
