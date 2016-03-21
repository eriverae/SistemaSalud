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
public class AccgrupPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "acgr_acce")
    private long acgrAcce;
    @Basic(optional = false)
    @Column(name = "acgr_grup")
    private long acgrGrup;

    public AccgrupPK() {
    }

    public AccgrupPK(long acgrAcce, long acgrGrup) {
        this.acgrAcce = acgrAcce;
        this.acgrGrup = acgrGrup;
    }

    public long getAcgrAcce() {
        return acgrAcce;
    }

    public void setAcgrAcce(long acgrAcce) {
        this.acgrAcce = acgrAcce;
    }

    public long getAcgrGrup() {
        return acgrGrup;
    }

    public void setAcgrGrup(long acgrGrup) {
        this.acgrGrup = acgrGrup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) acgrAcce;
        hash += (int) acgrGrup;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccgrupPK)) {
            return false;
        }
        AccgrupPK other = (AccgrupPK) object;
        if (this.acgrAcce != other.acgrAcce) {
            return false;
        }
        if (this.acgrGrup != other.acgrGrup) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.AccgrupPK[ acgrAcce=" + acgrAcce + ", acgrGrup=" + acgrGrup + " ]";
    }
    
}
