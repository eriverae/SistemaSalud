/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;
import org.jose4j.keys.HmacKey;

/**
 * REST Web Service
 *
 * @author rm-rf
 */
@Path("auth")
@RequestScoped
public class AuthResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AuthResource
     */
    public AuthResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.acme.sisc.seguridad.rest.AuthResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AuthResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    public static void main(String aaa[]) {
//        Key key = MacProvider.generateKey();
//        key = new Key() {
//            @Override
//            public String getAlgorithm() {
//                return "";
//            }
//
//            @Override
//            public String getFormat() {
//                return "";
//            }
//
//            @Override
//            public byte[] getEncoded() {
//                return "".getBytes();
//            }
//        };
        Key key = new HmacKey("MyKey123456789".getBytes());
        System.out.println(key);
//        Key key = MacProvider.generateKey(SignatureAlgorithm.NONE, random)

        String s = Jwts.builder().setSubject("Joe").signWith(SignatureAlgorithm.HS512, key).compact();

        System.out.println(s);

        assert Jwts.parser().setSigningKey(key).parseClaimsJws(s).getBody().getSubject().equals("Joe");

        try {

            Jwts.parser().setSigningKey(key).parseClaimsJws(s);

            //OK, we can trust this JWT
        } catch (SignatureException e) {

            //don't trust the JWT!
        }
    }
}
