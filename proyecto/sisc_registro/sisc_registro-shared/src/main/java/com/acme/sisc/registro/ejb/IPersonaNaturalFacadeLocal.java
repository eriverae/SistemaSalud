package com.acme.sisc.registro.ejb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.acme.sisc.agenda.entidades.PersonaNatural;
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
public interface IPersonaNaturalFacadeLocal{

    int count();

    void crearPersonaNatural(PersonaNatural personaNatural) throws Exception;

    PersonaNatural find(Object id);

    List<PersonaNatural> findAll();

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByIdentificacion(TipoIdentificacion tId, long identificacion);

    List<PersonaNatural> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    PersonaNatural modificarPersonaNatural(PersonaNatural personaNatural);

    void remove(PersonaNatural entity);
    
    void remove(Long id);
    
}