/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GABRIEL
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cirugia")
    private Long idCirugia;
    @Size(max = 150)
    @Column(name = "nombre_cirugia")
    private String nombreCirugia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cirugia", fetch = FetchType.LAZY)
    private List<CitaCirugia> citaCirugiaList;

    @Version
    @Column(name = "VERSION")
    private Long version;
    
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

    @XmlTransient
    public List<CitaCirugia> getCitaCirugiaList() {
        return citaCirugiaList;
    }

    public void setCitaCirugiaList(List<CitaCirugia> citaCirugiaList) {
        this.citaCirugiaList = citaCirugiaList;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
}
