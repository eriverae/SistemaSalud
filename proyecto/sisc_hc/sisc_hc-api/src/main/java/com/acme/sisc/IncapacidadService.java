/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Incapacidad;
import com.acme.sisc.sisc_hc.shared.IIncapacidadFacadeRemote;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author GABRIEL
 */
@Path("/incapacidad/")
public class IncapacidadService {
    @EJB
    IIncapacidadFacadeRemote facadeIncapacidad;
    
    
    @POST
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public String PostIncapacidad(Incapacidad incapacidad){
        facadeIncapacidad.addIncapacidad(incapacidad);
        return "{}";
    }

}
