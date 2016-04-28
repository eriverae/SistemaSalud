/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import com.acme.sisc.agenda.entidades.Usuario;
import com.acme.sisc.seguridad.ProxyAutenticador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Erika
 */
@Path("seguridad")
@RequestScoped
public class SeguridadResource {

    @Context
    private UriInfo context;
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    /**
     * Creates a new instance of SeguridadResource
     */
    public SeguridadResource() {
    }

    /**
     * Retrieves representation of an instance of com.acme.sisc.seguridad.rest.SeguridadResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of SeguridadResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void autenticarUsuario(String usuario, String password) {
      boolean valor;
      
      valor = ProxyAutenticador.getInstance().autenticar(usuario, password);
      
      LOGGER.log(Level.INFO,"Valor Boolean" + valor);
    }
}
