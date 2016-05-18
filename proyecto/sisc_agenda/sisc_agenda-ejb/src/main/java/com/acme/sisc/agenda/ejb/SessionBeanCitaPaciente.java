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
import com.acme.sisc.agenda.exceptions.CitaException;
import com.acme.sisc.agenda.shared.ICitaLocal;
import com.acme.sisc.agenda.shared.ICitaRemote;
import java.util.ArrayList;
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
     *
     * @param cita
     * @throws CitaException
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void agendarCita(Cita cita) throws CitaException {
        logger.info("Inicia crearCitaPaciente(...)");
        //Se verifica si el paciente ya existe;
        Cita cita1 = cita;      //findByIdentificacion(cliente.getTipoIdentificacion(), cliente.getIdentificacion());
//        if (cita != null) {
//            em.lock(cita, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
//            LOGGER.log(Level.WARNING, "La cita {0} ya existe !!", cita.getIdCita());
//
//            throw new CitaException("La cita " + cita.getIdCita() + "- con el id del paciente " + cita.getPacienteEps().getPersona().getIdPersona() + " ya existe en el sistema");
//
//        }
//        em.persist(cita);
//        LOGGER.info("Finaliza crearCita(...)");
    }

    /**
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

    ////////////////////////////////////////////////////////////////////////////
    //historial de citas
    /**
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
     *
     * @param startPosition
     * @param maxResults
     * @param sortFields
     * @param sortDirections
     * @return
     */
    @Override
    public List<Cita> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Cita.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }

    ////////////////////////////////////////////////////////////////////////////
    /**
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
     *
     * @param entity
     */
    @Override
    public void remove(Cita entity) {
        em.remove(entity);
    }

    @Override
    public List<Cita> buscarCitasDisponiblesPaciente(long idEspecialidad, long idEps, String fechaBusqueda) {
        try{
            return facadeCita.buscarCitasDisponiblesPaciente(idEspecialidad, idEps, fechaBusqueda);
        }catch(NullPointerException e) {
            return new ArrayList<>();
        }
    }

}
