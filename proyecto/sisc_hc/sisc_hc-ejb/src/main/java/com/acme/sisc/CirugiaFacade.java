/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cirugia;
import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.CitaCirugia;
import com.acme.sisc.agenda.shared.ICitaRemote;
import com.acme.sisc.common.ejbLocator.EJBLocator;
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
import javax.naming.NamingException;
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
    
    private static final String REMOTE_EJB_CITA = "java:global/sisc_agenda-ear-1.0-SNAPSHOT/sisc_agenda-ejb-1.0-SNAPSHOT/SessionBeanCitaPaciente!com.acme.sisc.agenda.shared.ICitaRemote";
    
    private ICitaRemote icitaremote;
    
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
        try {
            icitaremote = (ICitaRemote) EJBLocator.lookup(REMOTE_EJB_CITA);
            for (int i=0; i<listaCirugia.size(); i++) {
                Cita c = icitaremote.find(listaCirugia.get(i).getCita().getIdCita());
                CitaCirugia obj = listaCirugia.get(i);
                obj.setFechaGeneracion(new Date());
                obj.setCirugia(facadeCirugia.find(listaCirugia.get(i).getCirugia().getIdCirugia()));
                obj.setCita(c);
                em.persist(obj);
            }
        } catch (NamingException ex) {
            Logger.getLogger(CirugiaFacade.class.getName()).log(Level.SEVERE, null, ex);
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
