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

    public static Date parserStringToDateSimpleDateFormat(String fecha) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(WebConstant.SIMPLE_DATE_FORMAT);
            return dateFormat.parse(fecha);
        } catch (NullPointerException | ParseException ex) {
            _log.log(Level.SEVERE, "AgendaUtil.parserStringToDateSimpleDateFormat", ex);
            return null;

        }
    }

    public static String parserDateToStringSimpleDateFormat(Date fecha) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(WebConstant.SIMPLE_DATE_FORMAT);
            return dateFormat.format(fecha);
        } catch (NullPointerException ex) {
            _log.log(Level.SEVERE, "AgendaUtil.parserDateToStringSimpleDateFormat", ex);
            return null;

        }
    }

    public static String parserDateToString(Date fecha, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(fecha);
        } catch (NullPointerException ex) {
            _log.log(Level.SEVERE, "AgendaUtil.parserDateToString", ex);
            return null;

        }
    }

    public static Date parserStringToDate(String fecha, String formato) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(formato);
            return dateFormat.parse(fecha);
        } catch (NullPointerException | ParseException ex) {
            _log.log(Level.SEVERE, "AgendaUtil.parserStringToDateSimpleDateFormat", ex);
            return null;

        }
    }

    public static List<Date> devolverFechasXdiaDeLaSemana(Date fechaInicio, Date FechaFin, int diaDeLaSemana) {

        try {
            
            _log.log(Level.SEVERE, "["+fechaInicio.toString()+">> "+FechaFin.toString()+" dia: "+diaDeLaSemana+"]");
            
            List<Date> fechas = new ArrayList<Date>();
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
    
    public static List<Date> fechasInicioFinMes(){
        List<Date> listFechas=new ArrayList<Date>();
        listFechas.add(AgendaUtil.parserStringToDateSimpleDateFormat(
                AgendaUtil.parserDateToString(new Date(), "'01-'MM-yyyy")));        
        listFechas.add(new Date(listFechas.get(0).getTime()+(2678400000l)));
        return listFechas;
    }
    
    public static void main(String args[]) {
        
        for(Date fecha:AgendaUtil.fechasInicioFinMes()){
            System.out.println(">> "+fecha.toString());
        }

       
    }
}
