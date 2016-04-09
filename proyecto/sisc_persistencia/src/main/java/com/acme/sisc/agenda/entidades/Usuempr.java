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
@Table(name = "usuempr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuempr.findAll", query = "SELECT u FROM Usuempr u")})
public class Usuempr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuempr")
    private Long idUsuempr;
    
    @Version
    @Column(name = "VERSION")
    private Long version;
    
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private PersonaJuridica idPersona;
    
    @JoinColumn(name = "usua_usua", referencedColumnName = "usua_usua")
    @ManyToOne
    private Usuario usuario;

    public Usuempr() {
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuempr != null ? idUsuempr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuempr)) {
            return false;
        }
        Usuempr other = (Usuempr) object;
        if ((this.idUsuempr == null && other.idUsuempr != null) || (this.idUsuempr != null && !this.idUsuempr.equals(other.idUsuempr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Usuempr[ usuemprPK=" + idUsuempr + " ]";
    }

    public Long getIdUsuempr() {
        return idUsuempr;
    }

    public void setIdUsuempr(Long idUsuempr) {
        this.idUsuempr = idUsuempr;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public PersonaJuridica getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(PersonaJuridica idPersona) {
        this.idPersona = idPersona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
