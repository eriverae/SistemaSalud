/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb;
//package com.acme.sisc.agenda;

import com.acme.sisc.agenda.ejb.facade.FacadeAgenda;
import com.acme.sisc.agenda.ejb.facade.FacadeMedicoEps;
import com.acme.sisc.agenda.entidades.Agenda;
import com.acme.sisc.agenda.exceptions.AgendaException;
import com.acme.sisc.agenda.shared.IAgendaLocal;
import com.acme.sisc.agenda.shared.IAgendaRemote;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class SessionBeanAgendaMedico implements  IAgendaLocal,IAgendaRemote{
    
    Logger _log = Logger.getLogger(this.getClass().getName());
    
    @EJB
    FacadeMedicoEps facadeMedicoEps;       
     
    @EJB
    FacadeAgenda    facadeAgenda;
    
   
      @Override
    public List<Agenda> consultaAgendaMedico(long idMedico, Date fechaInicial, Date fechaFinal) throws AgendaException {
        try{
            _log.log(Level.INFO, "CONSULTADO AGENDA: "+idMedico);
           return facadeAgenda.consultarAgendasMedico(idMedico);
        }catch(NullPointerException e){
           return null;
        }
    }

    @Override
    public String consultarCitasAgendaMedico(String idAgenda, String fechaCita)throws  AgendaException {
        return "consultando citas de medico: "+idAgenda+" fechaCita: "+fechaCita;
    }

    @Override
    public boolean insertarAgenda(long idMedico,long idEps,List<Agenda> agendas) throws  AgendaException{
       try{
           for(Agenda agenda:agendas){
              /**
               * Conultar Obj persona eps para medico
               */
               agenda.setMedicoEps(facadeMedicoEps.consultarMedicoEps(idMedico, idEps));
               if(facadeAgenda.insertarAgenda(agenda)){
                   _log.log(Level.INFO, "NO SE INSERTO AGENDA "+agenda);
               }
           }
           return true;
       }catch(NullPointerException e){
           return false;
       }
    }

  

   
}
