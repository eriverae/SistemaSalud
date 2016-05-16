/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Usuario;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import javax.ejb.Remote;

/**
 *
 * @author rm-rf
 */
@Remote
public interface UsuarioFacadeRemote {

    Usuario crearUsuario(Usuario usuario) throws SeguridadException;

    Usuario findByEmail(String email);

    Usuario modificarUsuario(Usuario usuario);

    java.util.List<Usuario> findAll();

    void remove(Usuario usuario);
    
    void remove(Long usuario);

    Usuario find(Object id);

    java.util.List<Usuario> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    int count();

    boolean autenticar(String usuario, String password);

}
