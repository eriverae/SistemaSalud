/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.CitaCirugia;
import com.acme.sisc.sisc_hc.shared.ICirugiaFacadeLocal;
import com.acme.sisc.sisc_hc.shared.ICirugiaFacadeRemote;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author GABRIEL
 */
@Path("/cirugia/")
public class CirugiaService {
    @EJB
    ICirugiaFacadeRemote facadeCirugia;
    
    @GET
    @Produces({"application/json"})
    public Response GetCirugiasALL(@QueryParam("idcita") String idcita){
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
    }

    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response addcirugiaCita(List<CitaCirugia> cita_cirugia){
        facadeCirugia.addCirugiaCita(cita_cirugia);
        return Response
            .status(200)
            .entity("{}")
            .build();
    }
}
