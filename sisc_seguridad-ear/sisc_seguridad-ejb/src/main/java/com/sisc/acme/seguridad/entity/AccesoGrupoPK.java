/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisc.acme.seguridad.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Erika
 */
@Embeddable
public class AccesoGrupoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "acgr_acce", nullable = false)
    private long acgrAcce;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acgr_grup", nullable = false)
    private long acgrGrup;

    public AccesoGrupoPK() {
    }

    public AccesoGrupoPK(long acgrAcce, long acgrGrup) {
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
        if (!(object instanceof AccesoGrupoPK)) {
            return false;
        }
        AccesoGrupoPK other = (AccesoGrupoPK) object;
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
        return "com.sisc.acme.seguridad.entity.AccesoGrupoPK[ acgrAcce=" + acgrAcce + ", acgrGrup=" + acgrGrup + " ]";
    }
    
}
