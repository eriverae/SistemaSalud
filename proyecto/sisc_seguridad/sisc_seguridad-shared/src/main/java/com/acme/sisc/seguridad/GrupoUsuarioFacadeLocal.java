/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;


import com.acme.sisc.agenda.entidades.GrupoUsuario;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import javax.ejb.Local;
/**
 *
 * @author Julio
 */
@Local
public interface GrupoUsuarioFacadeLocal {
    void crearGrupoUsuario(GrupoUsuario grupoUsuario) throws SeguridadException;

    GrupoUsuario findByGrupUsua(Long usuario, Long grupo);

    GrupoUsuario modificarGrupoUsuario(GrupoUsuario grupoUsuario);

    java.util.List<com.acme.sisc.agenda.entidades.GrupoUsuario> findAll();

    void remove(GrupoUsuario grupoUsuario);

    void remove(long grupoUsuario);

    GrupoUsuario find(Object id);

    int count();

    java.util.List<com.acme.sisc.agenda.entidades.GrupoUsuario> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);
    
}
