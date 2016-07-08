/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.rest;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.agenda.util.AgendaUtil;
import java.util.Date;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import com.acme.sisc.agenda.shared.IUtilitariosAgendaLocal;
import javax.ejb.EJB;
import javax.ws.rs.QueryParam;

/**
 *
 * @author desarrollador
 */
@Path("utilidades")
@RequestScoped
public class RestFullUtilidades {
    
    @EJB
    private IUtilitariosAgendaLocal iUtilitariosAgendaLocal;
    
    @Context
    private UriInfo context;
    private final static Logger _log = Logger.getLogger(RestFullUtilidades.class.getName()); 
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)    
    @Path("/fechaActual")
    public String consularFechaActual(){        
        return AgendaUtil.parserDateToString(new Date(),WebConstant.JSON_DATE_FORMAT);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)    
    @Path("/personaNatural")
//    http://localhost:8080/SiscAgenda/api/utilidades/personaNatural?email=male@gmail.com
    public PersonaNatural consultaPersonaNatural(@QueryParam("email") String email){
        return iUtilitariosAgendaLocal.consultaPersonaNatural(email);
    }
}
