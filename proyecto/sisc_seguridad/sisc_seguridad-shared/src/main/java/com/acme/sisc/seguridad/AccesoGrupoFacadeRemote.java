/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.AccesoGrupo;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import javax.ejb.Remote;

/**
 *
 * @author Julio
 */
@Remote
public interface AccesoGrupoFacadeRemote {

    void crearAccesoGrupo(AccesoGrupo accesoGrupo) throws SeguridadException;

    AccesoGrupo findByAcceGrup(Long acceso, Long grupo);

    AccesoGrupo modificarGrupoAcceso(AccesoGrupo accesoGrupo);

    java.util.List<com.acme.sisc.agenda.entidades.AccesoGrupo> findAll();

    void remove(AccesoGrupo accesoGrupo);

    void remove(long accesoGrupo);

    AccesoGrupo find(Object id);

    int count();

    java.util.List<com.acme.sisc.agenda.entidades.AccesoGrupo> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);
    
}
