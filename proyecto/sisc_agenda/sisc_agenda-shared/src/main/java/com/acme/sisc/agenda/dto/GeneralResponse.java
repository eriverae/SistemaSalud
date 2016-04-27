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
public class GeneralResponse implements Serializable {
    
    private String codigoRespuesta;
    private ErrorObjSiscAgenda error;
    private Object objectResponse;

    public ErrorObjSiscAgenda getError() {
        return error;
    }

    public void setError(ErrorObjSiscAgenda error) {
        this.error = error;
    }

    public Object getObjectResponse() {
        return objectResponse;
    }

    public void setObjectResponse(Object objectResponse) {
        this.objectResponse = objectResponse;
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    
    
    
    
}
