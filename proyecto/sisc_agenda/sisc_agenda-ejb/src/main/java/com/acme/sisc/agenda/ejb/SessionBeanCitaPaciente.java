/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.dto.GeneralResponse;
import com.acme.sisc.agenda.ejb.facade.FacadeCita;
import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.shared.ICitaLocal;
import com.acme.sisc.agenda.shared.ICitaRemote;
import java.util.ArrayList;
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
     * Hace llamado al facadeCita para buscar una cita por el id
     *
     * @param id
     * @return
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    @Override
    public Cita find(Long id) {
        logger.log(Level.WARNING, "\nllamando a facadeCita id = " + id);
        return facadeCita.ObtenerLaCita(id);
    }

    /**
     * Trae resumen de las ultimas citas de un paciente
     *
     * @param idPaciente
     * @return
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    @Override
    public List<Cita> listaCitasPendientePaciente(long idPaciente) {
        try {
            logger.log(Level.INFO, "\n\nconsultando la lista de citas del paciente: ", idPaciente);
            return facadeCita.CitasDelPaciante(idPaciente);
        } catch (NullPointerException nu) {
            return null;
        }
    }

    /**
     * Metodo para cancelar cita de un paciente
     *
     * @param idCita
     * @return
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public GeneralResponse cancelarCita1(Long idCita) {
        logger.log(Level.WARNING, "\n\nSESION-BEAN-CITA-PACIENTE\n El paciente cancela la cita: " + idCita);
        return facadeCita.PacienteCancelaSuCita(idCita);
    }

    /**
     * Trae el historial de citas de un paciente
     *
     * @param idPaciente
     * @return
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    @Override
    public List<Cita> listaCitasHistorialPacienteEPS(long idPaciente) {
        try {
            logger.log(Level.INFO, "\n\nconsultando la lista historial EPS de paciente: ", idPaciente);
            return facadeCita.CitasDelPacianteHistorialEPS(idPaciente);
        } catch (NullPointerException nu) {
            return null;
        }
    }

    /**
     * Metodo para contar registros de la tabla cita
     *
     * @return
     */
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Cita> rt = cq.from(Cita.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * Metodo para eliminar una cita por id
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        logger.log(Level.FINE, "\n\nEliminar cita con id {0}", id);
        Cita cita = this.find(id);
        if (cita != null) {
            remove(cita);
            logger.log(Level.INFO, "Cita de paciente eliminado correctamente");
        } else {
            logger.log(Level.INFO, "La cita con id de paciente {} no existe", id);
        }
    }

    /**
     * Metodo para eliminar una cita por objeto
     *
     * @param entity
     */
    @Override
    public void remove(Cita entity) {
        em.remove(entity);
    }

    /**
     * Metodo para buscar citas dispobibles por paciente
     *
     * @param idEspecialidad
     * @param idEps
     * @param fechaBusqueda
     * @return
     */
    @Override
    public List<Cita> buscarCitasDisponiblesPaciente(long idEspecialidad, long idEps, String fechaBusqueda) {
        try {
            return facadeCita.buscarCitasDisponiblesPaciente(idEspecialidad, idEps, fechaBusqueda);
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Metodo para agendar cita del medico por parte del paciente.
     *
     * @param idCita
     * @param idPersona
     * @return
     */
    @Override
    public GeneralResponse agendarCita(long idCita, long idPersona) {
        return facadeCita.agendarCita(idCita, idPersona);
    }

}
