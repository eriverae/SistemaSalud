/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.Incapacidad;
import com.acme.sisc.sisc_hc.shared.IIncapacidadFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IIncapacidadFacadeRemote;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author GABRIEL
 */
@Stateless
public class IncapacidadFacade implements IIncapacidadFacadeLocal, IIncapacidadFacadeRemote{
    @EJB
    IIncapacidadFacadeRemote facadeIncapacidad;
    
    private static final Logger LOGGER = Logger.getLogger(MedicamentoFacade.class.getName());

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }

    public IncapacidadFacade() {
        
    }
    
    @Override
    public Incapacidad find(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Incapacidad> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addIncapacidad(Object incapacidad) {
        try{
            facadeIncapacidad.findAll();
            Cita c = em.find(Cita.class, new Long("1"));
            Incapacidad obj = (Incapacidad) incapacidad;
            obj.setFechaGeneracion(new Date());
            obj.setCita(c);
            //listaMedicamentos.get(i).setMedicamento(facadeCita.findById(listaMedicamentos.get(i).getCita().getId()));
            em.persist(obj);
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,"No se encontro cliente {0} ", e);
        }
    }
    
}
