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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Julio
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@DiscriminatorValue("NATURAL")
@Table(name = "persona_natural")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaNatural.findAll", query = "SELECT p FROM PersonaNatural p"),
    @NamedQuery(name = "PersonaNatural.findByAllPacientes", query = "SELECT p FROM PersonaNatural p WHERE p.rolPersonaNatural = 'PACIENTE' "),
    @NamedQuery(name = "PersonaNatural.findByAllMedicos", query = "SELECT p FROM PersonaNatural p WHERE p.rolPersonaNatural = 'MEDICO' "),
    @NamedQuery(name = "PersonaNatural.findByCorreoElectronico", query = "SELECT p FROM PersonaNatural p WHERE p.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "PersonaNatural.findByNombres", query = "SELECT p FROM PersonaNatural p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "PersonaNatural.findByApellidos", query = "SELECT p FROM PersonaNatural p WHERE p.apellidos = :apellidos")
})
public class PersonaNatural extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @Column(name = "rol_persona_natural")
    private String rolPersonaNatural;

    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<PersonaEps> listaPersonasEps;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<PersonaNaturalAlergia> listaAlergias;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medico", fetch = FetchType.LAZY)
    private List<PersonaNaturalEspecialidad> listaEspecialidadesMedico;

    //Se estandarizan las listas con entidades aparte
    //por si en algún momento se requiere añadir más
    //campos o funcionalidades propias de la relación M*M
    //@ManyToMany
    //@JoinTable(
    //    name = "OPERACION_PACIENTE",
    //    joinColumns = @JoinColumn(name = "PACIENTE_ID", referencedColumnName = "ID_PERSONA"),
    //    inverseJoinColumns = @JoinColumn(name = "OPERACION_ID", referencedColumnName = "ID_OPERACION"),
    //    uniqueConstraints = @UniqueConstraint(name = "OPER_PACIENTE_UNIQUE", columnNames = {"PACIENTE_ID", "OPERACION_ID"}))
    //private List<Operacion> listaOperacionesPaciente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<PersonaNaturalOperacion> listaOperaciones;
    
    public PersonaNatural() {
    }

    public PersonaNatural(String correoElectronico, String nombres, String apellidos, Character genero, Date fechaNacimiento, long telefonoCelular, long telefonoFijo, String direccion, Character rh, String grupoSanguineo) {

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

    @JsonIgnore
    public List<PersonaEps> getListaPersonasEps() {
        return listaPersonasEps;
    }

    public void setListaPersonasEps(List<PersonaEps> listaPersonasEps) {
        this.listaPersonasEps = listaPersonasEps;
    }

    public String getRolPersonaNatural() {
        return rolPersonaNatural;
    }

    public void setRolPersonaNatural(String rolPersonaNatural) {
        this.rolPersonaNatural = rolPersonaNatural;
    }

    @JsonIgnore
    public List<PersonaNaturalAlergia> getListaAlergias() {
        return listaAlergias;
    }

    public void setListaAlergias(List<PersonaNaturalAlergia> listaAlergias) {
        this.listaAlergias = listaAlergias;
    }

    @JsonIgnore
    public List<PersonaNaturalOperacion> getListaOperaciones() {
        return listaOperaciones;
    }

    public void setListaOperaciones(List<PersonaNaturalOperacion> listaOperaciones) {
        this.listaOperaciones = listaOperaciones;
    }

    @JsonIgnore
    public List<PersonaNaturalEspecialidad> getListaEspecialidadesMedico() {
        return listaEspecialidadesMedico;
    }

    public void setListaEspecialidadesMedico(List<PersonaNaturalEspecialidad> listaEspecialidadesMedico) {
        this.listaEspecialidadesMedico = listaEspecialidadesMedico;
    }

}
