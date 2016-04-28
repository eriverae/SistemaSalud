/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.sisc_hc.errorhandling;

import com.acme.sisc.sisc_hc.exceptions.CustomException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author GABRIEL
 */
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<CustomException> {

    public Response toResponse(CustomException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(ex.getErrorCode());
        errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
        errorMessage.setMessage(ex.getMessage());
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        errorMessage.setDeveloperMessage(errorStackTrace.toString());
        errorMessage.setLink("www.banco.com/soporte");

        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
