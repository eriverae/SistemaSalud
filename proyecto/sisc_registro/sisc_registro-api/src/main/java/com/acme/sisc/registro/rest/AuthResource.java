/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.registro.rest;

import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.seguridad.ProxyAutenticador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author efrr_000
 */
@Path("auth")
@RequestScoped
public class AuthResource {
    private static final Logger LOGGER = Logger.getLogger(PersonaNaturalResource.class.getName());
  
    @Context
    private UriInfo context;

    public AuthResource(){}
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response autenticar(String usr, String pwd) {
      try {
          Boolean result = ProxyAutenticador.getInstance().autenticar(usr, pwd);
          LOGGER.log(Level.WARNING, "Resultado de la autenticacion: " + result);
          if (result)
            return Response.ok().build();
          else 
            return Response.status(2)
                .entity(this)
                .type(MediaType.APPLICATION_JSON)
                .build();
      }catch (Exception e){
        //TODO Definir manejo
        LOGGER.log(Level.SEVERE, "Houston, estamos en problemas...", e);
        return Response.status(-1)
            .entity(this)
            .type(MediaType.APPLICATION_JSON)
            .build();
      }
    }
}
