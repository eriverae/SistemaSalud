/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author efrr_000
 */
public class ProxyAutenticador implements IAutenticador {

    private ProxyAutenticador() {
        try {
            proxy = (IAutenticador) Class.forName(getClaseAutenticadora()).newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(ProxyAutenticador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProxyAutenticador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProxyAutenticador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ProxyAutenticador instance;
    private IAutenticador proxy;

    @Override
    public Boolean autenticar(String usr, String pwd) {
        return proxy.autenticar(usr, pwd);
    }

    private String getClaseAutenticadora() throws InstantiationException, IllegalAccessException {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        return rb.getString("clase_autenticadora");
    }

    public static ProxyAutenticador getInstance() {
        if (instance == null) {
            instance = new ProxyAutenticador();
        }
        return instance;
    }

    public IAutenticador getProxy() {
        return proxy;
    }

    public void setProxy(IAutenticador proxy) {
        this.proxy = proxy;
    }
}
