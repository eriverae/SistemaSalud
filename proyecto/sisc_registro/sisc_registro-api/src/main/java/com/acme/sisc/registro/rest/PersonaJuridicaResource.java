/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.registro.rest;

import com.acme.sisc.agenda.entidades.PersonaJuridica;
import com.acme.sisc.agenda.entidades.TipoIdentificacion;
import com.acme.sisc.registro.ejb.IPersonaJuridicaFacadeLocal;
import com.acme.sisc.registro.pagination.PaginatedListWrapperPJ;
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
@Path("personaJuridica")
@RequestScoped
public class PersonaJuridicaResource {

    private static final Logger LOGGER = Logger.getLogger(PersonaJuridicaResource.class.getName());

    @Context
    private UriInfo context;

    @EJB
    IPersonaJuridicaFacadeLocal facadePersonaJuridica;

    /**
     * Creates a new instance of ClientesResource
     */
    public PersonaJuridicaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapperPJ listPersonaJuridica(@DefaultValue("1")
            @QueryParam("page") Integer page,
            @DefaultValue("id")
            @QueryParam("sortFields") String sortFields,
            @DefaultValue("asc")
            @QueryParam("sortDirections") String sortDirections) {
        PaginatedListWrapperPJ paginatedListWrapper = new PaginatedListWrapperPJ();
        paginatedListWrapper.setCurrentPage(page);
        paginatedListWrapper.setSortFields(sortFields);
        paginatedListWrapper.setSortDirections(sortDirections);
        paginatedListWrapper.setPageSize(10);
        return findPersonaJuridica(paginatedListWrapper);
    }

    private PaginatedListWrapperPJ findPersonaJuridica(PaginatedListWrapperPJ wrapper) {
        int total = facadePersonaJuridica.count();
        wrapper.setTotalResults(total);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(facadePersonaJuridica.findRange(start,
                wrapper.getPageSize(),
                wrapper.getSortFields(),
                wrapper.getSortDirections()));
        return wrapper;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaJuridica consultarPersonaJuridica(@PathParam("id") Long id) {
        LOGGER.log(Level.FINE, "Consultando persona natural con id {0} \n\n\n", id);
        return facadePersonaJuridica.find(id);
    }

    @DELETE
    @Path("{id}")
    public void eliminarPersonaJuridica(@PathParam("id") Long id) {
        LOGGER.log(Level.FINE, "Request para eliminar persona natural con id {0}", id);
        facadePersonaJuridica.remove(id);
    }

    /**
     * PUT method for updating or creating an instance of
     * PersonaJuridicaResource
     *
     * @param PersonaJuridica representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarPersonaJuridica(PersonaJuridica personaJuridica) {
        try {
            if (personaJuridica.getIdPersona() == null) {
                personaJuridica.setTipoIdentificacion(TipoIdentificacion.NIT);
                facadePersonaJuridica.crearPersonaJuridica(personaJuridica);
            } else {
                facadePersonaJuridica.modificarPersonaJuridica(personaJuridica);
            }
        } catch (Exception e) {
            //TODO Definir manejo
            LOGGER.log(Level.SEVERE, "Houston, estamos en problemas...", e);
        }
    }
}
