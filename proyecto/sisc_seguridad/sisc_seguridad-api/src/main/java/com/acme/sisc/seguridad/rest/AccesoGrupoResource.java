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

    public AccesoGrupoResource() {
    }
    
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
    
    @GET
    @Path("{idAccgrup}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccesoGrupo consultarAcceso(@PathParam("idAccgrup") Long id){
        return facadeAccesoGrupo.find(id);

    }
    
    @DELETE
    @Path("{idAccgrup}")
    public void eliminarAcceso(@PathParam("idAccgrup") Long id){
      LOGGER.log(Level.FINE,"Request para eliminar AccesoGrupo con id {0}", id);
      facadeAccesoGrupo.remove(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarAcceso(AccesoGrupo accgrup) {
      try {
        if (accgrup.getIdAccgrup() == null){
          facadeAccesoGrupo.crearAccesoGrupo(accgrup);
        }else{
          facadeAccesoGrupo.modificarGrupoAcceso(accgrup);
        }
      }catch (Exception e){
          //TODO Definir manejo
          LOGGER.log(Level.SEVERE, "Houston, estamos en problemas ...", e);
      }
    }
    
    @POST
    @Path("actAccGr/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizaAccesoGrupo(String req) {
        String[] spl = req.split("-");
        System.out.println(req);
        LOGGER.log(Level.FINE,"Post para actualizat GrupoUsuario con {1}", req);
        Grupo grup = grupoFacade.find(Long.parseLong(spl[0]));
        Acceso acc = accesoFacade.find(Long.parseLong(spl[1]));
      try {
          facadeAccesoGrupo.actualizaAccesoGrupo(grup,acc,Boolean.parseBoolean(spl[2]));
//          facadeGrupoUsuario.actualizaGrupoUsuario(usua,grup,estado);
      }catch (Exception e){
          //TODO Definir manejo
          LOGGER.log(Level.SEVERE, "Houston, estamos en problemas ...", e);
      }
    }
    
}
