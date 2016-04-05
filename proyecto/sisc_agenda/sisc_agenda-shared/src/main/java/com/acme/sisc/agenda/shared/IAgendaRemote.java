/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.shared;

import javax.ejb.Remote;

/**
 *
 * @author desarrollador
 */
@Remote
public interface IAgendaRemote {
    public String consultaAgendaMedico(String idMedico);
    public String consultarCitasAgendaMedico(String idAgenda,String fechaCita);
}
