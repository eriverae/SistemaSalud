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
@Table(name = "alergia")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Alergia.findAll", query = "SELECT a FROM Alergia a"),
  @NamedQuery(name = "Alergia.findByIdAlergia", query = "SELECT a FROM Alergia a WHERE a.idAlergia = :idAlergia"),
  @NamedQuery(name = "Alergia.findByDescripcion", query = "SELECT a FROM Alergia a WHERE a.descripcion = :descripcion")})
public class Alergia implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_alergia")
  private Long idAlergia;
  @Basic(optional = false)
  @Column(name = "descripcion")
  private String descripcion;

  @Version
  @Column(name = "VERSION")
  private Long version;

  public Alergia() {
  }

  public Alergia(Long idAlergia) {
    this.idAlergia = idAlergia;
  }

  public Alergia(Long idAlergia, String descripcion) {
    this.idAlergia = idAlergia;
    this.descripcion = descripcion;
  }

  public Long getIdAlergia() {
    return idAlergia;
  }

  public void setIdAlergia(Long idAlergia) {
    this.idAlergia = idAlergia;
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
    hash += (idAlergia != null ? idAlergia.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Alergia)) {
      return false;
    }
    Alergia other = (Alergia) object;
    if ((this.idAlergia == null && other.idAlergia != null) || (this.idAlergia != null && !this.idAlergia.equals(other.idAlergia))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.acme.sisc.agenda.entidades.Alergia[ idAlergia=" + idAlergia + " ]";
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

}
