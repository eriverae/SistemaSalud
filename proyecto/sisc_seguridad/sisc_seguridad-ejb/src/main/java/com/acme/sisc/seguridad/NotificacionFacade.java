/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.LogNotifica;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.common.ejbLocator.EJBLocator;
import com.acme.sisc.common.util.JMSUtil;
import com.acme.sisc.registro.ejb.IPersonaNaturalFacadeRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
* Clase NotificacionFacade, es la fachada para las consultas, creación, modificación y 
* eliminacion de las Notificaciones
* @author  Julio
* @version 1.0
* @since   2016-06-27
*/
@Stateless
public class NotificacionFacade implements NotificacionFacadeLocal, NotificacionFacadeRemote {

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(NotificacionFacade.class.getName());

    /**
    * Metodo getEntityManager, es el punto de acceso para persistir las entidades desde la BD
    * @return EntityManager em, retorna el objeto de EntityManager
    */
    protected EntityManager getEntityManager() {
        return em;
    }

    private IPersonaNaturalFacadeRemote personaNaturalFacade;

    private static final String LOCAL_EJB_PERSONA = "java:global/sisc_registro-ear-1.0-SNAPSHOT/sisc_registro-ejb-1.0-SNAPSHOT/PersonaNaturalFacade!com.acme.sisc.registro.ejb.IPersonaNaturalFacadeRemote";

    /**
    * Metodo crearNotificacion, crea las notificaciones con los parametros definidos
    * @param String destino el el correo del usuario que se creo
    * @param String asunto es el asunto del correo
    * @param String cuerpo es el cuerpo del mensaje
    * @param String sistema es el modulo que lo implemento
    */
    @Override
    public void crearNotificacion(String destino, String asunto, String cuerpo, String sistema) {
        LOGGER.info("Inicia crearNotificacion(...)");

        LOGGER.info("mail notificacion:" + destino);
        
        LogNotifica ln = new LogNotifica();

        try {
            personaNaturalFacade = (IPersonaNaturalFacadeRemote) EJBLocator.lookup(LOCAL_EJB_PERSONA);
        } catch (NamingException ex) {
            Logger.getLogger(NotificacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        PersonaNatural pn = personaNaturalFacade.findByEmail(destino);

        ln.setLgnoAsunto(asunto);
        ln.setLgnoCuerpo(cuerpo);
        ln.setLgnoDest(destino);
        ln.setLgnoSiste(sistema);
        ln.setLgnoLgno(pn.getIdPersona());
        ln.setPersona(pn);
        
        JMSUtil.sendMessage(ln, "java:/jms/queue/SiscQueue");
        
        em.persist(ln);
    }

    /**
    * Metodo findAll, retorna una lista de objetos de tipo LogNotifica
    * @return java.util.List<LogNotifica>, todas las auditorias existentes en la BD
    */
    @Override
    public java.util.List<LogNotifica> findAll() {
        Query q = em.createNamedQuery("LogNotifica.findAll");
        return q.getResultList();
    }

    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return LogNotifica es el objeto que trae a partir del metodo find
    */
    @Override
    public LogNotifica find(Object id) {
        return em.find(LogNotifica.class, id);
    }

    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<LogNotifica> la lista de objetos en referencia
    */    
    @Override
    public java.util.List<LogNotifica> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(LogNotifica.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }

    /**
    * Metodo count, devuelve el conteo de objetos en referencia
    * @return int con el numero de objetos en referencia
    */
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<LogNotifica> rt = cq.from(LogNotifica.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
