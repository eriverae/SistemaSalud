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
import com.acme.sisc.agenda.entidades.PersonaEps;
import com.acme.sisc.agenda.util.AgendaUtil;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author BryanCFz-user
 */
@Stateful
public class FacadeCita extends AbstractFacade<Cita> {

    Logger _log = Logger.getLogger(this.getClass().getName());

    @EJB
    FacadeMedicoEps facadeMedicoEps;

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
            _log.log(Level.WARNING, "CONSULTANDO CITAS DE idMedico: {0} FECHA INICIO:{1} FECHA FIN:{2}",
                    new Object[]{idMedico, fechaInicio.toString(), fechaFin.toString()});
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
            _log.log(Level.SEVERE, "NO SE ENCONTRARON RESULTADOS DE CITAS AGENDADAS PARA EL MEDICO CON ID: {0} ENTRE: {1} AL: {2}",
                    new Object[]{idMedico, fechaFin.toString(), fechaFin.toString()});
            return null;
        }

    }

    public List<Cita> buscarCitasDisponiblesPaciente(long idEspecialidad, long idEps, String fechaBusqueda) {
        try {
            Query q = em.createNativeQuery(WebConstant.QUERY_CITA_FIND_CITAS_DIPONIBLES_PACIENTE, Cita.class);

            q.setParameter(1, idEspecialidad);
            q.setParameter(2, idEps);
            Date aux;
            if (fechaBusqueda != null) {

                aux = AgendaUtil.parserStringToDateSimpleDateFormat(fechaBusqueda);
                if (aux == null) {
                    aux = new Date();
                }
            } else {
                aux = AgendaUtil.getCurrentDate();
            }
            _log.log(Level.SEVERE, "QUERY: " + WebConstant.QUERY_CITA_FIND_CITAS_DIPONIBLES_PACIENTE);
            _log.log(Level.SEVERE, "1 : " + idEspecialidad);
            _log.log(Level.SEVERE, "2 : " + idEps);
            _log.log(Level.SEVERE, "3 : " + aux);
            _log.log(Level.SEVERE, "4 : " + (new Date(aux.getTime() + WebConstant.MS_DAY)));

            q.setParameter(3, aux);
            q.setParameter(4, new Date(aux.getTime() + WebConstant.MS_DAY));

            return (List<Cita>) q.getResultList();

        } catch (Exception e) {
            return null;
        }

    }

    public GeneralResponse agendarCita(long idCita, long idPersona) {
        GeneralResponse response = new GeneralResponse();
        PersonaEps personaEpsCita = null;
        ErrorObjSiscAgenda error;
        Cita cita;
        try {

            

            List<PersonaEps> listEps = facadeMedicoEps.consultarEpsMedico(idPersona);
            if (listEps != null && listEps.size() > 0) {
                for (PersonaEps personaEps : listEps) {
                    if (personaEps.getFechaFin() == null) {
                        personaEpsCita = personaEps;
                        break;
                    }
                }
                if (personaEpsCita == null) {
                    personaEpsCita = listEps.get(listEps.size() - 1);
                }
                cita = em.find(Cita.class, idCita);
                if (cita != null && cita.getEstadoCita().equals(WebConstant.ESTADO_CITA_DISPONIBLE)) {
                    cita.setEstadoCita(WebConstant.ESTADO_CITA_APARTADA);
                    cita.setPacienteEps(personaEpsCita);
                    cita = em.merge(cita);
                    response.setCodigoRespuesta("SUCCESS");

                } else {
                    /*Error cita no disponible*/
                     _log.log(Level.WARNING,"NO HAY CITA  {0}",idCita);
                    response.setCodigoRespuesta("ERROR");
                    error = new ErrorObjSiscAgenda();
                    error.setCodigoError("001");
                    error.setMensajeError("Este horario ya ha sido apartado por favor intente otro");
                    response.setError(error);
                }

            } else {
                /*error*/
                _log.log(Level.WARNING,"NO HAY LISTA ESP PERSONA PARA {0}",idPersona);
                response.setCodigoRespuesta("ERROR");
                error = new ErrorObjSiscAgenda();
                error.setCodigoError("000");
                error.setMensajeError("Error en el sistema intente mas tarde");
                response.setError(error);
            }

        } catch (Exception e) {
            _log.log(Level.SEVERE,"ERRO EN agendarCita ",e);
            response.setCodigoRespuesta("ERROR");
            error = new ErrorObjSiscAgenda();
            error.setCodigoError("000");
            error.setMensajeError("Error en el sistema intente mas tarde");
            response.setError(error);
        }
        return response;
    }
    
    public Cita mergeCita(Cita cita){
        return em.merge(cita);
    }
}
