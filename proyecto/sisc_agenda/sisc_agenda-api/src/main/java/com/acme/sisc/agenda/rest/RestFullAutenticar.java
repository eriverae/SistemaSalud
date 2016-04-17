/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.rest;

import com.acme.sisc.seguridad.ProxyAutenticador;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author desarrollador
 */
@Path("seguridad")
@RequestScoped
public class RestFullAutenticar {
    
    private final static Logger _log = Logger.getLogger(RestFullAutenticar.class.getName()); 
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)    
    @Path("/autenticar")   
   public String autenticar(String user,String password){
       if(ProxyAutenticador.getInstance().autenticar(user, password)){
         return "autenticado...";  
       }else{
           return "no autentico";
       }
       
   }
    
}
