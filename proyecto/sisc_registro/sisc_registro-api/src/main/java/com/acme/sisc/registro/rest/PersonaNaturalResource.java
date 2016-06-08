/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.registro.rest;

import com.acme.sisc.agenda.entidades.Alergia;
import com.acme.sisc.agenda.entidades.Enfermedad;
import com.acme.sisc.agenda.entidades.Operacion;
import com.acme.sisc.agenda.entidades.PersonaJuridica;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.agenda.entidades.PersonaNaturalBeneficiario;
import com.acme.sisc.common.errorhandling.ErrorMessage;
import com.acme.sisc.common.exceptions.CustomException;
import com.acme.sisc.common.pagination.PaginatedListWrapper;
import com.acme.sisc.registro.ejb.IPersonaNaturalFacadeLocal;
import com.acme.sisc.registro.pagination.PaginatedListWrapperPN;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author rreedd
 */
@Path("personaNatural")
@RequestScoped
public class PersonaNaturalResource {

    private static final Logger LOGGER = Logger.getLogger(PersonaNaturalResource.class.getName());

    @Context
    private UriInfo context;

    @EJB
    IPersonaNaturalFacadeLocal facadePersonaNatural;

    /**
     * Creates a new instance of ClientesResource
     */
    public PersonaNaturalResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper<PersonaNatural> listPersonaNatural(@DefaultValue("1")
            @QueryParam("page") Integer page,
            @DefaultValue("id")
            @QueryParam("sortFields") String sortFields,
            @DefaultValue("asc")
            @QueryParam("sortDirections") String sortDirections,
            @DefaultValue("ADMIN")
            @QueryParam("rol") String rol) {
        PaginatedListWrapper<PersonaNatural> paginatedListWrapper = new PaginatedListWrapper<>();
        paginatedListWrapper.setCurrentPage(page);
        paginatedListWrapper.setSortFields(sortFields);
        paginatedListWrapper.setSortDirections(sortDirections);
        paginatedListWrapper.setPageSize(10);
        if(!rol.equals("ADMIN") && !rol.isEmpty()){
            return personasPorRolWrapper(paginatedListWrapper, rol);
        }
        return findPersonaNatural(paginatedListWrapper);
    }

    private PaginatedListWrapper<PersonaNatural> findPersonaNatural(PaginatedListWrapper<PersonaNatural> wrapper) {
        int total = facadePersonaNatural.count();
        wrapper.setTotalResults(total);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(facadePersonaNatural.findRange(start,
                wrapper.getPageSize(),
                wrapper.getSortFields(),
                wrapper.getSortDirections()));
        return wrapper;
    }
    
    private PaginatedListWrapper<PersonaNatural> personasPorRolWrapper(PaginatedListWrapper<PersonaNatural> wrapper, String rol) {
        int total = facadePersonaNatural.count();
        wrapper.setTotalResults(total);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(
                facadePersonaNatural.findPersonaNaturalPorRol(start,
                        wrapper.getPageSize(),
                        wrapper.getSortFields(),
                        wrapper.getSortDirections(),
                        rol)
        );
        return wrapper;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaNatural consultarPersona(@PathParam("id") Long id) {
        PersonaNatural response = null;
        LOGGER.log(Level.FINE, "ATENCION id " + id + " \n\n\n");
        LOGGER.log(Level.FINE, "Consultando persona natural con id {0} \n\n\n", id);
        response = facadePersonaNatural.find(id);
        if (response == null) {
            LOGGER.log(Level.FINE, "Consultando persona natural con numero identificacion {0} \n\n\n", id);
            return facadePersonaNatural.findByNumeroIdentificacion(id);
        }
        return response;
    }

    @GET
    @Path("getByNumber/{numberId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaNatural consultarPersonaPorNumeroIdentificacion(@PathParam("numberId") Long numberId) {
        LOGGER.log(Level.FINE, "Consultando persona natural con numero identificacion {0} \n\n\n", numberId);
        return facadePersonaNatural.findByNumeroIdentificacion(numberId);
    }

    
    @GET
    @Path("getByEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaNatural consultarPersonaPorCorreo(@PathParam("email") String email) {
        LOGGER.log(Level.FINE, "Consultando persona natural con correo {0} \n\n\n", email);
        return facadePersonaNatural.findByEmail(email);
    }

    
    @DELETE
    @Path("{id}")
    public void eliminarPersona(@PathParam("id") Long id) {
        LOGGER.log(Level.FINE, "Request para eliminar persona natural con id {0}", id);
        facadePersonaNatural.remove(id);
    }

    /**
     * PUT method for updating or creating an instance of PersonaNaturalResource
     *
     * @param PersonaNatural representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarPersonaNatural(PersonaNatural personaNatural) {
        try {
            if (personaNatural.getIdPersona() == null) {
                personaNatural = facadePersonaNatural.crearPersonaNatural(personaNatural);
            } else {
                personaNatural = facadePersonaNatural.modificarPersonaNatural(personaNatural);
            }
        } catch (CustomException ex) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setCode(ex.getErrorCode());
            errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
            errorMessage.setMessage(ex.getMessage());
            StringWriter errorStackTrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(errorStackTrace));
            errorMessage.setDeveloperMessage(errorStackTrace.toString());

            return Response.status(errorMessage.getStatus())
                    .entity(errorMessage)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.ok(personaNatural).build();
    }

    @GET
    @Path("beneficiarios/{page}/{sortFields}/{sortDirections}/{cotizante}")
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper<PersonaNaturalBeneficiario> beneficiarios(
            @DefaultValue("1")
            @QueryParam("page") Integer page,
            @DefaultValue("id")
            @QueryParam("sortFields") String sortFields,
            @DefaultValue("asc")
            @QueryParam("sortDirections") String sortDirections,
            @DefaultValue("0")
            @QueryParam("cotizante") Long cotizante) {
        PaginatedListWrapper<PersonaNaturalBeneficiario> plw = new PaginatedListWrapper<>();
        plw.setCurrentPage(page);
        plw.setSortFields(sortFields);
        plw.setSortDirections(sortDirections);
        plw.setPageSize(10);
        return beneficiariosWrapper(plw, cotizante);
    }

    private PaginatedListWrapper<PersonaNaturalBeneficiario> beneficiariosWrapper(PaginatedListWrapper wrapper, Long cotizante) {
        int total = facadePersonaNatural.count();
        wrapper.setTotalResults(total);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(
                facadePersonaNatural.findBeneficiarios(start,
                        wrapper.getPageSize(),
                        wrapper.getSortFields(),
                        wrapper.getSortDirections(),
                        cotizante)
        );
        return wrapper;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("asociarBeneficiario")
    public void asociarBeneficiario(PersonaNatural cotizante, 
                                    PersonaNatural beneficiario,
                                    int parentezco){
        try{
            facadePersonaNatural.asociarBeneficiario(cotizante, beneficiario, parentezco);
        }
        catch(Exception ex){
            
        }
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaEPS")
    public List<PersonaJuridica> listaEPS(){
        return facadePersonaNatural.listaEPS();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaAlergias")
    public List<Alergia> listaAlergias(){
        return facadePersonaNatural.listaAlergias();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaEnfermedades")
    public List<Enfermedad> listaEnfermedades(){
        return facadePersonaNatural.listaEnfermedades();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaOperaciones")
    public List<Operacion> listaOperacion(){
        return facadePersonaNatural.listaOperaciones();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("asociarPacienteEPS")
    public Response asociarPacienteEPS(@QueryParam("paciente") Long paciente, @QueryParam("eps") Long eps) {
        try {
            facadePersonaNatural.asociarPaciente_EPS(paciente, eps);
        } catch (CustomException ex) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setCode(ex.getErrorCode());
            errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
            errorMessage.setMessage(ex.getMessage());
            StringWriter errorStackTrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(errorStackTrace));
            errorMessage.setDeveloperMessage(errorStackTrace.toString());

            return Response.status(errorMessage.getStatus())
                    .entity(errorMessage)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.ok(paciente).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getPacienteEPS/{paciente}")
    public PersonaJuridica getPacienteEPS(@PathParam("paciente") Long paciente) {
        PersonaJuridica response = null;
        try {
            response = facadePersonaNatural.getPaciente_EPS(paciente);
        } catch (CustomException ex) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setCode(ex.getErrorCode());
            errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
            errorMessage.setMessage(ex.getMessage());
            StringWriter errorStackTrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(errorStackTrace));
            errorMessage.setDeveloperMessage(errorStackTrace.toString());

        }
        return response;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("asociarMedicoEPS")
    public Response asociarMedicoEPS(@QueryParam("medico") Long medico, @QueryParam("eps") List<Long> eps) {
        try {
            facadePersonaNatural.asociarMedico_EPS(medico, eps);
        } catch (CustomException ex) {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setCode(ex.getErrorCode());
            errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
            errorMessage.setMessage(ex.getMessage());
            StringWriter errorStackTrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(errorStackTrace));
            errorMessage.setDeveloperMessage(errorStackTrace.toString());

            return Response.status(errorMessage.getStatus())
                    .entity(errorMessage)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.ok(medico).build();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Servicios a módulos
    ////////////////////////////////////////////////////////////////////////////
    @GET
    @Path("medicosPorEspecialidad/{page}/{sortFields}/{sortDirections}/{especialidad}/{eps}")
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapperPN medicosPorEspecialidad(
            @DefaultValue("1")
            @PathParam("page") Integer page,
            @DefaultValue("id")
            @PathParam("sortFields") String sortFields,
            @DefaultValue("asc")
            @PathParam("sortDirections") String sortDirections,
            @DefaultValue("0")
            @PathParam("especialidad") Long especialidad,
            @DefaultValue("0")
            @PathParam("eps") Long eps) {
        PaginatedListWrapperPN paginatedListWrapper = new PaginatedListWrapperPN();
        paginatedListWrapper.setCurrentPage(page);
        paginatedListWrapper.setSortFields(sortFields);
        paginatedListWrapper.setSortDirections(sortDirections);
        paginatedListWrapper.setPageSize(10);
        return medicosPorEspecialidadWrapper(paginatedListWrapper, especialidad, eps);
    }

    private PaginatedListWrapperPN medicosPorEspecialidadWrapper(PaginatedListWrapperPN wrapper, Long especialidad, Long eps) {
        int total = facadePersonaNatural.count();
        wrapper.setTotalResults(total);
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(
                facadePersonaNatural.medicosPorEspecialidadFindRange(start,
                        wrapper.getPageSize(),
                        wrapper.getSortFields(),
                        wrapper.getSortDirections(),
                        especialidad, eps)
        );
        return wrapper;
    }
}
