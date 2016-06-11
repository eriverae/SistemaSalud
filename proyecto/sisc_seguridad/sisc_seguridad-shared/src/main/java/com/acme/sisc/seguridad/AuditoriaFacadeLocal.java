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
 *
 * @author Julio
 */
@Local
public interface AuditoriaFacadeLocal {

    void crearAuditoria(String emailUsuario, String observacion, String dirIP, String hostName);

    java.util.List<AuditoriaUsuario> findAll();

    AuditoriaUsuario find(Object id);
    
    java.util.List<AuditoriaUsuario> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    int count();
    
}
