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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "incapacidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incapacidad.findAll", query = "SELECT i FROM Incapacidad i"),
    @NamedQuery(name = "Incapacidad.findByIdincapacidad", query = "SELECT i FROM Incapacidad i WHERE i.idincapacidad = :idincapacidad"),
    @NamedQuery(name = "Incapacidad.findByIdpersona", query = "SELECT i FROM Incapacidad i WHERE i.idpersona = :idpersona"),
    @NamedQuery(name = "Incapacidad.findByPeriodo", query = "SELECT i FROM Incapacidad i WHERE i.periodo = :periodo"),
    @NamedQuery(name = "Incapacidad.findByMotivo", query = "SELECT i FROM Incapacidad i WHERE i.motivo = :motivo")})
public class Incapacidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idincapacidad")
    private Long idincapacidad;
    @Basic(optional = false)
    @Column(name = "idpersona")
    private long idpersona;
    @Basic(optional = false)
    @Column(name = "periodo")
    private String periodo;
    @Basic(optional = false)
    @Column(name = "motivo")
    private String motivo;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Persona idPersona;

    public Incapacidad() {
    }

    public Incapacidad(Long idincapacidad) {
        this.idincapacidad = idincapacidad;
    }

    public Incapacidad(Long idincapacidad, long idpersona, String periodo, String motivo) {
        this.idincapacidad = idincapacidad;
        this.idpersona = idpersona;
        this.periodo = periodo;
        this.motivo = motivo;
    }

    public Long getIdincapacidad() {
        return idincapacidad;
    }

    public void setIdincapacidad(Long idincapacidad) {
        this.idincapacidad = idincapacidad;
    }

    public long getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(long idpersona) {
        this.idpersona = idpersona;
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

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idincapacidad != null ? idincapacidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incapacidad)) {
            return false;
        }
        Incapacidad other = (Incapacidad) object;
        if ((this.idincapacidad == null && other.idincapacidad != null) || (this.idincapacidad != null && !this.idincapacidad.equals(other.idincapacidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Incapacidad[ idincapacidad=" + idincapacidad + " ]";
    }
    
}
