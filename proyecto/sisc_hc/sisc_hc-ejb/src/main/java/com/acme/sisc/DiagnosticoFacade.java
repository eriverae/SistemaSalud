/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.CitaCirugia;
import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.agenda.entidades.Medicamento;
import com.acme.sisc.sisc_hc.shared.IDiagnosticoFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IDiagnosticoFacadeRemote;
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

/**
 *
 * @author GABRIEL
 */
@Stateless
public class DiagnosticoFacade implements IDiagnosticoFacadeLocal, IDiagnosticoFacadeRemote {

    @EJB
    IDiagnosticoFacadeRemote facadeDiagnostico;

    private static final Logger LOGGER = Logger.getLogger(DiagnosticoFacade.class.getName());

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
        Cita c = em.find(Cita.class, new Long("1"));
        c.setDiagnostico(diagnostico);
        em.merge(c);
    }

    @Override
    public ArrayList<HashMap> findByCita(Long idcita) {
        Query q = em.createNativeQuery("SELECT diagnostico FROM Cita WHERE id_cita =" + idcita.toString());
        String diagnostico =  (String) q.getSingleResult();

        HashMap m = new HashMap();
        m.put("diagnostico", diagnostico);
        ArrayList<HashMap> js = new ArrayList<HashMap>();
        js.add(m);

        return js;
    }

}
