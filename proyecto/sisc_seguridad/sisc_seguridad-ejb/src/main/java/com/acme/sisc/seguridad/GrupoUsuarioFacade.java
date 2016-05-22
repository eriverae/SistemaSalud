/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.agenda.entidades.GrupoUsuario;
import com.acme.sisc.agenda.entidades.Usuario;
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
public class GrupoUsuarioFacade implements GrupoUsuarioFacadeRemote, GrupoUsuarioFacadeLocal {

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(GrupoUsuarioFacade.class.getName());

    @Override
    public void crearGrupoUsuario(GrupoUsuario grupoUsuario) throws SeguridadException {
        LOGGER.info("Inicia crearGrupoUsuario(...)" + grupoUsuario.getUsuario().getUsuaUsua() + " - " + grupoUsuario.getGrupo().getGrupGrup());

        GrupoUsuario a = findByGrupUsua(grupoUsuario.getUsuario().getUsuaUsua(), grupoUsuario.getGrupo().getGrupGrup());
        if (a != null) {
            LOGGER.log(Level.WARNING, "GrupoUsuario {0}-{1} ya existe !!", new Object[]{grupoUsuario.getUsuario().getUsuaUsua(), grupoUsuario.getGrupo().getGrupGrup()});
            throw new SeguridadException("El GrupoUsuario " + grupoUsuario.getUsuario().getUsuaUsua() + " - " + grupoUsuario.getGrupo().getGrupGrup() + " ya existe en el sistema");
        }

        em.persist(grupoUsuario);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public GrupoUsuario findByGrupUsua(Long usuario, Long grupo) {
        LOGGER.log(Level.FINE, "Consulta GrupoUsuario {0}-{1}", new Object[]{usuario, grupo});
        Query q = em.createNamedQuery("Grupusu.findGrupoUsuario");
        q.setParameter("usuaUsua", usuario);
        q.setParameter("grupGrup", grupo);
        try {
            return (GrupoUsuario) q.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "No se encontro GrupoUsuario {0}-{1}", new Object[]{usuario, grupo});
            return null;
        }
    }
    
    @Override
    public GrupoUsuario modificarGrupoUsuario(GrupoUsuario grupoUsuario) {
        LOGGER.log(Level.FINE, "Modificando GrupoUsuario {0}", new Object[]{grupoUsuario.getIdGrupusu()});
        grupoUsuario = em.merge(grupoUsuario);
        return grupoUsuario;
    }

    @Override
    public java.util.List<com.acme.sisc.agenda.entidades.GrupoUsuario> findAll() {
        Query q = em.createNamedQuery("Grupusu.findAll");
        return q.getResultList();
    }

    @Override
    public void remove(GrupoUsuario grupoUsuario) {
        em.remove(grupoUsuario);
    }
    
    @Override
    public void remove(long grupoUsuario) {

        LOGGER.log(Level.FINE, "Eliminar GrupoUsuario con id {0}", grupoUsuario);
        GrupoUsuario gu = this.find(grupoUsuario);
        if (gu != null) {
            remove(gu);
            LOGGER.log(Level.INFO, "GrupoUsuario eliminado correctamente");
        } else {
            LOGGER.log(Level.INFO, "GrupoUsuario con id {} no existe", grupoUsuario);
        }
    }

    @Override
    public GrupoUsuario find(Object id) {
        return em.find(GrupoUsuario.class, id);
    }

    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<GrupoUsuario> rt = cq.from(GrupoUsuario.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public java.util.List<com.acme.sisc.agenda.entidades.GrupoUsuario> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(GrupoUsuario.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }

    @Override
    public java.util.List<Grupo> findByUsuaUsua(Long usuaUsua) {
        LOGGER.log(Level.FINE, "Consulta findByUsuaUsua {0}", new Object[]{usuaUsua});
        Query q = em.createNamedQuery("Grupo.findByUsuaUsua");
        q.setParameter("usuaUsua", usuaUsua);
        return q.getResultList();
    }

    @Override
    public void actualizaGrupoUsuario(Usuario usuaUsua, Grupo grupgrup, Boolean estado) {
        if (estado == true){
            GrupoUsuario a = new GrupoUsuario();
            a.setGrupo(grupgrup);
            a.setUsuario(usuaUsua);
            em.persist(a);
        }else{
            GrupoUsuario a = findByGrupUsua(usuaUsua.getUsuaUsua(), grupgrup.getGrupGrup());
            em.remove(a);
        }
    }
}
