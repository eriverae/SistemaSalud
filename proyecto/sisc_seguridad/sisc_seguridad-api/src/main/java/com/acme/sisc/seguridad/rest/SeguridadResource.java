/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.common.ejbLocator.EJBLocator;
import com.acme.sisc.registro.ejb.IPersonaNaturalFacadeRemote;
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
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jose4j.lang.JoseException;

/**
* Este es el servicio para crear, modificar, consultar y eliminar las operaciones 
* que se realizan sobre seguridad JWT, auntenticacion. 
* 
* @author  Erika
* @version 1.0
* @since   2016-05-22
*/
@Path("seguridad")
@RequestScoped
public class SeguridadResource {

    @Context
    private UriInfo context;
    
    @EJB
    GrupoFacadeLocal facadeGrupo;
    
    private IPersonaNaturalFacadeRemote personaNaturalFacade;

    private static final String LOCAL_EJB_PERSONA = "java:global/sisc_registro-ear-1.0-SNAPSHOT/sisc_registro-ejb-1.0-SNAPSHOT/PersonaNaturalFacade!com.acme.sisc.registro.ejb.IPersonaNaturalFacadeRemote";

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    /**
    * Constructor del servicio SeguridadResource
    *
    */
    public SeguridadResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.acme.sisc.seguridad.rest.SeguridadResource
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

    /**
    * Metodo autenticarUsuario hace la autenticacion con ProxyAutenticador, genera e l token
    * a partir de JWTUtils, crea la persona natual a partir del mail y los perfiles del usuario
    * @param Credenciales es el objeto que contiene el mail y contraseña del usuario
    * @return retorna Response con la autenticacion, los perfiles del usuario, la persona natural y el token.
    */
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

    /**
    * Metodo consultarPersona crea la persona natual a partir del mail
    * @param email String es el mail del usuario a consultar/crear
    * @return retorna PersonaNatural a partir de la invocacion remota del modulo de registro
    */
    private PersonaNatural consultarPersona(String email) {
        try {
            personaNaturalFacade = (IPersonaNaturalFacadeRemote) EJBLocator.lookup(LOCAL_EJB_PERSONA);
        } catch (NamingException ex) {
            Logger.getLogger(SeguridadResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        PersonaNatural personaNatural = personaNaturalFacade.findByEmail(email);
        return personaNatural;
    }

    /**
    * Metodo consultarGrupos consulta todos los perfiles del usuario
    * @param usuario String es el mail del usuario 
    * @return retorna List<String>, es el listado de los perfiles del usuario
    */
    private List<String> consultarGrupos(String usuario) {
        return facadeGrupo.obtenerGrupos(usuario);
    }
}
