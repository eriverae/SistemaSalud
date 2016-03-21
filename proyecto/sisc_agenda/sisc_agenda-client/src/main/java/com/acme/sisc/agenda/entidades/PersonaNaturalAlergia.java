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
@Table(name = "persona_natural_alergia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaNaturalAlergia.findAll", query = "SELECT p FROM PersonaNaturalAlergia p"),
    @NamedQuery(name = "PersonaNaturalAlergia.findByIdPersonaNatural", query = "SELECT p FROM PersonaNaturalAlergia p WHERE p.personaNaturalAlergiaPK.idPersonaNatural = :idPersonaNatural"),
    @NamedQuery(name = "PersonaNaturalAlergia.findByIdAlergia", query = "SELECT p FROM PersonaNaturalAlergia p WHERE p.personaNaturalAlergiaPK.idAlergia = :idAlergia")})
public class PersonaNaturalAlergia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaNaturalAlergiaPK personaNaturalAlergiaPK;

    public PersonaNaturalAlergia() {
    }

    public PersonaNaturalAlergia(PersonaNaturalAlergiaPK personaNaturalAlergiaPK) {
        this.personaNaturalAlergiaPK = personaNaturalAlergiaPK;
    }

    public PersonaNaturalAlergia(long idPersonaNatural, long idAlergia) {
        this.personaNaturalAlergiaPK = new PersonaNaturalAlergiaPK(idPersonaNatural, idAlergia);
    }

    public PersonaNaturalAlergiaPK getPersonaNaturalAlergiaPK() {
        return personaNaturalAlergiaPK;
    }

    public void setPersonaNaturalAlergiaPK(PersonaNaturalAlergiaPK personaNaturalAlergiaPK) {
        this.personaNaturalAlergiaPK = personaNaturalAlergiaPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaNaturalAlergiaPK != null ? personaNaturalAlergiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalAlergia)) {
            return false;
        }
        PersonaNaturalAlergia other = (PersonaNaturalAlergia) object;
        if ((this.personaNaturalAlergiaPK == null && other.personaNaturalAlergiaPK != null) || (this.personaNaturalAlergiaPK != null && !this.personaNaturalAlergiaPK.equals(other.personaNaturalAlergiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalAlergia[ personaNaturalAlergiaPK=" + personaNaturalAlergiaPK + " ]";
    }
    
}
