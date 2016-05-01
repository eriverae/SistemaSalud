/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cirugia;
import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.CitaCirugia;
import com.acme.sisc.sisc_hc.shared.ICirugiaFacadeLocal;
import com.acme.sisc.sisc_hc.shared.ICirugiaFacadeRemote;
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
public class CirugiaFacade implements ICirugiaFacadeLocal, ICirugiaFacadeRemote{
    @EJB
    ICirugiaFacadeRemote facadeCirugia;
    
    private static final Logger LOGGER = Logger.getLogger(MedicamentoFacade.class.getName());
    
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

    @Override
    public void addCirugiaCita(List<CitaCirugia> listaCirugia) {
        Cita c = em.find(Cita.class, new Long("1"));
        for (int i=0; i<listaCirugia.size(); i++) {
            CitaCirugia obj = listaCirugia.get(i);
            obj.setFechaGeneracion(new Date());
            obj.setCirugia(facadeCirugia.find(listaCirugia.get(i).getCirugia().getIdCirugia()));
            obj.setCita(c);
            //listaMedicamentos.get(i).setMedicamento(facadeCita.findById(listaMedicamentos.get(i).getCita().getId()));
            em.persist(obj);
        }
    }
    
    @Override
    public ArrayList<HashMap> findByCita(Long idcita) {
        Query q = em.createQuery("SELECT cc FROM CitaCirugia cc WHERE cc.cita.idCita="+idcita);
        List<CitaCirugia>lista = q.getResultList();

        ArrayList<HashMap> js= new ArrayList<HashMap>();

        for (int i = 0; i<lista.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", lista.get(i).getCita().getIdCita());
            m.put("idcirugia", lista.get(i).getCirugia().getIdCirugia());
            m.put("fechageneracion", lista.get(i).getFechaGeneracion());
            m.put("observaciones", lista.get(i).getObservaciones());
            m.put("detalles", lista.get(i).getDetalles());
            js.add(m);
        }
        return js;
    }
    
}
