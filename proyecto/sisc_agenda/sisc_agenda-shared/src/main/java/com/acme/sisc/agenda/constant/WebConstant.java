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
    
    
    public static final String QUERY_PARAMETER_ID_MEDICO    ="idMedico";
    public static final String QUERY_PARAMETER_ID_EPS       ="idEps";
    
    /**
     * Nombre querys
     */
    public static final String  QUERY_PERSONA_EPS_FIND_MEDICO_EPS       ="PersonaEps.findMedicoEps";
    public static final String  QUERY_AGENDA_FIND_BY_ID_MEDICO          ="Agenda.findByIdMedico";
    public static final String QUERY_PERSONA_EPS_FIND_LIST_MEDICO_EPS   ="PersonaEps.findListMedicoEps";
    
    public static final String QUERY_CITA_FIND_BY_ID_PACIENTE_          ="Cita.findById";
    
    /**
     * Formatos
     */
    public static final String SIMPLE_DATE_FORMAT="dd/MM/yyyy";
    public static final String JSON_DATE_FORMAT="{'\"day\":\"'dd'\",\"month\": \"'MM'\",\"year\":\"'yyyy'\","
            + "\"hours\":\"'HH'\",\"minutes\":\"'mm'\",\"seconds\":\"'ss'\"}'";
    
   
}
