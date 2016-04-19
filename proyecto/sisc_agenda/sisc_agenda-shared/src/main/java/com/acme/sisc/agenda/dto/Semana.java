/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author desarrollador
 */
public class Semana implements Serializable{
    
    private List<DiaAgenda> listaDias;
    private String numeroDiasSelecionado;

    public List<DiaAgenda> getListaDias() {
        return listaDias;
    }

    public void setListaDias(List<DiaAgenda> listaDias) {
        this.listaDias = listaDias;
    }

    public String getNumeroDiasSelecionado() {
        return numeroDiasSelecionado;
    }

    public void setNumeroDiasSelecionado(String numeroDiasSelecionado) {
        this.numeroDiasSelecionado = numeroDiasSelecionado;
    }
    

   
    
}
