/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.AccesoGrupo;
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
 * @author Julio
 */
@Stateless
public class AccesoGrupoFacade implements AccesoGrupoFacadeRemote, AccesoGrupoFacadeLocal {

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(AccesoGrupoFacade.class.getName());

    @Override
    public void crearAccesoGrupo(AccesoGrupo accesoGrupo) throws SeguridadException {
        LOGGER.info("Inicia Acceso(...)" + accesoGrupo.getAcceso().getAcceAcce() + " - " + accesoGrupo.getGrupo().getGrupGrup());

        AccesoGrupo a = findByAcceGrup(accesoGrupo.getAcceso().getAcceAcce(), accesoGrupo.getGrupo().getGrupGrup());
        if (a != null) {
            LOGGER.log(Level.WARNING, "AccesoGrupo {0}-{1} ya existe !!", new Object[]{accesoGrupo.getAcceso().getAcceAcce(), accesoGrupo.getGrupo().getGrupGrup()});
            throw new SeguridadException("El AccesoGrupo " + accesoGrupo.getAcceso().getAcceAcce() + "-" + accesoGrupo.getGrupo().getGrupGrup() + " ya existe en el sistema");
        }

        em.persist(accesoGrupo);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public AccesoGrupo findByAcceGrup(Long acceso, Long grupo) {
        LOGGER.log(Level.FINE, "Consulta AccesoGrupo {0}-{1}", new Object[]{acceso, grupo});
        Query q = em.createNamedQuery("Accgrup.findAccesoGrupo");
        q.setParameter("acceAcce", acceso);
        q.setParameter("grupGrup", grupo);
        try {
            return (AccesoGrupo) q.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "No se encontro AccesoGrupo {0}-{1}", new Object[]{acceso, grupo});
            return null;
        }
    }

    @Override
    public AccesoGrupo modificarGrupoAcceso(AccesoGrupo accesoGrupo) {
        LOGGER.log(Level.FINE, "Modificando AccesoGrupo {0}", new Object[]{accesoGrupo.getIdAccgrup()});
        accesoGrupo = em.merge(accesoGrupo);
        return accesoGrupo;
    }

    @Override
    public java.util.List<com.acme.sisc.agenda.entidades.AccesoGrupo> findAll() {
        Query q = em.createNamedQuery("Accgrup.findAll");
        return q.getResultList();
    }

    @Override
    public void remove(AccesoGrupo accesoGrupo) {
        em.remove(accesoGrupo);
    }

    @Override
    public void remove(long accesoGrupo) {

        LOGGER.log(Level.FINE, "Eliminar accesoGrupo con id {0}", accesoGrupo);
        AccesoGrupo acc = this.find(accesoGrupo);
        if (acc != null) {
            remove(acc);
            LOGGER.log(Level.INFO, "Acceso eliminado correctamente");
        } else {
            LOGGER.log(Level.INFO, "Acceso con id {} no existe", accesoGrupo);
        }
    }

    @Override
    public AccesoGrupo find(Object id) {
        return em.find(AccesoGrupo.class, id);
    }

    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<AccesoGrupo> rt = cq.from(AccesoGrupo.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public java.util.List<com.acme.sisc.agenda.entidades.AccesoGrupo> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(AccesoGrupo.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }
    
    

}
