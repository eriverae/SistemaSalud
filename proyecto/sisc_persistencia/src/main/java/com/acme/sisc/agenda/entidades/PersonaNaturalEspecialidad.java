/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jamer
 */
@Entity
@Table(name = "persona_natural_especialidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaNaturalEspecialidad.findAllMedicosEspecialidad", query = "SELECT p FROM PersonaNaturalEspecialidad p WHERE p.especialidad.idEspecialidad = :idEspecialidad"),
    @NamedQuery(name = "PersonaNaturalEspecialidad.findAll", query = "SELECT p FROM PersonaNaturalEspecialidad p"),
    @NamedQuery(name = "PersonaNaturalEspecialidad.findByIdMedico", query = "SELECT p FROM PersonaNaturalEspecialidad p WHERE p.medico.idPersona = :idMedico")})

public class PersonaNaturalEspecialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona_especialidad")
    private Long idPersonaEspecialidad;

    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad")
    @ManyToOne
    private Especialidad especialidad;

    @JoinColumn(name = "id_medico", referencedColumnName = "id_persona")
    @ManyToOne
    private PersonaNatural medico;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonaEspecialidad != null ? idPersonaEspecialidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalEspecialidad)) {
            return false;
        }
        PersonaNaturalEspecialidad other = (PersonaNaturalEspecialidad) object;
        if ((this.idPersonaEspecialidad == null && other.idPersonaEspecialidad != null) || (this.idPersonaEspecialidad != null && !this.idPersonaEspecialidad.equals(other.idPersonaEspecialidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalEspecialidad[ personaNaturalOperacionPK=" + idPersonaEspecialidad + " ]";
    }

    public Long getIdPersonaEspecialidad() {
        return idPersonaEspecialidad;
    }

    public void setIdPersonaEspecialidad(Long idPersonaEspecialidad) {
        this.idPersonaEspecialidad = idPersonaEspecialidad;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public PersonaNatural getMedico() {
        return medico;
    }

    public void setMedico(PersonaNatural medico) {
        this.medico = medico;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
