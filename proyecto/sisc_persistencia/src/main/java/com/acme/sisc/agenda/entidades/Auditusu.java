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
@Table(name = "auditusu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auditusu.findAll", query = "SELECT a FROM Auditusu a"),
    @NamedQuery(name = "Auditusu.findByAudiAudi", query = "SELECT a FROM Auditusu a WHERE a.audiAudi = :audiAudi"),
    @NamedQuery(name = "Auditusu.findByAudiFech", query = "SELECT a FROM Auditusu a WHERE a.audiFech = :audiFech"),
    @NamedQuery(name = "Auditusu.findByAudiUsua", query = "SELECT a FROM Auditusu a WHERE a.audiUsua = :audiUsua"),
    @NamedQuery(name = "Auditusu.findByAudiObser", query = "SELECT a FROM Auditusu a WHERE a.audiObser = :audiObser"),
    @NamedQuery(name = "Auditusu.findByAudiDrip", query = "SELECT a FROM Auditusu a WHERE a.audiDrip = :audiDrip"),
    @NamedQuery(name = "Auditusu.findByAudiHostn", query = "SELECT a FROM Auditusu a WHERE a.audiHostn = :audiHostn")})
public class Auditusu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "audi_audi")
    private Long audiAudi;
    @Column(name = "audi_fech")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audiFech;
    @Column(name = "audi_usua")
    private BigInteger audiUsua;
    @Column(name = "audi_obser")
    private String audiObser;
    @Column(name = "audi_drip")
    private String audiDrip;
    @Column(name = "audi_hostn")
    private String audiHostn;

    public Auditusu() {
    }

    public Auditusu(Long audiAudi) {
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
        if (!(object instanceof Auditusu)) {
            return false;
        }
        Auditusu other = (Auditusu) object;
        if ((this.audiAudi == null && other.audiAudi != null) || (this.audiAudi != null && !this.audiAudi.equals(other.audiAudi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Auditusu[ audiAudi=" + audiAudi + " ]";
    }
    
}
