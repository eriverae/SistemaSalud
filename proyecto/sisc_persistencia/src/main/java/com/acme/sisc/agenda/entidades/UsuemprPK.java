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
public class UsuemprPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "usem_usem")
    private long usemUsem;
    @Basic(optional = false)
    @Column(name = "usem_prju")
    private long usemPrju;

    public UsuemprPK() {
    }

    public UsuemprPK(long usemUsem, long usemPrju) {
        this.usemUsem = usemUsem;
        this.usemPrju = usemPrju;
    }

    public long getUsemUsem() {
        return usemUsem;
    }

    public void setUsemUsem(long usemUsem) {
        this.usemUsem = usemUsem;
    }

    public long getUsemPrju() {
        return usemPrju;
    }

    public void setUsemPrju(long usemPrju) {
        this.usemPrju = usemPrju;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usemUsem;
        hash += (int) usemPrju;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuemprPK)) {
            return false;
        }
        UsuemprPK other = (UsuemprPK) object;
        if (this.usemUsem != other.usemUsem) {
            return false;
        }
        if (this.usemPrju != other.usemPrju) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.UsuemprPK[ usemUsem=" + usemUsem + ", usemPrju=" + usemPrju + " ]";
    }
    
}
