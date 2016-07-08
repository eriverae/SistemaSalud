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
* Esta clase implementa el patron de Singleton, el cual llama a la autenticacion definido
* en un archivo de propiedades, y retorna la implementacion del metodo autenticar bajo la 
* clase definida previamente
*
* @author  Erika
* @version 1.0
* @since   2016-05-01
*/
public class ProxyAutenticador implements IAutenticador {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    private static ProxyAutenticador instance = null;

    private IAutenticador proxy;

    /**
    * Constructor del la clase ProxyAutenticador, en este se define la clase a implementar,
    * definida en el archivo de configuracion
    */
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

    /**
    * Metodo getInstance, donde se instancia una sola vez el ProxyAutenticador(), patron Singleton.
    * @return retorna instance de tipo ProxyAutenticador.
    */
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

    /**
    * Metodo autenticar, utiliza la interface que implementa y llama la metodo autenticar de la clase del archivo de configuracion
    * @param usuario String es el mail del usario
    * @param password String es la contraseña del usuario
    * @return retorna boolean, del metodo autenticar de usuarioFacade
    */
    @Override
    public boolean autenticar(String usuario, String password) {
        return (proxy.autenticar(usuario, password));
    }

}
