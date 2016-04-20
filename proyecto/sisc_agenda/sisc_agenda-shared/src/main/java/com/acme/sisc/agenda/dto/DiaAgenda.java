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
public class DiaAgenda implements Serializable{
    
    private String numeroDia;
    private String dia;
    private boolean incluir;

    public String getNumeroDia() {
        return numeroDia;
    }

    public void setNumeroDia(String numeroDia) {
        this.numeroDia = numeroDia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public boolean isIncluir() {
        return incluir;
    }

    public void setIncluir(boolean incluir) {
        this.incluir = incluir;
    }
    
    
}
