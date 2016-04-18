/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jamer
 */
@Entity
@DiscriminatorValue("JURIDICA")
@Table(name = "persona_juridica")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "PersonaJuridica.findAll", query = "SELECT p FROM PersonaJuridica p"),
  @NamedQuery(name = "PersonaJuridica.findByRazonSocial", query = "SELECT p FROM PersonaJuridica p WHERE p.razonSocial = :razonSocial"),
  @NamedQuery(name = "PersonaJuridica.findByFechaConstitucion", query = "SELECT p FROM PersonaJuridica p WHERE p.fechaConstitucion = :fechaConstitucion"),
  @NamedQuery(name = "PersonaJuridica.findByRepresentanteLegal", query = "SELECT p FROM PersonaJuridica p WHERE p.representanteLegal = :representanteLegal")})
public class PersonaJuridica extends Persona implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "razon_social")
  private String razonSocial;

  @Column(name = "fecha_constitucion")
  private String fechaConstitucion;

  @Column(name = "representante_legal")
  private String representanteLegal;

  @OneToMany(mappedBy = "eps",fetch = FetchType.LAZY)
  private List<PersonaEps> listaAfiliados;


  public PersonaJuridica() {
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public String getFechaConstitucion() {
    return fechaConstitucion;
  }

  public void setFechaConstitucion(String fechaConstitucion) {
    this.fechaConstitucion = fechaConstitucion;
  }

  public String getRepresentanteLegal() {
    return representanteLegal;
  }

  public void setRepresentanteLegal(String representanteLegal) {
    this.representanteLegal = representanteLegal;
  }

  @XmlTransient
  public List<PersonaEps> getListaAfiliados() {
    return listaAfiliados;
  }

  public void setListaAfiliados(List<PersonaEps> listaAfiliados) {
    this.listaAfiliados = listaAfiliados;
  }

}
