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
@Table(name = "persona_natural_beneficiario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaNaturalBeneficiario.findAll", query = "SELECT p FROM PersonaNaturalBeneficiario p"),
    @NamedQuery(name = "PersonaNaturalBeneficiario.findByIdPersonaNaturalBeneficiario", query = "SELECT p FROM PersonaNaturalBeneficiario p WHERE p.idPersonaNaturalBeneficiario = :idPersonaNaturalBeneficiario"),
    @NamedQuery(name = "PersonaNaturalBeneficiario.findByIdBeneficiario", query = "SELECT p FROM PersonaNaturalBeneficiario p WHERE p.idBeneficiario = :idBeneficiario"),
    @NamedQuery(name = "PersonaNaturalBeneficiario.findByIdCotizante", query = "SELECT p FROM PersonaNaturalBeneficiario p WHERE p.idCotizante = :idCotizante"),
    @NamedQuery(name = "PersonaNaturalBeneficiario.findByParentezco", query = "SELECT p FROM PersonaNaturalBeneficiario p WHERE p.parentezco = :parentezco")})
public class PersonaNaturalBeneficiario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_persona_natural_beneficiario")
    private Long idPersonaNaturalBeneficiario;
    @Basic(optional = false)
    @Column(name = "id_beneficiario")
    private short idBeneficiario;
    @Column(name = "id_cotizante")
    private Long idCotizante;
    @Column(name = "parentezco")
    private String parentezco;

    public PersonaNaturalBeneficiario() {
    }

    public PersonaNaturalBeneficiario(Long idPersonaNaturalBeneficiario) {
        this.idPersonaNaturalBeneficiario = idPersonaNaturalBeneficiario;
    }

    public PersonaNaturalBeneficiario(Long idPersonaNaturalBeneficiario, short idBeneficiario) {
        this.idPersonaNaturalBeneficiario = idPersonaNaturalBeneficiario;
        this.idBeneficiario = idBeneficiario;
    }

    public Long getIdPersonaNaturalBeneficiario() {
        return idPersonaNaturalBeneficiario;
    }

    public void setIdPersonaNaturalBeneficiario(Long idPersonaNaturalBeneficiario) {
        this.idPersonaNaturalBeneficiario = idPersonaNaturalBeneficiario;
    }

    public short getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(short idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public Long getIdCotizante() {
        return idCotizante;
    }

    public void setIdCotizante(Long idCotizante) {
        this.idCotizante = idCotizante;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonaNaturalBeneficiario != null ? idPersonaNaturalBeneficiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNaturalBeneficiario)) {
            return false;
        }
        PersonaNaturalBeneficiario other = (PersonaNaturalBeneficiario) object;
        if ((this.idPersonaNaturalBeneficiario == null && other.idPersonaNaturalBeneficiario != null) || (this.idPersonaNaturalBeneficiario != null && !this.idPersonaNaturalBeneficiario.equals(other.idPersonaNaturalBeneficiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNaturalBeneficiario[ idPersonaNaturalBeneficiario=" + idPersonaNaturalBeneficiario + " ]";
    }
    
}
