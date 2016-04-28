/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.sisc_hc.exceptions.CustomException;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeRemote;
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
@Path("/medicamento/")
public class MedicamentoService {
    private final static Logger log = Logger.getLogger(CirugiaService.class.getName());
    
    @EJB
    IMedicamentoFacadeRemote facadeMedicamento;
    
    @GET
    @Produces({"application/json"})
    public Response GetMedicamentosALL(@QueryParam("idcita") String idcita) throws CustomException{
        try{
            if (idcita == null){
                return Response
                .status(200)
                .entity(facadeMedicamento.findAll())
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
            log.log(Level.SEVERE, "MedicamentoService->GetMedicamentosALL... ", ex);
            throw new CustomException(503, "Error accediendo a los datos del medicamento... ");
        }
    }

    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response addMedicamentosCita(List<CitaMedicamento> cita_medicamento) throws CustomException{
        try{
            facadeMedicamento.addMedicamentoCita(cita_medicamento);
            return Response
                .status(200)
                .entity("{}")
                .build();
        }catch(Exception ex){
            log.log(Level.SEVERE, "MedicamentoService->addMedicamentosCita... ", ex);
            throw new CustomException(503, "Error adicionando los datos del medicamento... ");
        }
    }
    
}
