/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.ejb.facade.FacadeCita;
import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.shared.ICitaLocal;
import com.acme.sisc.agenda.shared.ICitaRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.bean.ViewScoped;  // se mantiene vivo para todas las peticiones
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BryanCFz-user
 */
@Stateless
//@Stateful(mappedName = "cita_paciente")
public class SessionBeanCitaPaciente implements ICitaLocal, ICitaRemote {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @PersistenceContext(unitName = WebConstant.UNIT_NAME_PERSISTENCE)
    private EntityManager em;

    //@Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    FacadeCita facadeCita;
    
    
    /**
     * 
     * @param idPaciente
     * @return 
     */
    @Override
    public List<Cita> listaCitasPaciente(long idPaciente) {
        try {
            logger.log(Level.INFO, "consultando la lista de citas del paciente", idPaciente);
            return facadeCita.CitasDelPaciante(idPaciente);
        } catch (NullPointerException nu) {
            return null;
        }
    }
    
    
    /**
     * Hace llamado al facadeCita para buscar una cita por el id
     * @param id
     * @return 
     */
    @Override
    public Cita find(Long id) {
        logger.log(Level.WARNING, "llamando a facadeCita id = "+ id);
        return facadeCita.ObtenerLaCita(id);
    }

    @Override
    public void remove(Long id) {
        logger.log(Level.FINE, "Eliminar cita con id {0}", id);
        Cita cita = this.find(id);
        if (cita != null) {
            remove(cita);
            logger.log(Level.INFO, "Cita de paciente eliminado correctamente");
        } else {
            logger.log(Level.INFO, "La cita con id de paciente {} no existe", id);
        }
    }
    
    
    /**
     * 
     * @param entity 
     */
    @Override
    public void remove(Cita entity) {
        em.remove(entity);
    }

    
    /**
     * 
     * @param cita
     * @return 
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public String cancelarCita(Cita cita) {
        
        logger.log(Level.WARNING, "\n\nSESION-BEAN-CITA-PACIENTE\n El paciente cancela la cita: "+ cita.getIdCita() + "\n Estado de la cita actual = " + cita.getEstadoCita());
        return facadeCita.PacienteCancelaSuCita(cita);
    }
    
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public String cancelarCita1(Long idCita) {
        logger.log(Level.WARNING, "\n\nSESION-BEAN-CITA-PACIENTE\n El paciente cancela la cita: "+ idCita);
        return facadeCita.PacienteCancelaSuCita(idCita);
    }


    

}

    /*@Override
    public void crearCita(Cita cita) throws CitaException {
        LOGGER.info("Inicia crearCita(...)");
        //Se verifica si el paciente ya existe;
        Cita cita = ;      //findByIdentificacion(cliente.getTipoIdentificacion(), cliente.getIdentificacion());
        if (cita != null) {
            em.lock(cita, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
            LOGGER.log(Level.WARNING, "La cita {0} ya existe !!", cita.getIdCita());
            
            throw new CitaException("La cita " + cita.getIdCita() + "- con el id del paciente " + cita.getPacienteEps().getPersona().getIdPersona() + " ya existe en el sistema");

        }
        em.persist(cita);
        LOGGER.info("Finaliza crearCita(...)");
    }*/