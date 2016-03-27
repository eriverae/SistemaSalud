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
@Table(name = "agenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agenda.findAll", query = "SELECT a FROM Agenda a"),
    @NamedQuery(name = "Agenda.findById", query = "SELECT a FROM Agenda a WHERE a.id = :id"),
    @NamedQuery(name = "Agenda.findByCiudad", query = "SELECT a FROM Agenda a WHERE a.ciudad = :ciudad"),
    @NamedQuery(name = "Agenda.findByLocalidad", query = "SELECT a FROM Agenda a WHERE a.localidad = :localidad"),
    @NamedQuery(name = "Agenda.findByDireccion", query = "SELECT a FROM Agenda a WHERE a.direccion = :direccion"),
    @NamedQuery(name = "Agenda.findByNumeroConsultorio", query = "SELECT a FROM Agenda a WHERE a.numeroConsultorio = :numeroConsultorio"),
    @NamedQuery(name = "Agenda.findByFechaBloque", query = "SELECT a FROM Agenda a WHERE a.fechaBloque = :fechaBloque"),
    @NamedQuery(name = "Agenda.findByHoraBloqueinicio", query = "SELECT a FROM Agenda a WHERE a.horaBloqueinicio = :horaBloqueinicio"),
    @NamedQuery(name = "Agenda.findByHoraBloqueFin", query = "SELECT a FROM Agenda a WHERE a.horaBloqueFin = :horaBloqueFin"),
    @NamedQuery(name = "Agenda.findByEstadoDiponible", query = "SELECT a FROM Agenda a WHERE a.estadoDiponible = :estadoDiponible"),
    @NamedQuery(name = "Agenda.findByIdPersona", query = "SELECT a FROM Agenda a WHERE a.idPersona = :idPersona")})
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "localidad")
    private String localidad;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "numero_consultorio")
    private Integer numeroConsultorio;
    @Basic(optional = false)
    @Column(name = "fecha_bloque")
    @Temporal(TemporalType.DATE)
    private Date fechaBloque;
    @Column(name = "hora_bloqueinicio")
    @Temporal(TemporalType.TIME)
    private Date horaBloqueinicio;
    @Column(name = "hora_bloque_fin")
    @Temporal(TemporalType.TIME)
    private Date horaBloqueFin;
    @Column(name = "estado_diponible")
    private String estadoDiponible;
    @Basic(optional = false)
    @Column(name = "id_persona")
    private long idPersona;

    public Agenda() {
    }

    public Agenda(Long id) {
        this.id = id;
    }

    public Agenda(Long id, String ciudad, String localidad, String direccion, Date fechaBloque, long idPersona) {
        this.id = id;
        this.ciudad = ciudad;
        this.localidad = localidad;
        this.direccion = direccion;
        this.fechaBloque = fechaBloque;
        this.idPersona = idPersona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getFechaBloque() {
        return fechaBloque;
    }

    public void setFechaBloque(Date fechaBloque) {
        this.fechaBloque = fechaBloque;
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

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
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
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Agenda[ id=" + id + " ]";
    }
    
}
