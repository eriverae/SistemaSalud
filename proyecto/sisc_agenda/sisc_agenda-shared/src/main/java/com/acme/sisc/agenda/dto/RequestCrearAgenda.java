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
public class RequestCrearAgenda  implements Serializable{
    
    private String  fechaInicio;
    private String  fechaFinal;
    private Semana  semana;
    private String  horaInicio;
    private String  horaFinal;
    private Integer cantidadMinutosXCita;
    private Long    idPersonaEps;
    private Long    idMedico;
    private String  especialidadCita;
   

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Semana getSemana() {
        return semana;
    }

    public void setSemana(Semana semana) {
        this.semana = semana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Integer getCantidadMinutosXCita() {
        return cantidadMinutosXCita;
    }

    public void setCantidadMinutosXCita(Integer cantidadMinutosXCita) {
        this.cantidadMinutosXCita = cantidadMinutosXCita;
    }

    public Long getIdPersonaEps() {
        return idPersonaEps;
    }

    public void setIdPersonaEps(Long idPersonaEps) {
        this.idPersonaEps = idPersonaEps;
    }

    public String getEspecialidadCita() {
        return especialidadCita;
    }

    public void setEspecialidadCita(String especialidadCita) {
        this.especialidadCita = especialidadCita;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }
    
    
    
    
}
