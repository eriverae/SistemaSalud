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
 *
 * @author rm-rf
 */
@Stateless
public class GrupoFacade implements GrupoFacadeRemote, GrupoFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(GrupoFacade.class.getName());
    
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void crearGrupo(Grupo grupo) throws SeguridadException{
        LOGGER.info("Inicia Grupo(...)");
        
        Grupo g = findByNom(grupo.getGrupNombr());
        if (g != null){
            LOGGER.warning("Acceso "+ grupo.getGrupNombr() + " ya existe !!");
            throw new SeguridadException("El grupo " +grupo.getGrupNombr() +" ya existe en el sistema");
        }
        
        grupo.setGrupEsta("Activo");
        em.persist(grupo);
    }

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

    @Override
    public Grupo modificarGrupo(Grupo grupo) {
        LOGGER.log(Level.FINE,"Modificando grupo con nombre : {0} - Version: ", new Object[]{grupo.getGrupNombr()} );
        grupo = em.merge(grupo);
        return grupo;
    }
    
    @Override
    public java.util.List<Grupo> findAll() {
        Query q = em.createNamedQuery("Grupo.findAll");
        return q.getResultList();
    }
    
    @Override
    public void remove(Grupo grupo) {
        em.remove(grupo);
    }
    
    
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
    
    @Override
    public Grupo find(Object id) {
        return em.find(Grupo.class, id);
    }
    

    @Override
    public java.util.List<Grupo> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Grupo.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }
    
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Grupo> rt = cq.from(Grupo.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public List<String> obtenerGrupos(String usuario){
        Query q = em.createNativeQuery("Select grup_nombr from Grupo, Grupusu, Usuario where Grupo.grup_grup = Grupusu.grup_grup"
                + " and Usuario.usua_usua = Grupusu.usua_usua and Usuario.usua_email = ? ");
        q.setParameter(1, usuario);
        return q.getResultList();
    }
    
}
