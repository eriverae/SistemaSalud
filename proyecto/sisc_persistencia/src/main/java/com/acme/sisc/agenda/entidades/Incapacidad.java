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
import javax.persistence.FetchType;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GABRIEL
 */
@Entity
@Table(name = "incapacidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incapacidad.findAll", query = "SELECT i FROM Incapacidad i"),
    @NamedQuery(name = "Incapacidad.findByIdincapacidad", query = "SELECT i FROM Incapacidad i WHERE i.idIncapacidad = :idIncapacidad"),
    @NamedQuery(name = "Incapacidad.findByPeriodo", query = "SELECT i FROM Incapacidad i WHERE i.periodo = :periodo"),
    @NamedQuery(name = "Incapacidad.findByMotivo", query = "SELECT i FROM Incapacidad i WHERE i.motivo = :motivo"),
    @NamedQuery(name = "Incapacidad.findByFechaGeneracion", query = "SELECT i FROM Incapacidad i WHERE i.fechaGeneracion = :fechaGeneracion")})
public class Incapacidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_incapacidad")
    private Long idIncapacidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "periodo")
    private String periodo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "motivo")
    private String motivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_generacion")
    @Temporal(TemporalType.DATE)
    private Date fechaGeneracion;
    @JoinColumn(name = "id_cita", referencedColumnName = "id_cita")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cita cita;
    
    @Version
    @Column(name = "VERSION")
    private Long version;

    public Incapacidad() {
    }

    public Incapacidad(Long idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }

    public Incapacidad(Long idIncapacidad, String periodo, String motivo, Date fechaGeneracion) {
        this.idIncapacidad = idIncapacidad;
        this.periodo = periodo;
        this.motivo = motivo;
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIncapacidad != null ? idIncapacidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incapacidad)) {
            return false;
        }
        Incapacidad other = (Incapacidad) object;
        if ((this.idIncapacidad == null && other.idIncapacidad != null) || (this.idIncapacidad != null && !this.idIncapacidad.equals(other.idIncapacidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Incapacidad[ idIncapacidad=" + idIncapacidad + " ]";
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getIdIncapacidad() {
        return idIncapacidad;
    }

    public void setIdIncapacidad(Long idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }
    
}
