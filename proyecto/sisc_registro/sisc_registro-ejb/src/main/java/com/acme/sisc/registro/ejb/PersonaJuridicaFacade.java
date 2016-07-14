/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.registro.ejb;

import com.acme.sisc.agenda.entidades.PersonaJuridica;
import com.acme.sisc.agenda.entidades.TipoIdentificacion;
import com.acme.sisc.common.exceptions.CustomException;
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
public class PersonaJuridicaFacade implements IPersonaJuridicaFacadeRemote, IPersonaJuridicaFacadeLocal {

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(PersonaJuridicaFacade.class.getName());

    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaJuridicaFacade() {
    }

    /**
     * Buscar una persona jurídica según su tipo y número de identificación.
     *
     * @param tId Tipo identificación.
     * @param identificacion Número identificación.
     * @return Objeto PersonaJuridica, si existe.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public PersonaJuridica findByIdentificacion(TipoIdentificacion tId, long identificacion) throws CustomException {
        LOGGER.log(Level.FINE, "Consulta persona {0}", identificacion);
        Query q = em.createNamedQuery("Persona.findByIdentificacion");
        q.setParameter("tipoIdentificacion", tId);
        q.setParameter("numeroIdentificacion", identificacion);
        try {
            return ((PersonaJuridica) q.getSingleResult());
        } catch (NoResultException nre) {
            String msgError = "No existe la EPS " + tId + "-" + identificacion;
            LOGGER.log(Level.WARNING, msgError);
            throw new CustomException(msgError);
        }
    }

    /**
     * Actualizar una persona jurídica.
     *
     * @param p Objeto de persona jurídica.
     * @return Objeto de persona jurídica.
     */
    @Override
    public PersonaJuridica modificarPersonaJuridica(PersonaJuridica p) {
        LOGGER.log(Level.FINE, "Modificando persona juridica con nombre : {0} - Versión: {1}", new Object[]{p.getRazonSocial(), p.getVersion()});
        p = em.merge(p);
        return p;
    }

    /**
     * Listar todas las personas jurídicas (EPS).
     *
     * @return Listado de PersonaJuridica.
     */
    @Override
    public List<PersonaJuridica> findAll() {
        //Query  q = em.createNamedQuery("Cliente.findAll");
        Query q = em.createQuery("SELECT p FROM PersonaJuridica p");
        return q.getResultList();
    }

    /**
     * Eliminar una EPS.
     *
     * @param entity Objeto de persona jurídica.
     */
    @Override
    public void remove(PersonaJuridica entity) {
        em.remove(entity);
    }

    /**
     * Eliminar una EPS según su identificador único.
     *
     * @param id Identificador único de la persona jurídica.
     */
    @Override
    public void remove(Long id) throws CustomException {
        LOGGER.log(Level.FINE, "Eliminar persona juridica con id {0}", id);
        PersonaJuridica p = this.find(id);
        if (p != null) {
            remove(p);
            LOGGER.log(Level.INFO, "Persona juridica eliminada correctamente");
        } else {
            String msgError = "No existe la EPS";
            LOGGER.log(Level.WARNING, msgError);
            throw new CustomException(msgError);
        }
    }

    /**
     * Buscar una EPS según su identificador único.
     *
     * @param id Identificador único.
     * @return Objeto de PersonaJuridica.
     */
    @Override
    public PersonaJuridica find(Object id) {
        return em.find(PersonaJuridica.class, id);
    }

    /**
     * Buscar un listado de EPS entre un rango.
     *
     * @param startPosition Posición inicial.
     * @param maxResults Máximo de resultados.
     * @param sortFields Campo por el cual ordenar la lista.
     * @param sortDirections Dirección de orden.
     * @return Listado de PersonaJuridica.
     */
    @Override
    public List<PersonaJuridica> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(PersonaJuridica.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }

    /**
     * Cantidad de personas jurídicas en el sistema.
     *
     * @return
     */
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<PersonaJuridica> rt = cq.from(PersonaJuridica.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * Creación de una EPS.
     *
     * @param personaJuridica Objeto de persona jurídica.
     * @throws Exception
     */
    @Override
    public void crearPersonaJuridica(PersonaJuridica personaJuridica) throws Exception {
        try {
            LOGGER.info("Inicia crearPersonaJuridica(...)");
            //Se verifica si ya existe
            PersonaJuridica p = findByIdentificacion(personaJuridica.getTipoIdentificacion(), personaJuridica.getNumeroIdentificacion());
            if (p != null) {
                em.lock(p, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
                LOGGER.warning("Persona juridica " + personaJuridica.getNumeroIdentificacion() + " ya existe !!");
                throw new CustomException("La EPS " + personaJuridica.getTipoIdentificacion() + "-"
                        + personaJuridica.getNumeroIdentificacion() + " ya existe en el sistema");
            }
            em.persist(personaJuridica);
            LOGGER.info("Finaliza crearPersonaJuridica(...)");
        } catch (Exception ex) {
            String msgError = "La EPS ya existe en el sistema";
            LOGGER.log(Level.WARNING, msgError + " - Exception: " + ex.getLocalizedMessage());
            throw new CustomException(msgError);
        }
    }
}
