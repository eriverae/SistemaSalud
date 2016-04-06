/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Examen;
import com.acme.sisc.sisc_hc.shared.IExamenFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IExamenFacadeRemote;
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
public class ExamenFacade implements IExamenFacadeLocal, IExamenFacadeRemote{
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamenFacade() {
        
    }
    
    @Override
    public Examen find(Object id) {
        return em.find(Examen.class, id);
    }

    @Override
    public List<Examen> findAll() {
        Query q = em.createQuery("SELECT m FROM Examen m");
        return q.getResultList();
    }
    
}
