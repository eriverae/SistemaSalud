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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "log_notifica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogNotifica.findAll", query = "SELECT l FROM LogNotifica l"),
    @NamedQuery(name = "LogNotifica.findByLgnoLgno", query = "SELECT l FROM LogNotifica l WHERE l.lgnoLgno = :lgnoLgno"),
    @NamedQuery(name = "LogNotifica.findByLgnoSiste", query = "SELECT l FROM LogNotifica l WHERE l.lgnoSiste = :lgnoSiste"),
    @NamedQuery(name = "LogNotifica.findByLgnoDest", query = "SELECT l FROM LogNotifica l WHERE l.lgnoDest = :lgnoDest"),
    @NamedQuery(name = "LogNotifica.findByLgnoAsunto", query = "SELECT l FROM LogNotifica l WHERE l.lgnoAsunto = :lgnoAsunto"),
    @NamedQuery(name = "LogNotifica.findByLgnoCuerpo", query = "SELECT l FROM LogNotifica l WHERE l.lgnoCuerpo = :lgnoCuerpo"),
    @NamedQuery(name = "LogNotifica.findByLgnoPers", query = "SELECT l FROM LogNotifica l WHERE l.lgnoPers = :lgnoPers")})
public class LogNotifica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lgno_lgno")
    private Long lgnoLgno;
    @Column(name = "lgno_siste")
    private String lgnoSiste;
    @Basic(optional = false)
    @Column(name = "lgno_dest")
    private String lgnoDest;
    @Basic(optional = false)
    @Column(name = "lgno_asunto")
    private String lgnoAsunto;
    @Basic(optional = false)
    @Column(name = "lgno_cuerpo")
    private String lgnoCuerpo;
    @Basic(optional = false)
    @Column(name = "lgno_pers")
    private long lgnoPers;

    public LogNotifica() {
    }

    public LogNotifica(Long lgnoLgno) {
        this.lgnoLgno = lgnoLgno;
    }

    public LogNotifica(Long lgnoLgno, String lgnoDest, String lgnoAsunto, String lgnoCuerpo, long lgnoPers) {
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
        if (!(object instanceof LogNotifica)) {
            return false;
        }
        LogNotifica other = (LogNotifica) object;
        if ((this.lgnoLgno == null && other.lgnoLgno != null) || (this.lgnoLgno != null && !this.lgnoLgno.equals(other.lgnoLgno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.LogNotifica[ lgnoLgno=" + lgnoLgno + " ]";
    }
    
}
