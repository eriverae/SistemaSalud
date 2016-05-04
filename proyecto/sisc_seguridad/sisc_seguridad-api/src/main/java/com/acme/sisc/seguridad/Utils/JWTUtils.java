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

    public static String generarTkn() throws JoseException {
        Key key = new AesKey(ByteUtil.randomBytes(16));
        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setPayload("Hello World!");
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
//        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
        jwe.setKey(key);
        String serializedJwe = jwe.getCompactSerialization();
        return serializedJwe;
//        System.out.println("Serialized Encrypted JWE: " + serializedJwe);
//        jwe = new JsonWebEncryption();
//        jwe.setKey(key);
//        jwe.setCompactSerialization(serializedJwe);
//        System.out.println("Payload: " + jwe.getPayload());
    }

    public static void test1() throws JoseException {
        // Generate an RSA key pair, which will be used for signing and verification of the JWT, wrapped in a JWK
        RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);

        // Give the JWK a Key ID (kid), which is just the polite thing to do
        rsaJsonWebKey.setKeyId("k1");

        // Create the Claims, which will be the content of the JWT
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("Issuer");  // who creates the token and signs it
        claims.setAudience("Audience"); // to whom the token is intended to be sent
        claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
        claims.setGeneratedJwtId(); // a unique identifier for the token
        claims.setIssuedAtToNow();  // when the token was issued/created (now)
        claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
        claims.setSubject("subject"); // the subject/principal is whom the token is about
        claims.setClaim("email", "mail@example.com"); // additional claims/attributes about the subject can be added
        List<String> groups = Arrays.asList("group-one", "other-group", "group-three");
        claims.setStringListClaim("groups", groups); // multi-valued claims work too and will end up as a JSON array

        // A JWT is a JWS and/or a JWE with JSON claims as the payload.
        // In this example it is a JWS so we create a JsonWebSignature object.
        JsonWebSignature jws = new JsonWebSignature();

        // The payload of the JWS is JSON content of the JWT Claims
        jws.setPayload(claims.toJson());

        // The JWT is signed using the private key
        jws.setKey(rsaJsonWebKey.getPrivateKey());

        // Set the Key ID (kid) header because it's just the polite thing to do.
        // We only have one key in this example but a using a Key ID helps
        // facilitate a smooth key rollover process
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());

        // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        // Sign the JWS and produce the compact serialization or the complete JWT/JWS
        // representation, which is a string consisting of three dot ('.') separated
        // base64url-encoded parts in the form Header.Payload.Signature
        // If you wanted to encrypt it, you can simply set this jwt as the payload
        // of a JsonWebEncryption object and set the cty (Content Type) header to "jwt".
        String jwt = jws.getCompactSerialization();

        // Now you can do something with the JWT. Like send it to some other party
        // over the clouds and through the interwebs.
        System.out.println("JWT: " + jwt);

        // Use JwtConsumerBuilder to construct an appropriate JwtConsumer, which will
        // be used to validate and process the JWT.
        // The specific validation requirements for a JWT are context dependent, however,
        // it typically advisable to require a expiration time, a trusted issuer, and
        // and audience that identifies your system as the intended recipient.
        // If the JWT is encrypted too, you need only provide a decryption key or
        // decryption key resolver to the builder.
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer("Issuer") // whom the JWT needs to have been issued by
                .setExpectedAudience("Audience") // to whom the JWT is intended for
                .setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                .build(); // create the JwtConsumer instance

        try {
            //  Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            System.out.println("JWT validation succeeded! " + jwtClaims);
        } catch (InvalidJwtException e) {
            // InvalidJwtException will be thrown, if the JWT failed processing or validation in anyway.
            // Hopefully with meaningful explanations(s) about what went wrong.
            System.out.println("Invalid JWT! " + e);
        }
    }

    public static void test2() throws JoseException {
        // Generate an EC key pair, which will be used for signing and verification of the JWT, wrapped in a JWK
        EllipticCurveJsonWebKey senderJwk = EcJwkGenerator.generateJwk(EllipticCurves.P256);

        // Give the JWK a Key ID (kid), which is just the polite thing to do
        senderJwk.setKeyId("sender's key");

        // Generate an EC key pair, wrapped in a JWK, which will be used for encryption and decryption of the JWT
        EllipticCurveJsonWebKey receiverJwk = EcJwkGenerator.generateJwk(EllipticCurves.P256);

        // Give the JWK a Key ID (kid), which is just the polite thing to do
        receiverJwk.setKeyId("receiver's key");

        // Create the Claims, which will be the content of the JWT
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("sender");  // who creates the token and signs it
        claims.setAudience("receiver"); // to whom the token is intended to be sent
        claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
        claims.setGeneratedJwtId(); // a unique identifier for the token
        claims.setIssuedAtToNow();  // when the token was issued/created (now)
        claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
        claims.setSubject("subject"); // the subject/principal is whom the token is about
        claims.setClaim("email", "mail@example.com"); // additional claims/attributes about the subject can be added
        List<String> groups = Arrays.asList("group-1", "other-group", "group-3");
        claims.setStringListClaim("groups", groups); // multi-valued claims work too and will end up as a JSON array

        // A JWT is a JWS and/or a JWE with JSON claims as the payload.
        // In this example it is a JWS nested inside a JWE
        // So we first create a JsonWebSignature object.
        JsonWebSignature jws = new JsonWebSignature();

        // The payload of the JWS is JSON content of the JWT Claims
        jws.setPayload(claims.toJson());

        // The JWT is signed using the sender's private key
        jws.setKey(senderJwk.getPrivateKey());

        // Set the Key ID (kid) header because it's just the polite thing to do.
        // We only have one signing key in this example but a using a Key ID helps
        // facilitate a smooth key rollover process
        jws.setKeyIdHeaderValue(senderJwk.getKeyId());

        // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.ECDSA_USING_P256_CURVE_AND_SHA256);

        // Sign the JWS and produce the compact serialization, which will be the inner JWT/JWS
        // representation, which is a string consisting of three dot ('.') separated
        // base64url-encoded parts in the form Header.Payload.Signature
        String innerJwt = jws.getCompactSerialization();

        // The outer JWT is a JWE
        JsonWebEncryption jwe = new JsonWebEncryption();

        // The output of the ECDH-ES key agreement will encrypt a randomly generated content encryption key
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.ECDH_ES_A128KW);

        // The content encryption key is used to encrypt the payload
        // with a composite AES-CBC / HMAC SHA2 encryption algorithm
        String encAlg = ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256;
        jwe.setEncryptionMethodHeaderParameter(encAlg);

        // We encrypt to the receiver using their public key
        jwe.setKey(receiverJwk.getPublicKey());
        jwe.setKeyIdHeaderValue(receiverJwk.getKeyId());

        // A nested JWT requires that the cty (Content Type) header be set to "JWT" in the outer JWT
        jwe.setContentTypeHeaderValue("JWT");

        // The inner JWT is the payload of the outer JWT
        jwe.setPayload(innerJwt);

        // Produce the JWE compact serialization, which is the complete JWT/JWE representation,
        // which is a string consisting of five dot ('.') separated
        // base64url-encoded parts in the form Header.EncryptedKey.IV.Ciphertext.AuthenticationTag
        String jwt = jwe.getCompactSerialization();

        // Now you can do something with the JWT. Like send it to some other party
        // over the clouds and through the interwebs.
        System.out.println("JWT: " + jwt);

        // Use JwtConsumerBuilder to construct an appropriate JwtConsumer, which will
        // be used to validate and process the JWT.
        // The specific validation requirements for a JWT are context dependent, however,
        // it typically advisable to require a expiration time, a trusted issuer, and
        // and audience that identifies your system as the intended recipient.
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer("sender") // whom the JWT needs to have been issued by
                .setExpectedAudience("receiver") // to whom the JWT is intended for
                .setDecryptionKey(receiverJwk.getPrivateKey()) // decrypt with the receiver's private key
                .setVerificationKey(senderJwk.getPublicKey()) // verify the signature with the sender's public key
                .build(); // create the JwtConsumer instance

        try {
            //  Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            System.out.println("JWT validation succeeded! " + jwtClaims);
        } catch (InvalidJwtException e) {
            // InvalidJwtException will be thrown, if the JWT failed processing or validation in anyway.
            // Hopefully with meaningful explanations(s) about what went wrong.
            System.out.println("Invalid JWT! " + e);
        }
    }

    public static void test3() throws JoseException {
        // The content to be encrypted
        String message = "Well, as of this moment, they're on DOUBLE SECRET PROBATION!";

        // The shared secret or shared symmetric key represented as a octet sequence JSON Web Key (JWK)
        String jwkJson = "{\"kty\":\"oct\",\"k\":\"Fdh9u8rINxfivbrianbbVT1u232VQBZYKx1HGAGPt2I\"}";
        JsonWebKey jwk = JsonWebKey.Factory.newJwk(jwkJson);

        // Create a new Json Web Encryption object
        JsonWebEncryption senderJwe = new JsonWebEncryption();

        // The plaintext of the JWE is the message that we want to encrypt.
        senderJwe.setPlaintext(message);

        // Set the "alg" header, which indicates the key management mode for this JWE.
        // In this example we are using the direct key management mode, which means
        // the given key will be used directly as the content encryption key.
        senderJwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.DIRECT);

        // Set the "enc" header, which indicates the content encryption algorithm to be used.
        // This example is using AES_128_CBC_HMAC_SHA_256 which is a composition of AES CBC
        // and HMAC SHA2 that provides authenticated encryption.
        senderJwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);

        // Set the key on the JWE. In this case, using direct mode, the key will used directly as
        // the content encryption key. AES_128_CBC_HMAC_SHA_256, which is being used to encrypt the
        // content requires a 256 bit key.
        senderJwe.setKey(jwk.getKey());

        // Produce the JWE compact serialization, which is where the actual encryption is done.
        // The JWE compact serialization consists of five base64url encoded parts
        // combined with a dot ('.') character in the general format of
        // <header>.<encrypted key>.<initialization vector>.<ciphertext>.<authentication tag>
        // Direct encryption doesn't use an encrypted key so that field will be an empty string
        // in this case.
        String compactSerialization = senderJwe.getCompactSerialization();

        // Do something with the JWE. Like send it to some other party over the clouds
        // and through the interwebs.
        System.out.println("JWE compact serialization: " + compactSerialization);

        // That other party, the receiver, can then use JsonWebEncryption to decrypt the message.
        JsonWebEncryption receiverJwe = new JsonWebEncryption();

        // Set the compact serialization on new Json Web Encryption object
        receiverJwe.setCompactSerialization(compactSerialization);

        // Symmetric encryption, like we are doing here, requires that both parties have the same key.
        // The key will have had to have been securely exchanged out-of-band somehow.
        receiverJwe.setKey(jwk.getKey());

        // Get the message that was encrypted in the JWE. This step performs the actual decryption steps.
        String plaintext = receiverJwe.getPlaintextString();

        // And do whatever you need to do with the clear text message.
        System.out.println("plaintext: " + plaintext);
    }

    public static void main(String aaa[]) {
        try {
            System.out.println(generarToken());
//            System.out.println(generarTkn());
//            test3();
        } catch (JoseException ex) {
            Logger.getLogger(JWTUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
