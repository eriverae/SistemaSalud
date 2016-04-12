/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.shared;

import com.acme.sisc.agenda.entidades.Agenda;
import com.acme.sisc.agenda.exceptions.AgendaException;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author desarrollador
 */
@Remote
public interface IAgendaRemote {
    
    public List<Agenda>  consultaAgendaMedico(long idMedico,Date fechaInicial,Date fechaFinal) throws AgendaException;
    public String  consultarCitasAgendaMedico(String idAgenda,String fechaCita)throws AgendaException;
    public boolean insertarAgenda(long idMedico,long idEps,List<Agenda> agendas)throws AgendaException;
}
