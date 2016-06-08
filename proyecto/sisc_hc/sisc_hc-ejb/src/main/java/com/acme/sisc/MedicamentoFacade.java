/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.AlergiaMedicamento;
import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.agenda.entidades.Medicamento;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.agenda.entidades.PersonaNaturalAlergia;
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
    public HashMap addMedicamentoCita(List<CitaMedicamento>listaMedicamentos) {
        HashMap m = new HashMap();
        try {
            icitaremote = (ICitaRemote) EJBLocator.lookup(REMOTE_EJB_CITA);
            for (int i=0; i<listaMedicamentos.size(); i++) {
                Cita c = icitaremote.find(listaMedicamentos.get(i).getCita().getIdCita());
                
                Long id_persona = c.getPacienteEps().getPersona().getIdPersona();

                Query q = em.createNativeQuery("select * from alergia_medicamento as am where am.id_alergia in (\n" +
                    "select pna.id_alergia from persona_natural as pn inner join persona_natural_alergia as pna\n" +
                    "on pn.id_persona = pna.id_paciente) and id_medicamento =" + listaMedicamentos.get(i).getMedicamento().getIdMedicamento(),AlergiaMedicamento.class);
                List<AlergiaMedicamento>listado_alergias_paciente = q.getResultList();
                if(listado_alergias_paciente.size() > 0){
                    m.put("message", "El paciente es alergico a este medicamento, no es posible formularlo");
                    m.put("status", 400);
                    return m;
                }else{
                    CitaMedicamento objectCM = findByCita_Medicament(c.getIdCita(),
                    listaMedicamentos.get(i).getMedicamento().getIdMedicamento());
                    objectCM.setFormula(listaMedicamentos.get(i).getFormula());
                    objectCM.setFechaGenracion(new Date());
                    objectCM.setMedicamento(facadeMedicamento.find(listaMedicamentos.get(i).getMedicamento().getIdMedicamento()));
                    objectCM.setCita(c);
                    em.merge(objectCM);
                }
            }
            m.put("message", "OK");
            m.put("status", 200);
            return m;
        } catch (NamingException ex) {
            Logger.getLogger(MedicamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            m.put("message", "ERROR... nos encontramos solucionando el problema.");
            m.put("status", 500);
            return m;
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
