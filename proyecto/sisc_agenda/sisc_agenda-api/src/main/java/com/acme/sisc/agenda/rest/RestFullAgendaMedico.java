/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.rest;

import com.acme.sisc.agenda.dto.GeneralResponse;
import com.acme.sisc.agenda.dto.RequestCrearAgenda;
import com.acme.sisc.agenda.dto.RequestOpcionesCitaAgendaMedico;
import com.acme.sisc.agenda.dto.ResponseAgendaMedico;
import com.acme.sisc.agenda.entidades.Agenda;
import com.acme.sisc.agenda.entidades.PersonaEps;
import com.acme.sisc.agenda.exceptions.AgendaException;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.acme.sisc.agenda.shared.IAgendaLocal;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ws.rs.POST;

import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;

/**
 *
 * @author desarrollador
 */
@Path("medico/agenda")
@RequestScoped
public class RestFullAgendaMedico {

    private final static Logger _log = Logger.getLogger(RestFullAgendaMedico.class.getName());

    @Context
    private UriInfo context;
    @EJB
    private IAgendaLocal agenda;

    
    /**
     * Rest que retorna objeto con una descripcion de las citas 
     * @param idMedico
     * @return 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idMedico}")
    public ResponseAgendaMedico consultaAgendaMedico(@PathParam("idMedico") Long idMedico) {

        try {
          return  agenda.consultarAgendaMesMedico(idMedico);
        } catch (Exception ex) {
            _log.log(Level.SEVERE, "RestFullAgendaMedico.consultaAgendaMedico", ex);
            return null;
        }
    }

    /**
     * Servicio REST que consulta lista de eps de un medico
     * @param idMedico
     * @return 
     */
    @GET
    @Path("/{idMedico}/listaEps")
    @Produces(MediaType.APPLICATION_JSON)

    public List<PersonaEps> consultarListaEps(@PathParam("idMedico") long idMedico) {
        return agenda.consutarEpsMedico(idMedico);
    }
    
    /**
     * Servicio Rest que crea una nueva agenda.
     * @param request
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/nuevaAgenda")
    public GeneralResponse insertarAgenda(RequestCrearAgenda request) {
        return agenda.insertarAgenda(request);
    }
     @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/opcionesCita")
    /*http://localhost:8080/SiscAgenda/api/medico/agenda/opcionesCita*/      
    public GeneralResponse opcionesCitaAgendaMedico(RequestOpcionesCitaAgendaMedico request){
        return agenda.opcionesCitaAgendaMedico(request);
    }
}
