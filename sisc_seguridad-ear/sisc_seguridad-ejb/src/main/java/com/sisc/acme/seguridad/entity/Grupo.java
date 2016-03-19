/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisc.acme.seguridad.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Erika
 */
@Entity
@Table(name = "grupo", catalog = "sisc_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"grup_grup"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByGrupGrup", query = "SELECT g FROM Grupo g WHERE g.grupGrup = :grupGrup"),
    @NamedQuery(name = "Grupo.findByGrupNombr", query = "SELECT g FROM Grupo g WHERE g.grupNombr = :grupNombr"),
    @NamedQuery(name = "Grupo.findByGrupDescri", query = "SELECT g FROM Grupo g WHERE g.grupDescri = :grupDescri"),
    @NamedQuery(name = "Grupo.findByGrupEsta", query = "SELECT g FROM Grupo g WHERE g.grupEsta = :grupEsta")})
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "grup_grup", nullable = false)
    private Long grupGrup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "grup_nombr", nullable = false, length = 45)
    private String grupNombr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "grup_descri", nullable = false, length = 500)
    private String grupDescri;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "grup_esta", nullable = false, length = 1)
    private String grupEsta;

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
        return "com.sisc.acme.seguridad.entity.Grupo[ grupGrup=" + grupGrup + " ]";
    }
    
}
