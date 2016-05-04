/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.dto;

import java.io.Serializable;

/**
 *
 * @author BryanCFz-user
 */
public class RespuestaCita implements Serializable{
    private String mensajer;
    private String estador;

    public String getEstador() {
        return estador;
    }

    public void setEstador(String estador) {
        this.estador = estador;
    }

    public String getMensajer() {
        return mensajer;
    }

    public void setMensajer(String mensajer) {
        this.mensajer = mensajer;
    }

    
}
