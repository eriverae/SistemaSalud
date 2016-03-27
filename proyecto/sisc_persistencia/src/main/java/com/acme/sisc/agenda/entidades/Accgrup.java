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
@Table(name = "accgrup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accgrup.findAll", query = "SELECT a FROM Accgrup a"),
    @NamedQuery(name = "Accgrup.findByAcgrAcce", query = "SELECT a FROM Accgrup a WHERE a.accgrupPK.acgrAcce = :acgrAcce"),
    @NamedQuery(name = "Accgrup.findByAcgrGrup", query = "SELECT a FROM Accgrup a WHERE a.accgrupPK.acgrGrup = :acgrGrup")})
public class Accgrup implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccgrupPK accgrupPK;

    public Accgrup() {
    }

    public Accgrup(AccgrupPK accgrupPK) {
        this.accgrupPK = accgrupPK;
    }

    public Accgrup(long acgrAcce, long acgrGrup) {
        this.accgrupPK = new AccgrupPK(acgrAcce, acgrGrup);
    }

    public AccgrupPK getAccgrupPK() {
        return accgrupPK;
    }

    public void setAccgrupPK(AccgrupPK accgrupPK) {
        this.accgrupPK = accgrupPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accgrupPK != null ? accgrupPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accgrup)) {
            return false;
        }
        Accgrup other = (Accgrup) object;
        if ((this.accgrupPK == null && other.accgrupPK != null) || (this.accgrupPK != null && !this.accgrupPK.equals(other.accgrupPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Accgrup[ accgrupPK=" + accgrupPK + " ]";
    }
    
}
