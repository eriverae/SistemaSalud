/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "persona_natural_operacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaNaturalOperacion.findAll", query = "SELECT p FROM PersonaNaturalOperacion p"),
    @NamedQuery(name = "PersonaNaturalOperacion.findByIdPersonaNatural", query = "SELECT p FROM PersonaNaturalOperacion p WHERE p.personaNaturalOperacionPK.idPersonaNatural = :idPersonaNatural"),
    @NamedQuery(name = "PersonaNaturalOperacion.findByIdOperacion", query = "SELECT p FROM PersonaNaturalOperacion p WHERE p.personaNaturalOperacionPK.idOperacion = :idOperacion")})
public class PersonaNaturalOperacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaNaturalOperacionPK personaNaturalOperacionPK;

    public PersonaNaturalOperacion() {
    }

    public PersonaNaturalOperacion(PersonaNaturalOperacionPK personaNaturalOperacionPK) {
        this.personaNaturalOperacionPK = personaNaturalOperacionPK;
    }

    public PersonaNaturalOperacion(long idPersonaNatural, long idOperacion) {
        this.personaNaturalOperacionPK = new PersonaNaturalOperacionPK(idPersonaNatural, idOperacion);
    }

    public PersonaNaturalOperacionPK getPersonaNaturalOperacionPK() {
        return personaNaturalOperacionPK;
    }

    public void setPersonaNaturalOperacionPK(PersonaNaturalOperacionPK personaNaturalOperacionPK) {
        this.personaNaturalOperacionPK = personaNaturalOperacionPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaNaturalOperacionPK != null ? personaNaturalOperacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalOperacion)) {
            return false;
        }
        PersonaNaturalOperacion other = (PersonaNaturalOperacion) object;
        if ((this.personaNaturalOperacionPK == null && other.personaNaturalOperacionPK != null) || (this.personaNaturalOperacionPK != null && !this.personaNaturalOperacionPK.equals(other.personaNaturalOperacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalOperacion[ personaNaturalOperacionPK=" + personaNaturalOperacionPK + " ]";
    }
    
}
