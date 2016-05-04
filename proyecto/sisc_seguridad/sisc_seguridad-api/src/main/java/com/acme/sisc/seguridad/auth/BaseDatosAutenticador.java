/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.auth;

import com.acme.sisc.seguridad.IAutenticador;
import com.acme.sisc.seguridad.UsuarioFacadeRemote;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author rm-rf
 */
public class BaseDatosAutenticador implements IAutenticador{
    
    @EJB
    private UsuarioFacadeRemote usuarioFacade;

    @Override
    public boolean autenticar(String usuario, String password) {
        if (usuarioFacade == null){
            InitialContext ic = null;
            try {
                ic = new InitialContext();
            } catch (NamingException ex) {
                Logger.getLogger(BaseDatosAutenticador.class.getName()).log(Level.SEVERE, null, ex);
            }
//            try {
//                //ic.list("java:app/sisc_seguridad-ejb-1.0-SNAPSHOT")
//                //ic.list("java:module")
//                usuarioFacade = (UsuarioFacadeRemote) ic.lookup("java:module/UsuarioFacade");
//            } catch (NamingException ex) {
//                Logger.getLogger(BaseDatosAutenticador.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                usuarioFacade = (UsuarioFacadeRemote) ic.lookup("java:app/sisc_seguridad-ejb-1.0-SNAPSHOT/UsuarioFacade");
//            } catch (NamingException ex) {
//                Logger.getLogger(BaseDatosAutenticador.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                usuarioFacade = (UsuarioFacadeRemote) ic.lookup("java:global/sisc_seguridad-ear-1.0-SNAPSHOT/sisc_seguridad-ejb-1.0-SNAPSHOT/UsuarioFacade");
//            } catch (NamingException ex) {
//                Logger.getLogger(BaseDatosAutenticador.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                usuarioFacade = (UsuarioFacadeRemote) ic.lookup("java:jboss/exported/sisc_seguridad-ear-1.0-SNAPSHOT/sisc_seguridad-ejb-1.0-SNAPSHOT/UsuarioFacade");
//            } catch (NamingException ex) {
//                Logger.getLogger(BaseDatosAutenticador.class.getName()).log(Level.SEVERE, null, ex);
//            }
            try {
                usuarioFacade = (UsuarioFacadeRemote) ic.lookup("java:comp/env/UsuarioFacade");
            } catch (NamingException ex) {
                Logger.getLogger(BaseDatosAutenticador.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                usuarioFacade = (UsuarioFacadeRemote) ic.lookup("java:comp/env/com.acme.sisc.seguridad.UsuarioFacadeRemote");
            } catch (NamingException ex) {
                Logger.getLogger(BaseDatosAutenticador.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                usuarioFacade = (UsuarioFacadeRemote) ic.lookup("java:comp/EJBContext");
            } catch (NamingException ex) {
                Logger.getLogger(BaseDatosAutenticador.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                final Hashtable jndiProperties = new Hashtable();
                jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
                final Context context = new InitialContext(jndiProperties);
                // The app name is the application name of the deployed EJBs. This is typically the ear name
                // without the .ear suffix. However, the application name could be overridden in the application.xml of the
                // EJB deployment on the server.
                // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
                final String appName = "";
                // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
                // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
                // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
                // jboss-as-ejb-remote-app
                //sisc_seguridad-ear-1.0-SNAPSHOT/sisc_seguridad-ejb-1.0-SNAPSHOT
                final String moduleName = "sisc_seguridad-ejb-1.0-SNAPSHOT";
                // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
                // our EJB deployment, so this is an empty string
                final String distinctName = "";
                // The EJB name which by default is the simple class name of the bean implementation class
                final String beanName = UsuarioFacadeRemote.class.getSimpleName();
                // the remote view fully qualified class name
                final String viewClassName = UsuarioFacadeRemote.class.getName();
                // let's do the lookup
                usuarioFacade = (UsuarioFacadeRemote) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return usuarioFacade.autenticar(usuario, password);
    }
}
