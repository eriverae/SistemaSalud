/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisc.acme.ejb;

import com.sisc.acme.seguridad.entity.AccesoGrupo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Erika
 */
@Stateless
public class AccesoGrupoFacade extends AbstractFacade<AccesoGrupo> {

    @PersistenceContext(unitName = "sisc_seguridad")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccesoGrupoFacade() {
        super(AccesoGrupo.class);
    }
    
}
