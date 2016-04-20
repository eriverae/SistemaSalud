/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.dto;

import java.io.Serializable;

/**
 *
 * @author desarrollador
 */
public  class ErrorObjSiscAgenda implements Serializable{
    
    private String codigoError;
    private String mensajeError;
    private Object objError;

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Object getObjError() {
        return objError;
    }

    public void setObjError(Object objError) {
        this.objError = objError;
    }
    
    
    
    
}
