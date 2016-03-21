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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "tipo_persona_natural")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPersonaNatural.findAll", query = "SELECT t FROM TipoPersonaNatural t"),
    @NamedQuery(name = "TipoPersonaNatural.findByIdTipoPersonaNatural", query = "SELECT t FROM TipoPersonaNatural t WHERE t.idTipoPersonaNatural = :idTipoPersonaNatural"),
    @NamedQuery(name = "TipoPersonaNatural.findByDescripcion", query = "SELECT t FROM TipoPersonaNatural t WHERE t.descripcion = :descripcion")})
public class TipoPersonaNatural implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_persona_natural")
    private Short idTipoPersonaNatural;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    public TipoPersonaNatural() {
    }

    public TipoPersonaNatural(Short idTipoPersonaNatural) {
        this.idTipoPersonaNatural = idTipoPersonaNatural;
    }

    public TipoPersonaNatural(Short idTipoPersonaNatural, String descripcion) {
        this.idTipoPersonaNatural = idTipoPersonaNatural;
        this.descripcion = descripcion;
    }

    public Short getIdTipoPersonaNatural() {
        return idTipoPersonaNatural;
    }

    public void setIdTipoPersonaNatural(Short idTipoPersonaNatural) {
        this.idTipoPersonaNatural = idTipoPersonaNatural;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPersonaNatural != null ? idTipoPersonaNatural.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPersonaNatural)) {
            return false;
        }
        TipoPersonaNatural other = (TipoPersonaNatural) object;
        if ((this.idTipoPersonaNatural == null && other.idTipoPersonaNatural != null) || (this.idTipoPersonaNatural != null && !this.idTipoPersonaNatural.equals(other.idTipoPersonaNatural))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.TipoPersonaNatural[ idTipoPersonaNatural=" + idTipoPersonaNatural + " ]";
    }
    
}
