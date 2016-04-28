/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.CitaCirugia;
import com.acme.sisc.sisc_hc.exceptions.CustomException;
import com.acme.sisc.sisc_hc.shared.ICirugiaFacadeLocal;
import com.acme.sisc.sisc_hc.shared.ICirugiaFacadeRemote;
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
@Path("/cirugia/")
public class CirugiaService {
    private final static Logger log = Logger.getLogger(CirugiaService.class.getName());
    
    @EJB
    ICirugiaFacadeRemote facadeCirugia;
    
    @GET
    @Produces({"application/json"})
    public Response GetCirugiasALL(@QueryParam("idcita") String idcita) throws CustomException{
        try{
            if (idcita == null){
                return Response
                .status(200)
                .entity(facadeCirugia.findAll())
                .build();
            }
            else{
                HashMap m = new HashMap();
                m.put("data", facadeCirugia.findByCita(Long.parseLong(idcita)));
                return Response
                .status(200)
                .entity(m)
                .build();
            }
        }catch(Exception ex){
            log.log(Level.SEVERE, "CirugiaService->GetCirugiasALL... ", ex);
            throw new CustomException(503, "Error accediendo a los datos de la cirugia... ");
        }
    }

    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response addcirugiaCita(List<CitaCirugia> cita_cirugia) throws CustomException{
        try{
            facadeCirugia.addCirugiaCita(cita_cirugia);
            return Response
                .status(200)
                .entity("{}")
                .build();
        }catch(Exception ex){
            log.log(Level.SEVERE, "CirugiaService->addcirugiaCita... ", ex);
            throw new CustomException(503, "Error adicionando los datos de la cirugia... ");
        }
    }
}
