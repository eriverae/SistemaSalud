/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "cita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c"),
    @NamedQuery(name = "Cita.findById", query = "SELECT c FROM Cita c WHERE c.id = :id"),
    @NamedQuery(name = "Cita.findByValor", query = "SELECT c FROM Cita c WHERE c.valor = :valor"),
    @NamedQuery(name = "Cita.findByEstadoPacienteAtendido", query = "SELECT c FROM Cita c WHERE c.estadoPacienteAtendido = :estadoPacienteAtendido"),
    @NamedQuery(name = "Cita.findByFechaPaciente", query = "SELECT c FROM Cita c WHERE c.fechaPaciente = :fechaPaciente"),
    @NamedQuery(name = "Cita.findByHoraInicio", query = "SELECT c FROM Cita c WHERE c.horaInicio = :horaInicio"),
    @NamedQuery(name = "Cita.findByIdAgenda", query = "SELECT c FROM Cita c WHERE c.idAgenda = :idAgenda")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @Column(name = "estado_paciente_atendido")
    private boolean estadoPacienteAtendido;
    @Basic(optional = false)
    @Column(name = "fecha_paciente")
    @Temporal(TemporalType.DATE)
    private Date fechaPaciente;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @Column(name = "id_agenda")
    private long idAgenda;
    
    @JoinColumn(name = "id_persona_natural", referencedColumnName = "id_persona_natural")
    @ManyToOne(optional = false)
    private PersonaNatural idPersonaNatural;
    
    @JoinColumn(name = "id_persona_eps", referencedColumnName = "id_persona_eps")
    @ManyToOne(optional = false)
    private PersonaEps idPersonaEps;

    public Cita() {
    }

    public Cita(Long id) {
        this.id = id;
    }

    public Cita(Long id, double valor, boolean estadoPacienteAtendido, Date fechaPaciente, long idAgenda) {
        this.id = id;
        this.valor = valor;
        this.estadoPacienteAtendido = estadoPacienteAtendido;
        this.fechaPaciente = fechaPaciente;
        this.idAgenda = idAgenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getEstadoPacienteAtendido() {
        return estadoPacienteAtendido;
    }

    public void setEstadoPacienteAtendido(boolean estadoPacienteAtendido) {
        this.estadoPacienteAtendido = estadoPacienteAtendido;
    }

    public Date getFechaPaciente() {
        return fechaPaciente;
    }

    public void setFechaPaciente(Date fechaPaciente) {
        this.fechaPaciente = fechaPaciente;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public PersonaNatural getIdPersonaNatural() {
        return idPersonaNatural;
    }

    public void setIdPersonaNatural(PersonaNatural idPersonaNatural) {
        this.idPersonaNatural = idPersonaNatural;
    }

    public PersonaEps getIdPersonaEps() {
        return idPersonaEps;
    }

    public void setIdPersonaEps(PersonaEps idPersonaEps) {
        this.idPersonaEps = idPersonaEps;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Cita[ id=" + id + " ]";
    }
    
}
