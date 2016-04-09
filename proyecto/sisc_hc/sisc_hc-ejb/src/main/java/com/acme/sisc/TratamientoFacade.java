/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Tratamiento;
import com.acme.sisc.sisc_hc.shared.ITratamientoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.ITratamientoFacadeRemote;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author GABRIEL
 */
@Stateless
public class TratamientoFacade implements ITratamientoFacadeLocal, ITratamientoFacadeRemote{
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }

    public TratamientoFacade() {
        
    }
    
    @Override
    public Tratamiento find(Object id) {
        return em.find(Tratamiento.class, id);
    }

    @Override
    public List<Tratamiento> findAll() {
        Query q = em.createQuery("SELECT m FROM Tratamiento m");
        return q.getResultList();
    }
    
}
