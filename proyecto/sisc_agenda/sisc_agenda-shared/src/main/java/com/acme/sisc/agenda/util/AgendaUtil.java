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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desarrollador
 */
public class AgendaUtil {
    
   private final static   Logger  _log= Logger.getLogger(AgendaUtil.class.getName());
    
    public static Date parserStringToDateSimpleDateFormat(String fecha){        
        try {
            DateFormat dateFormat = new SimpleDateFormat(WebConstant.SIMPLE_DATE_FORMAT);
            return  dateFormat.parse(fecha);           
        } catch (NullPointerException | ParseException ex) {
           _log.log(Level.SEVERE, "AgendaUtil.parserStringToDateSimpleDateFormat", ex);
            return null;
            
        }
    }
    public static String parserDateToStringSimpleDateFormat(Date fecha){        
        try {
            DateFormat dateFormat = new SimpleDateFormat(WebConstant.SIMPLE_DATE_FORMAT);
            return  dateFormat.format(fecha);
        } catch (NullPointerException  ex) {
           _log.log(Level.SEVERE, "AgendaUtil.parserDateToStringSimpleDateFormat", ex);
            return null;
            
        }
    }
    
    public static String parserDateToString(Date fecha,String format){        
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return  dateFormat.format(fecha);
        } catch (NullPointerException  ex) {
           _log.log(Level.SEVERE, "AgendaUtil.parserDateToString", ex);
            return null;
            
        }
    }
    
    public static void main (String args[]){
        System.out.print(">> "+AgendaUtil.parserDateToString(new Date(), WebConstant.JSON_DATE_FORMAT));
    }
}
