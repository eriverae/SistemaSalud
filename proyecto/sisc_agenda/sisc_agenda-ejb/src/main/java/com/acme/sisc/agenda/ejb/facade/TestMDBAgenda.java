/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

/**
 *
 * @author rasm
 */
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.acme.sisc.agenda.entidades.Agenda;

/**
 * Created by jmartinez on 5/3/16.
 */

@MessageDriven(activationConfig = {
  @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
  @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/SiscQueue"),
  @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "200")
})
public class TestMDBAgenda implements MessageListener{

  private static final Logger LOGGER = Logger.getLogger(TestMDBAgenda.class.getName());

  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public void onMessage(Message message) {
    LOGGER.info("Se recibio mensaje JMS ");
    try {
       LOGGER.log(Level.WARNING,"Esperando 10 segungos ..... ");
      Thread.sleep(10000);//Espera 10 segundos
    }catch (InterruptedException ie){
      LOGGER.log(Level.SEVERE,"Problemas con sleep del Thread", ie);
    }
    try {
      if (message instanceof TextMessage) {
         LOGGER.log(Level.WARNING,"Queue: Se recibe mensaje tipo TextMessage, hora: " + new Date());
        TextMessage msg = (TextMessage) message;
        LOGGER.log(Level.WARNING,"Mensaje : " + msg.getText());
      } else if (message instanceof ObjectMessage) {
        LOGGER.finest("Queue: Se recibe mensaje de tipo  ObjectMessage, hora: " + new Date());
        ObjectMessage msg = (ObjectMessage) message;
        Agenda cliente = (Agenda) msg.getObject();
       LOGGER.log(Level.WARNING,"********************Nueva agenda***************************");
        LOGGER.log(Level.WARNING,"Bloque inicio :" + cliente.getHoraBloqueinicio().toString());
        LOGGER.log(Level.WARNING,"Bloque fin    :" +cliente.getHoraBloqueFin().toString());
       LOGGER.log(Level.WARNING,"***********************************************************");

      } else {
        LOGGER.info("Mensaje no valido para este MDB");
      }
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE,"Error procesando mensaje en TestMDB",e);
    }

  }

}

