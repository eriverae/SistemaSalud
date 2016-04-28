/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.shared;

import java.util.List;
import javax.ejb.Local;

import com.acme.sisc.agenda.entidades.Agenda;
import com.acme.sisc.agenda.entidades.PersonaEps;
import com.acme.sisc.agenda.exceptions.AgendaException;
import com.acme.sisc.agenda.dto.GeneralResponse;
import com.acme.sisc.agenda.dto.RequestCrearAgenda;
import com.acme.sisc.agenda.dto.ResponseAgendaMedico;


import java.util.Date;

/**
 *
 * @author desarrollador
 */
@Local
public interface IAgendaLocal {

    public List<Agenda> consultaAgendaMedico(long idMedico, Date fechaInicial, Date fechaFinal) throws AgendaException;

    public String consultarCitasAgendaMedico(String idAgenda, String fechaCita) throws AgendaException;

    public List<PersonaEps> consutarEpsMedico(long idMedico);

    public boolean insertarAgenda(long idMedico, long idEps, List<Agenda> agendas) throws AgendaException;

    public GeneralResponse insertarAgenda(RequestCrearAgenda request);
    
    public ResponseAgendaMedico consultarAgendaMesMedico(long idMedico);
}
