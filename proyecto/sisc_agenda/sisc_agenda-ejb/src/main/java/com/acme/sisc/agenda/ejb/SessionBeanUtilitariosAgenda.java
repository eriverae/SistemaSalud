/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.ejb.facade.FacadeUtilitariosAgenda;
import com.acme.sisc.agenda.entidades.Especialidad;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.agenda.entidades.PersonaNaturalEspecialidad;
import com.acme.sisc.agenda.shared.IUtilitariosAgendaLocal;
import com.acme.sisc.agenda.shared.IUtilitariosAgendaRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    @Override
    public List<Especialidad> especialidadesEps() {
        logger.log(Level.WARNING, "\nESPECIALIDADES : sessionBean\n");
        return facadeUtilitariosAgenda.getEspecialidadesList();
    }
    
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    @Override
    public List<PersonaNaturalEspecialidad> listaEspecialidadMedicosEps(String especialidad) {
        logger.log(Level.WARNING, "\nLISTA MEDICOS SEGUN LA ESPECIALIDAD : sessionBean\n");
        return facadeUtilitariosAgenda.getMedicosList(especialidad);
    }
    
    
    
    
}
