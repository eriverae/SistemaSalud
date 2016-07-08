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
import com.acme.sisc.seguridad.AccesoGrupoFacadeLocal;
import com.acme.sisc.seguridad.dto.GrupoCompleto;
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
* Este es el servicio para crear, modificar, consultar y eliminar las operaciones 
* que se realizan los perfiles
* 
* @author  Julio
* @version 1.0
* @since   2016-05-22
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
    AccesoGrupoFacadeLocal facadeAccesoGrupo;

    /**
    * Constructor del servicio GrupoResource
    *
    */
    public GrupoResource() {
    }
    
    /**
    * Metodo listGrupos, retorna un PaginatedListWrapper de los perfiles a asociar al usuario
    * @param page es el numero actual de paginated
    * @param sortFields es el id del sortField actual de la pagina 
    * @param sortDirections es el orden de paginated por defecto asc
    * @return retorna PaginatedListWrapper con la tabla armada a partir la busqueda del objeto en referencia
    */
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
    
    /**
    * Metodo findGrupos, arma y devuelve el PaginatedListWrapper implementando los metodos del facade
    * @param wrapper es el PaginatedListWrapper que recibe
    * @return retorna wrapper con la informacion que consulta y setea en el facade
    */
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
    
    /**
    * Metodo consultarGrupo, devuelve un objeto de tipo GrupoCompleto que trae desde el facade con un id
    * @param id es el identificador del objeto al que se quiere consultar
    * @return retorna GrupoCompleto dto, el el objeto de perfiles y accesos del usuario
    */
    @GET
    @Path("{grupGrup}")
    @Produces(MediaType.APPLICATION_JSON)
    public GrupoCompleto consultarGrupo(@PathParam("grupGrup") Long id){
        GrupoCompleto GC = new GrupoCompleto();
        GC.setGrupo(facadeGrupo.find(id));
        GC.setAccesos(facadeAccesoGrupo.findByGrupGrup(id));
        return GC;

    }
    
    /**
    * Metodo eliminarGrupo, elimina el objeto de tipo Grupo con el id en referencia
    * @param id es el identificador del objeto al que se quiere eliminar
    */
    @DELETE
    @Path("{grupGrup}")
    public void eliminarGrupo(@PathParam("grupGrup") Long id){
      LOGGER.log(Level.FINE,"Request para eliminar acceso con id {0}", id);
      facadeGrupo.remove(id);
    }

    /**
    * Metodo guardarAcceso, crea y modifica el objeto de tipo Grupo del objeto en referencia
    * @param grupo es el objeto a crear o modificar que llega, si este objeto en su parte id es nulo se crea, sino se modifica
    */
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
