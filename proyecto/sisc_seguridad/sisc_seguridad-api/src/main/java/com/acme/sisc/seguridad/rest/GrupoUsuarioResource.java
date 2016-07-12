/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.agenda.entidades.GrupoUsuario;
import com.acme.sisc.agenda.entidades.Usuario;
import com.acme.sisc.common.pagination.PaginatedListWrapper;
import com.acme.sisc.common.exceptions.CustomException;
import com.acme.sisc.common.exceptions.CustomRunTimeException;
import com.acme.sisc.seguridad.GrupoFacadeLocal;
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
import com.acme.sisc.seguridad.UsuarioFacadeLocal;
import javax.ws.rs.core.Response;

/**
 * Este es el servicio para crear, modificar, consultar y eliminar las
 * operaciones que se realizan sobre la relacion de perfiles y usuarios
 *
 * @author Julio
 * @version 1.0
 * @since 2016-05-22
 */
@Path("grupoUsuario")
@RequestScoped
public class GrupoUsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(GrupoUsuarioResource.class.getName());

    @Context
    private UriInfo context;

    @EJB
    GrupoUsuarioFacadeLocal facadeGrupoUsuario;
    @EJB
    UsuarioFacadeLocal usuarioFacade;
    @EJB
    GrupoFacadeLocal grupoFacade;

    /**
     * Constructor del servicio GrupoUsuarioResource
     *
     */
    public GrupoUsuarioResource() {
    }

    /**
     * Metodo listGrupoUsuarios, retorna un PaginatedListWrapper de los perfiles
     * y usuario
     *
     * @param page es el numero actual de paginated
     * @param sortFields es el id del sortField actual de la pagina
     * @param sortDirections es el orden de paginated por defecto asc
     * @return retorna PaginatedListWrapper con la tabla armada a partir la
     * busqueda del objeto en referencia
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper listGrupoUsuarios(@DefaultValue("1")
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
        return findGrupoUsuarios(paginatedListWrapper);
    }

    /**
     * Metodo findGrupoUsuarios, arma y devuelve el PaginatedListWrapper
     * implementando los metodos del facade
     *
     * @param wrapper es el PaginatedListWrapper que recibe
     * @return retorna wrapper con la informacion que consulta y setea en el
     * facade
     */
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

    /**
     * Metodo consultarGruposPorUsuario, devuelve un response que trae desde el
     * facade con un id
     *
     * @param id es el identificador del objeto al que se quiere consultar
     * @return retorna Response, el el objeto de perfiles del usuario
     */
    @GET
    @Path("idusua/{usuaUsua}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarGruposPorUsuario(@PathParam("usuaUsua") Long id) throws CustomException, CustomRunTimeException {
        try {
            return Response.ok()
                    .entity(facadeGrupoUsuario.findByUsuaUsua(id))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error consultando GrupoUsuario... ");
        }
//        return facadeGrupoUsuario.findByUsuaUsua(id);
    }

    /**
     * Metodo consultarGrupoUsuario, devuelve un objeto de tipo GrupoUsuario que
     * trae desde el facade con un id
     *
     * @param id es el identificador del objeto al que se quiere consultar
     * @return retorna GrupoUsuario, el el objeto de perfiles
     */
    @GET
    @Path("{idGrupusu}")
    @Produces(MediaType.APPLICATION_JSON)
    public GrupoUsuario consultarGrupoUsuario(@PathParam("idGrupusu") Long id) throws CustomException, CustomRunTimeException {
        try {
            return facadeGrupoUsuario.find(id);
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error consultando GrupoUsuario... ");
        }
    }

    /**
     * Metodo eliminarGrupoUsuario, elimina el objeto de tipo GrupoUsuario con
     * el id en referencia
     *
     * @param id es el identificador del objeto al que se quiere eliminar
     */
    @DELETE
    @Path("{idGrupusu}")
    public void eliminarGrupoUsuario(@PathParam("idGrupusu") Long id) throws CustomException, CustomRunTimeException {
        try {
            LOGGER.log(Level.FINE, "Request para eliminar GrupoUsuario con id {0}", id);
            facadeGrupoUsuario.remove(id);
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error eliminando GrupoUsuario... ");
        }
    }

    /**
     * Metodo guardarGrupoUsuario, crea y modifica el objeto de tipo
     * GrupoUsuario del objeto en referencia
     *
     * @param grupoUsuario es el objeto a crear o modificar que llega, si este
     * objeto en su parte id es nulo se crea, sino se modifica
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarGrupoUsuario(GrupoUsuario grupoUsuario) throws CustomException, CustomRunTimeException {
        try {
            if (grupoUsuario.getIdGrupusu() == null) {
                facadeGrupoUsuario.crearGrupoUsuario(grupoUsuario);
            } else {
                facadeGrupoUsuario.modificarGrupoUsuario(grupoUsuario);
            }
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error guardando GrupoUsuario... ");
        }
    }

    /**
     * Metodo actualizaGrupoUsuario, actualiza los perfiles del usuario
     *
     * @param req es un String que contiene los perfiles, usuario y estado a
     * actualizar
     */
    @POST
    @Path("actUsGr/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizaGrupoUsuario(String req) throws CustomException, CustomRunTimeException {
        String[] spl = req.split("-");
        System.out.println(req);
        LOGGER.log(Level.FINE, "Post para actualizat GrupoUsuario con {1}", req);
        Usuario usua = usuarioFacade.find(Long.parseLong(spl[0]));
        Grupo grup = grupoFacade.find(Long.parseLong(spl[1]));
        try {
            facadeGrupoUsuario.actualizaGrupoUsuario(usua, grup, Boolean.parseBoolean(spl[2]));
//          facadeGrupoUsuario.actualizaGrupoUsuario(usua,grup,estado);
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error actualizando AccesoGrupo... ");
        }
    }
}
