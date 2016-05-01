/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author Julio
 */

@Entity
@Table(name = "cita")
@JsonIgnoreProperties(ignoreUnknown = true)
@NamedQueries({
    @NamedQuery(name = "Cita.findFechaInicioFechaFin", query = "SELECT c FROM Cita c where (c.agenda.medicoEps.persona.idPersona = :idMedico) AND (c.horaInicio >= :horaInicio  AND c.horaFin <= :horaFin)"),
    //@NamedQuery(name = "Cita.findIdPaciente", query = "SELECT c FROM Cita c WHERE c.pacienteEps.persona.idPersona = :idPaciente and c.pacienteEps.fechaFin=null and c.estadoCita<>'CANCELADA' ORDER BY c.horaFin DESC"),
    @NamedQuery(name = "Cita.findIdPaciente", query = "SELECT c FROM Cita c WHERE c.pacienteEps.persona.idPersona = :idPaciente and c.pacienteEps.fechaFin=null and c.estadoCita<>'CANCELADE1' ORDER BY c.horaFin DESC"),
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c"),
    @NamedQuery(name = "Cita.findById", query = "SELECT c FROM Cita c WHERE c.idCita = :idCita"),
    @NamedQuery(name = "Cita.findByValor", query = "SELECT c FROM Cita c WHERE c.valor = :valor"),
    @NamedQuery(name = "Cita.findByEstadoPacienteAtendido", query = "SELECT c FROM Cita c WHERE c.estadoPacienteAtendido = :estadoPacienteAtendido"),
    @NamedQuery(name = "Cita.findByFechaPaciente", query = "SELECT c FROM Cita c WHERE c.horaInicio = :fechaPaciente"),
    @NamedQuery(name = "Cita.findByHoraInicio", query = "SELECT c FROM Cita c WHERE c.horaInicio = :horaInicio"),
    @NamedQuery(name = "Cita.findByIdAgenda", query = "SELECT c FROM Cita c WHERE c.agenda.idAgenda = :idAgenda")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cita")
    private Long idCita;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @Column(name = "estado_paciente_atendido")
    private boolean estadoPacienteAtendido;
    
    @Basic(optional = false)
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    
    @Basic(optional = false)
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "estado_cita")
    private String estadoCita;  
    
    

    
    @JoinColumn(name = "id_paciente_eps", referencedColumnName = "id_persona_eps")
//    @ManyToOne(optional = false)
    @ManyToOne()
    private PersonaEps pacienteEps;

    @JoinColumn(name = "id_agenda", referencedColumnName = "id_agenda")
    @ManyToOne(optional = false)
    private Agenda agenda;

    @Version
    @Column(name = "VERSION")
    private Long version;

    public Cita() {
    }

    public Cita(Long id) {
        this.idCita = id;
    }

    public Cita(Long id, double valor, boolean estadoPacienteAtendido, Date fechaPaciente, Agenda agenda, String estadoCita) {
        this.idCita = id;
        this.valor = valor;
        this.estadoPacienteAtendido = estadoPacienteAtendido;
        this.horaFin = fechaPaciente;
        this.agenda = agenda;
       this.estadoCita = estadoCita;
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
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
        return horaFin;
    }

    public void setFechaPaciente(Date fechaPaciente) {
        this.horaFin = fechaPaciente;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public PersonaEps getPacienteEps() {
        return pacienteEps;
    }

    public void setPacienteEps(PersonaEps pacienteEps) {
        this.pacienteEps = pacienteEps;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCita != null ? idCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idCita fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.idCita == null && other.idCita != null) || (this.idCita != null && !this.idCita.equals(other.idCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Cita[ id=" + idCita + " ]";
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public boolean isEstadoPacienteAtendido() {
        return estadoPacienteAtendido;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    

}
