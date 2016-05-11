/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.ejb.facade.FacadeUtilitariosAgenda;
import com.acme.sisc.agenda.entidades.Especialidad;
import com.acme.sisc.agenda.shared.IUtilitariosAgendaLocal;
import com.acme.sisc.agenda.shared.IUtilitariosAgendaRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BryanCFz-user
 */
@Stateless
public class SessionBeanUtilitariosAgenda implements IUtilitariosAgendaLocal, IUtilitariosAgendaRemote {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @PersistenceContext(unitName = WebConstant.UNIT_NAME_PERSISTENCE)
    private EntityManager em;

    //@Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    FacadeUtilitariosAgenda facadeUtilitariosAgenda;

    @Override
    public List<Especialidad> especialidadesMedicosEps() {
        logger.log(Level.WARNING, "\nESPECIALIDADES : sessionBean\n");
        return facadeUtilitariosAgenda.getEspecialidadesList();
    }
    
    
    
    
}
