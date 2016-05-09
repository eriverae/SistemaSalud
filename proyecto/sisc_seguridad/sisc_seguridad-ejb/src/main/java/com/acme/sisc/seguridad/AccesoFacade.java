/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Acceso;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rm-rf
 */
@Stateless
public class AccesoFacade implements AccesoFacadeRemote, AccesoFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(AccesoFacade.class.getName());
    
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void crearAcceso(Acceso acceso) throws SeguridadException{
        LOGGER.info("Inicia Acceso(...)");
        
        Acceso a = findByAcceNombre(acceso.getAcceNombre());
        if (a != null){
            LOGGER.warning("Acceso "+ acceso.getAcceNombre() + " ya existe !!");
            throw new SeguridadException("El acceso " +acceso.getAcceNombre() +" ya existe en el sistema");
        }
        
        acceso.setAcceEsta("Activo");
        em.persist(acceso);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Acceso findByAcceNombre(String nombre) {
        LOGGER.log(Level.FINE,"Consulta Acceso {0}",  nombre);
        Query  q = em.createNamedQuery("Acceso.findByAcceNombre");
        q.setParameter("acceNombre", nombre);
        try{
            return (Acceso) q.getSingleResult();
        }catch(NoResultException e){
            LOGGER.log(Level.WARNING,"No se encontro acceso {0}", nombre);
            return null;
        }
    }

    @Override
    public Acceso modificarAcceso(Acceso acceso) {
        LOGGER.log(Level.FINE,"Modificando acceso con nombre : {0} - Version: ", new Object[]{acceso.getAcceNombre()} );
        acceso = em.merge(acceso);
        return acceso;
    }
    
    @Override
    public java.util.List<Acceso> findAll() {
        Query q = em.createNamedQuery("Acceso.findAll");
        return q.getResultList();
    }
    
    @Override
    public void remove(Acceso acceso) {
        em.remove(acceso);
    }
    
    
    @Override
    public void remove(Long acceso) {
        LOGGER.log(Level.FINE,"Eliminar acceso con id {0}", acceso);
      Acceso acc = this.find(acceso);
      if (acc!=null){
        remove(acc);
        LOGGER.log(Level.INFO,"Acceso eliminado correctamente");
      }else{
        LOGGER.log(Level.INFO, "Acceso con id {} no existe", acceso);
      }
    }
    
    @Override
    public Acceso find(Object id) {
        return em.find(Acceso.class, id);
    }
    

    @Override
    public java.util.List<Acceso> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Acceso.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }
    
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Acceso> rt = cq.from(Acceso.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
