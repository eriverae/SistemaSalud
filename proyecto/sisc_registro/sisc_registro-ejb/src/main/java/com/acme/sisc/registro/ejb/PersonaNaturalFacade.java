/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.registro.ejb;

import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.agenda.entidades.TipoIdentificacion;
//import com.acme.sisc.registro.queue.JMSUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rreedd
 */
@Stateless
public class PersonaNaturalFacade implements IPersonaNaturalFacadeRemote, IPersonaNaturalFacadeLocal   {
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(PersonaNaturalFacade.class.getName());

    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaNaturalFacade() {
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public PersonaNatural findByIdentificacion(TipoIdentificacion tId, long identificacion){
        LOGGER.log(Level.FINE,"Consulta persona {0}",  identificacion);
        Query  q = em.createNamedQuery("Persona.findByIdentificacion");
        q.setParameter("tipoIdentificacion", tId);
        q.setParameter("numeroIdentificacion", identificacion);
        try{
           return ((PersonaNatural)q.getSingleResult());
        }catch(NoResultException nre){
            LOGGER.log(Level.WARNING,"No se encontró persona con identificacion {0}", identificacion);
            return null;
        }        
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public PersonaNatural findByNumeroIdentificacion(long identificacion) {
        LOGGER.log(Level.FINE, "Consulta persona {0}",  identificacion);
        Query  q = em.createNamedQuery("Persona.findByNumeroIdentificacion");
        q.setParameter("numeroIdentificacion", identificacion);
        try{
           return ((PersonaNatural)q.getSingleResult());
        }catch(NoResultException nre){
            LOGGER.log(Level.WARNING,"No se encontró persona con identificacion {0}", identificacion);
            return null;
        }   
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public PersonaNatural findByEmail(String email){
        LOGGER.log(Level.FINE,"Consulta persona, email {0}",  email);
        Query  q = em.createNamedQuery("Persona.findByCorreoElectronico");
        q.setParameter("correoElectronico", email);
        try{
           return ((PersonaNatural)q.getSingleResult());
        }catch(NoResultException nre){
            LOGGER.log(Level.WARNING,"No se encontró persona con correo {0}", email);
            return null;
        }        
    }
    
    @Override
    public PersonaNatural modificarPersonaNatural(PersonaNatural p){
        LOGGER.log(Level.FINE,"Modificando persona natural con nombre : {0} - Versión: {1}", new Object[]{p.getNombres(),p.getVersion()} );
        p = em.merge(p);
        return p;
    }
    
    @Override
    public List<PersonaNatural> findAll() {
        //Query  q = em.createNamedQuery("Cliente.findAll");
        Query q = em.createQuery("SELECT p FROM PersonaNatural p");
        return q.getResultList();
    }
    
    @Override
    public void remove(PersonaNatural entity) {
        em.remove(entity);
    }
    
    public void remove(Long id){
      LOGGER.log(Level.FINE,"Eliminar persona natural con id {0}", id);
      PersonaNatural p = this.find(id);
      if (p!=null){
        remove(p);
        LOGGER.log(Level.INFO,"Persona natural eliminado correctamente");
      }else{
        LOGGER.log(Level.INFO, "Cliente con id {} no existe", id);
      }
    }
    
    @Override
    public PersonaNatural find(Object id) {
        return em.find(PersonaNatural.class, id);
    }
    
    @Override
    public List<PersonaNatural> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(PersonaNatural.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }
    
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<PersonaNatural> rt = cq.from(PersonaNatural.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void crearPersonaNatural(PersonaNatural personaNatural) throws Exception {
        try {
            LOGGER.info("Inicia crearPersonaNatural(...)");
           //Se verifica si ya existe
            PersonaNatural p = findByIdentificacion(personaNatural.getTipoIdentificacion(), personaNatural.getNumeroIdentificacion());
            if(p!= null){
                em.lock(p, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
                LOGGER.warning("Persona natural "+ personaNatural.getNumeroIdentificacion() + " ya existe !!");
                throw new Exception("La persona natural " + personaNatural.getTipoIdentificacion()+ "-" 
                        + personaNatural.getNumeroIdentificacion() + " ya existe en el sistema");
            }
            em.persist(personaNatural);
            //Prueba queues
            //JMSUtil.sendMessage(personaNatural,"java:/jms/queue/BancoQueue");
            LOGGER.info("Finaliza crearPersonaNatural despues(...)");
        }
        catch (Exception ex) {
            LOGGER.log(Level.WARNING,"No se encontró cliente {0}", personaNatural.getTipoIdentificacion() + " " 
                    + personaNatural.getNumeroIdentificacion() + " Exception: " + ex.getLocalizedMessage());
        }
    }
}
