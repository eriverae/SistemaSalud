/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.common.exceptions.CustomException;
import com.acme.sisc.common.exceptions.CustomRunTimeException;
import com.acme.sisc.sisc_hc.shared.IDiagnosticoFacadeRemote;
import com.acme.sisc.utils.DiagnosticoCita;
import java.util.HashMap;
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
@Path("/diagnostico/")
public class DiagnosticoService {

    @EJB
    IDiagnosticoFacadeRemote facadeDiagnostico;

    @GET
    @Produces({"application/json"})
    public Response GetDiagnostico(@QueryParam("idcita") String idcita)
            throws CustomException, CustomRunTimeException {
        try {
            HashMap m = new HashMap();
            m.put("data", facadeDiagnostico.findByCita(Long.parseLong(idcita)));
            return Response
                    .status(200)
                    .entity(m)
                    .build();

        } catch (Exception ex) {
            throw new CustomException(Response.Status.BAD_REQUEST.getStatusCode(), 503, "Error accediendo a los datos del Dianostico... ");
        }
    }

    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response addDiagnosticoCita(DiagnosticoCita diagnostico)
            throws CustomException, CustomRunTimeException {
        try {
            facadeDiagnostico.addDiagnosticoCita(diagnostico.getId_cita(),diagnostico.getDiagnostico());
            return Response
                    .status(200)
                    .entity("{}")
                    .build();
        } catch (Exception ex) {
            throw new CustomException(Response.Status.BAD_REQUEST.getStatusCode(), 503, "Error adicionando los datos del Diagnostico... ");
        }
    }

}
