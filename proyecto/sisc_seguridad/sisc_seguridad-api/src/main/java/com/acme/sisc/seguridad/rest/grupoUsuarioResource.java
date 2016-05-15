/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;


import com.acme.sisc.agenda.entidades.GrupoUsuario;
import com.acme.sisc.common.pagination.PaginatedListWrapper;
import com.acme.sisc.seguridad.GrupoUsuarioFacadeLocal;
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
import com.acme.sisc.seguridad.GrupoUsuarioFacadeLocal;
/**
 *
 * @author Julio
 */
@Path("grupoUsuario")
@RequestScoped
public class grupoUsuarioResource {
    private static final Logger LOGGER = Logger.getLogger(grupoUsuarioResource.class.getName());
    
    @Context
    private UriInfo context;
    
    @EJB
    GrupoUsuarioFacadeLocal facadeGrupoUsuario;

    public grupoUsuarioResource() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper listGrupoUsuarios(@DefaultValue("1")
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
        return findGrupoUsuarios(paginatedListWrapper);
    }
    
    private PaginatedListWrapper findGrupoUsuarios(PaginatedListWrapper wrapper) {
        int totalGrupoUsuarios = facadeGrupoUsuario.count();
        wrapper.setTotalResults(totalGrupoUsuarios);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(facadeGrupoUsuario.findRange(start,
                wrapper.getPageSize(),
                wrapper.getSortFields(),
                wrapper.getSortDirections()));
        return wrapper;
    }
    
    @GET
    @Path("{idGrupusu}")
    @Produces(MediaType.APPLICATION_JSON)
    public GrupoUsuario consultarGrupoUsuario(@PathParam("idGrupusu") Long id){
        return facadeGrupoUsuario.find(id);

    }
    
    @DELETE
    @Path("{idGrupusu}")
    public void eliminarGrupoUsuario(@PathParam("idGrupusu") Long id){
      LOGGER.log(Level.FINE,"Request para eliminar GrupoUsuario con id {0}", id);
      facadeGrupoUsuario.remove(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarGrupoUsuario(GrupoUsuario grupoUsuario) {
      try {
        if (grupoUsuario.getIdGrupusu() == null){
          facadeGrupoUsuario.crearGrupoUsuario(grupoUsuario);
        }else{
          facadeGrupoUsuario.modificarGrupoUsuario(grupoUsuario);
        }
      }catch (Exception e){
          //TODO Definir manejo
          LOGGER.log(Level.SEVERE, "Houston, estamos en problemas ...", e);
      }
    }
}
