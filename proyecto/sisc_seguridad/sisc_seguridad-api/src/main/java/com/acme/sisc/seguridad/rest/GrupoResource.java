/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.seguridad.GrupoFacadeLocal;
import com.acme.sisc.common.pagination.PaginatedListWrapper;
import com.acme.sisc.seguridad.AccesoFacadeLocal;
import java.util.List;
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
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author rm-rf
 */
@Path("grupos")
@RequestScoped
public class GrupoResource {
    
    private static final Logger LOGGER = Logger.getLogger(AccesoResource.class.getName());
    
    @Context
    private UriInfo context;
    
    @EJB
    GrupoFacadeLocal facadeGrupo;
    
    @EJB
    AccesoFacadeLocal facadeAcceso;

    public GrupoResource() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper listGrupos(@DefaultValue("1")
                                            @QueryParam("page")
                                            Integer page,
                                             @DefaultValue("id")
                                            @QueryParam("sortFields")
                                            String sortFields,
                                             @DefaultValue("asc")
                                            @QueryParam("sortDirections")
                                            String sortDirections) {
        PaginatedListWrapper paginatedListWrapper = new PaginatedListWrapper();
        paginatedListWrapper.setCurrentPage(page);
        paginatedListWrapper.setSortFields(sortFields);
        paginatedListWrapper.setSortDirections(sortDirections);
        paginatedListWrapper.setPageSize(10);
        return findGrupos(paginatedListWrapper);
    }
    
    private PaginatedListWrapper findGrupos(PaginatedListWrapper wrapper) {
        int totalAccesos = facadeGrupo.count();
        wrapper.setTotalResults(totalAccesos);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(facadeGrupo.findRange(start,
                wrapper.getPageSize(),
                wrapper.getSortFields(),
                wrapper.getSortDirections()));
        return wrapper;
    }
    
    @GET
    @Path("{grupGrup}")
    @Produces(MediaType.APPLICATION_JSON)
    public Grupo consultarAcceso(@PathParam("grupGrup") Long id){
        return facadeGrupo.find(id);

    }
    
    @DELETE
    @Path("{grupGrup}")
    public void eliminarGrupo(@PathParam("grupGrup") Long id){
      LOGGER.log(Level.FINE,"Request para eliminar acceso con id {0}", id);
      facadeGrupo.remove(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Grupo guardarAcceso(Grupo grupo) {
      try {
        if (grupo.getGrupGrup() == null){
          grupo = facadeGrupo.crearGrupo(grupo);
        }else{
          facadeGrupo.modificarGrupo(grupo);
        }
        return grupo;
      }catch (Exception e){
          //TODO Definir manejo
          LOGGER.log(Level.SEVERE, "Houston, estamos en problemas ...", e);
      }
      return null;
    }
}
