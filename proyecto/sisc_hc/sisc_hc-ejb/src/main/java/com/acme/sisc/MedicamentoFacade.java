/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.agenda.entidades.Medicamento;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeRemote;
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
import com.acme.sisc.common.ejbLocator.EJBLocator;
import com.acme.sisc.agenda.shared.ICitaRemote;
import javax.naming.NamingException;

/**
 *
 * @author GABRIEL
 */
@Stateless
public class MedicamentoFacade implements IMedicamentoFacadeLocal, IMedicamentoFacadeRemote{
    @EJB
    IMedicamentoFacadeRemote facadeMedicamento;
    
    private static final Logger LOGGER = Logger.getLogger(MedicamentoFacade.class.getName());
    private static final String REMOTE_EJB_CITA = "java:global/sisc_agenda-ear-1.0-SNAPSHOT/sisc_agenda-ejb-1.0-SNAPSHOT/SessionBeanCitaPaciente!com.acme.sisc.agenda.shared.ICitaRemote";
    
    private ICitaRemote icitaremote;
    
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

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addMedicamentoCita(List<CitaMedicamento>listaMedicamentos) {
        try {
            icitaremote = (ICitaRemote) EJBLocator.lookup(REMOTE_EJB_CITA);
            for (int i=0; i<listaMedicamentos.size(); i++) {
                Cita c = icitaremote.find(listaMedicamentos.get(i).getCita().getIdCita());
                CitaMedicamento objectCM = findByCita_Medicament(c.getIdCita(),
                        listaMedicamentos.get(i).getMedicamento().getIdMedicamento());
                objectCM.setFormula(listaMedicamentos.get(i).getFormula());
                objectCM.setFechaGenracion(new Date());
                objectCM.setMedicamento(facadeMedicamento.find(listaMedicamentos.get(i).getMedicamento().getIdMedicamento()));
                objectCM.setCita(c);
                em.merge(objectCM);
            }
        } catch (NamingException ex) {
            Logger.getLogger(MedicamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public ArrayList<HashMap> findByCita(Long idcita) {
        Query q = em.createQuery("SELECT cm FROM CitaMedicamento cm WHERE cm.cita.idCita="+idcita);
        List<CitaMedicamento>lista = q.getResultList();

        ArrayList<HashMap> js= new ArrayList<HashMap>();

        for (int i = 0; i<lista.size();i++ ){
            HashMap m = new HashMap();
            m.put("cita", lista.get(i).getCita().getIdCita());
            m.put("medicamento", lista.get(i).getMedicamento().getIdMedicamento());
            m.put("medicamento_name", lista.get(i).getMedicamento().getNombreMedicamento());
            m.put("fechageneracion", lista.get(i).getFechaGenracion());
            m.put("formula", lista.get(i).getFormula());
            js.add(m);
        }
        return js;
    }

    @Override
    public CitaMedicamento findByCita_Medicament(Long idcita, Long idmedicamento) {
        Query q = em.createNativeQuery("SELECT * FROM cita_medicamento where id_cita = " + idcita + 
                " AND id_medicamento = "+ idmedicamento, CitaMedicamento.class);
        if (q.getResultList().isEmpty()){
            CitaMedicamento obj = new CitaMedicamento();
            return obj;
        }else{
            return (CitaMedicamento)q.getSingleResult();
        }
    }
    
}
