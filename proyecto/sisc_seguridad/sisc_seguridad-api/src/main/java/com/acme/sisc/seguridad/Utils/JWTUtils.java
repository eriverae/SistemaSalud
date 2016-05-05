/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.Utils;

import java.security.Key;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.EcJwkGenerator;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.AesKey;
import org.jose4j.keys.EllipticCurves;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;

/**
 *
 * @author Julio
 */
public class JWTUtils {

    private static String claveSecreta = "HolaEstamosRevis";

    public static String generarToken() throws JoseException {
        //byte[] secret = "[B@16d2633==er4.".getBytes();
        byte[] secret = claveSecreta.getBytes();
        Key key = new AesKey(secret);
        JsonWebEncryption jwe = new JsonWebEncryption();
        Calendar currentTime = Calendar.getInstance();
        Calendar finishTime = Calendar.getInstance();
        finishTime.add(Calendar.MINUTE, 5);
        String payload = "{\"iat\":" + currentTime.getTimeInMillis() + ", \"exp\":" + finishTime.getTimeInMillis() + "}";
        System.out.println("login - payload: " + payload);
        jwe.setPayload(payload);
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        jwe.setKey(key);
        String serializedJwe = null;
        serializedJwe = jwe.getCompactSerialization();
        return serializedJwe;
    }

}
