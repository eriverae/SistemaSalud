/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import com.acme.sisc.agenda.entidades.Usuario;
import com.acme.sisc.seguridad.UsuarioFacadeLocal;
import com.acme.sisc.common.pagination.PaginatedListWrapper;
import com.acme.sisc.seguridad.GrupoUsuarioFacadeLocal;
import com.acme.sisc.seguridad.dto.UsuarioCompleto;
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

    @EJB
    GrupoUsuarioFacadeLocal facadeGrupoUsuario;

    public UsuarioResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper listUsuarios(@DefaultValue("1")
            @QueryParam("page") Integer page,
            @DefaultValue("id")
            @QueryParam("sortFields") String sortFields,
            @DefaultValue("asc")
            @QueryParam("sortDirections") String sortDirections) {
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
    @Path("{usuaUsua}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioCompleto consultarUsuario(@PathParam("usuaUsua") Long id) {
        UsuarioCompleto UC = new UsuarioCompleto();
        UC.setUsuario(facadeUsuario.find(id));
        UC.setGrupos(facadeGrupoUsuario.findByUsuaUsua(id));
        return UC;
    }

    @DELETE
    @Path("{usuaUsua}")
    public void eliminarUsuario(@PathParam("usuaUsua") Long id) {
        LOGGER.log(Level.FINE, "Request para eliminar usuario con id {0}", id);
        facadeUsuario.remove(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario guardarUsuario(Usuario usuario) {
        try {
            if (usuario.getUsuaUsua() == null) {
                usuario = facadeUsuario.crearUsuario(usuario);
            } else {
                usuario = facadeUsuario.modificarUsuario(usuario);
            }
            return usuario;
        } catch (Exception e) {
            //TODO Definir manejo
            LOGGER.log(Level.SEVERE, "Houston, estamos en problemas ...", e);
        }
        return null;
    }

    @POST
    @Path("actCon/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cambiarContrasena(String req) {
        try {
            String[] spl = req.split("-");
            System.out.println(req);
            LOGGER.log(Level.FINE, "Post para cambiarContrasena con {1}", req);

            Usuario usua;
            String passOld = spl[1];
            String passNew = spl[2];
            if (passOld == null || passOld.equalsIgnoreCase("undefined")) {
                usua = facadeUsuario.find(Long.parseLong(spl[0]));
                facadeUsuario.cambiarContrasena(usua, "", passNew);
            } else {
                usua = facadeUsuario.findByEmail(spl[0]);
                facadeUsuario.cambiarContrasena(usua, passOld, passNew);
            }
            
        } catch (Exception e) {
            //TODO Definir manejo
            LOGGER.log(Level.SEVERE, "Houston, estamos en problemas ...", e);
        }
    }

}
