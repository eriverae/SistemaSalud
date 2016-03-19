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
@Table(name = "grupusu", catalog = "sisc_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"grus_grup"}),
    @UniqueConstraint(columnNames = {"grus_usua"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoUsuario.findAll", query = "SELECT g FROM GrupoUsuario g"),
    @NamedQuery(name = "GrupoUsuario.findByGrusGrup", query = "SELECT g FROM GrupoUsuario g WHERE g.grupoUsuarioPK.grusGrup = :grusGrup"),
    @NamedQuery(name = "GrupoUsuario.findByGrusUsua", query = "SELECT g FROM GrupoUsuario g WHERE g.grupoUsuarioPK.grusUsua = :grusUsua")})
public class GrupoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GrupoUsuarioPK grupoUsuarioPK;

    public GrupoUsuario() {
    }

    public GrupoUsuario(GrupoUsuarioPK grupoUsuarioPK) {
        this.grupoUsuarioPK = grupoUsuarioPK;
    }

    public GrupoUsuario(long grusGrup, long grusUsua) {
        this.grupoUsuarioPK = new GrupoUsuarioPK(grusGrup, grusUsua);
    }

    public GrupoUsuarioPK getGrupoUsuarioPK() {
        return grupoUsuarioPK;
    }

    public void setGrupoUsuarioPK(GrupoUsuarioPK grupoUsuarioPK) {
        this.grupoUsuarioPK = grupoUsuarioPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupoUsuarioPK != null ? grupoUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoUsuario)) {
            return false;
        }
        GrupoUsuario other = (GrupoUsuario) object;
        if ((this.grupoUsuarioPK == null && other.grupoUsuarioPK != null) || (this.grupoUsuarioPK != null && !this.grupoUsuarioPK.equals(other.grupoUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisc.acme.seguridad.entity.GrupoUsuario[ grupoUsuarioPK=" + grupoUsuarioPK + " ]";
    }
    
}
