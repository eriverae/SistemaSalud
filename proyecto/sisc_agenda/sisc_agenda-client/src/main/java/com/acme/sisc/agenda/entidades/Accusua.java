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
@Table(name = "accusua")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accusua.findAll", query = "SELECT a FROM Accusua a"),
    @NamedQuery(name = "Accusua.findByAcusAcce", query = "SELECT a FROM Accusua a WHERE a.accusuaPK.acusAcce = :acusAcce"),
    @NamedQuery(name = "Accusua.findByAcusUsua", query = "SELECT a FROM Accusua a WHERE a.accusuaPK.acusUsua = :acusUsua")})
public class Accusua implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccusuaPK accusuaPK;

    public Accusua() {
    }

    public Accusua(AccusuaPK accusuaPK) {
        this.accusuaPK = accusuaPK;
    }

    public Accusua(long acusAcce, long acusUsua) {
        this.accusuaPK = new AccusuaPK(acusAcce, acusUsua);
    }

    public AccusuaPK getAccusuaPK() {
        return accusuaPK;
    }

    public void setAccusuaPK(AccusuaPK accusuaPK) {
        this.accusuaPK = accusuaPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accusuaPK != null ? accusuaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accusua)) {
            return false;
        }
        Accusua other = (Accusua) object;
        if ((this.accusuaPK == null && other.accusuaPK != null) || (this.accusuaPK != null && !this.accusuaPK.equals(other.accusuaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Accusua[ accusuaPK=" + accusuaPK + " ]";
    }
    
}
