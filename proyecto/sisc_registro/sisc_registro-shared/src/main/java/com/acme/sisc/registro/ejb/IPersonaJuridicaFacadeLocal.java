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

    /**
     * Cantidad de personas jurídicas en el sistema.
     * @return 
     */
    int count();

    /**
     * Creación de una EPS.
     * @param personaJuridica Objeto de persona jurídica.
     * @throws Exception 
     */
    void crearPersonaJuridica(PersonaJuridica personaJuridica) throws Exception;

    /**
     * Buscar una EPS según su identificador único.
     * @param id Identificador único.
     * @return Objeto de PersonaJuridica.
     */
    PersonaJuridica find(Object id);

    /**
     * Listar todas las personas jurídicas (EPS).
     * @return Listado de PersonaJuridica.
     */
    List<PersonaJuridica> findAll();

    /**
     * Buscar una persona jurídica según su tipo y número de identificación.
     * @param tId Tipo identificación.
     * @param identificacion Número identificación.
     * @return Objeto PersonaJuridica, si existe.
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    PersonaJuridica findByIdentificacion(TipoIdentificacion tId, long identificacion);

    /**
     * Buscar un listado de EPS entre un rango.
     * @param startPosition Posición inicial.
     * @param maxResults Máximo de resultados.
     * @param sortFields Campo por el cual ordenar la lista.
     * @param sortDirections Dirección de orden.
     * @return Listado de PersonaJuridica.
     */
    List<PersonaJuridica> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    /**
     * Actualizar una persona jurídica.
     * @param p Objeto de persona jurídica.
     * @return Objeto de persona jurídica.
     */
    PersonaJuridica modificarPersonaJuridica(PersonaJuridica personaJuridica);

    /**
     * Eliminar una EPS.
     * @param entity Objeto de persona jurídica.
     */
    void remove(PersonaJuridica entity);
    
    /**
     * Eliminar una EPS según su identificador único.
     * @param id Identificador único de la persona jurídica.
     */
    void remove(Long id);
}
