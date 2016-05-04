package com.acme.sisc.registro.ejb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.agenda.entidades.TipoIdentificacion;
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

    
    void crearPersonaNatural(PersonaNatural personaNatural) throws Exception;

    PersonaNatural find(Object id);

    List<PersonaNatural> findAll();

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByIdentificacion(TipoIdentificacion tId, long identificacion);

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByNumeroIdentificacion(long identificacion);

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaNatural findByEmail(String email);

    List<PersonaNatural> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    PersonaNatural modificarPersonaNatural(PersonaNatural personaNatural);

    void remove(PersonaNatural entity);
        
    public void remove(Long id);
    
}
