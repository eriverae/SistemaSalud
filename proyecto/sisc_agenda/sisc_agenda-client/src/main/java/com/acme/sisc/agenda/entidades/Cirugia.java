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
@Table(name = "cirugia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cirugia.findAll", query = "SELECT c FROM Cirugia c"),
    @NamedQuery(name = "Cirugia.findByIdCirugia", query = "SELECT c FROM Cirugia c WHERE c.idCirugia = :idCirugia"),
    @NamedQuery(name = "Cirugia.findByNombreCirugia", query = "SELECT c FROM Cirugia c WHERE c.nombreCirugia = :nombreCirugia")})
public class Cirugia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_cirugia")
    private Long idCirugia;
    @Column(name = "nombre_cirugia")
    private String nombreCirugia;

    public Cirugia() {
    }

    public Cirugia(Long idCirugia) {
        this.idCirugia = idCirugia;
    }

    public Long getIdCirugia() {
        return idCirugia;
    }

    public void setIdCirugia(Long idCirugia) {
        this.idCirugia = idCirugia;
    }

    public String getNombreCirugia() {
        return nombreCirugia;
    }

    public void setNombreCirugia(String nombreCirugia) {
        this.nombreCirugia = nombreCirugia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCirugia != null ? idCirugia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cirugia)) {
            return false;
        }
        Cirugia other = (Cirugia) object;
        if ((this.idCirugia == null && other.idCirugia != null) || (this.idCirugia != null && !this.idCirugia.equals(other.idCirugia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Cirugia[ idCirugia=" + idCirugia + " ]";
    }
    
}
