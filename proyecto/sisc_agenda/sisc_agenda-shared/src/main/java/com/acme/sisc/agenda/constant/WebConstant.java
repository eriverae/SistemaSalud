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
    public static final String QUERY_CITA_FIND_FECHA_INICIO_FECHA_FIN   ="Cita.findFechaInicioFechaFin";
    public static final String QUERY_CITA_FIND_BY_ID_PACIENTE           ="Cita.findIdPaciente";
    public static final String QUERY_CITA_FIND_BY_ID                    ="Cita.findById";
    
    /**
     * Nombre parametros de consultas
     */
    public static final String QUERY_PARAMETER_ID_MEDICO    ="idMedico";
    public static final String QUERY_PARAMETER_ID_EPS       ="idEps";
    public static final String QUERY_PARAMETER_HORA_INICIO  ="horaInicio";
    public static final String QUERY_PARAMETER_HORA_FINAL   ="horaFin";
    public static final String QUERY_PARAMETER_ID_PACIENTE  ="idPaciente";
    public static final String QUERY_PARAMETER_ID_CITA      ="idCita";
    
    
    /**
     * Formatos
     */
    public static final String SIMPLE_DATE_FORMAT="dd-MM-yyyy";
    public static final String DATE_FORMAT_CITA="dd-MM-yyyy HH':'mm':'ss";
    public static final String JSON_DATE_FORMAT="{'\"day\":\"'dd'\",\"month\": \"'MM'\",\"year\":\"'yyyy'\","
            + "\"hours\":\"'HH'\",\"minutes\":\"'mm'\",\"seconds\":\"'ss'\"}'";
    
    
    
   
}
