/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import com.acme.sisc.agenda.entidades.Usuario;
import com.acme.sisc.seguridad.UsuarioFacadeLocal;
import com.acme.sisc.seguridad.pagination.PaginatedListWrapper;
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
@Path("usuarios")
@RequestScoped
public class UsuarioResource {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    @Context
    private UriInfo context;
    
    @EJB
    UsuarioFacadeLocal facadeUsuario;

    public UsuarioResource() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper listUsuarios(@DefaultValue("1")
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
        return findUsuarios(paginatedListWrapper);
    }
    
    private PaginatedListWrapper findUsuarios(PaginatedListWrapper wrapper) {
        int totalUsuarios = facadeUsuario.count();
        wrapper.setTotalResults(totalUsuarios);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(facadeUsuario.findRange(start,
                wrapper.getPageSize(),
                wrapper.getSortFields(),
                wrapper.getSortDirections()));
        return wrapper;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario consultarUsuario(@PathParam("id") Long id){
        return facadeUsuario.find(id);

    }
    
    @DELETE
    @Path("{id}")
    public void eliminarUsuario(@PathParam("id") Long id){
      LOGGER.log(Level.FINE,"Request para eliminar usuario con id {0}", id);
      facadeUsuario.remove(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarUsuario(Usuario usuario) {
      try {
        if (usuario.getUsuaUsua() == null){
          facadeUsuario.crearUsuario(usuario);
        }else{
          facadeUsuario.modificarUsuario(usuario);
        }
      }catch (Exception e){
          //TODO Definir manejo
          LOGGER.log(Level.SEVERE, "Houston, estamos en problemas ...", e);
      }
    }
    
}
