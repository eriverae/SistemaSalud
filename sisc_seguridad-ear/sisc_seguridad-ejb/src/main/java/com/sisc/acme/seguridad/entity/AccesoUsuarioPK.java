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
public class AccesoUsuarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "acus_acce", nullable = false)
    private long acusAcce;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acus_usua", nullable = false)
    private long acusUsua;

    public AccesoUsuarioPK() {
    }

    public AccesoUsuarioPK(long acusAcce, long acusUsua) {
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
        if (!(object instanceof AccesoUsuarioPK)) {
            return false;
        }
        AccesoUsuarioPK other = (AccesoUsuarioPK) object;
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
        return "com.sisc.acme.seguridad.entity.AccesoUsuarioPK[ acusAcce=" + acusAcce + ", acusUsua=" + acusUsua + " ]";
    }
    
}
