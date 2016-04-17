/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desarrollador
 */
public class ProxyAutenticador implements IAutenticador{

    private  static ProxyAutenticador instancia;
    private  IAutenticador proxy;

    private  ProxyAutenticador()  {
        
        try {
            proxy   = (IAutenticador)Class.forName
                                (getProperty("claseAutenticadora")).newInstance();
            
            Logger.getLogger(ProxyAutenticador.class.getName()).log(
                    Level.INFO,
                    "instancia de:IAutenticador "+getProperty("claseAutenticadora"));
            
        } catch (ClassNotFoundException | InstantiationException |IllegalAccessException  ex) {
            Logger.getLogger(ProxyAutenticador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private  static String  getProperty(String key){        
        ResourceBundle rb = ResourceBundle.getBundle("conf");        
        return rb.getString(key);
    }
    
    
    public static ProxyAutenticador getInstance(){
        if(instancia==null){
             instancia=new ProxyAutenticador();
        }
       return instancia;
    }
    
       
    @Override
    public boolean autenticar(String user, String password) {
        return proxy.autenticar(user, password);
    }
    
}
