/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Julio
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuaUsua", query = "SELECT u FROM Usuario u WHERE u.usuaUsua = :usuaUsua"),
    @NamedQuery(name = "Usuario.findByUsuaEmail", query = "SELECT u FROM Usuario u WHERE u.usuaEmail = :usuaEmail"),
    @NamedQuery(name = "Usuario.findByUsuaPass", query = "SELECT u FROM Usuario u WHERE u.usuaPass = :usuaPass"),
    @NamedQuery(name = "Usuario.findByUsuaEsta", query = "SELECT u FROM Usuario u WHERE u.usuaEsta = :usuaEsta"),
    @NamedQuery(name = "Usuario.findByUsuaUsucs", query = "SELECT u FROM Usuario u WHERE u.usuaUsucs = :usuaUsucs"),
    @NamedQuery(name = "Usuario.findByUsuaUsucd", query = "SELECT u FROM Usuario u WHERE u.usuaUsucd = :usuaUsucd"),
    @NamedQuery(name = "Usuario.findByUsuaUsums", query = "SELECT u FROM Usuario u WHERE u.usuaUsums = :usuaUsums"),
    @NamedQuery(name = "Usuario.findByUsuaUsumd", query = "SELECT u FROM Usuario u WHERE u.usuaUsumd = :usuaUsumd"),
    @NamedQuery(name = "Usuario.findByUsuaBlock", query = "SELECT u FROM Usuario u WHERE u.usuaBlock = :usuaBlock"),
    @NamedQuery(name = "Usuario.findByUsuaPersn", query = "SELECT u FROM Usuario u WHERE u.usuaPersn = :usuaPersn"),
    @NamedQuery(name = "Usuario.findByUsuaConta", query = "SELECT u FROM Usuario u WHERE u.usuaConta = :usuaConta")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
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
    private String usuaBlock;
    @Basic(optional = false)
    @Column(name = "usua_persn")
    private long usuaPersn;
    @Basic(optional = false)
    @Column(name = "usua_conta")
    private long usuaConta;

    public Usuario() {
    }

    public Usuario(Long usuaUsua) {
        this.usuaUsua = usuaUsua;
    }

    public Usuario(Long usuaUsua, String usuaEmail, String usuaPass, String usuaEsta, String usuaUsucs, Date usuaUsucd, String usuaUsums, Date usuaUsumd, long usuaPersn, long usuaConta) {
        this.usuaUsua = usuaUsua;
        this.usuaEmail = usuaEmail;
        this.usuaPass = usuaPass;
        this.usuaEsta = usuaEsta;
        this.usuaUsucs = usuaUsucs;
        this.usuaUsucd = usuaUsucd;
        this.usuaUsums = usuaUsums;
        this.usuaUsumd = usuaUsumd;
        this.usuaPersn = usuaPersn;
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

    public String getUsuaBlock() {
        return usuaBlock;
    }

    public void setUsuaBlock(String usuaBlock) {
        this.usuaBlock = usuaBlock;
    }

    public long getUsuaPersn() {
        return usuaPersn;
    }

    public void setUsuaPersn(long usuaPersn) {
        this.usuaPersn = usuaPersn;
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
    
}
