/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.CitaExamen;
import com.acme.sisc.agenda.entidades.Examen;
import com.acme.sisc.agenda.shared.ICitaRemote;
import com.acme.sisc.common.ejbLocator.EJBLocator;
import com.acme.sisc.sisc_hc.shared.IExamenFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IExamenFacadeRemote;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author GABRIEL
 */
@Stateless
public class ExamenFacade implements IExamenFacadeLocal, IExamenFacadeRemote{
    @EJB
    IExamenFacadeRemote facadeExamen;
    
    private static final Logger LOGGER = Logger.getLogger(MedicamentoFacade.class.getName());
    private static final String REMOTE_EJB_CITA = "java:global/sisc_agenda-ear-1.0-SNAPSHOT/sisc_agenda-ejb-1.0-SNAPSHOT/SessionBeanCitaPaciente!com.acme.sisc.agenda.shared.ICitaRemote";
    
    private ICitaRemote icitaremote;
    
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

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addExamenCita(List<CitaExamen> listaExamen) {
        try{
            icitaremote = (ICitaRemote) EJBLocator.lookup(REMOTE_EJB_CITA);
            for (int i=0; i<listaExamen.size(); i++) {
                Cita c = icitaremote.find(listaExamen.get(i).getCita().getIdCita());
                CitaExamen objectCE = findByCita_Examn(c.getIdCita(),
                        listaExamen.get(i).getExamen().getIdExamen());
                objectCE.setFechaGeneracion(new Date());
                objectCE.setObservaciones(listaExamen.get(i).getObservaciones());
                objectCE.setDetalles(listaExamen.get(i).getDetalles());
                objectCE.setExamen(facadeExamen.find(listaExamen.get(i).getExamen().getIdExamen()));
                objectCE.setCita(c);
                em.merge(objectCE);
            }
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,"No se encontro cliente {0} ", e);
        }
    }
    
    @Override
    public ArrayList<HashMap> findByCita(Long idcita) {
        Query q = em.createQuery("SELECT ce FROM CitaExamen ce WHERE ce.cita.idCita="+idcita);
        List<CitaExamen>lista = q.getResultList();

        ArrayList<HashMap> js= new ArrayList<HashMap>();

        for (int i = 0; i<lista.size();i++ ){
            HashMap m = new HashMap();
            m.put("cita", lista.get(i).getCita().getIdCita());
            m.put("examen", lista.get(i).getExamen().getIdExamen());
            m.put("examen_name", lista.get(i).getExamen().getNombreExamen());
            m.put("fechageneracion", lista.get(i).getFechaGeneracion());
            m.put("observaciones", lista.get(i).getObservaciones());
            m.put("detalles", lista.get(i).getDetalles());
            js.add(m);
        }
        return js;
    }
    
    @Override
    public CitaExamen findByCita_Examn(Long idcita, Long idexamen) {
        Query q = em.createNativeQuery("SELECT * FROM cita_examen where id_cita = " + idcita + 
                " AND id_examen = "+ idexamen, CitaExamen.class);
        if (q.getResultList().isEmpty()){
            CitaExamen obj = new CitaExamen();
            return obj;
        }else{
            return (CitaExamen)q.getSingleResult();
        }
    }
    
}
