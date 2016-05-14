/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rm-rf
 */
@Remote
public interface GrupoFacadeRemote {

    public Grupo crearGrupo(Grupo grupo) throws SeguridadException;

    Grupo findByNom(String nombre);

    Grupo modificarGrupo(Grupo grupo);

    java.util.List<Grupo> findAll();

    void remove(Grupo grupo);
    
    void remove(Long grupo);

    Grupo find(Object id);

    java.util.List<Grupo> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    int count();
    
    public List<String> obtenerGrupos(String usuario);

}
