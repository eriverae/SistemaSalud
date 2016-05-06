/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.entidades.CitaCirugia;
import com.acme.sisc.agenda.entidades.CitaExamen;
import com.acme.sisc.agenda.entidades.CitaTratamiento;
import com.acme.sisc.agenda.entidades.Incapacidad;
import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.sisc_hc.shared.IHistoriaFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IHistoriaFacadeRemote;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
public class HistoriaFacade implements IHistoriaFacadeLocal, IHistoriaFacadeRemote{
    @EJB
    IHistoriaFacadeRemote facadeHistoria;
    
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriaFacade() {
        
    }
    
    @Override
    public ArrayList<HashMap> findByCita(Long idcita) {
        HashMap result_map = new HashMap();
        ArrayList<HashMap> result_js= new ArrayList<HashMap>();
        
        Query q = em.createQuery("SELECT cm FROM CitaMedicamento cm WHERE cm.cita.idCita="+idcita);
        List<CitaMedicamento>listaCitaMedicamento = q.getResultList();

        ArrayList<HashMap> js= new ArrayList<HashMap>();

        for (int i = 0; i<listaCitaMedicamento.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaMedicamento.get(i).getCita().getIdCita());
            m.put("idmedicamento", listaCitaMedicamento.get(i).getMedicamento().getIdMedicamento());
            m.put("fechageneracion", listaCitaMedicamento.get(i).getFechaGenracion());
            m.put("formula", listaCitaMedicamento.get(i).getFormula());
            js.add(m);
        }
        result_map.put("medicamentos", js);
        js.clear();
        
        Query q2 = em.createQuery("SELECT cc FROM CitaCirugia cc WHERE cc.cita.idCita="+idcita);
        List<CitaCirugia>listaCitaCirugia = q2.getResultList();
        
        for (int i = 0; i<listaCitaCirugia.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaCirugia.get(i).getCita().getIdCita());
            m.put("idcirugia", listaCitaCirugia.get(i).getCirugia().getIdCirugia());
            m.put("fechageneracion", listaCitaCirugia.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaCirugia.get(i).getObservaciones());
            m.put("detalles", listaCitaCirugia.get(i).getDetalles());
            js.add(m);
        }
        result_map.put("cirugia", js);
        js.clear();
        
        Query q3 = em.createQuery("SELECT ce FROM CitaExamen ce WHERE ce.cita.idCita="+idcita);
        List<CitaExamen>listaCitaExamen = q3.getResultList();
        
        for (int i = 0; i<listaCitaExamen.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaExamen.get(i).getCita().getIdCita());
            m.put("idexamen", listaCitaExamen.get(i).getExamen().getIdExamen());
            m.put("fechageneracion", listaCitaExamen.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaExamen.get(i).getObservaciones());
            m.put("detalles", listaCitaExamen.get(i).getDetalles());
            js.add(m);
        }
        result_map.put("examen", js);
        js.clear();
        
        Query q4 = em.createQuery("SELECT ct FROM CitaTratamiento ct WHERE ct.cita.idCita="+idcita);
        List<CitaTratamiento>listaCitaTratamiento = q4.getResultList();
        
        for (int i = 0; i<listaCitaTratamiento.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaTratamiento.get(i).getCita().getIdCita());
            m.put("idtratamiento", listaCitaTratamiento.get(i).getTratamiento().getIdTratamiento());
            m.put("fechageneracion", listaCitaTratamiento.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaTratamiento.get(i).getObservaciones());
            js.add(m);
        }
        result_map.put("tratamiento", js);
        js.clear();
        
        Query q5 = em.createQuery("SELECT i FROM Incapacidad i WHERE i.cita.idCita="+idcita);
        List<Incapacidad>listaIncapacidad = q5.getResultList();
        
        for (int i = 0; i<listaIncapacidad.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaIncapacidad.get(i).getCita().getIdCita());
            m.put("idtratamiento", listaIncapacidad.get(i).getIdIncapacidad());
            m.put("fechageneracion", listaIncapacidad.get(i).getFechaGeneracion());
            m.put("motivo", listaIncapacidad.get(i).getMotivo());
            m.put("periodo", listaIncapacidad.get(i).getPeriodo());
            js.add(m);
        }
        result_map.put("incapacidad", js);
        
        result_js.add(result_map);
        return result_js;
    }

    @Override
    public ArrayList<HashMap> findAll() {
        HashMap result_map = new HashMap();
        ArrayList<HashMap> result_js= new ArrayList<HashMap>();
        
        Query q = em.createQuery("SELECT cm FROM CitaMedicamento cm");
        List<CitaMedicamento>listaCitaMedicamento = q.getResultList();

        ArrayList<HashMap> js= new ArrayList<HashMap>();

        for (int i = 0; i<listaCitaMedicamento.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaMedicamento.get(i).getCita().getIdCita());
            m.put("idmedicamento", listaCitaMedicamento.get(i).getMedicamento().getIdMedicamento());
            m.put("fechageneracion", listaCitaMedicamento.get(i).getFechaGenracion());
            m.put("formula", listaCitaMedicamento.get(i).getFormula());
            js.add(m);
        }
        result_map.put("medicamentos", js);
        js.clear();
        
        Query q2 = em.createQuery("SELECT cc FROM CitaCirugia cc");
        List<CitaCirugia>listaCitaCirugia = q2.getResultList();
        
        for (int i = 0; i<listaCitaCirugia.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaCirugia.get(i).getCita().getIdCita());
            m.put("idcirugia", listaCitaCirugia.get(i).getCirugia().getIdCirugia());
            m.put("fechageneracion", listaCitaCirugia.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaCirugia.get(i).getObservaciones());
            m.put("detalles", listaCitaCirugia.get(i).getDetalles());
            js.add(m);
        }
        result_map.put("cirugia", js);
        js.clear();
        
        Query q3 = em.createQuery("SELECT ce FROM CitaExamen ce");
        List<CitaExamen>listaCitaExamen = q3.getResultList();
        
        for (int i = 0; i<listaCitaExamen.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaExamen.get(i).getCita().getIdCita());
            m.put("idexamen", listaCitaExamen.get(i).getExamen().getIdExamen());
            m.put("fechageneracion", listaCitaExamen.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaExamen.get(i).getObservaciones());
            m.put("detalles", listaCitaExamen.get(i).getDetalles());
            js.add(m);
        }
        result_map.put("examen", js);
        js.clear();
        
        Query q4 = em.createQuery("SELECT ct FROM CitaTratamiento ct");
        List<CitaTratamiento>listaCitaTratamiento = q4.getResultList();
        
        for (int i = 0; i<listaCitaTratamiento.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaTratamiento.get(i).getCita().getIdCita());
            m.put("idtratamiento", listaCitaTratamiento.get(i).getTratamiento().getIdTratamiento());
            m.put("fechageneracion", listaCitaTratamiento.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaTratamiento.get(i).getObservaciones());
            js.add(m);
        }
        result_map.put("tratamiento", js);
        js.clear();
        
        Query q5 = em.createQuery("SELECT i FROM Incapacidad i");
        List<Incapacidad>listaIncapacidad = q5.getResultList();
        
        for (int i = 0; i<listaIncapacidad.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaIncapacidad.get(i).getCita().getIdCita());
            m.put("idtratamiento", listaIncapacidad.get(i).getIdIncapacidad());
            m.put("fechageneracion", listaIncapacidad.get(i).getFechaGeneracion());
            m.put("motivo", listaIncapacidad.get(i).getMotivo());
            m.put("periodo", listaIncapacidad.get(i).getPeriodo());
            js.add(m);
        }
        result_map.put("incapacidad", js);
        
        result_js.add(result_map);
        return result_js;
    }
    
}
