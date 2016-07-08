/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;
import com.acme.sisc.agenda.entidades.AuditoriaUsuario;
import com.acme.sisc.common.pagination.PaginatedListWrapper;
import com.acme.sisc.seguridad.AuditoriaFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
* Este es el servicio para crear, modificar, consultar y eliminar las operaciones 
* que se realizan sobre las Auditorias
* 
* @author  Julio
* @version 1.0
* @since   2016-06-27
*/
@Path("auditorias")
@RequestScoped
public class AuditoriaResource {
    private static final Logger LOGGER = Logger.getLogger(AuditoriaResource.class.getName());

    @Context
    private UriInfo context;

    @EJB
    AuditoriaFacadeLocal facadeAuditoria;
    
    /**
    * Constructor del servicio AuditoriaResource
    *
    */
    public AuditoriaResource() {
        
    }

    /**
    * Metodo guardarAuditoria, llama al facade de Auditorias para crearlas
    * @param req String que contiene separados por los guiones, el mail del usuario, observacion, direccion IP y hostName
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarAuditoria(String req) {
        String[] spl = req.split("-");
        LOGGER.log(Level.FINE,"Post para actualizat guardarAuditoria con {1}", req);
        String emailUsuario, observacion, dirIP, hostName;
        emailUsuario = spl[0];
        observacion = spl[1];
        dirIP = spl[2];
        hostName = spl[3];
        try {
            facadeAuditoria.crearAuditoria(emailUsuario, observacion, dirIP, hostName);
        } catch (Exception e) {
            //TODO Definir manejo
            LOGGER.log(Level.SEVERE, "Houston, estamos en problemas ...", e);
        }
    }
}
