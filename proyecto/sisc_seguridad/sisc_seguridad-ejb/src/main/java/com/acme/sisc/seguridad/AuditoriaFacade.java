/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.AuditoriaUsuario;
import com.acme.sisc.agenda.entidades.Usuario;
import com.acme.sisc.common.ejbLocator.EJBLocator;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Julio
 */
@Stateless
public class AuditoriaFacade implements AuditoriaFacadeRemote, AuditoriaFacadeLocal {

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(AuditoriaFacade.class.getName());

    protected EntityManager getEntityManager() {
        return em;
    }
    
    private UsuarioFacadeLocal usuarioFacade;
    
    private static final String LOCAL_EJB_USUARIO = "java:app/sisc_seguridad-ejb-1.0-SNAPSHOT/UsuarioFacade!com.acme.sisc.seguridad.UsuarioFacadeLocal";

    @Override
    public void crearAuditoria(String emailUsuario, String observacion, String dirIP, String hostName) {
        LOGGER.info("Inicia crearAuditoria(...)");
        
        AuditoriaUsuario au = new AuditoriaUsuario();
        
        try {
            usuarioFacade = (UsuarioFacadeLocal) EJBLocator.lookup(LOCAL_EJB_USUARIO);
        } catch (NamingException ex) {
            Logger.getLogger(AuditoriaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        Usuario u = usuarioFacade.findByEmail(emailUsuario);
//        if (a != null){
//            LOGGER.warning("Acceso "+ acceso.getAcceNombre() + " ya existe !!");
//            throw new SeguridadException("El acceso " +acceso.getAcceNombre() +" ya existe en el sistema");
//        }
        au.setAudiFech(new Date());
        
        au.setAudiUsua(u.getUsuaUsua());
        au.setAudiObser(observacion);
        au.setAudiDrip(dirIP);
        au.setAudiHostn(hostName);
        au.setUsuario(u);
        em.persist(au);
    }

    @Override
    public java.util.List<AuditoriaUsuario> findAll() {
        Query q = em.createNamedQuery("AuditoriaUsuario.findAll");
        return q.getResultList();
    }

    @Override
    public AuditoriaUsuario find(Object id) {
        return em.find(AuditoriaUsuario.class, id);
    }
    

    @Override
    public java.util.List<AuditoriaUsuario> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(AuditoriaUsuario.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }

    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<AuditoriaUsuario> rt = cq.from(AuditoriaUsuario.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
