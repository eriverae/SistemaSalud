/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisc.acme.seguridad.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Erika
 */
@Entity
@Table(name = "accusua", catalog = "sisc_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"acus_usua"}),
    @UniqueConstraint(columnNames = {"acus_acce"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccesoUsuario.findAll", query = "SELECT a FROM AccesoUsuario a"),
    @NamedQuery(name = "AccesoUsuario.findByAcusAcce", query = "SELECT a FROM AccesoUsuario a WHERE a.accesoUsuarioPK.acusAcce = :acusAcce"),
    @NamedQuery(name = "AccesoUsuario.findByAcusUsua", query = "SELECT a FROM AccesoUsuario a WHERE a.accesoUsuarioPK.acusUsua = :acusUsua")})
public class AccesoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccesoUsuarioPK accesoUsuarioPK;

    public AccesoUsuario() {
    }

    public AccesoUsuario(AccesoUsuarioPK accesoUsuarioPK) {
        this.accesoUsuarioPK = accesoUsuarioPK;
    }

    public AccesoUsuario(long acusAcce, long acusUsua) {
        this.accesoUsuarioPK = new AccesoUsuarioPK(acusAcce, acusUsua);
    }

    public AccesoUsuarioPK getAccesoUsuarioPK() {
        return accesoUsuarioPK;
    }

    public void setAccesoUsuarioPK(AccesoUsuarioPK accesoUsuarioPK) {
        this.accesoUsuarioPK = accesoUsuarioPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accesoUsuarioPK != null ? accesoUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccesoUsuario)) {
            return false;
        }
        AccesoUsuario other = (AccesoUsuario) object;
        if ((this.accesoUsuarioPK == null && other.accesoUsuarioPK != null) || (this.accesoUsuarioPK != null && !this.accesoUsuarioPK.equals(other.accesoUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisc.acme.seguridad.entity.AccesoUsuario[ accesoUsuarioPK=" + accesoUsuarioPK + " ]";
    }
    
}
