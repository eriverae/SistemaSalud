/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.CitaExamen;
import com.acme.sisc.sisc_hc.shared.IExamenFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IExamenFacadeRemote;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author GABRIEL
 */
@Path("/examen/")
public class ExamenService {
    @EJB
    IExamenFacadeRemote facadeExamen;
    
    @GET
    @Produces({"application/json"})
    public Response GetExamensALL(){
        return Response
            .status(200)
            .entity(facadeExamen.findAll())
            .build();
    }
    
    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response addExamenCita(List<CitaExamen> cita_examen){
        facadeExamen.addExamenCita(cita_examen);
        return Response
            .status(200)
            .entity("{}")
            .build();
    }
}
