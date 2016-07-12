/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import com.acme.sisc.agenda.entidades.Acceso;
import com.acme.sisc.agenda.entidades.AccesoGrupo;
import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.common.pagination.PaginatedListWrapper;
import com.acme.sisc.seguridad.AccesoFacadeLocal;
import com.acme.sisc.seguridad.AccesoGrupoFacadeLocal;
import com.acme.sisc.seguridad.GrupoFacadeLocal;
import com.acme.sisc.common.exceptions.CustomException;
import com.acme.sisc.common.exceptions.CustomRunTimeException;
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
 * operaciones que se realizan sobre la relacion de perfiles y accesos
 *
 * @author Julio
 * @version 1.0
 * @since 2016-05-22
 */
@Path("accesoGrupo")
@RequestScoped
public class AccesoGrupoResource {

    private static final Logger LOGGER = Logger.getLogger(AccesoGrupoResource.class.getName());

    @Context
    private UriInfo context;

    @EJB
    AccesoGrupoFacadeLocal facadeAccesoGrupo;
    @EJB
    GrupoFacadeLocal grupoFacade;
    @EJB
    AccesoFacadeLocal accesoFacade;

    /**
     * Constructor del servicio AccesoGrupoResource
     *
     */
    public AccesoGrupoResource() {
    }

    /**
     * Metodo listAccesos, retorna un PaginatedListWrapper de los perfiles y
     * accesos del usuario
     *
     * @param page es el numero actual de paginated
     * @param sortFields es el id del sortField actual de la pagina
     * @param sortDirections es el orden de paginated por defecto asc
     * @return retorna PaginatedListWrapper con la tabla armada a partir la
     * busqueda del objeto en referencia
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper listAccesos(@DefaultValue("1")
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
        return findAccesos(paginatedListWrapper);
    }

    /**
     * Metodo findAccesos, arma y devuelve el PaginatedListWrapper implementando
     * los metodos del facade
     *
     * @param wrapper es el PaginatedListWrapper que recibe
     * @return retorna wrapper con la informacion que consulta y setea en el
     * facade
     */
    private PaginatedListWrapper findAccesos(PaginatedListWrapper wrapper) {
        int totalAccesos = facadeAccesoGrupo.count();
        wrapper.setTotalResults(totalAccesos);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(facadeAccesoGrupo.findRange(start,
                wrapper.getPageSize(),
                wrapper.getSortFields(),
                wrapper.getSortDirections()));
        return wrapper;
    }

    /**
     * Metodo consultarAcceso, devuelve un objeto de tipo AccesoGrupo que trae
     * desde el facade con un id
     *
     * @param id es el identificador del objeto al que se quiere consultar
     * @return retorna AccesoGrupo, el el objeto de perfiles y accesos del
     * usuario
     */
    @GET
    @Path("{idAccgrup}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccesoGrupo consultarAcceso(@PathParam("idAccgrup") Long id) throws CustomException, CustomRunTimeException {
        try {
            return facadeAccesoGrupo.find(id);
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error consultando AccesoGrupo... ");
        }
    }

    /**
     * Metodo eliminarAcceso, elimina el objeto de tipo AccesoGrupo con el id en
     * referencia
     *
     * @param id es el identificador del objeto al que se quiere eliminar
     */
    @DELETE
    @Path("{idAccgrup}")
    public void eliminarAcceso(@PathParam("idAccgrup") Long id) throws CustomException, CustomRunTimeException {
        try {
            LOGGER.log(Level.FINE, "Request para eliminar AccesoGrupo con id {0}", id);
            facadeAccesoGrupo.remove(id);
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error eliminando AccesoGrupo... ");
        }
    }

    /**
     * Metodo guardarAcceso, crea y modifica el objeto de tipo AccesoGrupo del
     * objeto en referencia
     *
     * @param accgrup es el objeto a crear o modificar que llega, si este objeto
     * en su parte id es nulo se crea, sino se modifica
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarAcceso(AccesoGrupo accgrup) throws CustomException, CustomRunTimeException {
        try {
            if (accgrup.getIdAccgrup() == null) {
                facadeAccesoGrupo.crearAccesoGrupo(accgrup);
            } else {
                facadeAccesoGrupo.modificarGrupoAcceso(accgrup);
            }
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error guardando AccesoGrupo... ");
        }
    }

    /**
     * Metodo actualizaAccesoGrupo, actualiza los perfiles y accesos del usuario
     * que lo consume
     *
     * @param req es un String que contiene los perfiles, accesos y estado a
     * actualizar
     */
    @POST
    @Path("actAccGr/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizaAccesoGrupo(String req) throws CustomException, CustomRunTimeException {
        String[] spl = req.split("-");
        System.out.println(req);
        LOGGER.log(Level.FINE, "Post para actualizat GrupoUsuario con {1}", req);
        Grupo grup = grupoFacade.find(Long.parseLong(spl[0]));
        Acceso acc = accesoFacade.find(Long.parseLong(spl[1]));
        try {
            facadeAccesoGrupo.actualizaAccesoGrupo(grup, acc, Boolean.parseBoolean(spl[2]));
//          facadeGrupoUsuario.actualizaGrupoUsuario(usua,grup,estado);
        } catch (Exception ex) {
            throw new CustomException(
                    Response.Status.BAD_REQUEST.getStatusCode(), 603,
                    "Error guardando AccesoGrupo... ");
        }
    }

}
