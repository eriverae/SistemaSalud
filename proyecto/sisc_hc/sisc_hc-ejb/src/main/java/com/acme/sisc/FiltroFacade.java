/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.PersonaEps;
import com.acme.sisc.sisc_hc.shared.ICabeceraFacadeRemote;
import com.acme.sisc.sisc_hc.shared.IFiltroFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IFiltroFacadeRemote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class FiltroFacade implements IFiltroFacadeLocal, IFiltroFacadeRemote {

    @EJB
    ICabeceraFacadeRemote cabeceraFacade;

    private static final Logger LOGGER = Logger.getLogger(CabeceraFacade.class.getName());

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public ArrayList<HashMap> findByCita(Long idcita) {
        Query q = em.createQuery("SELECT c FROM Cita c WHERE c.idCita=" + idcita);
        Cita cita = (Cita) q.getSingleResult();
        
        PersonaEps personaeps = cita.getPacienteEps();
        
        Query q1 = em.createQuery("SELECT c FROM Cita c WHERE c.pacienteEps.idPersonaEps = "+personaeps.getIdPersonaEps());
        List<Cita> citasporpaciente= q1.getResultList();
        
        ArrayList<HashMap> js = new ArrayList<HashMap>();

        for(int i=0; i<citasporpaciente.size();i++){
            HashMap m = new HashMap();
            m.put("idmedico", citasporpaciente.get(i).getAgenda().getMedicoEps().getIdPersonaEps());
            m.put("nombremedico",citasporpaciente.get(i).getAgenda().getMedicoEps().getPersona().getNombres().toUpperCase()
                    + citasporpaciente.get(i).getAgenda().getMedicoEps().getPersona().getApellidos().toUpperCase());
            js.add(m);
        }

        return js;
    }

}
