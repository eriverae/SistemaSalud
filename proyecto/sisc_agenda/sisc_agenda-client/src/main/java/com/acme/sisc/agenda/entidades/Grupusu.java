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
@Table(name = "grupusu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupusu.findAll", query = "SELECT g FROM Grupusu g"),
    @NamedQuery(name = "Grupusu.findByGrusGrup", query = "SELECT g FROM Grupusu g WHERE g.grupusuPK.grusGrup = :grusGrup"),
    @NamedQuery(name = "Grupusu.findByGrusUsua", query = "SELECT g FROM Grupusu g WHERE g.grupusuPK.grusUsua = :grusUsua")})
public class Grupusu implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GrupusuPK grupusuPK;

    public Grupusu() {
    }

    public Grupusu(GrupusuPK grupusuPK) {
        this.grupusuPK = grupusuPK;
    }

    public Grupusu(long grusGrup, long grusUsua) {
        this.grupusuPK = new GrupusuPK(grusGrup, grusUsua);
    }

    public GrupusuPK getGrupusuPK() {
        return grupusuPK;
    }

    public void setGrupusuPK(GrupusuPK grupusuPK) {
        this.grupusuPK = grupusuPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupusuPK != null ? grupusuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupusu)) {
            return false;
        }
        Grupusu other = (Grupusu) object;
        if ((this.grupusuPK == null && other.grupusuPK != null) || (this.grupusuPK != null && !this.grupusuPK.equals(other.grupusuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Grupusu[ grupusuPK=" + grupusuPK + " ]";
    }
    
}
