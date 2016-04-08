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
@Table(name = "grupusu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupusu.findAll", query = "SELECT g FROM Grupusu g")})
public class Grupusu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupusu")
    private Long idGrupusu;
    
    @Version
    @Column(name = "VERSION")
    private Long version;

    @JoinColumn(name = "acce_acce", referencedColumnName = "acce_acce")
    @ManyToOne
    private Acceso acceso;
    
    @JoinColumn(name = "usua_usua", referencedColumnName = "usua_usua")
    @ManyToOne
    private Usuario usuario;
    
    
    public Grupusu() {
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupusu != null ? idGrupusu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupusu)) {
            return false;
        }
        Grupusu other = (Grupusu) object;
        if ((this.idGrupusu == null && other.idGrupusu != null) || (this.idGrupusu != null && !this.idGrupusu.equals(other.idGrupusu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Grupusu[ grupusuPK=" + idGrupusu + " ]";
    }

    public Long getIdGrupusu() {
        return idGrupusu;
    }

    public void setIdGrupusu(Long idGrupusu) {
        this.idGrupusu = idGrupusu;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
