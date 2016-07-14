package com.acme.sisc.registro.ejb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.acme.sisc.agenda.entidades.Alergia;
import com.acme.sisc.agenda.entidades.Enfermedad;
import com.acme.sisc.agenda.entidades.Operacion;
import com.acme.sisc.agenda.entidades.PersonaJuridica;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.agenda.entidades.PersonaNaturalBeneficiario;
import com.acme.sisc.agenda.entidades.TipoIdentificacion;
import com.acme.sisc.common.exceptions.CustomException;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author rreedd
 */
@Local
public interface IPersonaNaturalFacadeLocal{

    /**
     * Cantidad de personas naturales registradas en el sistema.
     * @return Cantidad.
     */
    int count();

    /**
     * Crear una persona natural.
     * @param personaNatural Datos de la persona natural.
     * @return La misma persona natural con identificador único.
     * @throws CustomException 
     */
    PersonaNatural crearPersonaNatural(PersonaNatural personaNatural) throws CustomException;

    /**
     * Consultar una persona natural según su identificador único.
     * @param id Identificador único.
     * @return Objeto PersonaNatural, si existe.
     */
    PersonaNatural find(Object id);

    /**
     * Listar todas las personas naturales.
     * @return Lista con objetos PersonaNatural.
     */
    List<PersonaNatural> findAll();
    
    /**
     * Buscar personas naturales según un rol.
     * @param startPosition Posición inicial.
     * @param maxResults Máximo de resultados.
     * @param sortFields Campo por el cual ordenar la lista.
     * @param sortDirections Dirección de orden.
     * @param rol Rol a buscar.
     * @return Listado de personas.
     */
    List<PersonaNatural> findPersonaNaturalPorRol(int startPosition, int maxResults, String sortFields, String sortDirections, String rol);

    /**
     * Buscar una persona según su tipo y número de identificación.
     * @param tId Tipo identificación.
     * @param identificacion Número identificación.
     * @return Objeto Persona, si existe.
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByIdentificacion(TipoIdentificacion tId, long identificacion) throws CustomException;

    /**
     * Buscar persona por número de identificación.
     * @param identificacion Número de identificación.
     * @return Objeto Persona, si existe.
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByNumeroIdentificacion(long identificacion) throws CustomException;

    /**
     * Buscar una persona según su correo electrónico.
     * @param email Correo electrónico a buscar.
     * @return Objeto Persona, si existe.
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByEmail(String email);

    /**
     * Buscar un listado de personas naturales.
     * @param startPosition Posición inicial.
     * @param maxResults Máximo de resultados.
     * @param sortFields Campo por el cual ordenar la lista.
     * @param sortDirections Dirección de orden.
     * @return Listado de personas naturales.
     */
    List<PersonaNatural> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    /**
     * Actualizar una persona natural.
     * @param p Persona natural.
     * @return Persona natural.
     */
    PersonaNatural modificarPersonaNatural(PersonaNatural personaNatural) throws CustomException;

    /**
     * Eliminar una persona natural.
     * @param entity Persona natural.
     */
    void remove(PersonaNatural entity);
    
    /**
     * Eliminar una persona natural según su identificador único.
     * @param id Identificador único.
     */
    void remove(Long id) throws CustomException;
    
    /**
     * Listado de beneficiarios de un cotizante.
     * @param startPosition Posición inicial.
     * @param maxResults Máximo de resultados.
     * @param sortFields Campo por el cual ordenar la lista.
     * @param sortDirections Dirección de orden.
     * @param cotizante Persona natural asociada a cierta EPS.
     * @return Listado de beneficiarios.
     */
    List<PersonaNaturalBeneficiario> findBeneficiarios(int startPosition, int maxResults, String sortFields, String sortDirections, long cotizante) throws CustomException;
    
    /**
     * Asociar una persona natural como beneficiario de determinado 
     * cotizante.
     * @param cotizante Persona natural asociada a una EPS.
     * @param beneficiario Familiar del cotizante.
     * @param parentezco Tipo de familiar.
     * @throws CustomException 
     */
    void asociarBeneficiario(Long cotizante, Long beneficiario, int parentezco) throws CustomException;
    
    /**
     * Eliminar relación entre beneficiario y cotizante.
     * @param beneficiario 
     */
    void removerBeneficiario(PersonaNaturalBeneficiario beneficiario) throws CustomException;
    
    /**
     * Listar todas las EPS del sistema.
     * @return Lista de objetos PersonaJuridica.
     */
    List<PersonaJuridica> listaEPS();
    
    /**
     * Listar todas las alergias del sistema.
     * @return Listado de objetos Alergia.
     */
    List<Alergia> listaAlergias();
    
    /**
     * Listar todas las enfermedades del sistema.
     * @return Listado de objetos Enfermedad.
     */
    List<Enfermedad> listaEnfermedades();
        /**
     * Listar todas las operaciones del sistema.
     * @return Listado de objetos Operacion.
     */
    List<Operacion> listaOperaciones();
    
    /**
     * Consultar médicos según una EPS y una especialidad.
     * @param startPosition Posición inicial.
     * @param maxResults Máximo de resultados.
     * @param sortFields Campo por el cual ordenar la lista.
     * @param sortDirections Dirección de orden.
     * @param especialidad Identificador único de la especialidad.
     * @param eps Identificador único de la EPS.
     * @return Listado de los médicos que cumplan con los filtros.
     */
    List<PersonaNatural> medicosPorEspecialidadFindRange(int startPosition, int maxResults, String sortFields, String sortDirections, Long especialidad, Long eps);
    
    /**
     * Cambiar la EPS actual de determinado paciente.
     * @param paciente Identificador único del paciente.
     * @param eps Identificador único de la EPS.
     * @throws CustomException 
     */
    void asociarPaciente_EPS(Long paciente, Long eps) throws CustomException;
    
    /**
     * Consultar la EPS actual del paciente.
     * @param paciente Identificador único del paciente.
     * @return Objeto PersonaJuridica.
     * @throws CustomException 
     */
    PersonaJuridica getPaciente_EPS(Long paciente) throws CustomException;
    
    /**
     * Asociar un médico a una o más EPS.
     * @param medico Identificador único del médico.
     * @param eps Identificador único de la EPS.
     * @throws CustomException 
     */
    void asociarMedico_EPS(Long medico, List<Long> eps) throws CustomException;
    
    /**
     * Obtener el listado de EPS asociadas a determinado médico.
     * @param medico Identificador único del médico.
     * @return Listado de PersonaJuridica.
     * @throws CustomException 
     */
    List<PersonaJuridica> getMedico_EPS(Long medico) throws CustomException;
    
    /**
     * Asociar determinado paciente a una o más alergias.
     * @param paciente Identificador único del paciente.
     * @param alergias Listado de identificadores de las alergias.
     * @throws CustomException 
     */
    void asociarPaciente_Alergias(Long paciente, List<Long> alergias) throws CustomException;
    
    /**
     * Asociar determinado paciente a una o más enfermedades.
     * @param paciente Identificador único del paciente.
     * @param enfermedades Listado de identificadores de las enfermedades.
     * @throws CustomException 
     */
    void asociarPaciente_Enfermedades(Long paciente, List<Long> enfermedades) throws CustomException;
    
    /**
     * Asociar determinado paciente a una o más operaciones.
     * @param paciente Identificador único del paciente.
     * @param operaciones Listado de identificadores de las operaciones.
     * @throws CustomException 
     */
    void asociarPaciente_Operaciones(Long paciente, List<Long> operaciones) throws CustomException;
    
    /**
     * Obtener el listado de alergias asociadas a determinado paciente.
     * @param paciente Identificador único del paciente.
     * @return Listadop de Alergia.
     * @throws CustomException 
     */
    List<Alergia> getAlergiasPaciente(Long paciente) throws CustomException;
    
    /**
     * Obtener el listado de enfermedades asociadas a determinado paciente.
     * @param paciente Identificador único del paciente.
     * @return Listado de Enfermedad.
     * @throws CustomException 
     */
    List<Enfermedad> getEnfermedadesPaciente(Long paciente) throws CustomException;
    
    /**
     * Obtener el listado de operaciones asociadas a determinado paciente.
     * @param paciente Identificador único del paciente.
     * @return Listado de Operacion.
     * @throws CustomException 
     */
    List<Operacion> getOperacionesPaciente(Long paciente) throws CustomException;
}
