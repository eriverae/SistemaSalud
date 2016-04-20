/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.exceptions;

/**
 *
 * @author BryanCFz-user
 */
public class CitaException extends Exception {

    private int errorCode;

    public CitaException(String mensaje) {
        super(mensaje);
    }

    public CitaException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
