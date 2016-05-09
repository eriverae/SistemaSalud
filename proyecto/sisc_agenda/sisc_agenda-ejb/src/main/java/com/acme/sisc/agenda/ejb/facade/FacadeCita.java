/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

//import com.acme.sisc.UTILprueba.JMSUtil;
import com.acme.sisc.agenda.constant.CodesResponse;
import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.dto.ErrorObjSiscAgenda;
import com.acme.sisc.agenda.dto.GeneralResponse;
import com.acme.sisc.agenda.dto.RespuestaCita;
import com.acme.sisc.agenda.entidades.Cita;
import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
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
            List<Cita> listacitasPaciente = (List<Cita>) q.getResultList();
            _log.log(Level.WARNING, "ULTIMO REGISTRO LISTA-CITAS-PACIENTE, id= {0}", listacitasPaciente.get((listacitasPaciente.size() - 1)).getIdCita());
            /////////////////////////////////////////////
            /*java Message Bean*/
//            JMSUtil.sendMessage(listacitasPaciente.get(0), "java:/jms/queue/siscQueue");
//            _log.info("Finaliza CONSULTA DE CITAS(...)");
            /////////////////////////////////////////////
            return listacitasPaciente;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Cita> CitasDelPacianteHistorialEPS(long idPaciente) {
        try {
            Query q = em.createNamedQuery(WebConstant.QUERY_CITA_FIND_BY_ID_PACIENTE_HISTORIAL_EPS);
            q.setParameter(WebConstant.QUERY_PARAMETER_ID_PACIENTE, idPaciente);
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

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    private boolean autorizarCancelacionCita(Long idCita) {
        boolean autorizar = true;
        Cita cita = em.find(Cita.class, idCita);
        java.util.Date fechaSistema = new java.util.Date();

        java.sql.Date sDateCita = convertUtilToSql(cita.getHoraFin());
        java.sql.Date sDateSistema = convertUtilToSql(fechaSistema);

        if (sDateCita.toString().equalsIgnoreCase(sDateSistema.toString())) {
            //_log.log(Level.WARNING, "FECHAS IGUALES\n");
            _log.log(Level.WARNING, "\n\n  NO SE AUTORIZA LA CANCELACION DE CITA  -->  (fechas iguales) \n"
                    + "FECHA-CITA  " + sDateCita + "   =   "
                    + sDateSistema + "  FECHA-SISTEMA \n\n\n");
            autorizar = false;
        } else //_log.log(Level.WARNING, "FECHAS DIFERENTES\n");
        {
            if (sDateCita.after(sDateSistema)) {
                _log.log(Level.WARNING, "\n\n AUTORIZO CANCELAR CITA \n"
                        + "FECHA-CITA  " + sDateCita + "   >   "
                        + sDateSistema + "  FECHA-SISTEMA \n\n\n");
                autorizar = true;
            } else if (sDateCita.before(sDateSistema)) {
                _log.log(Level.WARNING, "\n\n  NO SE AUTORIZA LA CANCELACION DE CITA  -->  (fecha Caducada) \n"
                        + "FECHA-CITA  " + sDateCita + "   <   "
                        + sDateSistema + "  FECHA-SISTEMA \n\n\n");
                autorizar = false;
            }
        }

        return autorizar;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public GeneralResponse PacienteCancelaSuCita(Long idCita) {

        GeneralResponse response = new GeneralResponse();

        java.util.Date fecha = new java.util.Date();
        java.sql.Date fechaSistema = convertUtilToSql(fecha);

        try {
            _log.log(Level.WARNING, "\n\n\n1. CITA ID: " + idCita + "\n\n\n");

            if (autorizarCancelacionCita(idCita)) {
                Query q = em.createNativeQuery("update CITA set estado_cita = ? WHERE id_cita = ?");
                q.setParameter(1, "CANCELADA");
                q.setParameter(2, idCita);
                int resultado = q.executeUpdate();

                RespuestaCita respuestas = new RespuestaCita();
                respuestas.setEstador(WebConstant.ESTADO_CITA_CANCELADA);
                respuestas.setMensajer("Su cita se ha cancelado correctamente");
                response.setObjectResponse(respuestas);
                response.setCodigoRespuesta(CodesResponse.SUCCESS.value());
            } else {
                //java.util.Date fechaSistema = new java.util.Date();

                response.setCodigoRespuesta(CodesResponse.ERROR.value());
                ErrorObjSiscAgenda error = new ErrorObjSiscAgenda();
                error.setCodigoError("");
                error.setObjError(idCita);
                error.setMensajeError("Se debe cancelar la cita antes de la fecha actual: " + fechaSistema + ", por politicas de SISC. Tiene que ser el dia anterior");
                response.setError(error);
            }

        } catch (Exception e) {
            _log.log(Level.WARNING, "NO SE PUEDE CANCELAR LA CITA ");
        }
        return response;
    }

    /////////////////////////////
    /**
     *
     * @param idMedico
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    public List<Cita> validarCitasAgendadasMedico(long idMedico, java.util.Date fechaInicio, java.util.Date fechaFin, boolean limitar, int limiteRegistos) {

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
