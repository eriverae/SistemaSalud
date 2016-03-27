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
@Table(name = "persona_natural_enfermedad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaNaturalEnfermedad.findAll", query = "SELECT p FROM PersonaNaturalEnfermedad p"),
    @NamedQuery(name = "PersonaNaturalEnfermedad.findByIdPersona", query = "SELECT p FROM PersonaNaturalEnfermedad p WHERE p.personaNaturalEnfermedadPK.idPersona = :idPersona"),
    @NamedQuery(name = "PersonaNaturalEnfermedad.findByIdEnfermedad", query = "SELECT p FROM PersonaNaturalEnfermedad p WHERE p.personaNaturalEnfermedadPK.idEnfermedad = :idEnfermedad")})
public class PersonaNaturalEnfermedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaNaturalEnfermedadPK personaNaturalEnfermedadPK;

    public PersonaNaturalEnfermedad() {
    }

    public PersonaNaturalEnfermedad(PersonaNaturalEnfermedadPK personaNaturalEnfermedadPK) {
        this.personaNaturalEnfermedadPK = personaNaturalEnfermedadPK;
    }

    public PersonaNaturalEnfermedad(long idPersona, long idEnfermedad) {
        this.personaNaturalEnfermedadPK = new PersonaNaturalEnfermedadPK(idPersona, idEnfermedad);
    }

    public PersonaNaturalEnfermedadPK getPersonaNaturalEnfermedadPK() {
        return personaNaturalEnfermedadPK;
    }

    public void setPersonaNaturalEnfermedadPK(PersonaNaturalEnfermedadPK personaNaturalEnfermedadPK) {
        this.personaNaturalEnfermedadPK = personaNaturalEnfermedadPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaNaturalEnfermedadPK != null ? personaNaturalEnfermedadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalEnfermedad)) {
            return false;
        }
        PersonaNaturalEnfermedad other = (PersonaNaturalEnfermedad) object;
        if ((this.personaNaturalEnfermedadPK == null && other.personaNaturalEnfermedadPK != null) || (this.personaNaturalEnfermedadPK != null && !this.personaNaturalEnfermedadPK.equals(other.personaNaturalEnfermedadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalEnfermedad[ personaNaturalEnfermedadPK=" + personaNaturalEnfermedadPK + " ]";
    }
    
}
