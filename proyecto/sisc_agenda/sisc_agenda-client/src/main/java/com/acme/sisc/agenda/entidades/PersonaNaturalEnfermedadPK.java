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
public class PersonaNaturalEnfermedadPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_persona")
    private long idPersona;
    @Basic(optional = false)
    @Column(name = "id_enfermedad")
    private long idEnfermedad;

    public PersonaNaturalEnfermedadPK() {
    }

    public PersonaNaturalEnfermedadPK(long idPersona, long idEnfermedad) {
        this.idPersona = idPersona;
        this.idEnfermedad = idEnfermedad;
    }

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }

    public long getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(long idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersona;
        hash += (int) idEnfermedad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalEnfermedadPK)) {
            return false;
        }
        PersonaNaturalEnfermedadPK other = (PersonaNaturalEnfermedadPK) object;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.idEnfermedad != other.idEnfermedad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalEnfermedadPK[ idPersona=" + idPersona + ", idEnfermedad=" + idEnfermedad + " ]";
    }
    
}
