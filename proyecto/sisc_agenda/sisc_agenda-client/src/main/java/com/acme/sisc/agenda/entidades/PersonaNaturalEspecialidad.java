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
@Table(name = "persona_natural_especialidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaNaturalEspecialidad.findAll", query = "SELECT p FROM PersonaNaturalEspecialidad p"),
    @NamedQuery(name = "PersonaNaturalEspecialidad.findByIdPersonaNatural", query = "SELECT p FROM PersonaNaturalEspecialidad p WHERE p.personaNaturalEspecialidadPK.idPersonaNatural = :idPersonaNatural"),
    @NamedQuery(name = "PersonaNaturalEspecialidad.findByIdEspecialidad", query = "SELECT p FROM PersonaNaturalEspecialidad p WHERE p.personaNaturalEspecialidadPK.idEspecialidad = :idEspecialidad")})
public class PersonaNaturalEspecialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaNaturalEspecialidadPK personaNaturalEspecialidadPK;

    public PersonaNaturalEspecialidad() {
    }

    public PersonaNaturalEspecialidad(PersonaNaturalEspecialidadPK personaNaturalEspecialidadPK) {
        this.personaNaturalEspecialidadPK = personaNaturalEspecialidadPK;
    }

    public PersonaNaturalEspecialidad(long idPersonaNatural, long idEspecialidad) {
        this.personaNaturalEspecialidadPK = new PersonaNaturalEspecialidadPK(idPersonaNatural, idEspecialidad);
    }

    public PersonaNaturalEspecialidadPK getPersonaNaturalEspecialidadPK() {
        return personaNaturalEspecialidadPK;
    }

    public void setPersonaNaturalEspecialidadPK(PersonaNaturalEspecialidadPK personaNaturalEspecialidadPK) {
        this.personaNaturalEspecialidadPK = personaNaturalEspecialidadPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaNaturalEspecialidadPK != null ? personaNaturalEspecialidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalEspecialidad)) {
            return false;
        }
        PersonaNaturalEspecialidad other = (PersonaNaturalEspecialidad) object;
        if ((this.personaNaturalEspecialidadPK == null && other.personaNaturalEspecialidadPK != null) || (this.personaNaturalEspecialidadPK != null && !this.personaNaturalEspecialidadPK.equals(other.personaNaturalEspecialidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalEspecialidad[ personaNaturalEspecialidadPK=" + personaNaturalEspecialidadPK + " ]";
    }
    
}
