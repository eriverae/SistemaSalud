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
@Table(name = "grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByGrupGrup", query = "SELECT g FROM Grupo g WHERE g.grupGrup = :grupGrup"),
    @NamedQuery(name = "Grupo.findByNom", query = "SELECT g FROM Grupo g WHERE g.grupNombr = :grupNombr")})
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "grup_grup")
    private Long grupGrup;
    @Basic(optional = false)
    @Column(name = "grup_nombr")
    private String grupNombr;
    @Basic(optional = false)
    @Column(name = "grup_descri")
    private String grupDescri;
    @Basic(optional = false)
    @Column(name = "grup_esta")
    private String grupEsta;
    
    @Version
    @Column(name = "VERSION")
    private Long version;

    public Grupo() {
    }

    public Grupo(Long grupGrup) {
        this.grupGrup = grupGrup;
    }

    public Grupo(Long grupGrup, String grupNombr, String grupDescri, String grupEsta) {
        this.grupGrup = grupGrup;
        this.grupNombr = grupNombr;
        this.grupDescri = grupDescri;
        this.grupEsta = grupEsta;
    }

    public Long getGrupGrup() {
        return grupGrup;
    }

    public void setGrupGrup(Long grupGrup) {
        this.grupGrup = grupGrup;
    }

    public String getGrupNombr() {
        return grupNombr;
    }

    public void setGrupNombr(String grupNombr) {
        this.grupNombr = grupNombr;
    }

    public String getGrupDescri() {
        return grupDescri;
    }

    public void setGrupDescri(String grupDescri) {
        this.grupDescri = grupDescri;
    }

    public String getGrupEsta() {
        return grupEsta;
    }

    public void setGrupEsta(String grupEsta) {
        this.grupEsta = grupEsta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupGrup != null ? grupGrup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.grupGrup == null && other.grupGrup != null) || (this.grupGrup != null && !this.grupGrup.equals(other.grupGrup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Grupo[ grupGrup=" + grupGrup + " ]";
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
}
