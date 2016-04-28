/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.CitaExamen;
import com.acme.sisc.sisc_hc.exceptions.CustomException;
import com.acme.sisc.sisc_hc.shared.IExamenFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IExamenFacadeRemote;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author GABRIEL
 */
@Path("/examen/")
public class ExamenService {
    private final static Logger log = Logger.getLogger(CirugiaService.class.getName());
    
    @EJB
    IExamenFacadeRemote facadeExamen;
    
    @GET
    @Produces({"application/json"})
    public Response GetExamensALL(@QueryParam("idcita") String idcita) throws CustomException{
        try{
            if (idcita == null){
                return Response
                .status(200)
                .entity(facadeExamen.findAll())
                .build();
            }
            else{
                HashMap m = new HashMap();
                m.put("data", facadeExamen.findByCita(Long.parseLong(idcita)));
                return Response
                .status(200)
                .entity(m)
                .build();
            }
        }catch(Exception ex){
            log.log(Level.SEVERE, "ExamenService->GetExamensALL... ", ex);
            throw new CustomException(503, "Error accediendo a los datos del examen... ");
        }
    }
    
    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response addExamenCita(List<CitaExamen> cita_examen) throws CustomException{
        try{
            facadeExamen.addExamenCita(cita_examen);
            return Response
                .status(200)
                .entity("{}")
                .build();
        }catch(Exception ex){
            log.log(Level.SEVERE, "ExamenService->addExamenCita... ", ex);
            throw new CustomException(503, "Error adicionando los datos del examen... ");
        }
    }
}
