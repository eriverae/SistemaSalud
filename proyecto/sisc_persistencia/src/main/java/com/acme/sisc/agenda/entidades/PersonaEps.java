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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
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
    @NamedQuery(name = "PersonaEps.findByFechaInicio", query = "SELECT p FROM PersonaEps p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "PersonaEps.findByFechaFin", query = "SELECT p FROM PersonaEps p WHERE p.fechaFin = :fechaFin")})
public class PersonaEps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona_eps")
    private Long idPersonaEps;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private PersonaNatural persona;// Este atributo representa un Medico o un Paciente, segun el rol en PersonaNatural

    @JoinColumn(name = "id_eps", referencedColumnName = "id_persona")
    @ManyToOne
    private PersonaJuridica eps;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacienteEps", fetch = FetchType.LAZY)
    private List<Cita> listaCitasPaciente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicoEps", fetch = FetchType.LAZY)
    private List<Agenda> listaAgendasMedico;

    @Version
    @Column(name = "VERSION")
    private Long version;

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

    public PersonaNatural getPersona() {
        return persona;
    }

    public void setPersona(PersonaNatural persona) {
        this.persona = persona;
    }

    public PersonaJuridica getEps() {
        return eps;
    }

    public void setEps(PersonaJuridica eps) {
        this.eps = eps;
    }

    @XmlTransient
    public List<Cita> getListaCitasPaciente() {
        return listaCitasPaciente;
    }

    public void setListaCitasPaciente(List<Cita> listaCitasPaciente) {
        this.listaCitasPaciente = listaCitasPaciente;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Agenda> getListaAgendasMedico() {
        return listaAgendasMedico;
    }

    public void setListaAgendasMedico(List<Agenda> listaAgendasMedico) {
        this.listaAgendasMedico = listaAgendasMedico;
    }

}
