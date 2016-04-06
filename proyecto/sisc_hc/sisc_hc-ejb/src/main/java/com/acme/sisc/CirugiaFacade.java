/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cirugia;
import com.acme.sisc.sisc_hc.shared.ICirugiaFacadeLocal;
import com.acme.sisc.sisc_hc.shared.ICirugiaFacadeRemote;
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
public class CirugiaFacade implements ICirugiaFacadeLocal, ICirugiaFacadeRemote{
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }

    public CirugiaFacade() {
        
    }
    
    @Override
    public Cirugia find(Object id) {
        return em.find(Cirugia.class, id);
    }

    @Override
    public List<Cirugia> findAll() {
        Query q = em.createQuery("SELECT m FROM Cirugia m");
        return q.getResultList();
    }
    
}
