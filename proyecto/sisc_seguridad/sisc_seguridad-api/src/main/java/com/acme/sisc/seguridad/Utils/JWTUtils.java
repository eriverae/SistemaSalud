/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.Utils;

import java.security.Key;
import java.security.PrivateKey;
import java.util.Calendar;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;

/**
 *
 * @author Julio
 */
public class JWTUtils {

    private static String claveSecreta = "HolaEstamosRevis";

    /*public static String generarToken() throws JoseException {

        String examplePayload = "This is some text that is to be signed.";

        JsonWebSignature jws = new JsonWebSignature();

        jws.setPayload(examplePayload);

        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256);

        Key privateKey = new AesKey(claveSecreta.getBytes());
        jws.setKey(privateKey);

        String jwsCompactSerialization = jws.getCompactSerialization();

        return jwsCompactSerialization;
    }*/
    
    public static String generarToken() throws JoseException {
		 //byte[] secret = "[B@16d2633==er4.".getBytes();
		 byte[] secret = claveSecreta.getBytes();
		 Key key = new AesKey(secret);
		 
		 JsonWebEncryption jwe = new JsonWebEncryption();
		 Calendar currentTime = Calendar.getInstance();
		 Calendar finishTime = Calendar.getInstance();
		 finishTime.add(Calendar.MINUTE, 5);
		 String payload = "{\"iat\":"+currentTime.getTimeInMillis()+", \"exp\":"+finishTime.getTimeInMillis()+"}";
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
