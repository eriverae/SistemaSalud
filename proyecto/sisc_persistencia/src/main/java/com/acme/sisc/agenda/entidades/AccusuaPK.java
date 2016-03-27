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
public class AccusuaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "acus_acce")
    private long acusAcce;
    @Basic(optional = false)
    @Column(name = "acus_usua")
    private long acusUsua;

    public AccusuaPK() {
    }

    public AccusuaPK(long acusAcce, long acusUsua) {
        this.acusAcce = acusAcce;
        this.acusUsua = acusUsua;
    }

    public long getAcusAcce() {
        return acusAcce;
    }

    public void setAcusAcce(long acusAcce) {
        this.acusAcce = acusAcce;
    }

    public long getAcusUsua() {
        return acusUsua;
    }

    public void setAcusUsua(long acusUsua) {
        this.acusUsua = acusUsua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) acusAcce;
        hash += (int) acusUsua;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccusuaPK)) {
            return false;
        }
        AccusuaPK other = (AccusuaPK) object;
        if (this.acusAcce != other.acusAcce) {
            return false;
        }
        if (this.acusUsua != other.acusUsua) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.AccusuaPK[ acusAcce=" + acusAcce + ", acusUsua=" + acusUsua + " ]";
    }
    
}
