/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.seguridad.GrupoFacadeLocal;
import com.acme.sisc.seguridad.ProxyAutenticador;
import com.acme.sisc.seguridad.Utils.JWTUtils;
import com.acme.sisc.seguridad.dto.Credenciales;
import com.acme.sisc.seguridad.dto.RespuestaAutenticacion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
import javax.ws.rs.core.Response;
import org.jose4j.lang.JoseException;

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
    
    @EJB
    GrupoFacadeLocal facadeGrupo;

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    /**
     * Creates a new instance of SeguridadResource
     */
    public SeguridadResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.acme.sisc.seguridad.rest.SeguridadResource
     *
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
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticarUsuario(Credenciales credencial) {
        boolean valor;

        valor = ProxyAutenticador.getInstance().autenticar(credencial.getUsuario(), credencial.getPassword());

        LOGGER.log(Level.INFO, "Valor Boolean" + valor);
        RespuestaAutenticacion resultadoAutenticacion = new RespuestaAutenticacion();
        if (valor == true) {
            String token;

            try {
                token = JWTUtils.generarToken();
            } catch (JoseException ex) {
                LOGGER.log(Level.SEVERE, "Error generando Token", ex);
                        return Response.status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                .build();
            }
            

            PersonaNatural personaNatural = consultarPersona(credencial.getUsuario());

            List<String> gruposPersona = consultarGrupos(credencial.getUsuario());

            resultadoAutenticacion.setAutheticated(true);
            resultadoAutenticacion.setListaGrupos(gruposPersona);
            resultadoAutenticacion.setPersonaNatural(personaNatural);
            resultadoAutenticacion.setToken(token);
        } else {
            resultadoAutenticacion.setAutheticated(false);
        }

        return Response.ok()
                .entity(resultadoAutenticacion)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private PersonaNatural consultarPersona(String email) {
        PersonaNatural personaNatural = new PersonaNatural();
        //com.acme.sisc.registro.ejb.PersonaNaturalFacade findByEmail
        return personaNatural;
    }

    private List<String> consultarGrupos(String usuario) {
        return facadeGrupo.obtenerGrupos(usuario);
    }
}
