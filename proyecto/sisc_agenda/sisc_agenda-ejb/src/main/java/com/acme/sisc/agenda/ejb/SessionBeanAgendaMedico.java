/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb;

import com.acme.sisc.agenda.shared.IAgendaLocal;
import com.acme.sisc.agenda.shared.IAgendaRemote;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class SessionBeanAgendaMedico implements  IAgendaLocal,IAgendaRemote{

    @Override
    public String consultaAgendaMedico(String idMedico) {
         return "consultando agenda de medico: "+idMedico;
        
    }

    @Override
    public String consultarCitasAgendaMedico(String idAgenda, String fechaCita) {
        return "consultando citas de medico: "+idAgenda+" fechaCita: "+fechaCita;
    }

   
}
