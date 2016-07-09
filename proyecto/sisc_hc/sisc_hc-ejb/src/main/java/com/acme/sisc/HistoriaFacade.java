/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc;

import com.acme.sisc.agenda.entidades.CitaCirugia;
import com.acme.sisc.agenda.entidades.CitaExamen;
import com.acme.sisc.agenda.entidades.CitaTratamiento;
import com.acme.sisc.agenda.entidades.Incapacidad;
import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.sisc_hc.shared.IHistoriaFacadeLocal;
import com.acme.sisc.sisc_hc.shared.IHistoriaFacadeRemote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        
        ArrayList<HashMap> js2= new ArrayList<HashMap>();
        
        Query q2 = em.createQuery("SELECT cc FROM CitaCirugia cc WHERE cc.cita.idCita="+idcita);
        List<CitaCirugia>listaCitaCirugia = q2.getResultList();
        
        for (int i = 0; i<listaCitaCirugia.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaCirugia.get(i).getCita().getIdCita());
            m.put("idcirugia", listaCitaCirugia.get(i).getCirugia().getIdCirugia());
            m.put("fechageneracion", listaCitaCirugia.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaCirugia.get(i).getObservaciones());
            m.put("detalles", listaCitaCirugia.get(i).getDetalles());
            js2.add(m);
        }
        result_map.put("cirugia", js2);
        
        ArrayList<HashMap> js3= new ArrayList<HashMap>();
        
        Query q3 = em.createQuery("SELECT ce FROM CitaExamen ce WHERE ce.cita.idCita="+idcita);
        List<CitaExamen>listaCitaExamen = q3.getResultList();
        
        for (int i = 0; i<listaCitaExamen.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaExamen.get(i).getCita().getIdCita());
            m.put("idexamen", listaCitaExamen.get(i).getExamen().getIdExamen());
            m.put("fechageneracion", listaCitaExamen.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaExamen.get(i).getObservaciones());
            m.put("detalles", listaCitaExamen.get(i).getDetalles());
            js3.add(m);
        }
        result_map.put("examen", js3);
        
        ArrayList<HashMap> js4= new ArrayList<HashMap>();
        
        Query q4 = em.createQuery("SELECT ct FROM CitaTratamiento ct WHERE ct.cita.idCita="+idcita);
        List<CitaTratamiento>listaCitaTratamiento = q4.getResultList();
        
        for (int i = 0; i<listaCitaTratamiento.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaTratamiento.get(i).getCita().getIdCita());
            m.put("idtratamiento", listaCitaTratamiento.get(i).getTratamiento().getIdTratamiento());
            m.put("fechageneracion", listaCitaTratamiento.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaTratamiento.get(i).getObservaciones());
            js4.add(m);
        }
        result_map.put("tratamiento", js4);
        
        ArrayList<HashMap> js5= new ArrayList<HashMap>();
        
        Query q5 = em.createQuery("SELECT i FROM Incapacidad i WHERE i.cita.idCita="+idcita);
        List<Incapacidad>listaIncapacidad = q5.getResultList();
        
        for (int i = 0; i<listaIncapacidad.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaIncapacidad.get(i).getCita().getIdCita());
            m.put("idtratamiento", listaIncapacidad.get(i).getIdIncapacidad());
            m.put("fechageneracion", listaIncapacidad.get(i).getFechaGeneracion());
            m.put("motivo", listaIncapacidad.get(i).getMotivo());
            m.put("periodo", listaIncapacidad.get(i).getPeriodo());
            js5.add(m);
        }
        result_map.put("incapacidad", js5);
        
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
            //m.put("idcita", listaCitaMedicamento.get(i).getCita().getIdCita());
            m.put("idcita", listaCitaMedicamento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaMedicamento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
            m.put("idmedicamento", listaCitaMedicamento.get(i).getMedicamento().getNombreMedicamento());
            m.put("fechageneracion", listaCitaMedicamento.get(i).getFechaGenracion());
            m.put("formula", listaCitaMedicamento.get(i).getFormula());
            js.add(m);
        }
        result_map.put("medicamentos", js);
        
        ArrayList<HashMap> js2= new ArrayList<HashMap>();
        
        Query q2 = em.createQuery("SELECT cc FROM CitaCirugia cc");
        List<CitaCirugia>listaCitaCirugia = q2.getResultList();
        
        for (int i = 0; i<listaCitaCirugia.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaCirugia.get(i).getCita().getIdCita());
            m.put("idcirugia", listaCitaCirugia.get(i).getCirugia().getNombreCirugia());
            m.put("fechageneracion", listaCitaCirugia.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaCirugia.get(i).getObservaciones());
            m.put("detalles", listaCitaCirugia.get(i).getDetalles());
            js2.add(m);
        }
        result_map.put("cirugia", js2);
        
        ArrayList<HashMap> js3 = new ArrayList<HashMap>();
        
        Query q3 = em.createQuery("SELECT ce FROM CitaExamen ce");
        List<CitaExamen>listaCitaExamen = q3.getResultList();
        
        for (int i = 0; i<listaCitaExamen.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaExamen.get(i).getCita().getIdCita());
            m.put("idexamen", listaCitaExamen.get(i).getExamen().getNombreExamen());
            m.put("fechageneracion", listaCitaExamen.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaExamen.get(i).getObservaciones());
            m.put("detalles", listaCitaExamen.get(i).getDetalles());
            js3.add(m);
        }
        result_map.put("examen", js3);
        
        ArrayList<HashMap> js4= new ArrayList<HashMap>();
        
        Query q4 = em.createQuery("SELECT ct FROM CitaTratamiento ct");
        List<CitaTratamiento>listaCitaTratamiento = q4.getResultList();
        
        for (int i = 0; i<listaCitaTratamiento.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaTratamiento.get(i).getCita().getIdCita());
            m.put("idtratamiento", listaCitaTratamiento.get(i).getTratamiento().getNombreTratamiento());
            m.put("fechageneracion", listaCitaTratamiento.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaTratamiento.get(i).getObservaciones());
            js4.add(m);
        }
        result_map.put("tratamiento", js4);
        
        ArrayList<HashMap> js5= new ArrayList<HashMap>();
        
        Query q5 = em.createQuery("SELECT i FROM Incapacidad i");
        List<Incapacidad>listaIncapacidad = q5.getResultList();
        
        for (int i = 0; i<listaIncapacidad.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaIncapacidad.get(i).getCita().getIdCita());
            m.put("idtratamiento", listaIncapacidad.get(i).getIdIncapacidad());
            m.put("fechageneracion", listaIncapacidad.get(i).getFechaGeneracion());
            m.put("motivo", listaIncapacidad.get(i).getMotivo());
            m.put("periodo", listaIncapacidad.get(i).getPeriodo());
            js5.add(m);
        }
        result_map.put("incapacidad", js5);
        
        result_js.add(result_map);
        return result_js;
    }

    @Override
    public ArrayList<HashMap> find_last_cita() {
        HashMap result_map = new HashMap();
        ArrayList<HashMap> result_js= new ArrayList<HashMap>();

        Query q = em.createNativeQuery("select * from cita_medicamento \n" +
                "where id_cita in (\n" +
                "	select max(id_cita) from persona_natural inner join cita\n" +
                "	on persona_natural.id_persona = cita.id_paciente_eps\n" +
                ")", CitaMedicamento.class);
        List<CitaMedicamento>listaCitaMedicamento = q.getResultList();

        ArrayList<HashMap> js = new ArrayList<HashMap>();

        for (int i = 0; i<listaCitaMedicamento.size();i++ ){
            HashMap m = new HashMap();
            //m.put("idcita", listaCitaMedicamento.get(i).getCita().getIdCita());
            m.put("idcita", listaCitaMedicamento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaMedicamento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
            m.put("idmedicamento", listaCitaMedicamento.get(i).getMedicamento().getNombreMedicamento());
            m.put("fechageneracion", listaCitaMedicamento.get(i).getFechaGenracion());
            m.put("formula", listaCitaMedicamento.get(i).getFormula());
            js.add(m);
        }
        result_map.put("medicamentos", js);
        
        ArrayList<HashMap> js2 = new ArrayList<HashMap>();
        
        Query q2 = em.createNativeQuery("select * from cita_cirugia \n" +
                "where id_cita in (\n" +
                "	select max(id_cita) from persona_natural inner join cita\n" +
                "	on persona_natural.id_persona = cita.id_paciente_eps\n" +
                ")", CitaCirugia.class);
        List<CitaCirugia>listaCitaCirugia = q2.getResultList();
        
        for (int i = 0; i<listaCitaCirugia.size();i++ ){
            HashMap m = new HashMap();
            //m.put("idcita", listaCitaCirugia.get(i).getCita().getIdCita());
            m.put("idcita", listaCitaCirugia.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaCirugia.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
            m.put("idcirugia", listaCitaCirugia.get(i).getCirugia().getNombreCirugia());
            m.put("fechageneracion", listaCitaCirugia.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaCirugia.get(i).getObservaciones());
            m.put("detalles", listaCitaCirugia.get(i).getDetalles());
            js2.add(m);
        }
        result_map.put("cirugia", js2);
        
        ArrayList<HashMap> js3 = new ArrayList<HashMap>();
        
        Query q3 = em.createNativeQuery("select * from cita_examen \n" +
                "where id_cita in (\n" +
                "	select max(id_cita) from persona_natural inner join cita\n" +
                "	on persona_natural.id_persona = cita.id_paciente_eps\n" +
                ")", CitaExamen.class);
        List<CitaExamen>listaCitaExamen = q3.getResultList();
        
        for (int i = 0; i<listaCitaExamen.size();i++ ){
            HashMap m = new HashMap();
            //m.put("idcita", listaCitaExamen.get(i).getCita().getIdCita());
            m.put("idcita", listaCitaExamen.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaExamen.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
            m.put("idexamen", listaCitaExamen.get(i).getExamen().getNombreExamen());
            m.put("fechageneracion", listaCitaExamen.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaExamen.get(i).getObservaciones());
            m.put("detalles", listaCitaExamen.get(i).getDetalles());
            js3.add(m);
        }
        result_map.put("examen", js3);
        
        ArrayList<HashMap> js4= new ArrayList<HashMap>();
        
        Query q4 = em.createNativeQuery("select * from cita_tratamiento \n" +
                "where id_cita in (\n" +
                "	select max(id_cita) from persona_natural inner join cita\n" +
                "	on persona_natural.id_persona = cita.id_paciente_eps\n" +
                ")", CitaTratamiento.class);
        List<CitaTratamiento>listaCitaTratamiento = q4.getResultList();
        
        for (int i = 0; i<listaCitaTratamiento.size();i++ ){
            HashMap m = new HashMap();
            m.put("idcita", listaCitaTratamiento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaTratamiento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
            //m.put("idcita", listaCitaTratamiento.get(i).getCita().getIdCita());
            m.put("idtratamiento", listaCitaTratamiento.get(i).getTratamiento().getNombreTratamiento());
            m.put("fechageneracion", listaCitaTratamiento.get(i).getFechaGeneracion());
            m.put("observaciones", listaCitaTratamiento.get(i).getObservaciones());
            js4.add(m);
        }
        result_map.put("tratamiento", js4);
        result_js.add(result_map);
        return result_js;
    }
    
    @Override
    public ArrayList<HashMap> findWithFilter(String idcita,String medico,String fechainicio, String fechafin) {
        HashMap result_map = new HashMap();
        ArrayList<HashMap> result_js= new ArrayList<HashMap>();
        
        
        
        String where = " ";
        if((fechainicio.equals("") == false) && fechainicio != null){
            if (where.equals(" ")){
                where = where + " WHERE cm.fechaGenracion >'" + fechainicio+ "' ";
            }
            else {
                where = where + " AND cm.fechaGenracion >'" + fechainicio+ "' ";
            }
        }
        
        if((fechafin.equals("") == false) && fechafin != null){
            if (where.equals(" ")){
                where = where + " WHERE cm.fechaGenracion <'" + fechafin+"' ";
            }
            else {
                where = where + " AND cm.fechaGenracion <'" + fechafin+"' ";
            }
        }
        
        if((medico.equals("") == false) && medico != null){
            if (where.equals(" ")){
                where = where + " WHERE cm.cita.agenda.medicoEps.idPersonaEps =" + medico;
            }
            else {
                where = where + " AND cm.cita.agenda.medicoEps.idPersonaEps =" + medico;
            }
        }
        
        
        
        
        Query q = em.createQuery("SELECT cm FROM CitaMedicamento cm "+where+" order by cm.fechaGenracion");                                  

        
        
        List<CitaMedicamento>listaCitaMedicamento = q.getResultList();

        ArrayList<HashMap> js= new ArrayList<HashMap>();
        Long id_cita_mem = null;
        for (int i = 0; i<listaCitaMedicamento.size();i++ ){
            HashMap m = new HashMap();
            if (i == 0){
                id_cita_mem = listaCitaMedicamento.get(i).getCita().getIdCita();
                //m.put("idcita", listaCitaMedicamento.get(i).getCita().getIdCita());
                m.put("idcita", listaCitaMedicamento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaMedicamento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
                m.put("idmedicamento", listaCitaMedicamento.get(i).getMedicamento().getNombreMedicamento());
                m.put("fechageneracion", listaCitaMedicamento.get(i).getFechaGenracion());
                m.put("formula", listaCitaMedicamento.get(i).getFormula());
                js.add(m);
            }else{
                if (id_cita_mem != listaCitaMedicamento.get(i).getCita().getIdCita()){
                    HashMap mn = new HashMap();
                    mn.put("idcita", "");
                    mn.put("idmedicamento", "");
                    mn.put("fechageneracion", "");
                    mn.put("formula", "");
                    js.add(mn);
                    id_cita_mem = listaCitaMedicamento.get(i).getCita().getIdCita();
                }
                m.put("idcita", listaCitaMedicamento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaMedicamento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
                m.put("idmedicamento", listaCitaMedicamento.get(i).getMedicamento().getNombreMedicamento());
                m.put("fechageneracion", listaCitaMedicamento.get(i).getFechaGenracion());
                m.put("formula", listaCitaMedicamento.get(i).getFormula());
                js.add(m);
            }
        }
        result_map.put("medicamentos", js);
        
        
        
        
        where = " ";
        if((fechainicio.equals("") == false) && fechainicio != null){
            if (where.equals(" ")){
                where = where + " WHERE cc.fechaGeneracion >'" + fechainicio+ "' ";
            }
            else {
                where = where + " AND cc.fechaGeneracion >'" + fechainicio+ "' ";
            }
        }
        
        if((fechafin.equals("") == false) && fechafin != null){
            if (where.equals(" ")){
                where = where + " WHERE cc.fechaGeneracion <'" + fechafin+"' ";
            }
            else {
                where = where + " AND cc.fechaGeneracion <'" + fechafin+"' ";
            }
        }
        
        if((medico.equals("") == false) && medico != null){
            if (where.equals(" ")){
                where = where + " WHERE cc.cita.agenda.medicoEps.idPersonaEps =" + medico;
            }
            else {
                where = where + " AND cc.cita.agenda.medicoEps.idPersonaEps =" + medico;
            }
        }
        
        
        ArrayList<HashMap> js2= new ArrayList<HashMap>();
        
        Query q2 = em.createQuery("SELECT cc FROM CitaCirugia cc "+ where+" order by cc.fechaGeneracion");
        List<CitaCirugia>listaCitaCirugia = q2.getResultList();
        id_cita_mem = null;
        for (int i = 0; i<listaCitaCirugia.size();i++ ){
            HashMap m = new HashMap();
            if (i == 0){
                id_cita_mem = listaCitaMedicamento.get(i).getCita().getIdCita();
                //m.put("idcita", listaCitaCirugia.get(i).getCita().getIdCita());
                m.put("idcita", listaCitaCirugia.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaCirugia.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
                m.put("idcirugia", listaCitaCirugia.get(i).getCirugia().getNombreCirugia());
                m.put("fechageneracion", listaCitaCirugia.get(i).getFechaGeneracion());
                m.put("observaciones", listaCitaCirugia.get(i).getObservaciones());
                m.put("detalles", listaCitaCirugia.get(i).getDetalles());
                js2.add(m);
            }
            else{
                if (id_cita_mem != listaCitaMedicamento.get(i).getCita().getIdCita()){
                    HashMap mn = new HashMap();
                    mn.put("idcita", "");
                    mn.put("idcirugia", "");
                    mn.put("fechageneracion", "");
                    mn.put("observaciones", "");
                    mn.put("detalles", "");
                    js2.add(mn);
                    id_cita_mem = listaCitaMedicamento.get(i).getCita().getIdCita();
                }
                m.put("idcita", listaCitaCirugia.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaCirugia.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
                m.put("idcirugia", listaCitaCirugia.get(i).getCirugia().getNombreCirugia());
                m.put("fechageneracion", listaCitaCirugia.get(i).getFechaGeneracion());
                m.put("observaciones", listaCitaCirugia.get(i).getObservaciones());
                m.put("detalles", listaCitaCirugia.get(i).getDetalles());
                js2.add(m);
            }
            
        }
        result_map.put("cirugia", js2);
        
        where = " ";
        if((fechainicio.equals("") == false) && fechainicio != null){
            if (where.equals(" ")){
                where = where + " WHERE ce.fechaGeneracion >'" + fechainicio+ "' ";
            }
            else {
                where = where + " AND ce.fechaGeneracion >'" + fechainicio+ "' ";
            }
        }
        
        if((fechafin.equals("") == false) && fechafin != null){
            if (where.equals(" ")){
                where = where + " WHERE ce.fechaGeneracion <'" + fechafin+"' ";
            }
            else {
                where = where + " AND ce.fechaGeneracion <'" + fechafin+"' ";
            }
        }
        
        if((medico.equals("") == false) && medico != null){
            if (where.equals(" ")){
                where = where + " WHERE ce.cita.agenda.medicoEps.idPersonaEps =" + medico;
            }
            else {
                where = where + " AND ce.cita.agenda.medicoEps.idPersonaEps =" + medico;
            }
        }
        
        ArrayList<HashMap> js3= new ArrayList<HashMap>();
        
        Query q3 = em.createQuery("SELECT ce FROM CitaExamen ce "+where+" order by ce.fechaGeneracion");
        List<CitaExamen>listaCitaExamen = q3.getResultList();
        id_cita_mem = null;
        for (int i = 0; i<listaCitaExamen.size();i++ ){
            HashMap m = new HashMap();
            if (i == 0){
                id_cita_mem = listaCitaMedicamento.get(i).getCita().getIdCita();
                //m.put("idcita", listaCitaExamen.get(i).getCita().getIdCita());
                m.put("idcita", listaCitaExamen.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaExamen.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
                m.put("idexamen", listaCitaExamen.get(i).getExamen().getNombreExamen());
                m.put("fechageneracion", listaCitaExamen.get(i).getFechaGeneracion());
                m.put("observaciones", listaCitaExamen.get(i).getObservaciones());
                m.put("detalles", listaCitaExamen.get(i).getDetalles());
                js3.add(m);
            }
            else{
                if (id_cita_mem != listaCitaMedicamento.get(i).getCita().getIdCita()){
                    HashMap mn = new HashMap();
                    mn.put("idcita", "");
                    mn.put("idexamen", "");
                    mn.put("fechageneracion", "");
                    mn.put("observaciones", "");
                    mn.put("detalles", "");
                    js3.add(mn);
                    id_cita_mem = listaCitaMedicamento.get(i).getCita().getIdCita();
                }
                m.put("idcita", listaCitaExamen.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaExamen.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
                m.put("idexamen", listaCitaExamen.get(i).getExamen().getNombreExamen());
                m.put("fechageneracion", listaCitaExamen.get(i).getFechaGeneracion());
                m.put("observaciones", listaCitaExamen.get(i).getObservaciones());
                m.put("detalles", listaCitaExamen.get(i).getDetalles());
                js3.add(m);
            }
        }
        result_map.put("examen", js3);
        
        where = " ";
        if((fechainicio.equals("") == false) && fechainicio != null){
            if (where.equals(" ")){
                where = where + " WHERE ct.fechaGeneracion >'" + fechainicio+ "' ";
            }
            else {
                where = where + " AND ct.fechaGeneracion >'" + fechainicio+ "' ";
            }
        }
        
        if((fechafin.equals("") == false) && fechafin != null){
            if (where.equals(" ")){
                where = where + " WHERE ct.fechaGeneracion <'" + fechafin+"' ";
            }
            else {
                where = where + " AND ct.fechaGeneracion <'" + fechafin+"' ";
            }
        }
        
        if((medico.equals("") == false) && medico != null){
            if (where.equals(" ")){
                where = where + " WHERE ct.cita.agenda.medicoEps.idPersonaEps =" + medico;
            }
            else {
                where = where + " AND ct.cita.agenda.medicoEps.idPersonaEps =" + medico;
            }
        }
        
        ArrayList<HashMap> js4= new ArrayList<HashMap>();
        
        Query q4 = em.createQuery("SELECT ct FROM CitaTratamiento ct "+where+" order by ct.fechaGeneracion");
        List<CitaTratamiento>listaCitaTratamiento = q4.getResultList();
        id_cita_mem = null;
        for (int i = 0; i<listaCitaTratamiento.size();i++ ){
            HashMap m = new HashMap();
            if (i == 0){
                id_cita_mem = listaCitaMedicamento.get(i).getCita().getIdCita();
                //m.put("idcita", listaCitaTratamiento.get(i).getCita().getIdCita());
                m.put("idcita", listaCitaTratamiento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaTratamiento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
                m.put("idtratamiento", listaCitaTratamiento.get(i).getTratamiento().getNombreTratamiento());
                m.put("fechageneracion", listaCitaTratamiento.get(i).getFechaGeneracion());
                m.put("observaciones", listaCitaTratamiento.get(i).getObservaciones());
                js4.add(m);
            }
            else{
                if (id_cita_mem != listaCitaMedicamento.get(i).getCita().getIdCita()){
                    HashMap mn = new HashMap();
                    mn.put("idcita", "");
                    mn.put("idtratamiento", "");
                    mn.put("fechageneracion", "");
                    mn.put("observaciones", "");
                    js4.add(mn);
                    id_cita_mem = listaCitaMedicamento.get(i).getCita().getIdCita();
                }
                m.put("idcita", listaCitaTratamiento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaCitaTratamiento.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
                m.put("idtratamiento", listaCitaTratamiento.get(i).getTratamiento().getNombreTratamiento());
                m.put("fechageneracion", listaCitaTratamiento.get(i).getFechaGeneracion());
                m.put("observaciones", listaCitaTratamiento.get(i).getObservaciones());
                js4.add(m);
            }
        }
        result_map.put("tratamiento", js4);
        
        ArrayList<HashMap> js5= new ArrayList<HashMap>();
        
        Query q5 = em.createQuery("SELECT i FROM Incapacidad i WHERE i.cita.idCita="+idcita+" order by i.fechaGeneracion");
        List<Incapacidad>listaIncapacidad = q5.getResultList();
        id_cita_mem = null;
        for (int i = 0; i<listaIncapacidad.size();i++ ){
            HashMap m = new HashMap();
            if (i == 0){
                id_cita_mem = listaCitaMedicamento.get(i).getCita().getIdCita();
                //m.put("idcita", listaIncapacidad.get(i).getCita().getIdCita());
                m.put("idcita", listaIncapacidad.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaIncapacidad.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
                m.put("fechageneracion", listaIncapacidad.get(i).getFechaGeneracion());
                m.put("motivo", listaIncapacidad.get(i).getMotivo());
                m.put("periodo", listaIncapacidad.get(i).getPeriodo());
                js5.add(m);
            }
            else{
                if (id_cita_mem != listaCitaMedicamento.get(i).getCita().getIdCita()){
                    HashMap mn = new HashMap();
                    mn.put("idcita", "");
                    mn.put("fechageneracion", "");
                    mn.put("motivo", "");
                    mn.put("periodo", "");
                    js5.add(mn);
                    id_cita_mem = listaCitaMedicamento.get(i).getCita().getIdCita();
                }
                m.put("idcita", listaIncapacidad.get(i).getCita().getAgenda().getMedicoEps().getPersona().getNombres() + " "+listaIncapacidad.get(i).getCita().getAgenda().getMedicoEps().getPersona().getApellidos());
                m.put("fechageneracion", listaIncapacidad.get(i).getFechaGeneracion());
                m.put("motivo", listaIncapacidad.get(i).getMotivo());
                m.put("periodo", listaIncapacidad.get(i).getPeriodo());
                js5.add(m);
            }
        }
        result_map.put("incapacidad", js5);
        
        result_js.add(result_map);
        return result_js;
    }
    
}