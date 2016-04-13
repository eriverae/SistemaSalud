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
 * @author Jamer
 */
@Entity
@Table(name = "operacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operacion.findAll", query = "SELECT a FROM Operacion a"),
    @NamedQuery(name = "Operacion.findByIdOperacion", query = "SELECT a FROM Operacion a WHERE a.idOperacion = :idOperacion"),
    @NamedQuery(name = "Operacion.findByDescripcion", query = "SELECT a FROM Operacion a WHERE a.descripcion = :descripcion")})
public class Operacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacion")
    private Long idOperacion;
    
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    @Version
    @Column(name = "VERSION")
    private Long version;

    public Operacion() {
  }

  public Operacion(Long idOperacion) {
    this.idOperacion = idOperacion;
  }

  public Operacion(Long idOperacion, String descripcion) {
    this.idOperacion = idOperacion;
    this.descripcion = descripcion;
  }

  public Long getIdOperacion() {
    return idOperacion;
  }

  public void setIdOperacion(Long IdOperacion) {
    this.idOperacion = IdOperacion;
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
    hash += (idOperacion != null ? idOperacion.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Operacion)) {
      return false;
    }
    Operacion other = (Operacion) object;
    if ((this.idOperacion == null && other.idOperacion != null) || (this.idOperacion != null && !this.idOperacion.equals(other.idOperacion))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.acme.sisc.agenda.entidades.Operacion[ idOperacion=" + idOperacion + " ]";
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

}
