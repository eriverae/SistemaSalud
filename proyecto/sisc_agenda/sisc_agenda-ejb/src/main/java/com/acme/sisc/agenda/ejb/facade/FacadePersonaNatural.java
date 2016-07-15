/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rasm
 */
@Stateless
public class FacadePersonaNatural extends AbstractFacade<PersonaNatural> {

    Logger _log = Logger.getLogger(this.getClass().getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadePersonaNatural() {
        super(PersonaNatural.class);
    }

    @PersistenceContext(unitName = WebConstant.UNIT_NAME_PERSISTENCE)
    private EntityManager em;
    /**
     * Metodo para consultar una persona natural x email
     * @param email
     * @return 
     */
    public PersonaNatural consultarPersonaNatural(String email) {
        Query q = em.createNamedQuery("PersonaNatural.findByCorreoElectronico");
        q.setParameter("correoElectronico", email);
        try{
            return (PersonaNatural) q.getSingleResult();
        }catch(Exception e){
            _log.log(Level.SEVERE,"ERROR FacadePersonaNatural.consultarPersonaNatural({0})",email);
            return null;
        }
    }

}
