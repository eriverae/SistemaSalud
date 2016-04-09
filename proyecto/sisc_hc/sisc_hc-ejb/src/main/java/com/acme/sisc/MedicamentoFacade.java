/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Medicamento;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeRemote;
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
public class MedicamentoFacade implements IMedicamentoFacadeLocal, IMedicamentoFacadeRemote{
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicamentoFacade() {
        
    }
    
    @Override
    public Medicamento find(Object id) {
        return em.find(Medicamento.class, id);
    }

    @Override
    public List<Medicamento> findAll() {
        Query q = em.createQuery("SELECT m FROM Medicamento m");
        return q.getResultList();
    }
    
}
