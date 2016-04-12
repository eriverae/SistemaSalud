/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.entidades.Agenda;
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
public class FacadeAgenda  extends  AbstractFacade <Agenda>  {
    
     Logger log = Logger.getLogger(this.getClass().getName());

    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeAgenda() {
        super(Agenda.class);
    }
    
    @PersistenceContext(unitName = WebConstant.UNIT_NAME_PERSISTENCE)
    private EntityManager em;
    
    
    public boolean insertarAgenda(Agenda agenda){
        try{
            em.persist(agenda);
            return true;
        }catch(Exception e){
            log.log(Level.SEVERE, "ERROR EN insertarAgenda",e);
            return false;
        }
        
    }
    public List<Agenda> consultarAgendasMedico(long idMedico){
       try{
            Query  q = em.createNamedQuery(WebConstant.QUERY_AGENDA_FIND_BY_ID_MEDICO);
            q.setParameter(WebConstant.QUERY_PARAMETER_ID_MEDICO, idMedico);
            List<Agenda> listaAgendasMedico=(List<Agenda>) q.getResultList();
             return listaAgendasMedico;
        }catch(Exception e){
            e.printStackTrace();
            return  null;
        }
      
    }
}
