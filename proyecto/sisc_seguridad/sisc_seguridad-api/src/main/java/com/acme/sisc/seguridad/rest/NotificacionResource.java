/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import com.acme.sisc.agenda.entidades.LogNotifica;
import com.acme.sisc.common.pagination.PaginatedListWrapper;
import com.acme.sisc.seguridad.NotificacionFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Este es el servicio para crear, modificar, consultar y eliminar las
 * operaciones que se realizan sobre las notificaciones
 *
 * @author Julio
 * @version 1.0
 * @since 2016-06-27
 */
@Path("notificaciones")
@RequestScoped
public class NotificacionResource {

    private static final Logger LOGGER = Logger.getLogger(NotificacionResource.class.getName());

    @Context
    private UriInfo context;

    @EJB
    NotificacionFacadeLocal facadeNotificacion;

    /**
     * Constructor del servicio AccesoGrupoResource
     *
     */
    public NotificacionResource() {

    }

    /**
     * Metodo guardarNotificacion, crea el objeto de tipo LogNotifica del objeto
     * en referencia
     *
     * @param req es un String que contiene destino, asunto, cuerpo y modulo que
     * lo implementa
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarNotificacion(String req) {
        String[] spl = req.split("-");
        System.out.println(req);
        LOGGER.log(Level.FINE, "Post para actualizat guardarNotificacion con {1}", req);
        String destino, asunto, cuerpo, sistema;
        destino = spl[0];
        asunto = spl[1];
        cuerpo = spl[2];
        sistema = spl[3];
        try {
            facadeNotificacion.crearNotificacion(destino, asunto, cuerpo, sistema);
            //sendEmail();
        } catch (Exception e) {
            //TODO Definir manejo
            LOGGER.log(Level.SEVERE, "Houston, estamos en problemas ...", e);
        }
    }

    /**
     * Metodo sendMail, implementacion de los envios de correos.
     */
    private static void sendEmail() {

        // Create all the needed properties
        Properties connectionProperties = new Properties();
        // SMTP host
        connectionProperties.put("mail.smtp.host", "smtp.live.com");
        // Is authentication enabled
        connectionProperties.put("mail.smtp.auth", "true");
        // Is StartTLS enabled
        connectionProperties.put("mail.smtp.starttls.enable", "true");
        // SSL Port
        connectionProperties.put("mail.smtp.socketFactory.port", "25");
        // SSL Socket Factory class
        connectionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // SMTP port, the same as SSL port :)
        connectionProperties.put("mail.smtp.port", "25");

        System.out.print("Creating the session...");

        // Create the session
        Session session = Session.getDefaultInstance(connectionProperties,
                new javax.mail.Authenticator() {    // Define the authenticator
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("elzurco123@hotmail.com", "Julio2015");
            }
        });

        System.out.println("done!");

        // Create and send the message
        try {
            // Create the message 
            Message message = new MimeMessage(session);
            // Set sender
            message.setFrom(new InternetAddress("elzurco123@hotmail.com"));
            // Set the recipients
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("juliocsr624@gmail.com"));
            // Set message subject
            message.setSubject("Hello from Team ITCuties");
            // Set message text
            message.setText("Java is easy when you watch our tutorials ;)");

            System.out.print("Sending message...");
            // Send the message
            Transport.send(message);

            System.out.println("done!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
