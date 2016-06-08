/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.Incapacidad;
import com.acme.sisc.agenda.shared.ICitaRemote;
import com.acme.sisc.sisc_hc.shared.IIncapacidadFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IIncapacidadFacadeRemote;
import java.math.BigInteger;
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
public class IncapacidadFacade implements IIncapacidadFacadeLocal, IIncapacidadFacadeRemote {

    @EJB
    IIncapacidadFacadeRemote facadeIncapacidad;

    private static final Logger LOGGER = Logger.getLogger(IncapacidadFacade.class.getName());
    private static final String REMOTE_EJB_CITA = "java:global/sisc_agenda-ear-1.0-SNAPSHOT/sisc_agenda-ejb-1.0-SNAPSHOT/SessionBeanCitaPaciente!com.acme.sisc.agenda.shared.ICitaRemote";

    private ICitaRemote icitaremote;

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public IncapacidadFacade() {

    }

    @Override
    public Incapacidad find(Object id) {
        return em.find(Incapacidad.class, id);
    }

    @Override
    public List<Incapacidad> findAll() {
        Query q = em.createQuery("SELECT i FROM Incapacidad i");
        return q.getResultList();
    }

    @Override
    public void addIncapacidad(Long cita, String motivo, String periodo) {
        LOGGER.log(Level.WARNING, "OBJETO INCAPACIDAD ", motivo + "|" + cita);
        try {
            //facadeIncapacidad.findAll();
            Incapacidad obj = new Incapacidad();
            // Cita c = icitaremote.find(new Long("1"));
            Cita c = em.find(Cita.class, new Long("1"));
            obj.setFechaGeneracion(new Date());
            obj.setCita(c);
            obj.setMotivo(motivo);
            obj.setPeriodo(periodo);

            em.persist(obj);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ERROR EN ADD INCAPACIDAD ", e);
        }
    }

    @Override
    public ArrayList<HashMap> findByCita(Long idcita) {
        Query q = em.createQuery("SELECT i FROM Incapacidad i WHERE i.cita.idCita=" + idcita);
        List<Incapacidad> lista = q.getResultList();

        ArrayList<HashMap> js = new ArrayList<HashMap>();

        for (int i = 0; i < lista.size(); i++) {
            HashMap m = new HashMap();
            m.put("idcita", lista.get(i).getCita().getIdCita());
            m.put("idtratamiento", lista.get(i).getIdIncapacidad());
            m.put("fechageneracion", lista.get(i).getFechaGeneracion());
            m.put("motivo", lista.get(i).getMotivo());
            m.put("periodo", lista.get(i).getPeriodo());
            js.add(m);
        }
        return js;
    }

    @Override
    public ArrayList<HashMap> isIncapacitado(String idpaciente) {
        ArrayList<HashMap> js = new ArrayList<HashMap>();
        HashMap m = new HashMap();
        Query q = em.createNativeQuery("select id_incapacidad from incapacidad where id_cita in (select id_cita from cita where id_paciente_eps = " + idpaciente + ") and fecha_generacion = (select max(fecha_generacion) from incapacidad)");
        try {
            BigInteger id_incapacidad = (BigInteger) q.getSingleResult();

            if (id_incapacidad != null) {
                Query q1 = em.createQuery("SELECT i FROM Incapacidad i WHERE i.idIncapacidad =" + id_incapacidad.toString());
                Incapacidad incapacidad = (Incapacidad) q1.getSingleResult();

                if (incapacidad != null) {
                    Date hoy = new Date();
                    int periodo = Integer.parseInt(incapacidad.getPeriodo());
                    Date fecha = incapacidad.getFechaGeneracion();
                    m.put("fechainicio", fecha.toString());
                    m.put("periodo", periodo);
                    m.put("hoy", hoy.toGMTString());
                    m.put("idcita", incapacidad.getCita().getIdCita());
                    m.put("motivo", incapacidad.getMotivo());
                }
            }
        } catch (Exception e) {
            m.put("fechainicio", "");
            m.put("periodo", "");
            m.put("hoy", "");
            m.put("idcita", "");
            m.put("motivo", "");
        }
        js.add(m);
        return js;
    }

}
