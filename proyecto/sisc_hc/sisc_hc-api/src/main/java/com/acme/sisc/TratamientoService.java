/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.CitaTratamiento;
import com.acme.sisc.common.exceptions.CustomException;
import com.acme.sisc.common.exceptions.CustomRunTimeException;
import com.acme.sisc.sisc_hc.shared.ITratamientoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.ITratamientoFacadeRemote;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("/tratamiento/")
public class TratamientoService {
    
    @EJB
    ITratamientoFacadeRemote facadeTratamiento;
    
    @GET
    @Produces({"application/json"})
    public Response GetTratamientosALL(@QueryParam("idcita") String idcita) 
            throws CustomException, CustomRunTimeException{
        try{
            if (idcita == null){
                HashMap m = new HashMap();
                m.put("data", facadeTratamiento.findAll());
                return Response
                .status(200)
                .entity(m)
                .build();
            }
            else{
                HashMap m = new HashMap();
                m.put("data", facadeTratamiento.findByCita(Long.parseLong(idcita)));
                return Response
                .status(200)
                .entity(m)
                .build();
            }
        }catch(Exception ex){
            throw new CustomException(Response.Status.BAD_REQUEST.getStatusCode(), 503, "Error accediendo a los datos del tratamiento ... ");
        }
        
    }
    
    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response addMedicamentosCita(List<CitaTratamiento> cita_tratamiento) 
            throws CustomException, CustomRunTimeException{
        try{
            facadeTratamiento.addTratamientoCita(cita_tratamiento);
            return Response
                .status(200)
                .entity("{}")
                .build();
        }catch(Exception ex){
            throw new CustomException(Response.Status.BAD_REQUEST.getStatusCode(), 503, "Error adicionando los datos del tratamiento... ");
        }
    }
    
    @DELETE
    @Produces({"application/json"})
    public Response deleteTratamientoCita(
            @QueryParam("idcita") String idcita, 
            @QueryParam("idtratamiento") String idtratamiento
    ) throws CustomException, CustomRunTimeException{
        try{
            if (idcita == null || idtratamiento == null){
                HashMap m = new HashMap();
                m.put("mesage", "Property not found");
                m.put("status", 400);
                return Response.status(400).entity(m).build();
            }
            else{
                return Response.status(204)
                        .entity(facadeTratamiento.deleteTratamientoCita(
                                Long.parseLong(idcita), 
                                Long.parseLong(idtratamiento))).build();
            }
        }catch(Exception ex){
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 503, 
                    "Error eliminando los datos del tratamiento... ");
        }
    }
    
}
