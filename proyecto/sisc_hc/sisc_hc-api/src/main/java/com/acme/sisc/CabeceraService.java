/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.common.exceptions.CustomException;
import com.acme.sisc.common.exceptions.CustomRunTimeException;
import com.acme.sisc.sisc_hc.shared.ICabeceraFacadeRemote;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeRemote;
import java.util.HashMap;
import java.util.List;
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
@Path("/cabecera/")
public class CabeceraService {
    
    @EJB
    ICabeceraFacadeRemote facadecabecera;
    
    @GET
    @Produces({"application/json"})
    public Response GetMedicamentosALL(@QueryParam("idcita") String idcita) 
            throws CustomException, CustomRunTimeException{
        try{

                HashMap m = new HashMap();
                m.put("data", facadecabecera.findByCita(Long.parseLong(idcita)));
                return Response
                .status(200)
                .entity(m)
                .build();
            
        }catch(Exception ex){
            throw new CustomException(Response.Status.BAD_REQUEST.getStatusCode(), 503, "Error accediendo a los datos de cabecera... ");
        }
    }
    
}
