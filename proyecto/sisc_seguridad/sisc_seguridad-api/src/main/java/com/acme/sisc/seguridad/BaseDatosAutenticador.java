/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.seguridad.IAutenticador;
import com.acme.sisc.seguridad.UsuarioFacadeRemote;
import com.acme.sisc.common.ejbLocator.EJBLocator;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
* Esta clase llama al EJBLocator para crear un objeto de tipo Usuario, y accede al metodo del 
* facade con la información del usuario, email y contraseña
*
* @author  Julio
* @version 1.0
* @since   2016-05-05
*/
public class BaseDatosAutenticador implements IAutenticador {

    private static final Logger LOGGER = Logger.getLogger(BaseDatosAutenticador.class.getName());

    private static final String LOCAL_EJB_USUARIO = "java:app/sisc_seguridad-ejb-1.0-SNAPSHOT/UsuarioFacade!com.acme.sisc.seguridad.UsuarioFacadeLocal";

    private UsuarioFacadeLocal usuarioFacade;


    /**
    * Metodo autenticar, llama al metodo del usuarioFacade autentica, para retornar que la autenticacion es correcta
    * @param usuario String es el mail del usario
    * @param password String es la contraseña del usuario
    * @return retorna boolean, del metodo autenticar de usuarioFacade
    */
    @Override
    public boolean autenticar(String usuario, String password) {
        try {
            usuarioFacade = (UsuarioFacadeLocal) EJBLocator.lookup(LOCAL_EJB_USUARIO);
        } catch (NamingException ex) {
            Logger.getLogger(BaseDatosAutenticador.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOGGER.log(Level.INFO, "Se invoco EJB Usuario Local con EJBLocator");
        return usuarioFacade.autenticar(usuario, password);
    }
}
