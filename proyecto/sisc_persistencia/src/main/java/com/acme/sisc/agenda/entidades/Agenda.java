/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "agenda")
@JsonIgnoreProperties(ignoreUnknown = true)
@NamedQueries({
    @NamedQuery(name = "Agenda.findFechas", query = "SELECT a FROM Agenda a  WHERE a.medicoEps.persona.idPersona = :idMedico AND (a.horaBloqueinicio >= :horaInicio  AND a.horaBloqueFin <= :horaFin) "),
    @NamedQuery(name = "Agenda.findAll", query = "SELECT a FROM Agenda a"),
    @NamedQuery(name = "Agenda.findByIdMedico", query = "SELECT a FROM Agenda a WHERE a.medicoEps.persona.idPersona = :idMedico")})
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agenda")
    private Long idAgenda;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ciudad")
    private String ciudad;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "localidad")
    private String localidad;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "direccion")
    private String direccion;

    @Column(name = "numero_consultorio")
    private Integer numeroConsultorio;

    @Column(name = "hora_bloque_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaBloqueinicio;

    @Column(name = "hora_bloque_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaBloqueFin;

    @Size(max = 50)
    @Column(name = "estado_diponible")
    private String estadoDiponible;

    @JoinColumn(name = "id_medico_eps", referencedColumnName = "id_persona_eps")
    @ManyToOne(optional = false)
    private PersonaEps medicoEps;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda", fetch = FetchType.LAZY)
    private List<Cita> citasAgenda;
    
    @Basic(optional = false)
    @Column(name = "tiempo_minutos_cita")
    private int tiempoMinutosXCita;

    public int getTiempoMinutosXCita() {
        return tiempoMinutosXCita;
    }

    public void setTiempoMinutosXCita(int tiempoMinutosXCita) {
        this.tiempoMinutosXCita = tiempoMinutosXCita;
    }
    
    @Version
    @Column(name = "VERSION")
    private Long version;

    public Agenda() {
    }

    public Agenda(Long id) {
        this.idAgenda = id;
    }

    public Agenda(Long id, String ciudad, String localidad, String direccion, Date fechaBloque, PersonaEps medicoEps) {
        this.idAgenda = id;
        this.ciudad = ciudad;
        this.localidad = localidad;
        this.direccion = direccion;
        this.medicoEps = medicoEps;

    }

    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getNumeroConsultorio() {
        return numeroConsultorio;
    }

    public void setNumeroConsultorio(Integer numeroConsultorio) {
        this.numeroConsultorio = numeroConsultorio;
    }

    public Date getHoraBloqueinicio() {
        return horaBloqueinicio;
    }

    public void setHoraBloqueinicio(Date horaBloqueinicio) {
        this.horaBloqueinicio = horaBloqueinicio;
    }

    public Date getHoraBloqueFin() {
        return horaBloqueFin;
    }

    public void setHoraBloqueFin(Date horaBloqueFin) {
        this.horaBloqueFin = horaBloqueFin;
    }

    public String getEstadoDiponible() {
        return estadoDiponible;
    }

    public void setEstadoDiponible(String estadoDiponible) {
        this.estadoDiponible = estadoDiponible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgenda != null ? idAgenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idAgenda fields are not set
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.idAgenda == null && other.idAgenda != null) || (this.idAgenda != null && !this.idAgenda.equals(other.idAgenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Agenda[ id=" + idAgenda + " ]";
    }

    public PersonaEps getMedicoEps() {
        return medicoEps;
    }

    public void setMedicoEps(PersonaEps medicoEps) {
        this.medicoEps = medicoEps;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    @JsonIgnore
    public List<Cita> getCitasAgenda() {
        return citasAgenda;
    }

    public void setCitasAgenda(List<Cita> citasAgenda) {
        this.citasAgenda = citasAgenda;
    }

}
