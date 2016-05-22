/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.shared.ICitaRemote;
import com.acme.sisc.common.ejbLocator.EJBLocator;
import com.acme.sisc.sisc_hc.shared.IDiagnosticoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IDiagnosticoFacadeRemote;
import java.util.ArrayList;
import java.util.HashMap;
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
public class DiagnosticoFacade implements IDiagnosticoFacadeLocal, IDiagnosticoFacadeRemote {

    @EJB
    IDiagnosticoFacadeRemote facadeDiagnostico;

    private static final Logger LOGGER = Logger.getLogger(DiagnosticoFacade.class.getName());
    
    private static final String REMOTE_EJB_CITA = "java:global/sisc_agenda-ear-1.0-SNAPSHOT/sisc_agenda-ejb-1.0-SNAPSHOT/SessionBeanCitaPaciente!com.acme.sisc.agenda.shared.ICitaRemote";
    
    private ICitaRemote icitaremote;

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnosticoFacade() {

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addDiagnosticoCita(Long id_cita, String diagnostico) {
        try {
            icitaremote = (ICitaRemote) EJBLocator.lookup(REMOTE_EJB_CITA);
//            Cita c = em.find(Cita.class, new Long("1"));
            Cita c = icitaremote.find(id_cita);
            c.setObservaciones(diagnostico);
            em.merge(c);
        } catch (NamingException ex) {
            Logger.getLogger(DiagnosticoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<HashMap> findByCita(Long idcita) {
        Query q = em.createNativeQuery("SELECT observaciones FROM Cita WHERE id_cita =" + idcita.toString());
        String diagnostico =  (String) q.getSingleResult();

        HashMap m = new HashMap();
        m.put("diagnostico", diagnostico);
        ArrayList<HashMap> js = new ArrayList<HashMap>();
        js.add(m);

        return js;
    }

}
