/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Julio
 */
@Embeddable
public class PersonaNaturalEspecialidadPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_persona_natural")
    private long idPersonaNatural;
    @Basic(optional = false)
    @Column(name = "id_especialidad")
    private long idEspecialidad;

    public PersonaNaturalEspecialidadPK() {
    }

    public PersonaNaturalEspecialidadPK(long idPersonaNatural, long idEspecialidad) {
        this.idPersonaNatural = idPersonaNatural;
        this.idEspecialidad = idEspecialidad;
    }

    public long getIdPersonaNatural() {
        return idPersonaNatural;
    }

    public void setIdPersonaNatural(long idPersonaNatural) {
        this.idPersonaNatural = idPersonaNatural;
    }

    public long getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(long idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersonaNatural;
        hash += (int) idEspecialidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalEspecialidadPK)) {
            return false;
        }
        PersonaNaturalEspecialidadPK other = (PersonaNaturalEspecialidadPK) object;
        if (this.idPersonaNatural != other.idPersonaNatural) {
            return false;
        }
        if (this.idEspecialidad != other.idEspecialidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalEspecialidadPK[ idPersonaNatural=" + idPersonaNatural + ", idEspecialidad=" + idEspecialidad + " ]";
    }
    
}
