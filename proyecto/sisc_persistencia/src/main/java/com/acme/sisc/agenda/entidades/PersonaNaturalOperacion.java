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
@Table(name = "persona_natural_operacion")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "PersonaNaturalOperacion.findAll", query = "SELECT p FROM PersonaNaturalOperacion p"),
  @NamedQuery(name = "PersonaNaturalOperacion.findByIdPaciente", query = "SELECT p FROM PersonaNaturalOperacion p WHERE p.paciente.idPersona = :idPaciente")})

public class PersonaNaturalOperacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona_operacion")
    private Long idPersonaOperacion;

    @JoinColumn(name = "id_operacion", referencedColumnName = "id_operacion")
    @ManyToOne
    private Operacion operacion;

    @JoinColumn(name = "id_paciente", referencedColumnName = "id_persona")
    @ManyToOne
    private PersonaNatural paciente;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonaOperacion != null ? idPersonaOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalOperacion)) {
            return false;
        }
        PersonaNaturalOperacion other = (PersonaNaturalOperacion) object;
        if ((this.idPersonaOperacion == null && other.idPersonaOperacion != null) || (this.idPersonaOperacion != null && !this.idPersonaOperacion.equals(other.idPersonaOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalOperacion[ personaNaturalOperacionPK=" + idPersonaOperacion + " ]";
    }

    public Long getIdPersonaOperacion() {
        return idPersonaOperacion;
    }

    public void setIdPersonaOperacion(Long idPersonaOperacion) {
        this.idPersonaOperacion = idPersonaOperacion;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public PersonaNatural getPaciente() {
        return paciente;
    }

    public void setPaciente(PersonaNatural paciente) {
        this.paciente = paciente;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
