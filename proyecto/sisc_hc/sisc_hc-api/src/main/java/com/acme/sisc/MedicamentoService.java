/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeRemote;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
    public List GetMedicamentosALL(){
        return facadeMedicamento.findAll();
    }
}
