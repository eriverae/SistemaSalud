/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.UTILprueba;

/**
 *
 * @author BryanCFz-user
 */
import com.acme.sisc.agenda.entidades.Cita;

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

/**
 * Created by jmartinez on 5/3/16.
 */

@MessageDriven(activationConfig = {
  @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
  @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/siscQueue"),
  @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "200")
})
public class TestMDB implements MessageListener{

  private static final Logger LOGGER = Logger.getLogger(TestMDB.class.getName());

  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public void onMessage(Message message) {
    LOGGER.info("Se recibio mensaje JMS ");
    try {
      LOGGER.finest("Esperando 20 segungos ..... ");
      Thread.sleep(20000);//Espera 20 segundos
    }catch (InterruptedException ie){
      LOGGER.log(Level.WARNING,"Problemas con sleep del Thread", ie);
    }
    try {
      if (message instanceof TextMessage) {
        LOGGER.finest("Queue: Se recibe mensaje tipo TextMessage, hora: " + new Date());
        TextMessage msg = (TextMessage) message;
        LOGGER.finest("Mensaje : " + msg.getText());
      } else if (message instanceof ObjectMessage) {
        LOGGER.finest("Queue: Se recibe mensaje de tipo  ObjectMessage, hora: " + new Date());
        ObjectMessage msg = (ObjectMessage) message;
        Cita cita = (Cita) msg.getObject();
        LOGGER.info("Id cita:" + cita.getIdCita());
        LOGGER.info("observaciones:" + cita.getObservaciones());

      } else {
        LOGGER.info("Mensaje no valido para este MDB");
      }
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE,"Error procesando mensaje en TestMDB",e);
    }

  }

}