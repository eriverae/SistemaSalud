/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "accusua")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accusua.findAll", query = "SELECT a FROM Accusua a")})
public class Accusua implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_accusua")
    private Long idAccusua;
    
    @JoinColumn(name = "acce_acce", referencedColumnName = "acce_acce")
    @ManyToOne
    private Acceso acceso;
    
    @JoinColumn(name = "usua_usua", referencedColumnName = "usua_usua")
    @ManyToOne
    private Usuario usuario;
    
    @Version
    @Column(name = "VERSION")
    private Long version;

    public Accusua() {
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccusua != null ? idAccusua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accusua)) {
            return false;
        }
        Accusua other = (Accusua) object;
        if ((this.idAccusua == null && other.idAccusua != null) || (this.idAccusua != null && !this.idAccusua.equals(other.idAccusua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Accusua[ accusuaPK=" + idAccusua + " ]";
    }
    
    /**
     * @return the version
     */
    public Long getVersion() {
      return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Long version) {
      this.version = version;
    }

    public Long getIdAccusua() {
        return idAccusua;
    }

    public void setIdAccusua(Long idAccusua) {
        this.idAccusua = idAccusua;
    }

    public Acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(Acceso acceso) {
        this.acceso = acceso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
