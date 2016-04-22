/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.rest;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.errorhandling.ErrorMessage;
import com.acme.sisc.agenda.exceptions.CitaException;
import com.acme.sisc.agenda.shared.ICitaLocal;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author BryanCFz-user
 */
@Path("paciente")
@RequestScoped
public class RestFullCitaPaciente {

    private final static Logger logi = Logger.getLogger(RestFullAgendaMedico.class.getName());

        @Context
    private UriInfo context;
    
    @EJB
    ICitaLocal facadeCita;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idPaciente}/listaCitas")
    public List<Cita> CitasDePaciente(
            @PathParam("idPaciente") Long idPaciente) {

        return facadeCita.listaCitasPaciente(idPaciente);
    }

    @DELETE
    @Path("{id}")
    public void eliminarUnaCitaDePaciente(@PathParam("id") Long id) {
        logi.log(Level.FINE, "Request para eliminar cita del paciente con id {0}", id);
        facadeCita.remove(id);
    }

    
    //Crear y Modificar una Cita 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarCitaPaciente(Cita cita) {

        if (cita.getIdCita() == null) {
            try {
                facadeCita.crearCita(cita);
            } catch (CitaException ex) {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setCode(ex.getErrorCode());
                errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
                errorMessage.setMessage(ex.getMessage());
                StringWriter errorStackTrace = new StringWriter();
                ex.printStackTrace(new PrintWriter(errorStackTrace));
                errorMessage.setDeveloperMessage(errorStackTrace.toString());
                errorMessage.setLink("www.banco.com/soporte");

                return Response.status(errorMessage.getStatus())
                        .entity(errorMessage)
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
        } else {
            facadeCita.modificarCliente(cita);
        }
        return Response.ok().build();
    }

}