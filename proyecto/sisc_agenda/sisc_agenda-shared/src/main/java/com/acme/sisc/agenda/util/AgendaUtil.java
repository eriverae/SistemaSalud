/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.util;

import com.acme.sisc.agenda.constant.WebConstant;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desarrollador
 */
public class AgendaUtil {

    private final static Logger _log = Logger.getLogger(AgendaUtil.class.getName());
    /**
     * Metedo para convertir de strig a date
     * @param fecha
     * @return 
     */
    public static Date parserStringToDateSimpleDateFormat(String fecha) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(WebConstant.SIMPLE_DATE_FORMAT);
            return dateFormat.parse(fecha);
        } catch (NullPointerException | ParseException ex) {
            _log.log(Level.SEVERE, "AgendaUtil.parserStringToDateSimpleDateFormat", ex);
            return null;

        }
    }
    /**
     * Metodo para convertir de date a string 
     * @param fecha
     * @return 
     */
    public static String parserDateToStringSimpleDateFormat(Date fecha) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(WebConstant.SIMPLE_DATE_FORMAT);
            return dateFormat.format(fecha);
        } catch (NullPointerException ex) {
            _log.log(Level.SEVERE, "AgendaUtil.parserDateToStringSimpleDateFormat", ex);
            return null;

        }
    }
    /**
     * Metodo para convertir de Date a String enviando el formato 
     * @param fecha
     * @param format
     * @return 
     */
    public static String parserDateToString(Date fecha, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(fecha);
        } catch (NullPointerException ex) {
            _log.log(Level.SEVERE, "AgendaUtil.parserDateToString", ex);
            return null;

        }
    }
    /**
     * Metodo para convertir de String  a Date enviando el formato 
     * @param fecha
     * @param formato
     * @return 
     */
    public static Date parserStringToDate(String fecha, String formato) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(formato);
            return dateFormat.parse(fecha);
        } catch (NullPointerException | ParseException ex) {
            _log.log(Level.SEVERE, "AgendaUtil.parserStringToDateSimpleDateFormat", ex);
            return null;

        }
    }
    /**
     * Metodo para traer date
     * @return 
     */
    public static Date getCurrentDate() {
        try {
            DateFormat dateFormat = new SimpleDateFormat(WebConstant.SIMPLE_DATE_FORMAT);
            return dateFormat.parse(parserDateToString(new Date(), WebConstant.SIMPLE_DATE_FORMAT));
        } catch (NullPointerException | ParseException ex) {
            _log.log(Level.SEVERE, "AgendaUtil.getCurrentDate", ex);
            return null;

        }
    }
    /**
     * Metodo que devuelve las fechas de un dia de la semana entre un rango de fechas 
     * @param fechaInicio
     * @param FechaFin
     * @param diaDeLaSemana
     * @return 
     */
    public static List<Date> devolverFechasXdiaDeLaSemana(Date fechaInicio, Date FechaFin, int diaDeLaSemana) {

        try {           
     
            List<Date> fechas = new ArrayList<>();
            int diaFechaInicio = fechaInicio.getDay();

            fechaInicio.setTime(fechaInicio.getTime() + ((diaDeLaSemana > diaFechaInicio ? diaDeLaSemana - diaFechaInicio
                    : (diaDeLaSemana < diaFechaInicio ? ((7 - diaFechaInicio) + diaDeLaSemana) : 0)) * 86400000));

            while (fechaInicio.getTime() <= FechaFin.getTime()) {
                fechas.add(new Date(fechaInicio.getTime()));
                fechaInicio.setTime(fechaInicio.getTime() + (7 * 86400000));
            }
            return fechas;
        } catch (NullPointerException ex) {
            _log.log(Level.SEVERE, "AgendaUtil.devolverFechasXdiaDeLaSemana", ex);
            return null;
        }
    }
    /**
     * Metodo para devolver las fechas cuando inicia y termina el mes
     * @return 
     */
    public static List<Date> fechasInicioFinMes(){
        List<Date> listFechas=new ArrayList<>();
        listFechas.add(AgendaUtil.parserStringToDateSimpleDateFormat(
                AgendaUtil.parserDateToString(new Date(), "'01-'MM-yyyy")));        
        listFechas.add(new Date(listFechas.get(0).getTime()+(2678400000l)));
        return listFechas;
    }
    
    public static void main(String args[]) {
            
            System.out.println("getCurrentDate "+AgendaUtil.getCurrentDate().toString());
        

       
    }
}
