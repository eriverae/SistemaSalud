/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Incapacidad;
import com.acme.sisc.sisc_hc.exceptions.CustomException;
import com.acme.sisc.sisc_hc.shared.IIncapacidadFacadeRemote;
import java.util.HashMap;
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
@Path("/incapacidad/")
public class IncapacidadService {
    private final static Logger log = Logger.getLogger(CirugiaService.class.getName());
    
    @EJB
    IIncapacidadFacadeRemote facadeIncapacidad;
    
    @GET
    @Produces({"application/json"})
    public Response GetIncapacidadALL(@QueryParam("idcita") String idcita) throws CustomException{
        try{
            if (idcita == null){
                return Response
                .status(200)
                .entity(facadeIncapacidad.findAll())
                .build();
            }
            else{
                HashMap m = new HashMap();
                m.put("data", facadeIncapacidad.findByCita(Long.parseLong(idcita)));
                return Response
                .status(200)
                .entity(m)
                .build();
            }
        }catch(Exception ex){
            log.log(Level.SEVERE, "IncapacidadService->GetIncapacidadALL... ", ex);
            throw new CustomException(503, "Error accediendo los datos de la incapacidad... ");
        }
    }
    
    
    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response PostIncapacidad(Incapacidad incapacidad) throws CustomException{
        try{
            facadeIncapacidad.addIncapacidad(incapacidad);
            return Response
                .status(200)
                .entity("{}")
                .build();
        }catch(Exception ex){
            log.log(Level.SEVERE, "IncapacidadService->PostIncapacidad... ", ex);
            throw new CustomException(503, "Error adicionando los datos de la incapacidad... ");
        }
    }
}
