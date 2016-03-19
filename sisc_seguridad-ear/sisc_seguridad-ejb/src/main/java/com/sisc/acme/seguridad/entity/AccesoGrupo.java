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
@Table(name = "accgrup", catalog = "sisc_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"acgr_acce"}),
    @UniqueConstraint(columnNames = {"acgr_grup"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccesoGrupo.findAll", query = "SELECT a FROM AccesoGrupo a"),
    @NamedQuery(name = "AccesoGrupo.findByAcgrAcce", query = "SELECT a FROM AccesoGrupo a WHERE a.accesoGrupoPK.acgrAcce = :acgrAcce"),
    @NamedQuery(name = "AccesoGrupo.findByAcgrGrup", query = "SELECT a FROM AccesoGrupo a WHERE a.accesoGrupoPK.acgrGrup = :acgrGrup")})
public class AccesoGrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccesoGrupoPK accesoGrupoPK;

    public AccesoGrupo() {
    }

    public AccesoGrupo(AccesoGrupoPK accesoGrupoPK) {
        this.accesoGrupoPK = accesoGrupoPK;
    }

    public AccesoGrupo(long acgrAcce, long acgrGrup) {
        this.accesoGrupoPK = new AccesoGrupoPK(acgrAcce, acgrGrup);
    }

    public AccesoGrupoPK getAccesoGrupoPK() {
        return accesoGrupoPK;
    }

    public void setAccesoGrupoPK(AccesoGrupoPK accesoGrupoPK) {
        this.accesoGrupoPK = accesoGrupoPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accesoGrupoPK != null ? accesoGrupoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccesoGrupo)) {
            return false;
        }
        AccesoGrupo other = (AccesoGrupo) object;
        if ((this.accesoGrupoPK == null && other.accesoGrupoPK != null) || (this.accesoGrupoPK != null && !this.accesoGrupoPK.equals(other.accesoGrupoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sisc.acme.seguridad.entity.AccesoGrupo[ accesoGrupoPK=" + accesoGrupoPK + " ]";
    }
    
}
