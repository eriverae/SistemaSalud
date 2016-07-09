/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;
import com.acme.sisc.agenda.entidades.LogNotifica;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import javax.ejb.Local;

/**
 * Interface NotificacionFacadeLocal
 *
 * @author Julio
 * @version 1.0
 * @since 2016-06-27
 */

@Local
public interface NotificacionFacadeLocal {

    /**
    * Metodo crearNotificacion, crea las notificaciones con los parametros definidos
    * @param String destino el el correo del usuario que se creo
    * @param String asunto es el asunto del correo
    * @param String cuerpo es el cuerpo del mensaje
    * @param String sistema es el modulo que lo implemento
    */
    void crearNotificacion(String destino, String asunto, String cuerpo, String sistema);

    /**
    * Metodo findAll, retorna una lista de objetos de tipo LogNotifica
    * @return java.util.List<LogNotifica>, todas las auditorias existentes en la BD
    */
    java.util.List<LogNotifica> findAll();

    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return LogNotifica es el objeto que trae a partir del metodo find
    */
    LogNotifica find(Object id);
    
    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<LogNotifica> la lista de objetos en referencia
    */ 
    public java.util.List<LogNotifica> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    /**
    * Metodo count, devuelve el conteo de objetos en referencia
    * @return int con el numero de objetos en referencia
    */
    int count();
}
