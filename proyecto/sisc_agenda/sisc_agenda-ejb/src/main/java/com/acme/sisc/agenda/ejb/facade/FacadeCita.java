/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.entidades.Cita;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author BryanCFz-user
 */
@Stateful
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

    /**
     * Trae el listado de las citas de un paciente
     *
     * @param idPaciente
     * @return
     */
    public List<Cita> CitasDelPaciante(long idPaciente) {

        try {
            Query q = em.createNamedQuery(WebConstant.QUERY_CITA_FIND_BY_ID_PACIENTE);
            q.setParameter(WebConstant.QUERY_PARAMETER_ID_PACIENTE, idPaciente);

//            q.setFirstResult(1);
//            q.setMaxResults(5); //filtrar por cantidad resultado
            List<Cita> listacitasPaciente = (List<Cita>) q.getResultList();
            _log.log(Level.WARNING, "ULTIMO REGISTRO LISTA-CITAS-PACIENTE, id= {0}", listacitasPaciente.get((listacitasPaciente.size() - 1)).getIdCita());
            return listacitasPaciente;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Retorna una cita, siempre y cuando el id este en la base de datos. De lo
     * contrario retorna NULL
     *
     * @param idCita
     * @return
     */
    public Cita ObtenerLaCita(Long idCita) {
        try {
            Query q = em.createNamedQuery(WebConstant.QUERY_CITA_FIND_BY_ID);
            q.setParameter(WebConstant.QUERY_PARAMETER_ID_CITA, idCita);
            Cita cita = (Cita) q.getSingleResult();

            _log.log(Level.WARNING, "ENCONTRADA OBJETO CITA = " + cita.getIdCita());
            return cita;
        } catch (Exception e) {
            _log.log(Level.WARNING, "NO ENCUENTRO NADA .. LO SIENTO :(");
            return null;
        }
    }

    /////////////////////////
    /**
     * pacient cancela su cita mediante un click y aquio cambiamos el estado a
     * cancelado en su cita cancelada
     *
     * @param cita
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String PacienteCancelaSuCita(Cita cita) {
        try {
            _log.log(Level.WARNING, "1. CITA ID: " + cita.getIdCita() + "\n");
            cita.setEstadoCita("CANCELADA");

            Query q = em.createNativeQuery("update CITA set estado_cita = ? WHERE id_cita = ?");
            q.setParameter(1, "CANCELADA");
            q.setParameter(2, cita.getIdCita());
            int resultado = q.executeUpdate();
            _log.log(Level.INFO, "Resultado de la ejecucion update: " + resultado + "\n");
//            em.merge(cita);
//            em.flush();
            return "CITA CANCELADA.. ";

        } catch (Exception e) {
            _log.log(Level.WARNING, "NO SE PUEDE CANCELAR LA CITA ");
            return "NO SE PUEDE CANCELAR LA CITA ";

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String PacienteCancelaSuCita(Long idCita) {

        JSONObject json = new JSONObject();
        JSONArray citaArreglo = new JSONArray();
        JSONObject cita;
        
        try {
            _log.log(Level.WARNING, "1. CITA ID: " + idCita + "\n");

            Query q = em.createNativeQuery("update CITA set estado_cita = ? WHERE id_cita = ?");
            q.setParameter(1, "CANCELADA");
            q.setParameter(2, idCita);
            int resultado = q.executeUpdate();
            //return "CITA CANCELADA.. ";

            /*ejemplo funcional*/
            //http://stackoverflow.com/questions/6154845/returning-json-response-from-servlet-to-javascript-jsp-page
            //return "{\"idCita\":18}";
            
            cita = new JSONObject();
            cita.put("idCita", idCita);
            citaArreglo.put(cita);
            json.put("citaArreglo", citaArreglo);
            
            String jsonString = json.toString();
            _log.log(Level.INFO, "JSON-String= " + jsonString + "\n");
            return (json.toString());
            
        } catch (Exception e) {
            _log.log(Level.WARNING, "NO SE PUEDE CANCELAR LA CITA ");
            return "NO SE PUEDE CANCELAR LA CITA ";

        }
    }

    /////////////////////////////
    /**
     *
     * @param idMedico
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    public List<Cita> validarCitasAgendadasMedico(long idMedico, Date fechaInicio, Date fechaFin, boolean limitar, int limiteRegistos) {

        try {
            _log.log(Level.WARNING, "CONSULTANDO CITAS DE idMedico: " + idMedico + " FECHA INICIO:" + fechaInicio.toString() + " FECHA FIN:" + fechaFin.toString());
            Query q;
            if (limitar) {

                q = em.createNamedQuery(WebConstant.QUERY_CITA_FIND_FECHA_INICIO_FECHA_FIN);
                q.setMaxResults(limiteRegistos);
            } else {
                q = em.createNamedQuery(WebConstant.QUERY_CITA_FIND_FECHA_INICIO_FECHA_FIN);
            }

            q.setParameter(WebConstant.QUERY_PARAMETER_ID_MEDICO, idMedico);
            q.setParameter(WebConstant.QUERY_PARAMETER_HORA_INICIO, fechaInicio);
            q.setParameter(WebConstant.QUERY_PARAMETER_HORA_FINAL, fechaFin);
            List<Cita> listCitas = (List<Cita>) q.getResultList();

            if (listCitas != null && listCitas.size() > 0) {
                return listCitas;
            } else {
                return null;
            }

        } catch (NoResultException e) {
            _log.log(Level.SEVERE, "NO SE ENCONTRARON RESULTADOS DE CITAS AGENDADAS PARA EL MEDICO CON ID: " + idMedico + " ENTRE: "
                    + fechaFin.toString() + " AL: " + fechaFin.toString());
            return null;
        }

    }
}
