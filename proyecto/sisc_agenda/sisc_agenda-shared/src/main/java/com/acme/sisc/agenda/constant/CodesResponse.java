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
public enum CodesResponse {
    
    SUCCESS("SUCCESS"),
    ERROR("ERROR"),
    AGENDA_DISPONIBLE("AGENDA_DISPONIBLE")
    
    ;
    private final String value;
    
    CodesResponse(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CodesResponse fromValue(String v) {
        for (CodesResponse c: CodesResponse.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new java.lang.IllegalArgumentException(v);
    }
    
}
