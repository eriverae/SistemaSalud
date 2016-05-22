/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.sisc_hc.shared.ICabeceraFacadeLocal;
import com.acme.sisc.sisc_hc.shared.ICabeceraFacadeRemote;
import com.acme.sisc.sisc_hc.shared.IMedicamentoFacadeRemote;
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
public class CabeceraFacade implements ICabeceraFacadeRemote, ICabeceraFacadeLocal{

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
        Query q = em.createQuery("SELECT c FROM Cita c WHERE c.idCita="+idcita);
        Cita cita  = (Cita)q.getSingleResult();

        ArrayList<HashMap> js= new ArrayList<HashMap>();

        
            HashMap m = new HashMap();
            m.put("idcita", 1);
            m.put("nombre", (cita.getPacienteEps().getPersona().getNombres() +" "+ cita.getPacienteEps().getPersona().getApellidos()).toUpperCase());
            m.put("identificacion", cita.getPacienteEps().getPersona().getNumeroIdentificacion());
            m.put("fechanac",cita.getPacienteEps().getPersona().getFechaNacimiento().toString());
            m.put("correo",cita.getPacienteEps().getPersona().getCorreoElectronico());
            js.add(m);
        
        return js;
    }
    
    
    
    
}
