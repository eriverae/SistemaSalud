/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.constant;

/**
 *
 * @author desarrollador
 */
public class WebConstant {
    
    public static final String UNIT_NAME_PERSISTENCE="SistemaSaludPU";
        
    /**
     * Nombre querys
     */
        /*PersonaEps*/
    public static final String QUERY_PERSONA_EPS_FIND_MEDICO_EPS        ="PersonaEps.findMedicoEps";
    public static final String QUERY_PERSONA_EPS_FIND_LIST_MEDICO_EPS   ="PersonaEps.findListMedicoEps";
        /*Agenda*/
    public static final String QUERY_AGENDA_FIND_BY_ID_MEDICO           ="Agenda.findByIdMedico";    
        /*Cita*/
    public static final String QUERY_CITA_FIND_FECHA_INICIO_FECHA_FIN_LIMITADO  ="Cita.findFechaInicioFechaFinLimitado";
    public static final String QUERY_CITA_FIND_FECHA_INICIO_FECHA_FIN   ="Cita.findFechaInicioFechaFin";
    
    public static final String QUERY_CITA_FIND_BY_ID_PACIENTE_HISTORIAL_EPS   ="Cita.findIdPacienteHistorialEPS";
    
    public static final String QUERY_CITA_FIND_BY_ID_PACIENTE           ="Cita.findIdPaciente";
    public static final String QUERY_CITA_FIND_BY_ID                    ="Cita.findById";
    public static final String QUERY_CITA_FIND_CITAS_DIPONIBLES_PACIENTE=
            "select * from cita c " +
            "inner join agenda a on a.id_agenda = c.id_agenda " +
            "inner join persona_eps pe on a.id_medico_eps = pe.id_persona_eps " +
            "inner join persona_natural pn on pn.id_persona = pe.id_persona " +
            "inner join persona_natural_especialidad  pne on pne.id_medico = pe.id_persona " +
            "inner join especialidad e on e.id_especialidad = pne.id_especialidad " +
            "where " +
            "e.id_especialidad = ? and " +
            "pe.id_eps = ? and " +
            "(c.hora_inicio >= ? and c.hora_inicio  <= ? ) " +
            "and estado_cita ='DISPONIBLE' order by hora_inicio";
    
    /**
     * Nombre parametros de consultas
     */

    public static final String QUERY_PARAMETER_ID_MEDICO        ="idMedico";
    public static final String QUERY_PARAMETER_ID_EPS           ="idEps";
    public static final String QUERY_PARAMETER_HORA_INICIO      ="horaInicio";
    public static final String QUERY_PARAMETER_HORA_FINAL       ="horaFin";;

    public static final String QUERY_PARAMETER_LIMITE_CONSULTA  ="limite";
    public static final String QUERY_PARAMETER_ID_PACIENTE  ="idPaciente";
    public static final String QUERY_PARAMETER_ID_CITA      ="idCita";
    

    
    /**
     * Formatos
     */
    public static final String SIMPLE_DATE_FORMAT="dd-MM-yyyy";
    public static final String SIMPLE_DATE_FORMAT_HOUR="HH:mm:ss";
    public static final String DATE_FORMAT_CITA_JSON="yyyy'-'MM'-'dd'T'HH':'mm':'ss";
    public static final String DATE_FORMAT_CITA="dd-MM-yyyy HH':'mm':'ss";
    public static final String DATE_FORMAT_CITA_BD="yyyy-MM-dd HH':'mm':'ss";
    public static final String JSON_DATE_FORMAT="{'\"day\":\"'dd'\",\"month\": \"'MM'\",\"year\":\"'yyyy'\","
            + "\"hours\":\"'HH'\",\"minutes\":\"'mm'\",\"seconds\":\"'ss'\"}'";
    
    
    /**
     * Mensajes de error
     */
    public static final String MENSAJE_ERROR_CITA_CONFLICTO_EN_NUEVA_AGENDA="Existen conflictos al crear nueva AGENDA con las siguientes citas";
    
    /**
     * Mensajes de exito
     */    
    public static final String MENSAJE_AGENDA_CREADA="La agenda ha sido creada satisfactoriamente";
    
    /**
     * Estados CITA
     */
    public static final String ESTADO_CITA_DISPONIBLE="DISPONIBLE";
    public static final String ESTADO_CITA_CANCELADA="CANCELADA";
    public static final String ESTADO_CITA_APARTADA="APARTADA";
    
    
    
    public static final String COLOR_CITA_DISPONIBLE="#5cb85c";
    public static final String COLOR_CITA_APARTADA="#ff9933";
     public static final String COLOR_CITA_CANCELADA="#e60000";
   
    
    public static  final long MS_DAY=86400000;
    
    
    
    
   
}
