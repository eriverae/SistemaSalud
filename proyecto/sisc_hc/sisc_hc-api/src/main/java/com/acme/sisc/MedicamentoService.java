/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeRemote;
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
@Path("/medicamento/")
public class MedicamentoService {
    @EJB
    IMedicamentoFacadeRemote facadeMedicamento;
    
    @GET
    @Produces({"application/json"})
    public Response GetMedicamentosALL(){
        return Response
            .status(200)
            .entity(facadeMedicamento.findAll())
            .build();
    }

    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response addMedicamentosCita(List<CitaMedicamento> cita_medicamento){
        facadeMedicamento.addMedicamentoCita(cita_medicamento);
        return Response
            .status(200)
            .entity("{}")
            .build();
    }
    
}
