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
@Table(name = "accgrup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accgrup.findAll", query = "SELECT a FROM Accgrup a")})
public class Accgrup implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_accgrup")
    private Long idAccgrup;
    
    @JoinColumn(name = "acce_acce", referencedColumnName = "acce_acce")
    @ManyToOne
    private Acceso acceso;
    
    @JoinColumn(name = "grup_grup", referencedColumnName = "grup_grup")
    @ManyToOne
    private Grupo grupo;
    
    @Version
    @Column(name = "VERSION")
    private Long version;

    public Acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(Acceso acceso) {
        this.acceso = acceso;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Accgrup() {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccgrup != null ? idAccgrup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accgrup)) {
            return false;
        }
        Accgrup other = (Accgrup) object;
        if ((this.idAccgrup == null && other.idAccgrup != null) || (this.idAccgrup != null && !this.idAccgrup.equals(other.idAccgrup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Accgrup[ accgrupPK=" + idAccgrup + " ]";
    }

    public Long getIdAccgrup() {
        return idAccgrup;
    }

    public void setIdAccgrup(Long idAccgrup) {
        this.idAccgrup = idAccgrup;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
}
