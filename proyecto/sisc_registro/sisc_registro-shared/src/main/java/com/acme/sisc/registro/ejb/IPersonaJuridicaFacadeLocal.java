package com.acme.sisc.registro.ejb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.acme.sisc.agenda.entidades.PersonaJuridica;
import com.acme.sisc.agenda.entidades.TipoIdentificacion;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author rreedd
 */
@Local
public interface IPersonaJuridicaFacadeLocal{

    int count();

    void crearPersonaJuridica(PersonaJuridica personaJuridica) throws Exception;

    PersonaJuridica find(Object id);

    List<PersonaJuridica> findAll();

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaJuridica findByIdentificacion(TipoIdentificacion tId, long identificacion);

    List<PersonaJuridica> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    PersonaJuridica modificarPersonaJuridica(PersonaJuridica personaJuridica);

    void remove(PersonaJuridica entity);
    
    void remove(Long id);
    
}
