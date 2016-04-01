/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "persona_eps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaEps.findAll", query = "SELECT p FROM PersonaEps p"),
    @NamedQuery(name = "PersonaEps.findByIdPersonaEps", query = "SELECT p FROM PersonaEps p WHERE p.idPersonaEps = :idPersonaEps"),
    @NamedQuery(name = "PersonaEps.findByIdEps", query = "SELECT p FROM PersonaEps p WHERE p.idEps = :idEps"),
    @NamedQuery(name = "PersonaEps.findByFechaInicio", query = "SELECT p FROM PersonaEps p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "PersonaEps.findByFechaFin", query = "SELECT p FROM PersonaEps p WHERE p.fechaFin = :fechaFin")})
public class PersonaEps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_persona_eps")
    private Long idPersonaEps;
    @Column(name = "id_eps")
    private Long idEps;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "id_persona_natural", referencedColumnName = "id_persona_natural")
    @ManyToOne
    private PersonaNatural idPersonaNatural;
    @JoinColumn(name = "id_persona_natural", referencedColumnName = "id_persona_juridica")
    @ManyToOne
    private PersonaJuridica idPersonaNatural1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersonaEps")
    private List<Cita> citaList;

    public PersonaEps() {
    }

    public PersonaEps(Long idPersonaEps) {
        this.idPersonaEps = idPersonaEps;
    }

    public Long getIdPersonaEps() {
        return idPersonaEps;
    }

    public void setIdPersonaEps(Long idPersonaEps) {
        this.idPersonaEps = idPersonaEps;
    }

    public Long getIdEps() {
        return idEps;
    }

    public void setIdEps(Long idEps) {
        this.idEps = idEps;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public PersonaNatural getIdPersonaNatural() {
        return idPersonaNatural;
    }

    public void setIdPersonaNatural(PersonaNatural idPersonaNatural) {
        this.idPersonaNatural = idPersonaNatural;
    }

    public PersonaJuridica getIdPersonaNatural1() {
        return idPersonaNatural1;
    }

    public void setIdPersonaNatural1(PersonaJuridica idPersonaNatural1) {
        this.idPersonaNatural1 = idPersonaNatural1;
    }

    @XmlTransient
    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonaEps != null ? idPersonaEps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaEps)) {
            return false;
        }
        PersonaEps other = (PersonaEps) object;
        if ((this.idPersonaEps == null && other.idPersonaEps != null) || (this.idPersonaEps != null && !this.idPersonaEps.equals(other.idPersonaEps))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaEps[ idPersonaEps=" + idPersonaEps + " ]";
    }
    
}
