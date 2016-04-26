/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author desarrollador
 */
public class ResponseExitoCrearAgenda implements Serializable{
    
    private String mensaje;
    private int totalDeCitasAgendas;
    private  Date fechaInicialAgenda;
    private Date fechaFinalAgenda;
    private String dias;
 
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getTotalDeCitasAgendas() {
        return totalDeCitasAgendas;
    }

    public void setTotalDeCitasAgendas(int totalDeCitasAgendas) {
        this.totalDeCitasAgendas = totalDeCitasAgendas;
    }

    public Date getFechaInicialAgenda() {
        return fechaInicialAgenda;
    }

    public void setFechaInicialAgenda(Date fechaInicialAgenda) {
        this.fechaInicialAgenda = fechaInicialAgenda;
    }

    public Date getFechaFinalAgenda() {
        return fechaFinalAgenda;
    }

    public void setFechaFinalAgenda(Date fechaFinalAgenda) {
        this.fechaFinalAgenda = fechaFinalAgenda;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(List<DiaAgenda> dias) {
        if(dias!=null){
            this.dias="";
            for(DiaAgenda dia:dias){
                if(dia.isIncluir()){
                    this.dias +=dia.getDia()+", ";
                }
            }
            this.dias=this.dias.length()>2?this.dias.substring(0, this.dias.length()-2):this.dias;
        }else{
            this.dias="";
        }
        
    }
    
    
    
}
