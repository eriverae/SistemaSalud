/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.rest;

import com.acme.sisc.agenda.constant.WebConstant;
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

/**
 *
 * @author desarrollador
 */
@Path("utilidades")
@RequestScoped
public class RestFullUtilidades {
    
    
    @Context
    private UriInfo context;
    private final static Logger _log = Logger.getLogger(RestFullUtilidades.class.getName()); 
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)    
    @Path("/fechaActual")
    public String consularFechaActual(){        
        return AgendaUtil.parserDateToString(new Date(),WebConstant.JSON_DATE_FORMAT);
    }
}
