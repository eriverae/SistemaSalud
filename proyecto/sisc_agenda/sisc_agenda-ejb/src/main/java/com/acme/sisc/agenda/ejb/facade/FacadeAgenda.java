/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.entidades.Agenda;
import com.acme.sisc.common.util.JMSUtil;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author desarrollador
 */
@Stateless
public class FacadeAgenda extends AbstractFacade<Agenda> {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeAgenda() {
        super(Agenda.class);
    }

    @PersistenceContext(unitName = WebConstant.UNIT_NAME_PERSISTENCE)
    private EntityManager em;

    /**
     * Metodo que persiste un registro de la tabla agenda en bd.
     *
     * @param agenda
     * @return
     */
    public boolean insertarAgenda(Agenda agenda) {
        try {
            em.persist(agenda);
            JMSUtil.sendMessage(agenda, "java:/jms/queue/SiscQueue");
            return true;
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR EN insertarAgenda", e);
            return false;
        }

    }

    /**
     * Metodo que consulta en bd los registro de agendas
     *
     * @param idMedico
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public List<Agenda> consultarAgendasMedico(long idMedico, Date fechaInicial, Date fechaFinal) {
        try {
            Query q = em.createNamedQuery(WebConstant.QUERY_AGENDA_FIND_BY_ID_MEDICO);
            q.setParameter(WebConstant.QUERY_PARAMETER_ID_MEDICO, idMedico);
            List<Agenda> listaAgendasMedico = (List<Agenda>) q.getResultList();
            return listaAgendasMedico;
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR EN consultarAgendasMedico", e);
            return null;
        }

    }

}
