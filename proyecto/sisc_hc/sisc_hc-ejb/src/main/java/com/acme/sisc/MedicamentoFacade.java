/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.agenda.entidades.Medicamento;
import com.acme.sisc.agenda.entidades.Persona;
import com.acme.sisc.agenda.entidades.PersonaEps;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeRemote;
import java.util.Date;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author GABRIEL
 */
@Stateless
public class MedicamentoFacade implements IMedicamentoFacadeLocal, IMedicamentoFacadeRemote{
    @EJB
    IMedicamentoFacadeRemote facadeMedicamento;
    
    private static final Logger LOGGER = Logger.getLogger(MedicamentoFacade.class.getName());
    
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
        try{
            Cita c = em.find(Cita.class, new Long("1"));
            for (int i=0; i<listaMedicamentos.size(); i++) {
                CitaMedicamento obj = listaMedicamentos.get(i);
                obj.setFechaGenracion(new Date());
                Medicamento m = facadeMedicamento.find(listaMedicamentos.get(i).getMedicamento().getIdMedicamento());
                obj.setMedicamento(m);
                //Cita obj = new Cita();
                //obj.setId(Long.parseLong("1"));
                obj.setCita(c);
                //listaMedicamentos.get(i).setMedicamento(facadeCita.findById(listaMedicamentos.get(i).getCita().getId()));
                
                em.persist(obj);
            
            }
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,"No se encontro cliente {0} ", e);
        }
    }
}
