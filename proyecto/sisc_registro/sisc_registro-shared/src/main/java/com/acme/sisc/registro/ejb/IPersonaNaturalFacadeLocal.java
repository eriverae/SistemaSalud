package com.acme.sisc.registro.ejb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

    int count();

    void crearPersonaNatural(PersonaNatural personaNatural) throws CustomException;

    PersonaNatural find(Object id);

    List<PersonaNatural> findAll();

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByIdentificacion(TipoIdentificacion tId, long identificacion);

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByNumeroIdentificacion(long identificacion);

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByEmail(String email);

    List<PersonaNatural> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    PersonaNatural modificarPersonaNatural(PersonaNatural personaNatural) throws CustomException;

    void remove(PersonaNatural entity);
    
    void remove(Long id);
    
    List<PersonaNatural> medicosPorEspecialidadFindRange(int startPosition, int maxResults, String sortFields, String sortDirections, Long especialidad);
    
    List<PersonaNaturalBeneficiario> findBeneficiarios(int startPosition, int maxResults, String sortFields, String sortDirections, long cotizante);
    
    void asociarBeneficiario(PersonaNatural cotizante, PersonaNatural beneficiario, int parentezco);
    
    void removerBeneficiario(PersonaNaturalBeneficiario beneficiario);
}
