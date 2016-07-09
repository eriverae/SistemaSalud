/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;


import com.acme.sisc.agenda.entidades.AuditoriaUsuario;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import java.util.List;
import javax.ejb.Local;

/**
* Interface AuditoriaFacadeLocal
* 
* @author  Julio
* @version 1.0
* @since   2016-06-11
*/
@Local
public interface AuditoriaFacadeLocal {

    /**
    * Metodo crearAuditoria, crea las auditorias con los parametros definidos
    * @param String emailUsuario el el correo del usuario que ejecuto la accion
    * @param String observacion es la accion que se ejecuto 
    * @param String dirIP es la direccion Ip de el equipo del cliente
    * @param String hostName es el hostname del equipo
    */
    void crearAuditoria(String emailUsuario, String observacion, String dirIP, String hostName);

    /**
    * Metodo findAll, retorna una lista de objetos de tipo AuditoriaUsuario
    * @return java.util.List<AuditoriaUsuario>, todas las auditorias existentes en la BD
    */
    java.util.List<AuditoriaUsuario> findAll();

    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return AuditoriaUsuario es el objeto que trae a partir del metodo find
    */
    AuditoriaUsuario find(Object id);
    
    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<AuditoriaUsuario> la lista de objetos en referencia
    */
    java.util.List<AuditoriaUsuario> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    /**
    * Metodo count, devuelve el conteo de objetos en referencia
    * @return int con el numero de objetos en referencia
    */
    int count();
    
}
