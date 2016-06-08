/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Incapacidad;
import com.acme.sisc.common.exceptions.CustomException;
import com.acme.sisc.common.exceptions.CustomRunTimeException;
import com.acme.sisc.sisc_hc.shared.IIncapacidadFacadeRemote;
import com.acme.sisc.utils.IncapacidadCita;
import java.util.HashMap;
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
@Path("/incapacidad/")
public class IncapacidadService {

    @EJB
    IIncapacidadFacadeRemote facadeIncapacidad;

    @GET
    @Produces({"application/json"})
    public Response GetIncapacidadALL(@QueryParam("idcita") String idcita,
            @QueryParam("idpaciente") String idpaciente
    )
            throws CustomException, CustomRunTimeException {
        try {
            if (idpaciente != null) {
                HashMap m = new HashMap();
                m.put("data", facadeIncapacidad.isIncapacitado(idpaciente));
                return Response
                        .status(200)
                        .entity(m)
                        .build();
            } else if (idcita == null) {
                return Response
                        .status(200)
                        .entity(facadeIncapacidad.findAll())
                        .build();
            } else {
                HashMap m = new HashMap();
                m.put("data", facadeIncapacidad.findByCita(Long.parseLong(idcita)));
                return Response
                        .status(200)
                        .entity(m)
                        .build();
            }
        } catch (Exception ex) {
            throw new CustomException(Response.Status.BAD_REQUEST.getStatusCode(), 503, "Error accediendo los datos de la incapacidad... ");
        }
    }
    private static final Logger LOGGER = Logger.getLogger(IncapacidadService.class.getName());

    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response PostIncapacidad(IncapacidadCita incapacidad) throws
            CustomException, CustomRunTimeException {
        LOGGER.log(Level.WARNING, "OBJETO INCAPACIDADDD ", incapacidad.getCita());
        try {
            facadeIncapacidad.addIncapacidad(incapacidad.getCita(), incapacidad.getMotivo(), incapacidad.getPeriodo());
            return Response
                    .status(200)
                    .entity("{}")
                    .build();
        } catch (Exception ex) {
            throw new CustomException(Response.Status.BAD_REQUEST.getStatusCode(), 503, "Error adicionando los datos de la incapacidad... ");
        }
    }
}
