/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
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
* Clase GrupoFacade, es la fachada para las consultas, creación, modificación y 
* eliminacion de los perfiles
* 
* @author  Julio
* @version 1.0
* @since   2016-05-21
*/
@Stateless
public class GrupoFacade implements GrupoFacadeRemote, GrupoFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(GrupoFacade.class.getName());
    
    /**
    * Metodo getEntityManager, es el punto de acceso para persistir las entidades desde la BD
    * @return EntityManager em, retorna el objeto de EntityManager
    */
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
    * Metodo crearGrupo, crea el grupo con el objeto que llega del servicio, accediento a la entidad
    * @param grupo es el objeto a crear y persistir en la BD
    */
    @Override
    public Grupo crearGrupo(Grupo grupo) throws SeguridadException{
        LOGGER.info("Inicia Grupo(...)");
        
        Grupo g = findByNom(grupo.getGrupNombr());
        if (g != null){
            LOGGER.warning("Acceso "+ grupo.getGrupNombr() + " ya existe !!");
            throw new SeguridadException("El grupo " +grupo.getGrupNombr() +" ya existe en el sistema");
        }
        
        grupo.setGrupEsta("Activo");
        em.persist(grupo);
        em.refresh(grupo);
        return grupo;
    }

    /**
    * Metodo findByNom, devuelve un objeto de tipo Grupo consultado a partir del parametro de entrada
    * @param nombre String es el campo nombre del grupo, el cual va a buscar
    * @return retorna Grupo, el el objeto encontrado a partir del metodo de la busqueda
    */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Grupo findByNom(String nombre) {
        LOGGER.log(Level.FINE,"Consulta Grupo {0}",  nombre);
        Query  q = em.createNamedQuery("Grupo.findByNom");
        q.setParameter("grupNombr", nombre);
        try{
            return (Grupo) q.getSingleResult();
        }catch(NoResultException e){
            LOGGER.log(Level.WARNING,"No se encontro grupo {0}", nombre);
            return null;
        }
    }

    /**
    * Metodo modificarGrupo, devuelve un objeto de tipo Grupo una vez que se modifican sus atributos
    * @param Grupo grupo es el objeto a modififcar
    * @return retorna Grupo, el el objeto una vez modificado 
    */
    @Override
    public Grupo modificarGrupo(Grupo grupo) {
        LOGGER.log(Level.FINE,"Modificando grupo con nombre : {0} - Version: ", new Object[]{grupo.getGrupNombr()} );
        grupo = em.merge(grupo);
        return grupo;
    }
    
    /**
    * Metodo findAll, retorna una lista de objetos de tipo Grupo
    * @return java.util.List<Grupo>, todos los grupos existentes en la BD
    */
    @Override
    public java.util.List<Grupo> findAll() {
        Query q = em.createNamedQuery("Grupo.findAll");
        return q.getResultList();
    }
    
    /**
    * Metodo remove, elimina el objeto con el metodo remove 
    * @param Grupo grupo es el objeto a eliminar en la BD
    */
    @Override
    public void remove(Grupo grupo) {
        em.remove(grupo);
    }
    
    /**
    * Metodo remove, elimina el objeto con el metodo remove, antes de ello crea un objeto a partir del id 
    * @param Long grupo id del grupo a eliminar
    */
    @Override
    public void remove(Long grupo) {
        LOGGER.log(Level.FINE,"Eliminar grupo con id {0}", grupo);
      Grupo grup = this.find(grupo);
      if (grup!=null){
        remove(grup);
        LOGGER.log(Level.INFO,"Grupo eliminado correctamente");
      }else{
        LOGGER.log(Level.INFO, "Grupo con id {} no existe", grupo);
      }
    }
    
    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return Grupo es el objeto que trae a partir del metodo find
    */
    @Override
    public Grupo find(Object id) {
        return em.find(Grupo.class, id);
    }
    
    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<Grupo> la lista de objetos en referencia
    */
    @Override
    public java.util.List<Grupo> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Grupo.class));
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
        javax.persistence.criteria.Root<Grupo> rt = cq.from(Grupo.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    /**
    * Metodo obtenerGrupos, consulta los perfiles del usuario
    * @param String usuario es el mail del usuario
    * @return List<String>, es la lista de los perfiles del usuario
    */
    public List<String> obtenerGrupos(String usuario){
        Query q = em.createNativeQuery("Select grup_nombr from Grupo, Grupusu, Usuario where Grupo.grup_grup = Grupusu.grup_grup"
                + " and Usuario.usua_usua = Grupusu.usua_usua and Usuario.usua_email = ? ");
        q.setParameter(1, usuario);
        return q.getResultList();
    }

}