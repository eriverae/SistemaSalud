/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "persona_juridica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaJuridica.findAll", query = "SELECT p FROM PersonaJuridica p"),
    @NamedQuery(name = "PersonaJuridica.findByIdPersonaJuridica", query = "SELECT p FROM PersonaJuridica p WHERE p.idPersonaJuridica = :idPersonaJuridica"),
    @NamedQuery(name = "PersonaJuridica.findByRazonSocial", query = "SELECT p FROM PersonaJuridica p WHERE p.razonSocial = :razonSocial"),
    @NamedQuery(name = "PersonaJuridica.findByFechaConstitucion", query = "SELECT p FROM PersonaJuridica p WHERE p.fechaConstitucion = :fechaConstitucion"),
    @NamedQuery(name = "PersonaJuridica.findByRepresentanteLegal", query = "SELECT p FROM PersonaJuridica p WHERE p.representanteLegal = :representanteLegal")})
public class PersonaJuridica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_persona_juridica")
    private Long idPersonaJuridica;
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "fecha_constitucion")
    private Long fechaConstitucion;
    @Column(name = "representante_legal")
    private Long representanteLegal;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @OneToOne(optional = false)
    private Persona idPersona;
    @OneToMany(mappedBy = "idEps")
    private List<PersonaEps> personaEpsList;

    public PersonaJuridica() {
    }

    public PersonaJuridica(Long idPersonaJuridica) {
        this.idPersonaJuridica = idPersonaJuridica;
    }

    public Long getIdPersonaJuridica() {
        return idPersonaJuridica;
    }

    public void setIdPersonaJuridica(Long idPersonaJuridica) {
        this.idPersonaJuridica = idPersonaJuridica;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Long getFechaConstitucion() {
        return fechaConstitucion;
    }

    public void setFechaConstitucion(Long fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    public Long getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(Long representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @XmlTransient
    public List<PersonaEps> getPersonaEpsList() {
        return personaEpsList;
    }

    public void setPersonaEpsList(List<PersonaEps> personaEpsList) {
        this.personaEpsList = personaEpsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonaJuridica != null ? idPersonaJuridica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaJuridica)) {
            return false;
        }
        PersonaJuridica other = (PersonaJuridica) object;
        if ((this.idPersonaJuridica == null && other.idPersonaJuridica != null) || (this.idPersonaJuridica != null && !this.idPersonaJuridica.equals(other.idPersonaJuridica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaJuridica[ idPersonaJuridica=" + idPersonaJuridica + " ]";
    }
    
}
