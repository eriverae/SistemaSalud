/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GABRIEL
 */
@Entity
@Table(name = "alergia_medicamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlergiaMedicamento.findAll", query = "SELECT am FROM AlergiaMedicamento am")})
public class AlergiaMedicamento implements Serializable{
private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_genracion")
    @Temporal(TemporalType.DATE)
    private Date fechaGenracion;
    
    @JoinColumn(name = "id_medicamento", referencedColumnName = "id_medicamento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Medicamento medicamento;
    
    @JoinColumn(name = "id_alergia", referencedColumnName = "id_alergia")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Alergia alergia;
    
    
    @Version
    @Column(name = "VERSION")
    private Long version;
    
    public AlergiaMedicamento() {
    }
    
    public AlergiaMedicamento(Alergia alergia) {
        this.alergia = alergia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaGenracion() {
        return fechaGenracion;
    }

    public void setFechaGenracion(Date fechaGenracion) {
        this.fechaGenracion = fechaGenracion;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Alergia getAlergia() {
        return alergia;
    }

    public void setAlergia(Alergia alergia) {
        this.alergia = alergia;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlergiaMedicamento)) {
            return false;
        }
        AlergiaMedicamento other = (AlergiaMedicamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.AlergiaMedicamento[ id=" + id + " ]";
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
