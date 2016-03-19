/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisc.acme.seguridad.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Erika
 */
@Entity
@Table(name = "log_notifica", catalog = "sisc_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"lgno_lgno"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogNotificacion.findAll", query = "SELECT l FROM LogNotificacion l"),
    @NamedQuery(name = "LogNotificacion.findByLgnoLgno", query = "SELECT l FROM LogNotificacion l WHERE l.lgnoLgno = :lgnoLgno"),
    @NamedQuery(name = "LogNotificacion.findByLgnoSiste", query = "SELECT l FROM LogNotificacion l WHERE l.lgnoSiste = :lgnoSiste"),
    @NamedQuery(name = "LogNotificacion.findByLgnoDest", query = "SELECT l FROM LogNotificacion l WHERE l.lgnoDest = :lgnoDest"),
    @NamedQuery(name = "LogNotificacion.findByLgnoAsunto", query = "SELECT l FROM LogNotificacion l WHERE l.lgnoAsunto = :lgnoAsunto"),
    @NamedQuery(name = "LogNotificacion.findByLgnoCuerpo", query = "SELECT l FROM LogNotificacion l WHERE l.lgnoCuerpo = :lgnoCuerpo"),
    @NamedQuery(name = "LogNotificacion.findByLgnoPers", query = "SELECT l FROM LogNotificacion l WHERE l.lgnoPers = :lgnoPers")})
public class LogNotificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "lgno_lgno", nullable = false)
    private Long lgnoLgno;
    @Size(max = 100)
    @Column(name = "lgno_siste", length = 100)
    private String lgnoSiste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lgno_dest", nullable = false, length = 100)
    private String lgnoDest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lgno_asunto", nullable = false, length = 255)
    private String lgnoAsunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lgno_cuerpo", nullable = false, length = 255)
    private String lgnoCuerpo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lgno_pers", nullable = false)
    private long lgnoPers;

    public LogNotificacion() {
    }

    public LogNotificacion(Long lgnoLgno) {
        this.lgnoLgno = lgnoLgno;
    }

    public LogNotificacion(Long lgnoLgno, String lgnoDest, String lgnoAsunto, String lgnoCuerpo, long lgnoPers) {
        this.lgnoLgno = lgnoLgno;
        this.lgnoDest = lgnoDest;
        this.lgnoAsunto = lgnoAsunto;
        this.lgnoCuerpo = lgnoCuerpo;
        this.lgnoPers = lgnoPers;
    }

    public Long getLgnoLgno() {
        return lgnoLgno;
    }

    public void setLgnoLgno(Long lgnoLgno) {
        this.lgnoLgno = lgnoLgno;
    }

    public String getLgnoSiste() {
        return lgnoSiste;
    }

    public void setLgnoSiste(String lgnoSiste) {
        this.lgnoSiste = lgnoSiste;
    }

    public String getLgnoDest() {
        return lgnoDest;
    }

    public void setLgnoDest(String lgnoDest) {
        this.lgnoDest = lgnoDest;
    }

    public String getLgnoAsunto() {
        return lgnoAsunto;
    }

    public void setLgnoAsunto(String lgnoAsunto) {
        this.lgnoAsunto = lgnoAsunto;
    }

    public String getLgnoCuerpo() {
        return lgnoCuerpo;
    }

    public void setLgnoCuerpo(String lgnoCuerpo) {
        this.lgnoCuerpo = lgnoCuerpo;
    }

    public long getLgnoPers() {
        return lgnoPers;
    }

    public void setLgnoPers(long lgnoPers) {
        this.lgnoPers = lgnoPers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgnoLgno != null ? lgnoLgno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogNotificacion)) {
            return false;
        }
        LogNotificacion other = (LogNotificacion) object;
        if ((this.lgnoLgno == null && other.lgnoLgno != null) || (this.lgnoLgno != null && !this.lgnoLgno.equals(other.lgnoLgno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisc.acme.seguridad.entity.LogNotificacion[ lgnoLgno=" + lgnoLgno + " ]";
    }
    
}
