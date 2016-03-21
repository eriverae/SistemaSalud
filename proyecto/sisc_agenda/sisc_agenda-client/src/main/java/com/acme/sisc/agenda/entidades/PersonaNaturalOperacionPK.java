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
public class PersonaNaturalOperacionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_persona_natural")
    private long idPersonaNatural;
    @Basic(optional = false)
    @Column(name = "id_operacion")
    private long idOperacion;

    public PersonaNaturalOperacionPK() {
    }

    public PersonaNaturalOperacionPK(long idPersonaNatural, long idOperacion) {
        this.idPersonaNatural = idPersonaNatural;
        this.idOperacion = idOperacion;
    }

    public long getIdPersonaNatural() {
        return idPersonaNatural;
    }

    public void setIdPersonaNatural(long idPersonaNatural) {
        this.idPersonaNatural = idPersonaNatural;
    }

    public long getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(long idOperacion) {
        this.idOperacion = idOperacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersonaNatural;
        hash += (int) idOperacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalOperacionPK)) {
            return false;
        }
        PersonaNaturalOperacionPK other = (PersonaNaturalOperacionPK) object;
        if (this.idPersonaNatural != other.idPersonaNatural) {
            return false;
        }
        if (this.idOperacion != other.idOperacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalOperacionPK[ idPersonaNatural=" + idPersonaNatural + ", idOperacion=" + idOperacion + " ]";
    }
    
}
