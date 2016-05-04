package com.acme.sisc.agenda.rest;

import com.acme.sisc.agenda.pagination.PaginatedListWrapperCita;
import com.acme.sisc.agenda.shared.ICitaLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BryanCFz-user
 */


@Path("historialCitas")
@RequestScoped
public class citaResource {
 private static final Logger LOGGER = Logger.getLogger(citaResource.class.getName());
  
    @Context
    private UriInfo context;

    @EJB
    ICitaLocal facadeCita;

    /**
     * Creates a new instance of citaResource
     */
    public citaResource() {
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapperCita listHistorialCitas(@DefaultValue("1")
                                            @QueryParam("page")
                                            Integer page,
                                             @DefaultValue("id")
                                            @QueryParam("sortFields")
                                            String sortFields,
                                             @DefaultValue("asc")
                                            @QueryParam("sortDirections")
                                            String sortDirections) {
        PaginatedListWrapperCita paginatedListWrapper = new PaginatedListWrapperCita();
        paginatedListWrapper.setCurrentPage(page);
        paginatedListWrapper.setSortFields(sortFields);
        paginatedListWrapper.setSortDirections(sortDirections);
        paginatedListWrapper.setPageSize(10);
        return findCitas(paginatedListWrapper);
    }
    
    private PaginatedListWrapperCita findCitas(PaginatedListWrapperCita wrapper) {
        int total = facadeCita.count();
        
        LOGGER.log(Level.WARNING, "total de registros de citas: " + total);
        
        wrapper.setTotalResults(total);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(facadeCita.findRange(start,
                wrapper.getPageSize(),
                wrapper.getSortFields(),
                wrapper.getSortDirections()));
        return wrapper;
    }
    
}
