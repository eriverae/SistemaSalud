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
 *
 * @author Julio
 */
public class RespuestaAutenticacion implements Serializable{
    
    private boolean autheticated;
    private String token;
    private PersonaNatural personaNatural;
    private List <String> listaGrupos;
    

    public boolean isAutheticated() {
        return autheticated;
    }

    public void setAutheticated(boolean autheticated) {
        this.autheticated = autheticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PersonaNatural getPersonaNatural() {
        return personaNatural;
    }

    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }

    public List <String> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List <String> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }
    
    
    
}
