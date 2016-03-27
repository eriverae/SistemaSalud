/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Julio
 */
@Embeddable
public class GrupusuPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "grus_grup")
    private long grusGrup;
    @Basic(optional = false)
    @Column(name = "grus_usua")
    private long grusUsua;

    public GrupusuPK() {
    }

    public GrupusuPK(long grusGrup, long grusUsua) {
        this.grusGrup = grusGrup;
        this.grusUsua = grusUsua;
    }

    public long getGrusGrup() {
        return grusGrup;
    }

    public void setGrusGrup(long grusGrup) {
        this.grusGrup = grusGrup;
    }

    public long getGrusUsua() {
        return grusUsua;
    }

    public void setGrusUsua(long grusUsua) {
        this.grusUsua = grusUsua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) grusGrup;
        hash += (int) grusUsua;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupusuPK)) {
            return false;
        }
        GrupusuPK other = (GrupusuPK) object;
        if (this.grusGrup != other.grusGrup) {
            return false;
        }
        if (this.grusUsua != other.grusUsua) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.GrupusuPK[ grusGrup=" + grusGrup + ", grusUsua=" + grusUsua + " ]";
    }
    
}
