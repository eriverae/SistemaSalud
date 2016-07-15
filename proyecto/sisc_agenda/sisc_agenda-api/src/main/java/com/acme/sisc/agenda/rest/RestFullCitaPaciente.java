/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.rest;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.dto.CitaDisponible;
import com.acme.sisc.agenda.dto.GeneralResponse;
import com.acme.sisc.agenda.dto.ResponseCitasDisponiblesMedico;
import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.shared.ICitaLocal;
import com.acme.sisc.agenda.shared.IUtilitariosAgendaLocal;
import com.acme.sisc.agenda.util.AgendaUtil;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author BryanCFz-user
 */
@Path("paciente")

public class RestFullCitaPaciente {

    private final static Logger _log = Logger.getLogger(RestFullAgendaMedico.class.getName());

    @Context
    private UriInfo context;

    @EJB
    ICitaLocal facadeCita;

    @EJB
    IUtilitariosAgendaLocal facadeUtilitariosAgenda;

    /**
     * Servicio rest que se comunica con el ejb para consultar las ultimas citas
     * agendadas por un paciente
     *
     * @param idPaciente
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idPaciente}/listaCitas")
    public Object[] CitasDePaciente(
            @PathParam("idPaciente") Long idPaciente) {
        List<Cita> lista = facadeCita.listaCitasPendientePaciente(idPaciente);
        if (lista != null) {
            return lista.toArray();
        } else {
            return null;
        }
    }

    /**
     * Servicio rest que se comunica con el ejb para consultar el hitorial de
     * las citas agendadas por un paciente
     *
     * @param idPaciente
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idPaciente}/listaCitasHistorialEPS")
    public Object[] CitasPacienteHistorial(
            @PathParam("idPaciente") Long idPaciente) {
        List<Cita> lista = facadeCita.listaCitasHistorialPacienteEPS(idPaciente);
        //return facadeCita.listaCitasPaciente(idPaciente);
        return lista.toArray();
    }

    /**
     * Servicio rest que se comunica con el ejb para cancelar un cita del
     * paciente
     *
     * @param idCita
     * @return
     */
    @POST
    @Path("/{idCita}/cancelarCita")
    @Produces(MediaType.APPLICATION_JSON)
    public GeneralResponse cancelarUnaCitaDePaciente(
            @PathParam("idCita") String idCita
    ) {
        _log.log(Level.WARNING, "Request para cancelar la Cita con id {0}", idCita);
        Long idCitaA = Long.valueOf(idCita);
        return facadeCita.cancelarCita1(idCitaA);
    }

    /**
     * Servicio rest que se comunica con el ejb para consultar informacion
     * completa de una cita.
     *
     * @param idCita
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idCita}/consultarCita")
    public Cita consultarCitaId(
            @PathParam("idCita") Long idCita) {
        _log.log(Level.WARNING, "Obtener la cita segun su id: {0}", idCita);
        return facadeCita.find(idCita);
    }

    /**
     * Servicio rest que se comunica con el ejb para retornar las especialidades
     * de un medico
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/especialidadesMedicosEPS")
    public Object[] especialidadesMedicos() {
        _log.log(Level.WARNING, "SERVICIOrEST: ConsultarEspecialidades\n\n");
        return facadeUtilitariosAgenda.especialidadesEps().toArray();
    }

    /**
     * Servicio rest que se comunica con el ejb para consultar las citas
     * disponibles para un paciente filtrado por especilidad y fecha
     *
     * @param idEspecialidad
     * @param fechaBusqueda
     * @param idPaciente
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citasDisponibles")
    public List<ResponseCitasDisponiblesMedico> buscarCitasDisponiblesPaciente(
            @QueryParam("idEspecialidad") long idEspecialidad,
            @DefaultValue("null")
            @QueryParam("fechaBusqueda") String fechaBusqueda,
            @QueryParam("idPaciente") long idPaciente) {

        Map<Long, ResponseCitasDisponiblesMedico> response = new HashMap<>();
        List<ResponseCitasDisponiblesMedico> list = null;
        try {
            List<Cita> listCitas;
            if (fechaBusqueda.equals("null")) {
                listCitas = facadeCita.buscarCitasDisponiblesPaciente(idEspecialidad, idPaciente, null);
            } else {
                listCitas = facadeCita.buscarCitasDisponiblesPaciente(idEspecialidad, idPaciente, fechaBusqueda);
            }
            for (Cita cita : listCitas) {

                CitaDisponible e = new CitaDisponible();
                e.setCiudad(cita.getAgenda().getCiudad());
                e.setDireccion(cita.getAgenda().getDireccion());
                e.setEstadoCita(cita.getEstadoCita());
                e.setFecha(AgendaUtil.parserDateToString(cita.getHoraInicio(), WebConstant.SIMPLE_DATE_FORMAT));
                e.setHoraInicio(AgendaUtil.parserDateToString(cita.getHoraInicio(), WebConstant.SIMPLE_DATE_FORMAT_HOUR));
                e.setHoraFin(AgendaUtil.parserDateToString(cita.getHoraFin(), WebConstant.SIMPLE_DATE_FORMAT_HOUR));
                e.setLocalidad(cita.getAgenda().getLocalidad());
                e.setNumeroConsultorio(cita.getAgenda().getNumeroConsultorio());
                e.setIdCita(cita.getIdCita());

                if (response.get(cita.getAgenda().getMedicoEps().getPersona().getIdPersona()) != null) {
                    response.get(cita.getAgenda().getMedicoEps().getPersona().getIdPersona()).getCitasDisponibles().add(e);
                } else {
                    ResponseCitasDisponiblesMedico responseCitasDisponiblesMedico = new ResponseCitasDisponiblesMedico();
                    responseCitasDisponiblesMedico.setCorreoElectronico(cita.getAgenda().getMedicoEps().getPersona().getCorreoElectronico());
                    responseCitasDisponiblesMedico.setFotografia(cita.getAgenda().getMedicoEps().getPersona().getFotografia());
                    responseCitasDisponiblesMedico.setIdMedico(cita.getAgenda().getMedicoEps().getPersona().getIdPersona());
                    responseCitasDisponiblesMedico.setCiudad(cita.getAgenda().getCiudad());
                    responseCitasDisponiblesMedico.setLocalidad(cita.getAgenda().getLocalidad());
                    responseCitasDisponiblesMedico.setDireccion(cita.getAgenda().getDireccion());
                    responseCitasDisponiblesMedico.setFechaConsulta(AgendaUtil.parserDateToString(cita.getHoraInicio(), WebConstant.SIMPLE_DATE_FORMAT));
                    responseCitasDisponiblesMedico.setNumeroConsultorio(cita.getAgenda().getNumeroConsultorio());
                    if (cita.getAgenda().getMedicoEps().getPersona().getNombres() != null) {
                        responseCitasDisponiblesMedico.setNombreCompleto(cita.getAgenda().getMedicoEps().getPersona().getApellidos() != null
                                ? cita.getAgenda().getMedicoEps().getPersona().getNombres() + " " + cita.getAgenda().getMedicoEps().getPersona().getApellidos()
                                : cita.getAgenda().getMedicoEps().getPersona().getNombres());
                    } else {
                        responseCitasDisponiblesMedico.setNombreCompleto(cita.getAgenda().getMedicoEps().getPersona().getApellidos() != null ? cita.getAgenda().getMedicoEps().getPersona().getApellidos() : "");
                    }
                    responseCitasDisponiblesMedico.setNumeroIdentificacion(cita.getAgenda().getMedicoEps().getPersona().getNumeroIdentificacion() + "");
                    responseCitasDisponiblesMedico.setTipoIdentificacion(cita.getAgenda().getMedicoEps().getPersona().getTipoIdentificacion().name());
                    responseCitasDisponiblesMedico.getCitasDisponibles().add(e);

                    response.put(responseCitasDisponiblesMedico.getIdMedico(), responseCitasDisponiblesMedico);
                }

            }

            list = new ArrayList<>(response.values());

        } catch (NullPointerException e) {

        }

        return list;
    }

    /**
     * Servicio rest que se comunica con el ejb para agendar una cita sobre el
     * horario disponible de un medico.
     *
     * @param idCita
     * @param idPaciente
     * @return
     */
    @POST
    @Path("/agendar/cita/{idCita}/paciente/{idPaciente}")
    @Produces(MediaType.APPLICATION_JSON)
    public GeneralResponse agendarCitaConMedico(
            @PathParam("idCita") Long idCita,
            @PathParam("idPaciente") Long idPaciente) {

        return facadeCita.agendarCita(idCita, idPaciente);
    }

}
