/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.common.exceptions;

/**
 *
 * @author GABRIEL
 */
public class CustomRunTimeException extends RuntimeException{
    private int errorCode;

    public CustomRunTimeException(String mensaje) {
        super(mensaje);
    }

    public CustomRunTimeException(int errorCode, String msg) {
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
