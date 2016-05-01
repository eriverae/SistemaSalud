/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.rest;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.shared.ICitaLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author BryanCFz-user
 */
@Path("paciente")
//@RequestScoped
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

    @POST
    @Path("/cancelarCita")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String cancelarUnaCitaDePaciente(Cita cita) {
        logi.log(Level.WARNING,  "Request para cancelar la Cita con id {0}", cita.getIdCita());
        return facadeCita.cancelarCita(cita); 
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idCita}/consultarCita")
    public Cita consultarCitaId(
            @PathParam("idCita") Long idCita) {
        logi.log(Level.WARNING, "Obtener la cita segun su id: " + idCita);
        return facadeCita.find(idCita);
    }
    

    
    /**
     * Crear y Modificar una Cita
     * @param cita
     * @return 
     */ 
/*    @POST
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
    }*/

}
