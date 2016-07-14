/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import com.acme.sisc.agenda.entidades.Usuario;
import com.acme.sisc.common.dtos.RespuestaDTO;
import com.acme.sisc.seguridad.UsuarioFacadeLocal;
import com.acme.sisc.common.pagination.PaginatedListWrapper;
import com.acme.sisc.common.exceptions.CustomException;
import com.acme.sisc.common.exceptions.CustomRunTimeException;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Este es el servicio para crear, modificar, consultar y eliminar las
 * operaciones que se realizan el usuario
 *
 * @author Julio
 * @version 1.0
 * @since 2016-05-22
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

    /**
     * Constructor del servicio AccesoGrupoResource
     *
     */
    public UsuarioResource() {
    }

    /**
     * Metodo listUsuarios, retorna un PaginatedListWrapper de los usuarios
     *
     * @param page es el numero actual de paginated
     * @param sortFields es el id del sortField actual de la pagina
     * @param sortDirections es el orden de paginated por defecto asc
     * @return retorna PaginatedListWrapper con la tabla armada a partir la
     * busqueda del objeto en referencia
     */
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

    /**
     * Metodo findUsuarios, arma y devuelve el PaginatedListWrapper
     * implementando los metodos del facade
     *
     * @param wrapper es el PaginatedListWrapper que recibe
     * @return retorna wrapper con la informacion que consulta y setea en el
     * facade
     */
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

    /**
     * Metodo consultarUsuario, devuelve un objeto de tipo UsuarioCompleto (dto)
     * con el objeto usuario y sus perfiles
     *
     * @param id es el identificador del objeto al que se quiere consultar
     * @return retorna UsuarioCompleto, el el objeto de perfiles y objeto del
     * usuario
     */
    @GET
    @Path("{usuaUsua}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioCompleto consultarUsuario(@PathParam("usuaUsua") Long id) throws CustomException, CustomRunTimeException {
        try {
            UsuarioCompleto UC = new UsuarioCompleto();
            UC.setUsuario(facadeUsuario.find(id));
            UC.setGrupos(facadeGrupoUsuario.findByUsuaUsua(id));
            return UC;
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error consultando UsuarioComple... ");
        }
    }

    /**
     * Metodo eliminarUsuario, elimina el objeto de tipo Usuario con el id en
     * referencia
     *
     * @param id es el identificador del objeto al que se quiere eliminar
     */
    @DELETE
    @Path("{usuaUsua}")
    public void eliminarUsuario(@PathParam("usuaUsua") Long id) throws CustomException, CustomRunTimeException {
        try {
            LOGGER.log(Level.FINE, "Request para eliminar usuario con id {0}", id);
            facadeUsuario.remove(id);
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error elimindo Usuario... ");
        }
    }

    /**
     * Metodo guardarUsuario, crea y modifica el objeto de tipo Usuario del
     * objeto en referencia
     *
     * @param usuario es el objeto a crear o modificar que llega, si este objeto
     * en su parte id es nulo se crea, sino se modifica
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario guardarUsuario(Usuario usuario) throws CustomException, CustomRunTimeException {
        try {
            if (usuario.getUsuaUsua() == null) {
                usuario = facadeUsuario.crearUsuario(usuario);
            } else {
                usuario = facadeUsuario.modificarUsuario(usuario);
            }
            return usuario;
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error guardando Usuario... ");
        }
    }

    /**
     * Metodo cambiarContrasena, cambia la contraseña del usuario
     *
     * @param String req, contiene el id del usuario, password anterior,
     * password nuevo
     */
    @POST
    @Path("actCon/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiarContrasena(String req) {
        try {
            String respuesta;
            String[] spl = req.split("-");
            System.out.println(req);
            LOGGER.log(Level.FINE, "Post para cambiarContrasena con {1}", req);

            Usuario usua;
            String usuaFind = spl[0];
            String passOld = spl[1];
            String passNew = spl[2];
            if (usuaFind.matches("^[0-9]+$")) {
                usua = facadeUsuario.find(Long.parseLong(usuaFind));
                respuesta = facadeUsuario.cambiarContrasena(usua, "", passNew);
            } else {
                usua = facadeUsuario.findByEmail(usuaFind);
                respuesta = facadeUsuario.cambiarContrasena(usua, passOld, passNew);
            }
            RespuestaDTO cc = new RespuestaDTO();
            cc.put("cambioContraseña", respuesta);
            return Response.ok()
                    .entity(cc)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            //TODO Definir manejo
            LOGGER.log(Level.SEVERE, "Error cambiar contraseña ...", e);
        }
        return null;
    }

}
