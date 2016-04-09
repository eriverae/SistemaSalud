/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "acceso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acceso.findAll", query = "SELECT a FROM Acceso a"),
    @NamedQuery(name = "Acceso.findByAcceAcce", query = "SELECT a FROM Acceso a WHERE a.acceAcce = :acceAcce"),
    @NamedQuery(name = "Acceso.findByAcceNombre", query = "SELECT a FROM Acceso a WHERE a.acceNombre = :acceNombre")})
public class Acceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "acce_acce")
    private Long acceAcce;
    @Basic(optional = false)
    @Column(name = "acce_nombre")
    private String acceNombre;
    @Basic(optional = false)
    @Column(name = "acce_desc")
    private String acceDesc;
    @Basic(optional = false)
    @Column(name = "acce_esta")
    private String acceEsta;
    
    @Version
    @Column(name = "VERSION")
    private Long version;

    public Acceso() {
    }

    public Acceso(Long acceAcce) {
        this.acceAcce = acceAcce;
    }

    public Acceso(Long acceAcce, String acceNombre, String acceDesc, String acceEsta) {
        this.acceAcce = acceAcce;
        this.acceNombre = acceNombre;
        this.acceDesc = acceDesc;
        this.acceEsta = acceEsta;
    }

    public Long getAcceAcce() {
        return acceAcce;
    }

    public void setAcceAcce(Long acceAcce) {
        this.acceAcce = acceAcce;
    }

    public String getAcceNombre() {
        return acceNombre;
    }

    public void setAcceNombre(String acceNombre) {
        this.acceNombre = acceNombre;
    }

    public String getAcceDesc() {
        return acceDesc;
    }

    public void setAcceDesc(String acceDesc) {
        this.acceDesc = acceDesc;
    }

    public String getAcceEsta() {
        return acceEsta;
    }

    public void setAcceEsta(String acceEsta) {
        this.acceEsta = acceEsta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acceAcce != null ? acceAcce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acceso)) {
            return false;
        }
        Acceso other = (Acceso) object;
        if ((this.acceAcce == null && other.acceAcce != null) || (this.acceAcce != null && !this.acceAcce.equals(other.acceAcce))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Acceso[ acceAcce=" + acceAcce + " ]";
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
    
}
