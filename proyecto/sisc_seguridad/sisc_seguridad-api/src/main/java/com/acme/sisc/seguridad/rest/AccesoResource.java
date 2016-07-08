/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import com.acme.sisc.agenda.entidades.Acceso;
import com.acme.sisc.seguridad.AccesoFacadeLocal;
import com.acme.sisc.common.pagination.PaginatedListWrapper;
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
* Este es el servicio para crear, modificar, consultar y eliminar las operaciones 
* que se realizan sobre los Accesos
* 
* @author  Julio
* @version 1.0
* @since   2016-05-07    
*/
@Path("accesos")
@RequestScoped
public class AccesoResource {
    
    private static final Logger LOGGER = Logger.getLogger(AccesoResource.class.getName());
    
    @Context
    private UriInfo context;
    
    @EJB
    AccesoFacadeLocal facadeAcceso;

    /**
    * Constructor del servicio AccesoResource
    *
    */
    public AccesoResource() {
    }
    
    /**
    * Metodo listAccesos, retorna un PaginatedListWrapper con accesos del perfil
    * @param page es el numero actual de paginated
    * @param sortFields es el id del sortField actual de la pagina 
    * @param sortDirections es el orden de paginated por defecto asc
    * @return retorna PaginatedListWrapper con la tabla armada a partir la busqueda del objeto en referencia
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper listAccesos(@DefaultValue("1")
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
        return findAccesos(paginatedListWrapper);
    }
    
    /**
    * Metodo findAccesos, arma y devuelve el PaginatedListWrapper implementando los metodos JPA del facade
    * @param wrapper es el PaginatedListWrapper que recibe
    * @return retorna wrapper con la informacion que consulta y setea en el facade
    */
    private PaginatedListWrapper findAccesos(PaginatedListWrapper wrapper) {
        int totalAccesos = facadeAcceso.count();
        wrapper.setTotalResults(totalAccesos);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(facadeAcceso.findRange(start,
                wrapper.getPageSize(),
                wrapper.getSortFields(),
                wrapper.getSortDirections()));
        return wrapper;
    }
    
    /**
    * Metodo consultarAcceso, devuelve un objeto de tipo Acceso que trae desde el facade con un id
    * @param id es el identificador del objeto al que se quiere consultar
    * @return retorna Acceso, el el objeto accesos del perfil
    */
    @GET
    @Path("{acceAcce}")
    @Produces(MediaType.APPLICATION_JSON)
    public Acceso consultarAcceso(@PathParam("acceAcce") Long id){
        return facadeAcceso.find(id);

    }
    
    /**
    * Metodo eliminarAcceso, elimina el objeto de tipo Acceso con el id en referencia
    * @param id es el identificador del objeto al que se quiere eliminar
    */
    @DELETE
    @Path("{acceAcce}")
    public void eliminarAcceso(@PathParam("acceAcce") Long id){
      LOGGER.log(Level.FINE,"Request para eliminar acceso con id {0}", id);
      facadeAcceso.remove(id);
    }

    /**
    * Metodo guardarAcceso, crea y modifica el objeto de tipo Acceso del objeto en referencia
    * @param acceso es el objeto a crear o modificar que llega, si este objeto en su parte id es nulo se crea, sino se modifica
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarAcceso(Acceso acceso) {
      try {
        if (acceso.getAcceAcce() == null){
          facadeAcceso.crearAcceso(acceso);
        }else{
          facadeAcceso.modificarAcceso(acceso);
        }
      }catch (Exception e){
          //TODO Definir manejo
          LOGGER.log(Level.SEVERE, "Houston, estamos en problemas ...", e);
      }
    }
    
}
