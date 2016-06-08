/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.registro.ejb;

import com.acme.sisc.agenda.entidades.PersonaJuridica;
import com.acme.sisc.agenda.entidades.TipoIdentificacion;
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
public class PersonaJuridicaFacade implements IPersonaJuridicaFacadeRemote, IPersonaJuridicaFacadeLocal   {
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(PersonaJuridicaFacade.class.getName());

    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaJuridicaFacade() {
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public PersonaJuridica findByIdentificacion(TipoIdentificacion tId, long identificacion){
        LOGGER.log(Level.FINE,"Consulta persona {0}",  identificacion);
        Query  q = em.createNamedQuery("Persona.findByIdentificacion");
        q.setParameter("tipoIdentificacion", tId);
        q.setParameter("numeroIdentificacion", identificacion);
        try{
           return ((PersonaJuridica)q.getSingleResult());
        }catch(NoResultException nre){
            LOGGER.log(Level.WARNING,"No se encontró cliente {0}", identificacion);
            return null;
        }        
    }
    
    @Override
    public PersonaJuridica modificarPersonaJuridica(PersonaJuridica p){
        LOGGER.log(Level.FINE,"Modificando persona juridica con nombre : {0} - Versión: {1}", new Object[]{p.getRazonSocial(),p.getVersion()} );
        p = em.merge(p);
        return p;
    }
    
    @Override
    public List<PersonaJuridica> findAll() {
        //Query  q = em.createNamedQuery("Cliente.findAll");
        Query q = em.createQuery("SELECT p FROM PersonaJuridica p");
        return q.getResultList();
    }
    
    @Override
    public void remove(PersonaJuridica entity) {
        em.remove(entity);
    }
    
    @Override
    public void remove(Long id){
      LOGGER.log(Level.FINE,"Eliminar persona juridica con id {0}", id);
      PersonaJuridica p = this.find(id);
      if (p!=null){
        remove(p);
        LOGGER.log(Level.INFO,"Persona juridica eliminado correctamente");
      }else{
        LOGGER.log(Level.INFO, "Cliente con id {} no existe", id);
      }
    }
    
    @Override
    public PersonaJuridica find(Object id) {
        return em.find(PersonaJuridica.class, id);
    }
    
    @Override
    public List<PersonaJuridica> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(PersonaJuridica.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }
    
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<PersonaJuridica> rt = cq.from(PersonaJuridica.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void crearPersonaJuridica(PersonaJuridica personaJuridica) throws Exception {
        try {
            LOGGER.info("Inicia crearPersonaJuridica(...)");
           //Se verifica si ya existe
            PersonaJuridica p = findByIdentificacion(personaJuridica.getTipoIdentificacion(), personaJuridica.getNumeroIdentificacion());
            if(p!= null){
                em.lock(p, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
                LOGGER.warning("Persona juridica "+ personaJuridica.getNumeroIdentificacion() + " ya existe !!");
                throw new Exception("La persona juridica " + personaJuridica.getTipoIdentificacion()+ "-" 
                        + personaJuridica.getNumeroIdentificacion() + " ya existe en el sistema");
            }
            em.persist(personaJuridica);
            LOGGER.info("Finaliza crearPersonaJuridica(...)");
        }
        catch (Exception ex) {
            LOGGER.log(Level.WARNING,"No se encontró persona juridica {0}", personaJuridica.getTipoIdentificacion() + " " 
                    + personaJuridica.getNumeroIdentificacion() + " Exception: " + ex.getLocalizedMessage());
        }
    }
}
