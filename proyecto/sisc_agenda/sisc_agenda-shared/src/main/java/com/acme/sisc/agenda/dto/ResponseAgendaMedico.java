/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desarrollador
 */
public class ResponseAgendaMedico {
    
    private String  fechaActual;
    private boolean existeAgenda;
    private List<EventoCalendarioAgenda> events;

    
    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public List<EventoCalendarioAgenda> getEvents() {
        if(events==null){
            events=new ArrayList<>();
        }
        return events;
    }

    public void setEvents(List<EventoCalendarioAgenda> events) {
        this.events = events;
    }

    public boolean isExisteAgenda() {
        return existeAgenda;
    }

    public void setExisteAgenda(boolean existeAgenda) {
        this.existeAgenda = existeAgenda;
    }
    
    
    
    
    
}
