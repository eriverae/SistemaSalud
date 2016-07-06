/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.common.exceptions.CustomException;
import com.acme.sisc.common.exceptions.CustomRunTimeException;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeRemote;
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
@Path("/medicamento/")
public class MedicamentoService {
    
    @EJB
    IMedicamentoFacadeRemote facadeMedicamento;
    
    @GET
    @Produces({"application/json"})
    public Response GetMedicamentosALL(@QueryParam("idcita") String idcita) 
            throws CustomException, CustomRunTimeException{
        try{
            if (idcita == null){
                HashMap m = new HashMap();
                m.put("data", facadeMedicamento.findAll());
                return Response
                .status(200)
                .entity(m)
                .build();
            }
            else{
                HashMap m = new HashMap();
                m.put("data", facadeMedicamento.findByCita(Long.parseLong(idcita)));
                return Response
                .status(200)
                .entity(m)
                .build();
            }
        }catch(Exception ex){
            throw new CustomException(Response.Status.BAD_REQUEST.getStatusCode(), 503, "Error accediendo a los datos del medicamento... ");
        }
    }

    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response addMedicamentosCita(List<CitaMedicamento> cita_medicamento) 
            throws CustomException, CustomRunTimeException{
        try{
            return Response
                .status(200)
                .entity(facadeMedicamento.addMedicamentoCita(cita_medicamento))
                .build();
        }catch(Exception ex){
            throw new CustomException(Response.Status.BAD_REQUEST.getStatusCode(), 503, "Error adicionando los datos del medicamento... ");
        }
    }
    
    @DELETE
    @Produces({"application/json"})
    public Response deleteMedicamentoCita(
            @QueryParam("idcita") String idcita, 
            @QueryParam("idmedicamento") String idmedicamento
    ) throws CustomException, CustomRunTimeException{
        try{
            if (idcita == null || idmedicamento == null){
                HashMap m = new HashMap();
                m.put("mesage", "Property not found");
                m.put("status", 400);
                return Response.status(400).entity(m).build();
            }
            else{
                return Response.status(204)
                        .entity(facadeMedicamento.deleteMedicamentoCita(
                                Long.parseLong(idcita), 
                                Long.parseLong(idmedicamento))).build();
            }
        }catch(Exception ex){
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 503, 
                    "Error eliminando los datos del medicamento... ");
        }
    }
    
}
