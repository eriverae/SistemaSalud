/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Julio
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findAllOrderByEstado", query = "SELECT u FROM Usuario u ORDER BY u.usuaEsta"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.usuaEmail = :email"),
    @NamedQuery(name = "Usuario.findByUsuaUsua", query = "SELECT u FROM Usuario u WHERE u.usuaUsua = :usuaUsua")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usua_usua")
    private Long usuaUsua;
    @Basic(optional = false)
    @Column(name = "usua_email")
    private String usuaEmail;
    @Basic(optional = false)
    @Column(name = "usua_pass")
    private String usuaPass;
    @Basic(optional = false)
    @Column(name = "usua_esta")
    private String usuaEsta;
    @Basic(optional = false)
    @Column(name = "usua_usucs")
    private String usuaUsucs;
    @Basic(optional = false)
    @Column(name = "usua_usucd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuaUsucd;
    @Basic(optional = false)
    @Column(name = "usua_usums")
    private String usuaUsums;
    @Basic(optional = false)
    @Column(name = "usua_usumd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuaUsumd;
    @Column(name = "usua_block")
    private boolean usuaBlock;
    /* Este Atributo no va
    @Basic(optional = false)
    @Column(name = "usua_persn")
    private long usuaPersn;
    */
    @Basic(optional = false)
    @Column(name = "usua_conta")
    private long usuaConta;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "audiAudi", fetch = FetchType.LAZY)
    private List<Auditusu> listaAuditores;
    
    @Version
    @Column(name = "VERSION")
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Usuario() {
    }

    public Usuario(Long usuaUsua) {
        this.usuaUsua = usuaUsua;
    }

    public Usuario(Long usuaUsua, String usuaEmail, String usuaPass, String usuaEsta, String usuaUsucs, Date usuaUsucd, String usuaUsums, Date usuaUsumd, long usuaConta) {
        this.usuaUsua = usuaUsua;
        this.usuaEmail = usuaEmail;
        this.usuaPass = usuaPass;
        this.usuaEsta = usuaEsta;
        this.usuaUsucs = usuaUsucs;
        this.usuaUsucd = usuaUsucd;
        this.usuaUsums = usuaUsums;
        this.usuaUsumd = usuaUsumd;
        this.usuaConta = usuaConta;
    }

    public Long getUsuaUsua() {
        return usuaUsua;
    }

    public void setUsuaUsua(Long usuaUsua) {
        this.usuaUsua = usuaUsua;
    }

    public String getUsuaEmail() {
        return usuaEmail;
    }

    public void setUsuaEmail(String usuaEmail) {
        this.usuaEmail = usuaEmail;
    }

    public String getUsuaPass() {
        return usuaPass;
    }

    public void setUsuaPass(String usuaPass) {
        this.usuaPass = usuaPass;
    }

    public String getUsuaEsta() {
        return usuaEsta;
    }

    public void setUsuaEsta(String usuaEsta) {
        this.usuaEsta = usuaEsta;
    }

    public String getUsuaUsucs() {
        return usuaUsucs;
    }

    public void setUsuaUsucs(String usuaUsucs) {
        this.usuaUsucs = usuaUsucs;
    }

    public Date getUsuaUsucd() {
        return usuaUsucd;
    }

    public void setUsuaUsucd(Date usuaUsucd) {
        this.usuaUsucd = usuaUsucd;
    }

    public String getUsuaUsums() {
        return usuaUsums;
    }

    public void setUsuaUsums(String usuaUsums) {
        this.usuaUsums = usuaUsums;
    }

    public Date getUsuaUsumd() {
        return usuaUsumd;
    }

    public void setUsuaUsumd(Date usuaUsumd) {
        this.usuaUsumd = usuaUsumd;
    }

    public boolean   getUsuaBlock() {
        return usuaBlock;
    }

    public void setUsuaBlock(boolean usuaBlock) {
        this.usuaBlock = usuaBlock;
    }

    public long getUsuaConta() {
        return usuaConta;
    }

    public void setUsuaConta(long usuaConta) {
        this.usuaConta = usuaConta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuaUsua != null ? usuaUsua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuaUsua == null && other.usuaUsua != null) || (this.usuaUsua != null && !this.usuaUsua.equals(other.usuaUsua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.sisc.agenda.entidades.Usuario[ usuaUsua=" + usuaUsua + " ]";
    }

    public List<Auditusu> getListaAuditores() {
        return listaAuditores;
    }

    @XmlTransient
    public void setListaAuditores(List<Auditusu> listaAuditores) {
        this.listaAuditores = listaAuditores;
    }
    
}
