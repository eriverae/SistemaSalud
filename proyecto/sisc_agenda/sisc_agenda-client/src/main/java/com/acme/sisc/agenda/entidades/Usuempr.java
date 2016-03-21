/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "usuempr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuempr.findAll", query = "SELECT u FROM Usuempr u"),
    @NamedQuery(name = "Usuempr.findByUsemUsem", query = "SELECT u FROM Usuempr u WHERE u.usuemprPK.usemUsem = :usemUsem"),
    @NamedQuery(name = "Usuempr.findByUsemPrju", query = "SELECT u FROM Usuempr u WHERE u.usuemprPK.usemPrju = :usemPrju")})
public class Usuempr implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuemprPK usuemprPK;

    public Usuempr() {
    }

    public Usuempr(UsuemprPK usuemprPK) {
        this.usuemprPK = usuemprPK;
    }

    public Usuempr(long usemUsem, long usemPrju) {
        this.usuemprPK = new UsuemprPK(usemUsem, usemPrju);
    }

    public UsuemprPK getUsuemprPK() {
        return usuemprPK;
    }

    public void setUsuemprPK(UsuemprPK usuemprPK) {
        this.usuemprPK = usuemprPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuemprPK != null ? usuemprPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuempr)) {
            return false;
        }
        Usuempr other = (Usuempr) object;
        if ((this.usuemprPK == null && other.usuemprPK != null) || (this.usuemprPK != null && !this.usuemprPK.equals(other.usuemprPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Usuempr[ usuemprPK=" + usuemprPK + " ]";
    }
    
}
