/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.common.errorhandling;

import com.acme.sisc.common.exceptions.CustomRunTimeException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author GABRIEL
 */
public class CustomRunTimeExceptionMapper implements ExceptionMapper<CustomRunTimeException> {
    private final static Logger log = Logger.getLogger(CustomRunTimeExceptionMapper.class.getName());
    
    @Override
    public Response toResponse(CustomRunTimeException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(ex.getErrorCode());
        errorMessage.setStatus(Response.Status.SERVICE_UNAVAILABLE.getStatusCode());
        errorMessage.setMessage(ex.getMessage());
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        errorMessage.setDeveloperMessage(errorStackTrace.toString());
        errorMessage.setLink("www.sisc.com/soporte");
        
        log.log(Level.SEVERE, "ERROR,se presento un error Tecnico... ", errorStackTrace.toString());
        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
