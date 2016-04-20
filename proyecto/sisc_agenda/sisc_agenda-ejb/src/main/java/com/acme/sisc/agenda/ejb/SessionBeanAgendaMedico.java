/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb;
//package com.acme.sisc.agenda;

import com.acme.sisc.agenda.constant.CodesResponse;
import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.dto.DiaAgenda;
import com.acme.sisc.agenda.dto.ErrorObjSiscAgenda;
import com.acme.sisc.agenda.dto.GeneralResponse;
import com.acme.sisc.agenda.dto.RequestCrearAgenda;
import com.acme.sisc.agenda.ejb.facade.FacadeAgenda;
import com.acme.sisc.agenda.ejb.facade.FacadeCita;
import com.acme.sisc.agenda.ejb.facade.FacadeMedico;
import com.acme.sisc.agenda.ejb.facade.FacadeMedicoEps;
import com.acme.sisc.agenda.entidades.Agenda;
import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.PersonaEps;
import com.acme.sisc.agenda.exceptions.AgendaException;
import com.acme.sisc.agenda.shared.IAgendaLocal;
import com.acme.sisc.agenda.shared.IAgendaRemote;
import com.acme.sisc.agenda.util.AgendaUtil;
import java.util.ArrayList;
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
public class SessionBeanAgendaMedico implements IAgendaLocal, IAgendaRemote {

    Logger _log = Logger.getLogger(this.getClass().getName());

    @EJB
    FacadeMedicoEps facadeMedicoEps;

    @EJB
    FacadeAgenda facadeAgenda;

    @EJB
    FacadeMedico facadeMedico;

    @EJB
    FacadeCita facadeCita;

    @Override
    public List<Agenda> consultaAgendaMedico(long idMedico, Date fechaInicial, Date fechaFinal) throws AgendaException {
        try {
            _log.log(Level.INFO, "CONSULTADO AGENDA: " + idMedico);
            return facadeAgenda.consultarAgendasMedico(idMedico);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public String consultarCitasAgendaMedico(String idAgenda, String fechaCita) throws AgendaException {
        return "consultando citas de medico: " + idAgenda + " fechaCita: " + fechaCita;
    }

    @Override
    public boolean insertarAgenda(long idMedico, long idEps, List<Agenda> agendas) throws AgendaException {
        try {
            for (Agenda agenda : agendas) {
                /**
                 * Conultar Obj persona eps para medico
                 */
                agenda.setMedicoEps(facadeMedicoEps.consultarMedicoEps(idMedico, idEps));
                if (facadeAgenda.insertarAgenda(agenda)) {
                    _log.log(Level.INFO, "NO SE INSERTO AGENDA " + agenda);
                }
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public List<PersonaEps> consutarEpsMedico(long idMedico) {
        try {
            return facadeMedicoEps.consultarEpsMedico(idMedico);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public GeneralResponse insertarAgenda(RequestCrearAgenda request) {

        GeneralResponse response = new GeneralResponse();
        Agenda agenda;
        List<Cita> citasAgenda;
        _log.warning(">> "+request.getFechaInicio() + " " + request.getHoraInicio());
        _log.warning(">> "+request.getFechaFinal() + " " + request.getHoraFinal());
        Date fechaHoraInicioCita = AgendaUtil.parserStringToDate(
                request.getFechaInicio() + " " + request.getHoraInicio(),
                WebConstant.DATE_FORMAT_CITA);

        Date fechaHoraFinCita = AgendaUtil.parserStringToDate(
                request.getFechaFinal() + " " + request.getHoraFinal(),
                WebConstant.DATE_FORMAT_CITA);
        try {

            List<Cita> listCitasConflicto = facadeCita.validarCitasAgendadasMedico(request.getIdMedico(), fechaHoraInicioCita, fechaHoraFinCita);

            if (listCitasConflicto == null) {
                
                
                
                agenda = new Agenda();
                
                
                agenda.setHoraBloqueinicio(new Date(fechaHoraInicioCita.getTime()));
                agenda.setHoraBloqueFin(new Date(fechaHoraFinCita.getTime()));
                
                citasAgenda = new ArrayList<Cita>();
                agenda.setCiudad("CIUDAD");
                agenda.setDireccion("DIRECCION");
                agenda.setLocalidad("LO CALIDAD ");
                agenda.setNumeroConsultorio(201);
                agenda.setTiempoMinutosXCita(request.getCantidadMinutosXCita());
                agenda.setEstadoDiponible(CodesResponse.AGENDA_DISPONIBLE.value());
                
                agenda.setMedicoEps(facadeMedicoEps.consultarMedicoEpsXId(request.getIdPersonaEps()));
                
               
                response.setCodigoRespuesta(CodesResponse.SUCCESS.value());

                if (request.getSemana() != null && request.getSemana().getListaDias() != null) {
                    _log.log(Level.WARNING," 1");
                    List<DiaAgenda> diasAgenda = request.getSemana().getListaDias();
                    for (DiaAgenda dia : diasAgenda) {
                       
                        if (dia.isIncluir()) {
                             _log.log(Level.WARNING," 2");
                             
                            List<Date> listFechasCistas = AgendaUtil.devolverFechasXdiaDeLaSemana( new Date(agenda.getHoraBloqueinicio().getTime()), new Date(agenda.getHoraBloqueFin().getTime()), dia.getNumeroDia());
//
                            for (Date fechaCita : listFechasCistas) {
                                
                                
                                
                                Date fechaAux = new Date(fechaCita.getTime());
                                fechaAux.setHours(fechaHoraFinCita.getHours());
                                fechaAux.setMinutes(fechaHoraFinCita.getMinutes());
                                fechaAux.setSeconds(fechaHoraFinCita.getSeconds());
                                    
                                
                                 _log.log(Level.WARNING," 3: "+fechaCita.toString()+" "+fechaAux.toString());
                                
                                while (fechaCita.getTime() <= fechaAux.getTime()) {
                                        Cita cita=new Cita();
                                        cita.setEstadoPacienteAtendido(false);
                                        cita.setHoraInicio(fechaCita);
                                         _log.log(Level.WARNING, "CITA: "+fechaCita.toString()+" "+fechaAux.toString());
                                        fechaCita.setTime(fechaCita.getTime()+(agenda.getTiempoMinutosXCita()*60000));
                                        cita.setHoraFin(new Date(fechaCita.getTime()));
                                        cita.setFechaPaciente(fechaCita);
                                        cita.setValor(0);
                                        cita.setAgenda(agenda);
                                        citasAgenda.add(cita);
                                       
                                }
                            }

                        }
                    }
                    
                     _log.log(Level.SEVERE, "Antes de insertar:["+ agenda.getHoraBloqueinicio().toString()+" >> "+ agenda.getHoraBloqueFin().toString());
               agenda.setCitasAgenda(citasAgenda);
                if(facadeAgenda.insertarAgenda(agenda)){
                    
                           _log.log(Level.WARNING,"INSERTO EN BD");
                 }else{
                           _log.log(Level.WARNING," no INSERTO EN BD");
                 }
             
                       
                       
                }

            }

        } catch (Exception e) {
            response.setCodigoRespuesta(CodesResponse.ERROR.value());
            _log.log(Level.SEVERE, "ERROR EN  SessionBeanAgendaMedico.insertarAgenda", e);

        }
        return response;
    }

}
