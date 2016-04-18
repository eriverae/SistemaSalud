/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.PersonaEps;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BryanCFz-user
 */
public class FacadeCita extends AbstractFacade<Cita>{

    Logger _log = Logger.getLogger(this.getClass().getName());
    
    @PersistenceContext(unitName = WebConstant.UNIT_NAME_PERSISTENCE)
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public FacadeCita(){
        super(Cita.class);
    }
    
    public List<Cita> CitasDelPaciante(long idPAciente){
                
        PersonaEps paciente = em.find(PersonaEps.class, idPAciente);
        if(paciente!=null && paciente.getListaCitasPaciente()!=null){
            return paciente.getListaCitasPaciente();
        } else {
            return null;
        }
    }
    
}
