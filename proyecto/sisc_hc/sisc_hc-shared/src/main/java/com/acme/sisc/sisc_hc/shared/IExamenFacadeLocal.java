/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.sisc_hc.shared;

import com.acme.sisc.agenda.entidades.CitaExamen;
import com.acme.sisc.agenda.entidades.Examen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author GABRIEL
 */
@Local
public interface IExamenFacadeLocal {
    Examen find(Object id);
    List<Examen> findAll();   
    public void addExamenCita(List<CitaExamen> cita_examen);
    ArrayList<HashMap> findByCita(Long idcita);
}
