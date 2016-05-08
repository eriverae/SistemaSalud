/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.common.exceptions.CustomException;
import com.acme.sisc.common.exceptions.CustomRunTimeException;
import com.acme.sisc.sisc_hc.shared.IHistoriaFacadeRemote;
import com.acme.sisc.sisc_hc.shared.IHistoriaFacadeLocal;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author GABRIEL
 */
@Path("/historia/")
public class HistoriaService {
    @EJB
    IHistoriaFacadeRemote facadeHistoria;
    
    @GET
    @Produces({"application/json"})
    public Response GetHistoriaALL(@QueryParam("idcita") String idcita) 
            throws CustomException, CustomRunTimeException{
        try{
            HashMap m = new HashMap();
            if (idcita == null){
                m.put("data", facadeHistoria.findAll());
                return Response
                .status(200)
                .entity(m)
                .build();
            }
            else{
                m.put("data", facadeHistoria.findByCita(Long.parseLong(idcita)));
                return Response
                .status(200)
                .entity(m)
                .build();
            }
        }catch(Exception ex){
            throw new CustomException(Response.Status.BAD_REQUEST.getStatusCode(), 503, "Error accediendo a los datos de la historia... ");
        }
    }
    
    @GET
    @Produces({"application/json"})
    @Path("/lastcita/")
    public Response GetLastHistory() throws CustomException, CustomRunTimeException{
        try{
            HashMap m = new HashMap();
            m.put("data", facadeHistoria.find_last_cita());
            return Response
            .status(200)
            .entity(m)
            .build();
        }catch(Exception ex){
            throw new CustomException(Response.Status.BAD_REQUEST.getStatusCode(), 503, "Error accediendo a los datos de la ultima historia... ");
        }
    }
}
