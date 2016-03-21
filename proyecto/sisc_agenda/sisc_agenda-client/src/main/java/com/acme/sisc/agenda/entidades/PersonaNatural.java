/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "persona_natural")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaNatural.findAll", query = "SELECT p FROM PersonaNatural p"),
    @NamedQuery(name = "PersonaNatural.findByIdPersonaNatural", query = "SELECT p FROM PersonaNatural p WHERE p.idPersonaNatural = :idPersonaNatural"),
    @NamedQuery(name = "PersonaNatural.findByTipoPersona", query = "SELECT p FROM PersonaNatural p WHERE p.tipoPersona = :tipoPersona"),
    @NamedQuery(name = "PersonaNatural.findByCorreoElectronico", query = "SELECT p FROM PersonaNatural p WHERE p.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "PersonaNatural.findByNombres", query = "SELECT p FROM PersonaNatural p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "PersonaNatural.findByApellidos", query = "SELECT p FROM PersonaNatural p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "PersonaNatural.findByGenero", query = "SELECT p FROM PersonaNatural p WHERE p.genero = :genero"),
    @NamedQuery(name = "PersonaNatural.findByFechaNacimiento", query = "SELECT p FROM PersonaNatural p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "PersonaNatural.findByTelefonoCelular", query = "SELECT p FROM PersonaNatural p WHERE p.telefonoCelular = :telefonoCelular"),
    @NamedQuery(name = "PersonaNatural.findByTelefonoFijo", query = "SELECT p FROM PersonaNatural p WHERE p.telefonoFijo = :telefonoFijo"),
    @NamedQuery(name = "PersonaNatural.findByDireccion", query = "SELECT p FROM PersonaNatural p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "PersonaNatural.findByFotografia", query = "SELECT p FROM PersonaNatural p WHERE p.fotografia = :fotografia"),
    @NamedQuery(name = "PersonaNatural.findByRh", query = "SELECT p FROM PersonaNatural p WHERE p.rh = :rh"),
    @NamedQuery(name = "PersonaNatural.findByGrupoSanguineo", query = "SELECT p FROM PersonaNatural p WHERE p.grupoSanguineo = :grupoSanguineo"),
    @NamedQuery(name = "PersonaNatural.findByTarjetaProfesional", query = "SELECT p FROM PersonaNatural p WHERE p.tarjetaProfesional = :tarjetaProfesional")})
public class PersonaNatural implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_persona_natural")
    private Long idPersonaNatural;
    @Column(name = "tipo_persona")
    private Short tipoPersona;
    @Basic(optional = false)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "genero")
    private Character genero;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "telefono_celular")
    private long telefonoCelular;
    @Basic(optional = false)
    @Column(name = "telefono_fijo")
    private long telefonoFijo;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fotografia")
    private String fotografia;
    @Lob
    @Column(name = "huella")
    private byte[] huella;
    @Basic(optional = false)
    @Column(name = "rh")
    private Character rh;
    @Basic(optional = false)
    @Column(name = "grupo_sanguineo")
    private String grupoSanguineo;
    @Column(name = "tarjeta_profesional")
    private String tarjetaProfesional;
    @OneToMany(mappedBy = "idPersonaNatural")
    private List<PersonaEps> personaEpsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersonaNatural")
    private List<Cita> citaList;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @OneToOne(optional = false)
    private Persona idPersona;

    public PersonaNatural() {
    }

    public PersonaNatural(Long idPersonaNatural) {
        this.idPersonaNatural = idPersonaNatural;
    }

    public PersonaNatural(Long idPersonaNatural, String correoElectronico, String nombres, String apellidos, Character genero, Date fechaNacimiento, long telefonoCelular, long telefonoFijo, String direccion, Character rh, String grupoSanguineo) {
        this.idPersonaNatural = idPersonaNatural;
        this.correoElectronico = correoElectronico;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.telefonoCelular = telefonoCelular;
        this.telefonoFijo = telefonoFijo;
        this.direccion = direccion;
        this.rh = rh;
        this.grupoSanguineo = grupoSanguineo;
    }

    public Long getIdPersonaNatural() {
        return idPersonaNatural;
    }

    public void setIdPersonaNatural(Long idPersonaNatural) {
        this.idPersonaNatural = idPersonaNatural;
    }

    public Short getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(Short tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(long telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public long getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(long telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public byte[] getHuella() {
        return huella;
    }

    public void setHuella(byte[] huella) {
        this.huella = huella;
    }

    public Character getRh() {
        return rh;
    }

    public void setRh(Character rh) {
        this.rh = rh;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional(String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }

    @XmlTransient
    public List<PersonaEps> getPersonaEpsList() {
        return personaEpsList;
    }

    public void setPersonaEpsList(List<PersonaEps> personaEpsList) {
        this.personaEpsList = personaEpsList;
    }

    @XmlTransient
    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonaNatural != null ? idPersonaNatural.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaNatural)) {
            return false;
        }
        PersonaNatural other = (PersonaNatural) object;
        if ((this.idPersonaNatural == null && other.idPersonaNatural != null) || (this.idPersonaNatural != null && !this.idPersonaNatural.equals(other.idPersonaNatural))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.PersonaNatural[ idPersonaNatural=" + idPersonaNatural + " ]";
    }
    
}
