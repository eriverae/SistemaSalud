/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Acceso;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import javax.ejb.Local;

/**
* Interface AccesoFacadeLocal
* 
* @author  Julio
* @version 1.0
* @since   2016-04-27
*/
@Local
public interface AccesoFacadeLocal {
    
    /**
    * Metodo crearAcceso, crea el acceso con el objeto que llega del servicio, accediento a la entidad
    * @param acceso es el objeto a crear y persistir en la BD
    */
    void crearAcceso(Acceso acceso) throws SeguridadException;

    /**
    * Metodo findByAcceNombre, devuelve un objeto de tipo Acceso consultado a partir del parametro de entrada
    * @param nombre String es el campo nombre del acceso, el cual va a buscar
    * @return retorna Acceso, el el objeto encontrado a partir del metodo de la busqueda
    */
    Acceso findByAcceNombre(String nombre);

    /**
    * Metodo modificarAcceso, devuelve un objeto de tipo Acceso una vez que se modifican sus atributos
    * @param Acceso acceso es el objeto a modififcar
    * @return retorna Acceso, el el objeto una vez modificado 
    */
    Acceso modificarAcceso(Acceso acceso);

    /**
    * Metodo findAll, retorna una lista de obbjetos de tipo Acceso
    * @return java.util.List<Acceso>, todos los accesos existentes en la BD
    */    
    java.util.List<Acceso> findAll();

    /**
    * Metodo remove, elimina el objeto con el metodo remove 
    * @param Acceso acceso es el objeto a eliminar en la BD
    */
    void remove(Acceso acceso);
    
    /**
    * Metodo remove, elimina el objeto con el metodo remove, antes de ello crea un objeto a partir del id 
    * @param Long acceso id del acceso a eliminar
    */
    void remove(Long acceso);

    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return Acceso es el objeto que trae a partir del metodo find
    */
    Acceso find(Object id);

    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<Acceso> la lista de objetos en referencia
    */
    java.util.List<Acceso> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    /**
    * Metodo count, devuelve el conteo de objetos en referencia
    * @return int con el numero de objetos en referencia
    */ 
    int count();
}
