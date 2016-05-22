/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.CitaTratamiento;
import com.acme.sisc.agenda.entidades.Tratamiento;
import com.acme.sisc.agenda.shared.ICitaRemote;
import com.acme.sisc.common.ejbLocator.EJBLocator;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.acme.sisc.sisc_hc.shared.ITratamientoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.ITratamientoFacadeRemote;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
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
public class TratamientoFacade implements ITratamientoFacadeLocal, ITratamientoFacadeRemote{
    @EJB
    ITratamientoFacadeRemote facadeTratamiento;
    
    private static final Logger LOGGER = Logger.getLogger(MedicamentoFacade.class.getName());
    private static final String REMOTE_EJB_CITA = "java:global/sisc_agenda-ear-1.0-SNAPSHOT/sisc_agenda-ejb-1.0-SNAPSHOT/SessionBeanCitaPaciente!com.acme.sisc.agenda.shared.ICitaRemote";
    
    private ICitaRemote icitaremote;
    
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

    @Override
    public void addTratamientoCita(List<CitaTratamiento> listaTratamiento) {
        try{
            icitaremote = (ICitaRemote) EJBLocator.lookup(REMOTE_EJB_CITA);
            for (int i=0; i<listaTratamiento.size(); i++) {
                Cita c = icitaremote.find(listaTratamiento.get(i).getCita().getIdCita());
                CitaTratamiento obj = listaTratamiento.get(i);
                obj.setFechaGeneracion(new Date());
                obj.setTratamiento(facadeTratamiento.find(listaTratamiento.get(i).getTratamiento().getIdTratamiento()));
                obj.setCita(c);
                em.persist(obj);
            }
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,"No se encontro cliente {0} ", e);
        }
    }
    
    @Override
    public ArrayList<HashMap> findByCita(Long idcita) {
        Query q = em.createQuery("SELECT cm FROM CitaTratamiento cm WHERE cm.cita.idCita="+idcita);
        List<CitaTratamiento>lista = q.getResultList();

        ArrayList<HashMap> js= new ArrayList<HashMap>();

        for (int i = 0; i<lista.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", lista.get(i).getCita().getIdCita());
            m.put("idtratamiento", lista.get(i).getTratamiento().getIdTratamiento());
            m.put("fechageneracion", lista.get(i).getFechaGeneracion());
            m.put("observaciones", lista.get(i).getObservaciones());
            js.add(m);
        }
        return js;
    }
    
}
