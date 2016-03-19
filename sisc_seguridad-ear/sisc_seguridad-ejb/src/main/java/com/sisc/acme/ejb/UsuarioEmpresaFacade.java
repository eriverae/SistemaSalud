/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisc.acme.ejb;

import com.sisc.acme.seguridad.entity.UsuarioEmpresa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Erika
 */
@Stateless
public class UsuarioEmpresaFacade extends AbstractFacade<UsuarioEmpresa> {

    @PersistenceContext(unitName = "sisc_seguridad")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioEmpresaFacade() {
        super(UsuarioEmpresa.class);
    }
    
}
