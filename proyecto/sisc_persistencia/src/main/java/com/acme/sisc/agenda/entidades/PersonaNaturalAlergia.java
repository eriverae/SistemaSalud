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
@Table(name = "persona_natural_alergia")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "PersonaNaturalAlergia.findAll", query = "SELECT p FROM PersonaNaturalAlergia p"),
  @NamedQuery(name = "PersonaNaturalAlergia.findByIdPaciente", query = "SELECT p FROM PersonaNaturalAlergia p WHERE p.paciente.idPersona = :idPaciente")})
public class PersonaNaturalAlergia implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_persona_alergia")
  private Long idPersonaAlergia;

  @JoinColumn(name = "id_alergia", referencedColumnName = "id_alergia")
  @ManyToOne
  private Alergia alergia;

  @JoinColumn(name = "id_paciente", referencedColumnName = "id_persona")
  @ManyToOne
  private PersonaNatural paciente;

  @Version
  @Column(name = "VERSION")
  private Long version;

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idPersonaAlergia != null ? idPersonaAlergia.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof PersonaNaturalAlergia)) {
      return false;
    }
    PersonaNaturalAlergia other = (PersonaNaturalAlergia) object;
    if ((this.idPersonaAlergia == null && other.idPersonaAlergia != null) || (this.idPersonaAlergia != null && !this.idPersonaAlergia.equals(other.idPersonaAlergia))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.acme.sisc.agenda.entidades.PersonaNaturalAlergia[ personaNaturalAlergiaPK=" + idPersonaAlergia + " ]";
  }

  public Long getIdPersonaAlergia() {
    return idPersonaAlergia;
  }

  public void setIdPersonaAlergia(Long idPersonaAlergia) {
    this.idPersonaAlergia = idPersonaAlergia;
  }

  public Alergia getAlergia() {
    return alergia;
  }

  public void setAlergia(Alergia alergia) {
    this.alergia = alergia;
  }

  public PersonaNatural getPaciente() {
    return paciente;
  }

  public void setPaciente(PersonaNatural paciente) {
    this.paciente = paciente;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

}
