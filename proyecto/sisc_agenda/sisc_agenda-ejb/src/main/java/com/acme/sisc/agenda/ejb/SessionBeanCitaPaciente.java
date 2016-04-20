/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.ejb.facade.FacadeCita;
import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.exceptions.CitaException;
import com.acme.sisc.agenda.shared.ICitaLocal;
import com.acme.sisc.agenda.shared.ICitaRemote;
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
public class SessionBeanCitaPaciente implements ICitaLocal, ICitaRemote {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @PersistenceContext(unitName = WebConstant.UNIT_NAME_PERSISTENCE)
    private EntityManager em;

    @EJB
    FacadeCita facadeCita;

    @Override
    public List<Cita> listaCitasPaciente(long idPaciente) {
        try {
            logger.log(Level.INFO, "consultando la lista de citas del paciente", idPaciente);
            return facadeCita.CitasDelPaciante(idPaciente);
        } catch (NullPointerException nu) {
            return null;
        }
    }

    @Override
    public void remove(Long id) {
        logger.log(Level.FINE, "Eliminar cliente con id {0}", id);
        Cita cita = this.find(id);
        if (cita != null) {
            remove(cita);
            logger.log(Level.INFO, "Cita de paciente eliminado correctamente");
        } else {
            logger.log(Level.INFO, "La cita con id de paciente {} no existe", id);
        }
    }

    @Override
    public void remove(Cita entity) {
        em.remove(entity);
    }

    @Override
    public Cita find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearCita(Cita cita) throws CitaException {
        /*LOGGER.info("Inicia crearCita(...)");
        //Se verifica si el paciente ya existe;
        Cita cita = ;      //findByIdentificacion(cliente.getTipoIdentificacion(), cliente.getIdentificacion());
        if (cita != null) {
            em.lock(cita, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
            LOGGER.log(Level.WARNING, "La cita {0} ya existe !!", cita.getIdCita());
            
            throw new CitaException("La cita " + cita.getIdCita() + "- con el id del paciente " + cita.getPacienteEps().getPersona().getIdPersona() + " ya existe en el sistema");

        }
        em.persist(cita);
        LOGGER.info("Finaliza crearCita(...)");*/
    }

    @Override
    public Cita modificarCliente(Cita cita) {
        logger.log(Level.FINE, "Modificando la cita : {0} - del Paciente: {1}", new Object[]{cita.getIdCita(), cita.getPacienteEps().getPersona().getNombres()});
        cita = em.merge(cita);
        return cita;
    }

}
