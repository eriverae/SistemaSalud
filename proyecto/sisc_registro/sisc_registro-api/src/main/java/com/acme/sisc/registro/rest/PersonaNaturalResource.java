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
import com.acme.sisc.common.errorhandling.CustomExceptionMapper;
import com.acme.sisc.common.errorhandling.ErrorMessage;
import com.acme.sisc.common.exceptions.CustomException;
import com.acme.sisc.common.pagination.PaginatedListWrapper;
import com.acme.sisc.registro.ejb.IPersonaNaturalFacadeLocal;
import com.acme.sisc.registro.pagination.PaginatedListWrapperPN;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
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
 * Servicio REST que provee las funcionalidades para todo tipo de persona
 * natural.
 *
 * @author rreedd
 */
@Path("personaNatural")
@RequestScoped
public class PersonaNaturalResource {

    /**
     * Instancia del logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PersonaNaturalResource.class.getName());

    private static final CustomExceptionMapper mapper = new CustomExceptionMapper();

    @Context
    private UriInfo context;

    /**
     * Bean con el negocio para personas naturales.
     */
    @EJB
    IPersonaNaturalFacadeLocal facadePersonaNatural;

    /**
     * Creates a new instance of ClientesResource
     */
    public PersonaNaturalResource() {
    }

    /**
     * Función por defecto para listar las personas naturales.
     *
     * @param page Número de página.
     * @param sortFields Campo por el cuál se debe ordenar.
     * @param sortDirections Ordenamiento de los registros.
     * @return Página de registros de personas jurídicas.
     */
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
        if (!rol.equals("ADMIN") && !rol.isEmpty()) {
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

    /**
     * Consultar una persona natural dado su identificador.
     *
     * @param id Identificador único.
     * @return Representación de la persona, si existe.
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaNatural consultarPersona(@PathParam("id") Long id) {
        PersonaNatural response = null;
        try {
            LOGGER.log(Level.FINE, "ATENCION id " + id + " \n\n\n");
            LOGGER.log(Level.FINE, "Consultando persona natural con id {0} \n\n\n", id);
            response = facadePersonaNatural.find(id);
            if (response == null) {
                LOGGER.log(Level.FINE, "Consultando persona natural con numero identificacion {0} \n\n\n", id);
                return facadePersonaNatural.findByNumeroIdentificacion(id);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en asociarPacienteEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
        return response;
    }

    /**
     * Consultar una persona natural según su número de identificación.
     *
     * @param numberId Número de identificación.
     * @return Representación de la persona, si existe.
     */
    @GET
    @Path("getByNumber/{numberId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaNatural consultarPersonaPorNumeroIdentificacion(@PathParam("numberId") Long numberId) {
        try {
            LOGGER.log(Level.FINE, "Consultando persona natural con numero identificacion {0} \n\n\n", numberId);
            return facadePersonaNatural.findByNumeroIdentificacion(numberId);
        } catch (CustomException ex) {
            Logger.getLogger(PersonaNaturalResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Consultar una persona natural según su correo electrónico.
     *
     * @param email Correo electrónico.
     * @return Representación de la persona, si existe.
     */
    @GET
    @Path("getByEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaNatural consultarPersonaPorCorreo(@PathParam("email") String email) {
        LOGGER.log(Level.FINE, "Consultando persona natural con correo {0} \n\n\n", email);
        return facadePersonaNatural.findByEmail(email);
    }

    /**
     * Eliminar una persona natural dado su identificador único.
     *
     * @param id Identificador único.
     */
    @DELETE
    @Path("{id}")
    public void eliminarPersona(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.FINE, "Request para eliminar persona natural con id {0}", id);
            facadePersonaNatural.remove(id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en asociarPacienteEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
    }

    /**
     * Crear o actualizar una persona natural.
     *
     * @param personaNatural Entidad representativa de la persona.
     * @return Response exitoso o fallido.
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
            return mapper.toResponse(ex);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en guardarPersonaNatural: " + e.toString() + "\n" + e.getStackTrace());
        }

        return Response.ok(personaNatural).build();
    }

    /**
     * Listado de los beneficiarios de determinado cotizante.
     *
     * @param page Número de página.
     * @param sortFields Campo por el cuál se debe ordenar.
     * @param sortDirections Ordenamiento de los registros.
     * @param cotizante Identificador único del cotizante.
     * @return Página de registros de los beneficiarios asociados.
     */
    @POST
    @Path("beneficiarios")
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper<PersonaNaturalBeneficiario> beneficiarios(
            @DefaultValue("1")
            @QueryParam("page") Integer page,
            @DefaultValue("id")
            @QueryParam("sortFields") String sortFields,
            @DefaultValue("asc")
            @QueryParam("sortDirections") String sortDirections,
            @DefaultValue("0")
            @QueryParam("cotizante") String cotizante) {
        PaginatedListWrapper<PersonaNaturalBeneficiario> plw = new PaginatedListWrapper<>();
        plw.setCurrentPage(page);
        plw.setSortFields(sortFields);
        plw.setSortDirections(sortDirections);
        plw.setPageSize(10);
        return beneficiariosWrapper(plw, cotizante);
    }

    private PaginatedListWrapper<PersonaNaturalBeneficiario> beneficiariosWrapper(PaginatedListWrapper wrapper, String cotizante) {
        try {
            int total = facadePersonaNatural.count();
            wrapper.setTotalResults(total);
            int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
            wrapper.setList(
                    facadePersonaNatural.findBeneficiarios(start,
                            wrapper.getPageSize(),
                            wrapper.getSortFields(),
                            wrapper.getSortDirections(),
                            Long.parseLong(cotizante))
            );
            return wrapper;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en beneficiariosWrapper: " + e.toString() + "\n" + e.getStackTrace());
            return null;
        }
    }

    /**
     * Asociar dos personas naturales como cotizante y beneficiario según
     * determinado parentezco.
     *
     * @param cotizante Identificador único del cotizante.
     * @param beneficiario Identificador único del beneficiario.
     * @param parentezco Parentezco entre ambas personas.
     * @return Resultado exitoso o fallido.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("asociarBeneficiario")
    public Response asociarBeneficiario(@QueryParam("cotizante") String cotizante,
            @QueryParam("beneficiario") String beneficiario,
            @QueryParam("parentezco") String parentezco) {
        try {
            facadePersonaNatural.asociarBeneficiario(Long.valueOf(cotizante),
                    Long.valueOf(beneficiario), Integer.valueOf(parentezco));
        } catch (CustomException ex) {
            return mapper.toResponse(ex);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en guardarPersonaNatural: " + e.toString() + "\n" + e.getStackTrace());
        }
        return Response.ok(0).build();
    }

    /**
     * Listar las EPS registradas en el sistema.
     *
     * @return Listado de personas jurídicas.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaEPS")
    public List<PersonaJuridica> listaEPS() {
        return facadePersonaNatural.listaEPS();
    }

    /**
     * Listar las alergias registradas en el sistema.
     *
     * @return Listado de alergias.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaAlergias")
    public List<Alergia> listaAlergias() {
        return facadePersonaNatural.listaAlergias();
    }

    /**
     * Listar las enfermedades registradas en el sistema.
     *
     * @return Listado de enfermedades.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaEnfermedades")
    public List<Enfermedad> listaEnfermedades() {
        return facadePersonaNatural.listaEnfermedades();
    }

    /**
     * Listar las operaciones registradas en el sistema.
     *
     * @return Listado de operaciones.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaOperaciones")
    public List<Operacion> listaOperacion() {
        return facadePersonaNatural.listaOperaciones();
    }

    /**
     * Asociar un paciente a una EPS y dar por terminada una relación anterior a
     * otra EPS
     *
     * @param paciente Identificador único del paciente.
     * @param eps Identificador único de la EPS.
     * @return Representación del paciente en forma de entidad.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("asociarPacienteEPS")
    public Response asociarPacienteEPS(@QueryParam("paciente") Long paciente, @QueryParam("eps") Long eps) {
        try {
            facadePersonaNatural.asociarPaciente_EPS(paciente, eps);
        } catch (CustomException ex) {
            return mapper.toResponse(ex);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en asociarPacienteEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
        return Response.ok(paciente).build();
    }

    /**
     * Obtener la asociación vigente entre el paciente y una EPS.
     *
     * @param paciente Identificador único del paciente.
     * @return Objeto representativo de la EPS encontrada.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getPacienteEPS/{paciente}")
    public PersonaJuridica getPacienteEPS(@PathParam("paciente") Long paciente) {
        PersonaJuridica response = null;
        try {
            response = facadePersonaNatural.getPaciente_EPS(paciente);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en asociarPacienteEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
        return response;
    }

    /**
     * Asociar un médico a una o más EPS.
     *
     * @param medico Identificador único de la persona.
     * @param eps Identificador único de la EPS.
     * @return Objeto completo del médico con las asociaciones.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("asociarMedicoEPS")
    public Response asociarMedicoEPS(@QueryParam("medico") String medico, @QueryParam("eps") String eps) {
        try {
            System.out.println(medico);
            eps = eps.replace("[", "");
            eps = eps.replace("]", "");
            String[] lista = eps.split(",");

            List<Long> listado = new ArrayList<>();
            for (String elemento : lista) {
                if (!elemento.isEmpty()) {
                    listado.add(Long.valueOf(elemento));
                }
            }

            facadePersonaNatural.asociarMedico_EPS(Long.valueOf(medico), listado);
        } catch (CustomException ex) {
            return mapper.toResponse(ex);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en asociarPacienteEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
        return Response.ok(medico).build();
    }

    /**
     * Obtener las asociaciones del médico con una o más EPS.
     *
     * @param medico Identificador único del médico.
     * @return Lista de EPS asociadas al médico.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getMedicoEPS/{medico}")
    public List<PersonaJuridica> getMedicoEPS(@PathParam("medico") Long medico) {
        List<PersonaJuridica> response = null;
        try {
            response = facadePersonaNatural.getMedico_EPS(medico);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en getMedicoEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
        return response;
    }

    /**
     * Asociar alergias a un paciente.
     *
     * @param paciente Identificador único del paciente.
     * @param alergias Listado de alergias a asociar.
     * @return Objeto Paciente con alergias asociadas.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("asociarPacienteAlergias")
    public Response asociarPacienteAlergias(@QueryParam("paciente") String paciente, @QueryParam("alergias") String alergias) {
        try {
            alergias = alergias.replace("[", "");
            alergias = alergias.replace("]", "");
            String[] lista = alergias.split(",");

            List<Long> listado = new ArrayList<>();
            for (String elemento : lista) {
                listado.add(Long.valueOf(elemento));
            }

            facadePersonaNatural.asociarPaciente_Alergias(Long.valueOf(paciente), listado);
        } catch (CustomException ex) {
            return mapper.toResponse(ex);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en asociarPacienteEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
        return Response.ok(paciente).build();
    }

    /**
     * Asociar enfermedades a un paciente.
     *
     * @param paciente Identificador único del paciente.
     * @param enfermedades Listado de enfermedades del paciente.
     * @return Paciente con enfermedades asociadas.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("asociarPacienteEnfermedades")
    public Response asociarPacienteEnfermedades(@QueryParam("paciente") String paciente, @QueryParam("enfermedades") String enfermedades) {
        try {
            enfermedades = enfermedades.replace("[", "");
            enfermedades = enfermedades.replace("]", "");
            String[] lista = enfermedades.split(",");

            List<Long> listado = new ArrayList<>();
            for (String elemento : lista) {
                listado.add(Long.valueOf(elemento));
            }

            facadePersonaNatural.asociarPaciente_Enfermedades(Long.valueOf(paciente), listado);
        } catch (CustomException ex) {
            return mapper.toResponse(ex);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en asociarPacienteEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
        return Response.ok(paciente).build();
    }

    /**
     * Asociar operaciones realizadas al paciente.
     *
     * @param paciente Identificador único del paciente.
     * @param operaciones Listado de operaciones.
     * @return Paciente con las operaciones asociadas.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("asociarPacienteOperaciones")
    public Response asociarPacienteOperaciones(@QueryParam("paciente") String paciente, @QueryParam("operaciones") String operaciones) {
        try {
            operaciones = operaciones.replace("[", "");
            operaciones = operaciones.replace("]", "");
            String[] lista = operaciones.split(",");

            List<Long> listado = new ArrayList<>();
            for (String elemento : lista) {
                listado.add(Long.valueOf(elemento));
            }

            facadePersonaNatural.asociarPaciente_Operaciones(Long.valueOf(paciente), listado);
        } catch (CustomException ex) {
            return mapper.toResponse(ex);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en asociarPacienteEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
        return Response.ok(paciente).build();
    }

    /**
     * Obtener las alergias asociadas a un paciente.
     *
     * @param paciente Identificador único del paciente.
     * @return Listado de alergias asociadas.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAlergiasPaciente/{paciente}")
    public List<Alergia> getAlergiasPaciente(@PathParam("paciente") Long paciente) {
        List<Alergia> response = null;
        try {
            response = facadePersonaNatural.getAlergiasPaciente(paciente);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en asociarPacienteEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
        return response;
    }

    /**
     * Obtener las enfermedades de un paciente.
     *
     * @param paciente Identificador único del paciente.
     * @return Enfermedades asociadas al paciente.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getEnfermedadesPaciente/{paciente}")
    public List<Enfermedad> getEnfermedadesPaciente(@PathParam("paciente") Long paciente) {
        List<Enfermedad> response = null;
        try {
            response = facadePersonaNatural.getEnfermedadesPaciente(paciente);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en asociarPacienteEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
        return response;
    }

    /**
     * Obtener las operaciones de un paciente.
     *
     * @param paciente Identificador único del paciente.
     * @return Listado de operaciones hechas al paciente.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getOperacionesPaciente/{paciente}")
    public List<Operacion> getOperacionesPaciente(@PathParam("paciente") Long paciente) {
        List<Operacion> response = null;
        try {
            response = facadePersonaNatural.getOperacionesPaciente(paciente);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en asociarPacienteEPS: " + e.toString() + "\n" + e.getStackTrace());
        }
        return response;
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
