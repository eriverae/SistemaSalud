/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Acceso;
import com.acme.sisc.agenda.entidades.AccesoGrupo;
import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import javax.ejb.Local;

/**
* Interface AccesoGrupoFacadeLocal
* 
* @author  Julio
* @version 1.0
* @since   2016-05-22
*/
@Local
public interface AccesoGrupoFacadeLocal {

    /**
    * Metodo crearAccesoGrupo, crea la relacion en la tabla de perfiles accesos con el objeto que llega del servicio, accediento a la entidad
    * @param AccesoGrupo accesoGrupo es el objeto a crear y persistir en la BD
    */
    void crearAccesoGrupo(AccesoGrupo accesoGrupo) throws SeguridadException;

    /**
    * Metodo findByAcceGrup, devuelve un objeto de tipo AccesoGrupo consultado a partir de los parametros de entrada
    * @param Long acceso es el id de la tabla Acceso
    * @param Long grupo es el id de la tabla Grupo
    * @return retorna AccesoGrupo, el el objeto encontrado a partir del metodo de la busqueda
    */
    AccesoGrupo findByAcceGrup(Long acceso, Long grupo);

    /**
    * Metodo modificarGrupoAcceso, devuelve un objeto de tipo AccesoGrupo una vez que se modifican sus atributos
    * @param AccesoGrupo accesoGrupo es el objeto a modififcar
    * @return retorna AccesoGrupo, el el objeto una vez modificado 
    */
    AccesoGrupo modificarGrupoAcceso(AccesoGrupo accesoGrupo);

    /**
    * Metodo findAll, retorna una lista de obbjetos de tipo AccesoGrupo
    * @return java.util.List<AccesoGrupo>, todos los accesos con la relacion de sus perfiles existentes en la BD
    */
    java.util.List<com.acme.sisc.agenda.entidades.AccesoGrupo> findAll();

    /**
    * Metodo remove, elimina el objeto con el metodo remove 
    * @param AccesoGrupo accesoGrupo es el objeto a eliminar en la BD
    */
    void remove(AccesoGrupo accesoGrupo);

    /**
    * Metodo remove, elimina el objeto con el metodo remove, antes de ello crea un objeto a partir del id 
    * @param Long accesoGrupo id del AccesoGrupo a eliminar
    */
    void remove(long accesoGrupo);

    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return AccesoGrupo es el objeto que trae a partir del metodo find
    */
    AccesoGrupo find(Object id);

    /**
    * Metodo count, devuelve el conteo de objetos en referencia
    * @return int con el numero de objetos en referencia
    */
    int count();

    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<AccesoGrupo> la lista de objetos en referencia
    */
    java.util.List<com.acme.sisc.agenda.entidades.AccesoGrupo> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    /**
    * Metodo findByGrupGrup, devuelve un objeto de tipo Acceso consultado a partir del parametro de entrada
    * @param Long grupGrup es el id del perfil a consultar los accesos
    * @return retorna java.util.List<Acceso>, son una lista de accesos del perfil
    */
    java.util.List<Acceso> findByGrupGrup(Long grupGrup);

    /**
    * Metodo actualizaAccesoGrupo, crea y elimiana las relaciones de acceso y perfiles acorde a estado
    * @param Grupo grupGrup es el objeto de los perfiles
    * @param Acceso acceAcce es el objeto de los accesos
    * @param Boolean estado si llega true crea la relacion sino la elimina
    */
    void actualizaAccesoGrupo(Grupo grupGrup, Acceso acceAcce, Boolean estado);
    
}
