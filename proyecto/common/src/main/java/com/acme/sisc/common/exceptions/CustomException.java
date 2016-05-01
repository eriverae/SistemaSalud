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
public class CustomException extends Exception{
    private int errorCode;
    private int status;

    public CustomException(String mensaje) {
        super(mensaje);
    }

    public CustomException(int status, int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
