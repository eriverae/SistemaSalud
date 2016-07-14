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
* Clase AccesoFacade, es la fachada para las consultas, creación, modificación y 
* eliminación de los accesos
* 
* @author  Julio
* @version 1.0
* @since   2016-05-08
*/
@Stateless
public class AccesoFacade implements AccesoFacadeRemote, AccesoFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(AccesoFacade.class.getName());
    
    /**
    * Metodo getEntityManager, es el punto de acceso para persistir las entidades desde la BD
    * @return EntityManager em, retorna el objeto de EntityManager
    */
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
    * Metodo crearAcceso, crea el acceso con el objeto que llega del servicio, accediento a la entidad
    * @param acceso es el objeto a crear y persistir en la BD
    */
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

    /**
    * Metodo findByAcceNombre, devuelve un objeto de tipo Acceso consultado a partir del parametro de entrada
    * @param nombre String es el campo nombre del acceso, el cual va a buscar
    * @return retorna Acceso, el el objeto encontrado a partir del metodo de la busqueda
    */
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

    /**
    * Metodo modificarAcceso, devuelve un objeto de tipo Acceso una vez que se modifican sus atributos
    * @param Acceso acceso es el objeto a modififcar
    * @return retorna Acceso, el el objeto una vez modificado 
    */
    @Override
    public Acceso modificarAcceso(Acceso acceso) {
        LOGGER.log(Level.FINE,"Modificando acceso con nombre : {0} - Version: ", new Object[]{acceso.getAcceNombre()} );
        acceso = em.merge(acceso);
        return acceso;
    }
    
    /**
    * Metodo findAll, retorna una lista de obbjetos de tipo Acceso
    * @return java.util.List<Acceso>, todos los accesos existentes en la BD
    */
    @Override
    public java.util.List<Acceso> findAll() {
        Query q = em.createNamedQuery("Acceso.findAll");
        return q.getResultList();
    }
    
    /**
    * Metodo remove, elimina el objeto con el metodo remove 
    * @param Acceso acceso es el objeto a eliminar en la BD
    */
    @Override
    public void remove(Acceso acceso) {
        em.remove(acceso);
    }
    
    /**
    * Metodo remove, elimina el objeto con el metodo remove, antes de ello crea un objeto a partir del id 
    * @param Long acceso id del acceso a eliminar
    */
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
    
    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return Acceso es el objeto que trae a partir del metodo find
    */
    @Override
    public Acceso find(Object id) {
        return em.find(Acceso.class, id);
    }
    
    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<Acceso> la lista de objetos en referencia
    */
    @Override
    public java.util.List<Acceso> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Acceso.class));
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
        javax.persistence.criteria.Root<Acceso> rt = cq.from(Acceso.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
