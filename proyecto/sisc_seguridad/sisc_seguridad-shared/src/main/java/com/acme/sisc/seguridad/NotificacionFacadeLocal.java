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
 *
 * @author Julio
 */

@Local
public interface NotificacionFacadeLocal {

    void crearNotificacion(String destino, String asunto, String cuerpo, String sistema);

    java.util.List<LogNotifica> findAll();

    LogNotifica find(Object id);
    
    public java.util.List<LogNotifica> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    int count();
}
