/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.common.dtos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Julio
 */
public class RespuestaDTO {

    private Map<String, Object> respuesta;

    public RespuestaDTO() {
        respuesta = new HashMap<>();
    }

    public Map<String, Object> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Map<String, Object> respuesta) {
        this.respuesta = respuesta;
    }

    public Object put(String key, Object val) {
        return respuesta.put(key, val);
    }

    public Object remove(String key) {
        return respuesta.remove(key);
    }

    public boolean contains(String key) {
        return respuesta.containsKey(key);
    }

    public Object get(String key) {
        return respuesta.get(key);
    }
}
