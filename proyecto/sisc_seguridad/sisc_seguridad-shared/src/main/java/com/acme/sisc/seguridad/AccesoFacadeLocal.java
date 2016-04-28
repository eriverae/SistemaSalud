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
 *
 * @author Julio
 */
@Local
public interface AccesoFacadeLocal {
    
    void crearAcceso(Acceso acceso) throws SeguridadException;

    Acceso findByAcceNombre(String nombre);

    Acceso modificarAcceso(Acceso acceso);

    java.util.List<Acceso> findAll();

    void remove(Acceso acceso);
    
    void remove(Long acceso);

    Acceso find(Object id);

    java.util.List<Acceso> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    int count();
}
