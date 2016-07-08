/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.Utils;

import java.security.Key;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;

/**
* Esta clase de JWTUtils es la encargada de generar el token con la libreria
* de jose4j 
* 
* @author  Julio
* @version 1.0
* @since   2016-05-21
*/
public class JWTUtils {

    private final static String KEY_APPLICATION = "HolaEstamosRevis";
//    private final static String KEY_APPLICATION = "SISC_KEY_AUTHENT";

    /**
    * Metodo generarToken, crea el token con el payload y su respectiva encriptacion
    * @return String con el token generado a partir de la Key definida
    */
    public static String generarToken() throws JoseException {
        byte[] secret = KEY_APPLICATION.getBytes();
        Key key = new AesKey(secret);
        JsonWebEncryption jwe = new JsonWebEncryption();
        Calendar currentTime = Calendar.getInstance();
        Calendar finishTime = Calendar.getInstance();
        finishTime.add(Calendar.MINUTE, 5);
        String payload = "{"
                +"\"iis\": \"localhost\","
                +"\"iat\": \""+currentTime.getTimeInMillis()+"\","
                +"\"home\": \"true,"
                +"\"exp\": \""+finishTime.getTimeInMillis()+"\","
                +"\"scope\": \"#/home\","
                +"}";
//        String payload = "{\"iat\":" + currentTime.getTimeInMillis() + ", \"exp\":" + finishTime.getTimeInMillis() + "}";
        System.out.println("login - payload: " + payload);
        jwe.setPayload(payload);
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        jwe.setKey(key);
        String serializedJwe = jwe.getCompactSerialization();
        return serializedJwe;
    }

}
