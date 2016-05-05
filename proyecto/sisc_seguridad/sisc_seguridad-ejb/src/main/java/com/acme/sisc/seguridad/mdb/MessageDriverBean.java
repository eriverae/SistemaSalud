/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.mdb;

import com.acme.sisc.agenda.entidades.Usuario;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
  @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
  @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/BancoQueue"),
  @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "200")
})
public class MessageDriverBean implements MessageListener{

  private static final Logger LOGGER = Logger.getLogger(MessageDriverBean.class.getName());

  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public void onMessage(Message message) {
    LOGGER.info("Se recibio mensaje JMS ");
    try {
      LOGGER.finest("Esperando 10 segungos ..... ");
      Thread.sleep(10000);//Espera 10 segundos
    }catch (InterruptedException ie){
      LOGGER.log(Level.WARNING,"Problemas con sleep del Thread", ie);
    }
    try {
      if (message instanceof TextMessage) {
        LOGGER.finest("Queue: Se recibe mensaje tipo TextMessage, hora: " + new Date());
        TextMessage msg = (TextMessage) message;
        LOGGER.finest("Mensaje : " + msg.getText());
      } else if (message instanceof ObjectMessage) {
        LOGGER.info("Queue: Se recibe mensaje de tipo  ObjectMessage, hora: " + new Date());
        ObjectMessage msg = (ObjectMessage) message;
        Usuario usuario = (Usuario) msg.getObject();
        LOGGER.info("Nombre Usuario:" + usuario.getUsuaEmail());

      } else {
        LOGGER.info("Mensaje no valido para este MDB");
      }
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE,"Error procesando mensaje en TestMDB",e);
    }

  }

}
