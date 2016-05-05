/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.common.ejbLocator;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author jmartinez
 */
public class EJBLocator {
  
  private static final InitialContext ctx;
  
  static{
    try{
      ctx = new InitialContext();
    }catch(NamingException e){
      throw new RuntimeException(e);
    }
  }
  
  
  public static Object lookup(String name) throws NamingException{
    return ctx.lookup(name);
  }
  
}