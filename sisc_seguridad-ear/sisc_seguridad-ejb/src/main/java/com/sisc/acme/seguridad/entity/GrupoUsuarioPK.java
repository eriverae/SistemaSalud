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
public class GrupoUsuarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "grus_grup", nullable = false)
    private long grusGrup;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grus_usua", nullable = false)
    private long grusUsua;

    public GrupoUsuarioPK() {
    }

    public GrupoUsuarioPK(long grusGrup, long grusUsua) {
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
        if (!(object instanceof GrupoUsuarioPK)) {
            return false;
        }
        GrupoUsuarioPK other = (GrupoUsuarioPK) object;
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
        return "com.sisc.acme.seguridad.entity.GrupoUsuarioPK[ grusGrup=" + grusGrup + ", grusUsua=" + grusUsua + " ]";
    }
    
}
