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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        return em.find(Incapacidad.class, id);
    }

    @Override
    public List<Incapacidad> findAll() {
        Query q = em.createQuery("SELECT i FROM Incapacidad i");
        return q.getResultList();
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
    
    @Override
    public ArrayList<HashMap> findByCita(Long idcita) {
        Query q = em.createQuery("SELECT i FROM Incapacidad i WHERE i.cita.idCita="+idcita);
        List<Incapacidad>lista = q.getResultList();

        ArrayList<HashMap> js= new ArrayList<HashMap>();

        for (int i = 0; i<lista.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", lista.get(i).getCita().getIdCita());
            m.put("idtratamiento", lista.get(i).getIdIncapacidad());
            m.put("fechageneracion", lista.get(i).getFechaGeneracion());
            m.put("motivo", lista.get(i).getMotivo());
            m.put("periodo", lista.get(i).getPeriodo());
            js.add(m);
        }
        return js;
    }
    
}
