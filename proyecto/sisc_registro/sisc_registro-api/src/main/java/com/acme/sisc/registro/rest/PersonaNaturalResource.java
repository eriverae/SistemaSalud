/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.registro.rest;

import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.registro.ejb.IPersonaNaturalFacadeLocal;
import com.acme.sisc.registro.pagination.PaginatedListWrapperPN;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author rreedd
 */
@Path("personaNatural")
@RequestScoped
public class PersonaNaturalResource {

    private static final Logger LOGGER = Logger.getLogger(PersonaNaturalResource.class.getName());

    @Context
    private UriInfo context;

    @EJB
    IPersonaNaturalFacadeLocal facadePersonaNatural;

    /**
     * Creates a new instance of ClientesResource
     */
    public PersonaNaturalResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapperPN listPersonaNatural(@DefaultValue("1")
            @QueryParam("page") Integer page,
            @DefaultValue("id")
            @QueryParam("sortFields") String sortFields,
            @DefaultValue("asc")
            @QueryParam("sortDirections") String sortDirections) {
        PaginatedListWrapperPN paginatedListWrapper = new PaginatedListWrapperPN();
        paginatedListWrapper.setCurrentPage(page);
        paginatedListWrapper.setSortFields(sortFields);
        paginatedListWrapper.setSortDirections(sortDirections);
        paginatedListWrapper.setPageSize(10);
        return findPersonaNatural(paginatedListWrapper);
    }

    private PaginatedListWrapperPN findPersonaNatural(PaginatedListWrapperPN wrapper) {
        int total = facadePersonaNatural.count();
        wrapper.setTotalResults(total);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(facadePersonaNatural.findRange(start,
                wrapper.getPageSize(),
                wrapper.getSortFields(),
                wrapper.getSortDirections()));
        return wrapper;
    }

//    @GET
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public PersonaNatural consultarPersona(@PathParam("id") Long id){
//        LOGGER.log(Level.FINE, "Consultando persona natural con id {0} \n\n\n", id);
//        return facadePersonaNatural.find(id);
//    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaNatural consultarPersona(@PathParam("id") Long id) {
        PersonaNatural response = null;
        LOGGER.log(Level.FINE, "ATENCION id " + id + " \n\n\n");
        LOGGER.log(Level.FINE, "Consultando persona natural con id {0} \n\n\n", id);
        response = facadePersonaNatural.find(id);
        if (response == null) {
            LOGGER.log(Level.FINE, "Consultando persona natural con numero identificacion {0} \n\n\n", id);
            return facadePersonaNatural.findByNumeroIdentificacion(id);
        }
        return response;
    }

    @GET
    @Path("{numberId}/personaByNumberId")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaNatural consultarPersonaPorNumeroIdentificacion(@PathParam("numberId") Long numberId){
        LOGGER.log(Level.FINE, "Consultando persona natural con numero identificacion {0} \n\n\n", numberId);
        return facadePersonaNatural.findByNumeroIdentificacion(numberId);
    }
    
    @DELETE
    @Path("{id}")
    public void eliminarPersona(@PathParam("id") Long id) {
        LOGGER.log(Level.FINE, "Request para eliminar persona natural con id {0}", id);
        facadePersonaNatural.remove(id);
    }

    /**
     * PUT method for updating or creating an instance of PersonaNaturalResource
     *
     * @param PersonaNatural representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarPersonaNatural(PersonaNatural personaNatural) {
        try {
            if (personaNatural.getIdPersona() == null) {
                facadePersonaNatural.crearPersonaNatural(personaNatural);
            } else {
                facadePersonaNatural.modificarPersonaNatural(personaNatural);
            }
        } catch (Exception e) {
            //TODO Definir manejo
            LOGGER.log(Level.SEVERE, "Houston, estamos en problemas...", e);
        }
    }
}
