/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.seguridad.rest.UsuarioResource;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erika
 */
public class ProxyAutenticador implements IAutenticador {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    private static ProxyAutenticador instance = null;

    private IAutenticador proxy;

    private ProxyAutenticador() throws InstantiationException, IllegalAccessException {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("configuracion");//Sin la extensión .properties
            
            String valor = rb.getString("clase_autenticador");
            
            LOGGER.log(Level.FINEST, "Clase Instanciada{0}", valor);
            
            proxy = (IAutenticador) Class.forName(valor).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProxyAutenticador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ProxyAutenticador getInstance() {
        if (instance == null) {
            try {
                instance = new ProxyAutenticador();
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ProxyAutenticador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    @Override
    public boolean autenticar(String usuario, String password) {
        return (proxy.autenticar(usuario, password));
    }

}
