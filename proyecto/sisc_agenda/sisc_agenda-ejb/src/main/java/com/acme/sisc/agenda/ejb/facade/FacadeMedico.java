/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.entidades.PersonaEps;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author desarrollador
 */
@Stateless
public class FacadeMedico extends  AbstractFacade <PersonaNatural>  {
         Logger _log = Logger.getLogger(this.getClass().getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeMedico() {
        super(PersonaNatural.class);
    }
    
    @PersistenceContext(unitName = WebConstant.UNIT_NAME_PERSISTENCE)
    private EntityManager em;
    
    /**
     * Consulta lista eps de persona natural
     * @param idMedico
     * @return 
     */
    public List<PersonaEps> consultarListaEpsMedico(long idMedico){
       PersonaNatural medico= em.find(PersonaNatural.class, idMedico);
       if(medico!=null&&medico.getListaPersonasEps()!=null){
           return medico.getListaPersonasEps();
       }else{
           return null;
       }
       
    }
    
}
