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
public class PersonaNaturalAlergiaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_persona_natural")
    private long idPersonaNatural;
    @Basic(optional = false)
    @Column(name = "id_alergia")
    private long idAlergia;

    public PersonaNaturalAlergiaPK() {
    }

    public PersonaNaturalAlergiaPK(long idPersonaNatural, long idAlergia) {
        this.idPersonaNatural = idPersonaNatural;
        this.idAlergia = idAlergia;
    }

    public long getIdPersonaNatural() {
        return idPersonaNatural;
    }

    public void setIdPersonaNatural(long idPersonaNatural) {
        this.idPersonaNatural = idPersonaNatural;
    }

    public long getIdAlergia() {
        return idAlergia;
    }

    public void setIdAlergia(long idAlergia) {
        this.idAlergia = idAlergia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersonaNatural;
        hash += (int) idAlergia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalAlergiaPK)) {
            return false;
        }
        PersonaNaturalAlergiaPK other = (PersonaNaturalAlergiaPK) object;
        if (this.idPersonaNatural != other.idPersonaNatural) {
            return false;
        }
        if (this.idAlergia != other.idAlergia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalAlergiaPK[ idPersonaNatural=" + idPersonaNatural + ", idAlergia=" + idAlergia + " ]";
    }
    
}
