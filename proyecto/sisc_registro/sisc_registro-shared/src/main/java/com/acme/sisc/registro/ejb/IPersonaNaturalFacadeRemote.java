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
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author rreedd
 */
@Remote
public interface IPersonaNaturalFacadeRemote {

    int count();

    PersonaNatural crearPersonaNatural(PersonaNatural personaNatural) throws CustomException;

    PersonaNatural find(Object id);

    List<PersonaNatural> findAll();
    
    List<PersonaNatural> findPersonaNaturalPorRol(int startPosition, int maxResults, String sortFields, String sortDirections, String rol);

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByIdentificacion(TipoIdentificacion tId, long identificacion);

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByNumeroIdentificacion(long identificacion);

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByEmail(String email);

    List<PersonaNatural> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    PersonaNatural modificarPersonaNatural(PersonaNatural personaNatural) throws CustomException;

    void remove(PersonaNatural entity);
        
    public void remove(Long id);
    
    List<PersonaNaturalBeneficiario> findBeneficiarios(int startPosition, int maxResults, String sortFields, String sortDirections, long cotizante);
    
    void asociarBeneficiario(PersonaNatural cotizante, PersonaNatural beneficiario, int parentezco);
    
    void removerBeneficiario(PersonaNaturalBeneficiario beneficiario);
    
    List<PersonaJuridica> listaEPS();
    
    List<Alergia> listaAlergias();
    
    List<Enfermedad> listaEnfermedades();
    
    List<Operacion> listaOperaciones();
    
    List<PersonaNatural> medicosPorEspecialidadFindRange(int startPosition, int maxResults, String sortFields, String sortDirections, Long especialidad, Long eps);
    
    void asociarPaciente_EPS(Long paciente, Long eps) throws CustomException;
    
    PersonaJuridica getPaciente_EPS(Long paciente) throws CustomException;
    
    void asociarMedico_EPS(Long medico, List<Long> eps) throws CustomException;
    
    List<PersonaJuridica> getMedico_EPS(Long medico) throws CustomException;
    
    void asociarPaciente_Alergias(Long paciente, List<Long> alergias) throws CustomException;
    
    void asociarPaciente_Enfermedades(Long paciente, List<Long> enfermedades) throws CustomException;
    
    void asociarPaciente_Operaciones(Long paciente, List<Long> operaciones) throws CustomException;
}
