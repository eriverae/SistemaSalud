/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.PersonaEps;
import java.util.Date;
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
 * @author BryanCFz-user
 */


@Stateless
public class FacadeCita extends AbstractFacade<Cita> {

    Logger _log = Logger.getLogger(this.getClass().getName());

    @PersistenceContext(unitName = WebConstant.UNIT_NAME_PERSISTENCE)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeCita() {
        super(Cita.class);
    }

    public List<Cita> CitasDelPaciante(long idPAciente) {

        PersonaEps paciente = em.find(PersonaEps.class, idPAciente);
        if (paciente != null && paciente.getListaCitasPaciente() != null) {
            return paciente.getListaCitasPaciente();
        } else {
            return null;
        }
    }
    /**
     * 
     * @param idMedico
     * @param fechaInicio
     * @param fechaFin
     * @return 
     */
    public List<Cita> validarCitasAgendadasMedico(long idMedico, Date fechaInicio, Date fechaFin) {

        try {
            Query q = em.createNamedQuery(WebConstant.QUERY_CITA_FIND_FECHA_INICIO_FECHA_FIN);
            q.setParameter(WebConstant.QUERY_PARAMETER_ID_MEDICO, idMedico);
            q.setParameter(WebConstant.QUERY_PARAMETER_HORA_INICIO, fechaInicio);
            q.setParameter(WebConstant.QUERY_PARAMETER_HORA_FINAL, fechaFin);

            List<Cita> listCitas = (List<Cita>) q.getResultList();
            
            if(listCitas!=null&&listCitas.size()>0){
                return listCitas;
            }else{
                return null;
            }

        } catch (NoResultException e) {
            _log.log(Level.SEVERE, "NO SE ENCONTRARON RESULTADOS DE CITAS AGENDADAS PARA EL MEDICO CON ID: "+idMedico +" ENTRE: "+
                    fechaFin.toString()+" AL: "+fechaFin.toString());
            return null;
        }
        
    }

}
