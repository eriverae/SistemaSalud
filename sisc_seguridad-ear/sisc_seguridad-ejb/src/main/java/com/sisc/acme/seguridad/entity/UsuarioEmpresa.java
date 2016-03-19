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
@Table(name = "usuempr", catalog = "sisc_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"usem_prju"}),
    @UniqueConstraint(columnNames = {"usem_usem"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioEmpresa.findAll", query = "SELECT u FROM UsuarioEmpresa u"),
    @NamedQuery(name = "UsuarioEmpresa.findByUsemUsem", query = "SELECT u FROM UsuarioEmpresa u WHERE u.usuarioEmpresaPK.usemUsem = :usemUsem"),
    @NamedQuery(name = "UsuarioEmpresa.findByUsemPrju", query = "SELECT u FROM UsuarioEmpresa u WHERE u.usuarioEmpresaPK.usemPrju = :usemPrju")})
public class UsuarioEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioEmpresaPK usuarioEmpresaPK;

    public UsuarioEmpresa() {
    }

    public UsuarioEmpresa(UsuarioEmpresaPK usuarioEmpresaPK) {
        this.usuarioEmpresaPK = usuarioEmpresaPK;
    }

    public UsuarioEmpresa(long usemUsem, long usemPrju) {
        this.usuarioEmpresaPK = new UsuarioEmpresaPK(usemUsem, usemPrju);
    }

    public UsuarioEmpresaPK getUsuarioEmpresaPK() {
        return usuarioEmpresaPK;
    }

    public void setUsuarioEmpresaPK(UsuarioEmpresaPK usuarioEmpresaPK) {
        this.usuarioEmpresaPK = usuarioEmpresaPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioEmpresaPK != null ? usuarioEmpresaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEmpresa)) {
            return false;
        }
        UsuarioEmpresa other = (UsuarioEmpresa) object;
        if ((this.usuarioEmpresaPK == null && other.usuarioEmpresaPK != null) || (this.usuarioEmpresaPK != null && !this.usuarioEmpresaPK.equals(other.usuarioEmpresaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisc.acme.seguridad.entity.UsuarioEmpresa[ usuarioEmpresaPK=" + usuarioEmpresaPK + " ]";
    }
    
}
