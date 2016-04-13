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
@Table(name = "tratamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tratamiento.findAll", query = "SELECT t FROM Tratamiento t"),
    @NamedQuery(name = "Tratamiento.findByIdTratamiento", query = "SELECT t FROM Tratamiento t WHERE t.idTratamiento = :idTratamiento"),
    @NamedQuery(name = "Tratamiento.findByNombreTratamiento", query = "SELECT t FROM Tratamiento t WHERE t.nombreTratamiento = :nombreTratamiento")})
public class Tratamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tratamiento")
    private Long idTratamiento;
    @Size(max = 150)
    @Column(name = "nombre_tratamiento")
    private String nombreTratamiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tratamiento", fetch = FetchType.LAZY)
    private List<CitaTratamiento> citaTratamientoList;
    @Version
    @Column(name = "VERSION")
    private Long version;

    public Tratamiento() {
    }

    public Tratamiento(Long idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public Long getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Long idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getNombreTratamiento() {
        return nombreTratamiento;
    }

    public void setNombreTratamiento(String nombreTratamiento) {
        this.nombreTratamiento = nombreTratamiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTratamiento != null ? idTratamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tratamiento)) {
            return false;
        }
        Tratamiento other = (Tratamiento) object;
        if ((this.idTratamiento == null && other.idTratamiento != null) || (this.idTratamiento != null && !this.idTratamiento.equals(other.idTratamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Tratamiento[ idTratamiento=" + idTratamiento + " ]";
    }
    
    @XmlTransient
    public List<CitaTratamiento> getCitaTratamientoList() {
        return citaTratamientoList;
    }

    public void setCitaTratamientoList(List<CitaTratamiento> citaTratamientoList) {
        this.citaTratamientoList = citaTratamientoList;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
}
