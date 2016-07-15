/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.entidades.PersonaEps;
import com.acme.sisc.agenda.exceptions.AgendaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author desarrollador
 */
@Stateless
public class FacadeMedicoEps extends AbstractFacade<PersonaEps> {

    Logger _log = Logger.getLogger(this.getClass().getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeMedicoEps() {
        super(PersonaEps.class);
    }

    @PersistenceContext(unitName = WebConstant.UNIT_NAME_PERSISTENCE)
    private EntityManager em;

    /**
     * Consulta a relacion medico EPS por id del medico y idEps
     *
     * @param idMedico
     * @param idEps
     * @return
     * @throws AgendaException
     */
    public PersonaEps consultarMedicoEps(long idMedico, long idEps) throws AgendaException {
        try {
            Query q = em.createNamedQuery(WebConstant.QUERY_PERSONA_EPS_FIND_MEDICO_EPS);
            q.setParameter(WebConstant.QUERY_PARAMETER_ID_MEDICO, idMedico);
            q.setParameter(WebConstant.QUERY_PARAMETER_ID_EPS, idEps);

            PersonaEps personaEps = (PersonaEps) q.getSingleResult();
            return personaEps;
        } catch (NoResultException e) {
            _log.log(Level.SEVERE, "error en consultarMedicoEps :", e);
            throw new AgendaException("Error consultando PersonaEps idMedico: " + idMedico + " idEps: " + idEps);
        }
    }

    public PersonaEps consultarMedicoEpsXId(long idPersonaEps) throws AgendaException {
        PersonaEps personaEps = em.find(PersonaEps.class, idPersonaEps);
        if (personaEps != null) {
            return em.find(PersonaEps.class, idPersonaEps);
        } else {
            throw new AgendaException("Error consultando PersonaEps idPersonaEps: " + idPersonaEps);
        }
    }

    /**
     * Trae todas larelaciones entre medico y Eps
     *
     * @param idMedico
     * @return
     * @throws AgendaException
     */
    public List<PersonaEps> consultarEpsMedico(long idMedico) throws AgendaException {
        try {
            Query q = em.createNamedQuery(WebConstant.QUERY_PERSONA_EPS_FIND_LIST_MEDICO_EPS);
            q.setParameter(WebConstant.QUERY_PARAMETER_ID_MEDICO, idMedico);
            List<PersonaEps> listEpsMedico = (List<PersonaEps>) q.getResultList();
            return listEpsMedico;
        } catch (NoResultException e) {
            _log.log(Level.SEVERE, "error en consultarEpsMedico :", e);
            throw new AgendaException("Error consultando lista eps medico idMedico: " + idMedico);
        }
    }

}
