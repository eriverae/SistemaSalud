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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCirugiaCita(List<CitaCirugia> listaCirugia) {
        try {
            icitaremote = (ICitaRemote) EJBLocator.lookup(REMOTE_EJB_CITA);
            for (int i=0; i<listaCirugia.size(); i++) {
                Cita c = icitaremote.find(listaCirugia.get(i).getCita().getIdCita());
                CitaCirugia objectCC = findByCita_Ciru(c.getIdCita(),
                        listaCirugia.get(i).getCirugia().getIdCirugia());
                objectCC.setFechaGeneracion(new Date());
                objectCC.setObservaciones(listaCirugia.get(i).getObservaciones());
                objectCC.setDetalles(listaCirugia.get(i).getDetalles());
                objectCC.setCirugia(facadeCirugia.find(listaCirugia.get(i).getCirugia().getIdCirugia()));
                objectCC.setCita(c);
                em.merge(objectCC);
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
            m.put("cita", lista.get(i).getCita().getIdCita());
            m.put("cirugia", lista.get(i).getCirugia().getIdCirugia());
            m.put("cirugia_name", lista.get(i).getCirugia().getNombreCirugia());
            m.put("fechageneracion", lista.get(i).getFechaGeneracion());
            m.put("observaciones", lista.get(i).getObservaciones());
            m.put("detalles", lista.get(i).getDetalles());
            js.add(m);
        }
        return js;
    }
    
    @Override
    public CitaCirugia findByCita_Ciru(Long idcita, Long idcirugia) {
        Query q = em.createNativeQuery("SELECT * FROM cita_cirugia where id_cita = " + idcita + 
                " AND id_cirugia = "+ idcirugia, CitaCirugia.class);
        if (q.getResultList().isEmpty()){
            CitaCirugia obj = new CitaCirugia();
            return obj;
        }else{
            return (CitaCirugia)q.getSingleResult();
        }
    }
    
    @Override
    public HashMap deleteCirugiaCita(Long idcita, Long idcirugia){
        HashMap m = new HashMap();
        try{
            Query q = em.createNativeQuery("SELECT * FROM cita_cirugia where id_cita = " + idcita + 
                " AND id_cirugia = "+ idcirugia, CitaCirugia.class);
            if (q.getResultList().isEmpty()){
                m.put("message", "OK");
                m.put("status", 204);
                return m;
            }else{
                em.remove((CitaCirugia)q.getSingleResult());
                m.put("message", "OK");
                m.put("status", 204);
                return m;
            }
        }catch(Exception ex){
            Logger.getLogger(CirugiaFacade.class.getName()).log(Level.SEVERE, null, ex);
            m.put("message", "ERROR... nos encontramos solucionando el problema.");
            m.put("status", 500);
            return m;
        }
    }
    
}
