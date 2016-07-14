/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import java.util.List;
import javax.ejb.Local;

/**
* Interface GrupoFacadeLocal
* 
* @author  Julio
* @version 1.0
* @since   2016-05-14
*/
@Local
public interface GrupoFacadeLocal {
    
    /**
    * Metodo crearGrupo, crea el grupo con el objeto que llega del servicio, accediento a la entidad
    * @param grupo es el objeto a crear y persistir en la BD
    */
    public Grupo crearGrupo(Grupo grupo) throws SeguridadException;

    /**
    * Metodo findByNom, devuelve un objeto de tipo Grupo consultado a partir del parametro de entrada
    * @param nombre String es el campo nombre del grupo, el cual va a buscar
    * @return retorna Grupo, el el objeto encontrado a partir del metodo de la busqueda
    */
    Grupo findByNom(String nombre);

    /**
    * Metodo modificarGrupo, devuelve un objeto de tipo Grupo una vez que se modifican sus atributos
    * @param Grupo grupo es el objeto a modififcar
    * @return retorna Grupo, el el objeto una vez modificado 
    */
    Grupo modificarGrupo(Grupo grupo);

    /**
    * Metodo findAll, retorna una lista de objetos de tipo Grupo
    * @return java.util.List<Grupo>, todos los grupos existentes en la BD
    */
    java.util.List<Grupo> findAll();

    /**
    * Metodo remove, elimina el objeto con el metodo remove 
    * @param Grupo grupo es el objeto a eliminar en la BD
    */
    void remove(Grupo grupo);
    
    /**
    * Metodo remove, elimina el objeto con el metodo remove, antes de ello crea un objeto a partir del id 
    * @param Long grupo id del grupo a eliminar
    */
    void remove(Long grupo);

    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return Grupo es el objeto que trae a partir del metodo find
    */
    Grupo find(Object id);

    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<Grupo> la lista de objetos en referencia
    */
    java.util.List<Grupo> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    /**
    * Metodo count, devuelve el conteo de objetos en referencia
    * @return int con el numero de objetos en referencia
    */
    int count();
    
    /**
    * Metodo obtenerGrupos, consulta los perfiles del usuario
    * @param String usuario es el mail del usuario
    * @return List<String>, es la lista de los perfiles del usuario
    */    
    public List<String> obtenerGrupos(String usuario);
}
