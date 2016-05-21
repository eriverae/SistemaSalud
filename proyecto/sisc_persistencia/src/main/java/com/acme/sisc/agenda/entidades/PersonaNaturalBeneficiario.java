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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rreedd
 */

@Entity
@Table(name = "persona_natural_beneficiario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaNaturalBeneficiario.findAllByCotizante", query = "SELECT b FROM PersonaNaturalBeneficiario b WHERE b.cotizante.idPersona = :idCotizante"),
    @NamedQuery(name = "PersonaNaturalBeneficiario.findAll", query = "SELECT b FROM PersonaNaturalBeneficiario b")})

public class PersonaNaturalBeneficiario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona_natural_beneficiario")
    private Long idPersonaNaturalBeneficiario;

    @JoinColumn(name = "id_cotizante", referencedColumnName = "id_persona")
    @ManyToOne
    private PersonaNatural cotizante;

    @JoinColumn(name = "id_beneficiario", referencedColumnName = "id_persona")
    @ManyToOne
    private PersonaNatural beneficiario;

    @Column(name = "parentezco")
    private Integer parentezco;
    
    @Version
    @Column(name = "VERSION")
    private Long version;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonaNaturalBeneficiario != null ? idPersonaNaturalBeneficiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalEspecialidad)) {
            return false;
        }
        PersonaNaturalBeneficiario other = (PersonaNaturalBeneficiario) object;
        if ((this.idPersonaNaturalBeneficiario == null && other.idPersonaNaturalBeneficiario != null) 
                || (this.idPersonaNaturalBeneficiario != null && !this.idPersonaNaturalBeneficiario.equals(other.idPersonaNaturalBeneficiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalBeneficiario[ personaNaturalBeneficiarioPK=" + idPersonaNaturalBeneficiario + " ]";
    }

    public Long getIdPersonaNaturalBeneficiario() {
        return idPersonaNaturalBeneficiario;
    }

    public void setIdPersonaNaturalBeneficiario(Long idPersonaNaturalBeneficiario) {
        this.idPersonaNaturalBeneficiario = idPersonaNaturalBeneficiario;
    }

    public PersonaNatural getCotizante() {
        return cotizante;
    }

    public void setCotizante(PersonaNatural cotizante) {
        this.cotizante = cotizante;
    }

    public PersonaNatural getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(PersonaNatural beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * @return the parentezco
     */
    public Integer getParentezco() {
        return parentezco;
    }

    /**
     * @param parentezco the parentezco to set
     */
    public void setParentezco(Integer parentezco) {
        this.parentezco = parentezco;
    }
}
