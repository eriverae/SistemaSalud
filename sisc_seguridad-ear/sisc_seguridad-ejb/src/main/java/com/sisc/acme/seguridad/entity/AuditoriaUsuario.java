/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisc.acme.seguridad.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Erika
 */
@Entity
@Table(name = "auditusu", catalog = "sisc_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"audi_audi"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditoriaUsuario.findAll", query = "SELECT a FROM AuditoriaUsuario a"),
    @NamedQuery(name = "AuditoriaUsuario.findByAudiAudi", query = "SELECT a FROM AuditoriaUsuario a WHERE a.audiAudi = :audiAudi"),
    @NamedQuery(name = "AuditoriaUsuario.findByAudiFech", query = "SELECT a FROM AuditoriaUsuario a WHERE a.audiFech = :audiFech"),
    @NamedQuery(name = "AuditoriaUsuario.findByAudiUsua", query = "SELECT a FROM AuditoriaUsuario a WHERE a.audiUsua = :audiUsua"),
    @NamedQuery(name = "AuditoriaUsuario.findByAudiObser", query = "SELECT a FROM AuditoriaUsuario a WHERE a.audiObser = :audiObser"),
    @NamedQuery(name = "AuditoriaUsuario.findByAudiDrip", query = "SELECT a FROM AuditoriaUsuario a WHERE a.audiDrip = :audiDrip"),
    @NamedQuery(name = "AuditoriaUsuario.findByAudiHostn", query = "SELECT a FROM AuditoriaUsuario a WHERE a.audiHostn = :audiHostn")})
public class AuditoriaUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "audi_audi", nullable = false)
    private Long audiAudi;
    @Column(name = "audi_fech")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audiFech;
    @Column(name = "audi_usua")
    private BigInteger audiUsua;
    @Size(max = 500)
    @Column(name = "audi_obser", length = 500)
    private String audiObser;
    @Size(max = 100)
    @Column(name = "audi_drip", length = 100)
    private String audiDrip;
    @Size(max = 100)
    @Column(name = "audi_hostn", length = 100)
    private String audiHostn;

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

    public BigInteger getAudiUsua() {
        return audiUsua;
    }

    public void setAudiUsua(BigInteger audiUsua) {
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
        return "com.sisc.acme.seguridad.entity.AuditoriaUsuario[ audiAudi=" + audiAudi + " ]";
    }
    
}
