/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "auditusu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditoriaUsuario.findAll", query = "SELECT a FROM AuditoriaUsuario a"),
    @NamedQuery(name = "AuditoriaUsuario.findByAudiAudi", query = "SELECT a FROM AuditoriaUsuario a WHERE a.audiAudi = :audiAudi")})
public class AuditoriaUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "audi_audi")
    private Long audiAudi;
    @Column(name = "audi_fech")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audiFech;
    @Column(name = "audi_usua")
    private Long audiUsua;
    @Column(name = "audi_obser")
    private String audiObser;
    @Column(name = "audi_drip")
    private String audiDrip;
    @Column(name = "audi_hostn")
    private String audiHostn;
    
    @JoinColumn(name = "usua_usua", referencedColumnName = "usua_usua")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Version
    @Column(name = "VERSION")
    private Long version;

    public AuditoriaUsuario() {
    }

    public AuditoriaUsuario(Long audiAudi) {
        this.audiAudi = audiAudi;
    }

    public Long getAudiAudi() {
        return audiAudi;
    }

    public void setAudiAudi(Long audiAudi) {
        this.audiAudi = audiAudi;
    }

    public Date getAudiFech() {
        return audiFech;
    }

    public void setAudiFech(Date audiFech) {
        this.audiFech = audiFech;
    }

    public Long getAudiUsua() {
        return audiUsua;
    }

    public void setAudiUsua(Long audiUsua) {
        this.audiUsua = audiUsua;
    }

    public String getAudiObser() {
        return audiObser;
    }

    public void setAudiObser(String audiObser) {
        this.audiObser = audiObser;
    }

    public String getAudiDrip() {
        return audiDrip;
    }

    public void setAudiDrip(String audiDrip) {
        this.audiDrip = audiDrip;
    }

    public String getAudiHostn() {
        return audiHostn;
    }

    public void setAudiHostn(String audiHostn) {
        this.audiHostn = audiHostn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (audiAudi != null ? audiAudi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditoriaUsuario)) {
            return false;
        }
        AuditoriaUsuario other = (AuditoriaUsuario) object;
        if ((this.audiAudi == null && other.audiAudi != null) || (this.audiAudi != null && !this.audiAudi.equals(other.audiAudi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Auditusu[ audiAudi=" + audiAudi + " ]";
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
}
