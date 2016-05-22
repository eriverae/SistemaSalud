/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.sisc_hc.shared;

import com.acme.sisc.agenda.entidades.Cirugia;
import com.acme.sisc.agenda.entidades.CitaCirugia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author GABRIEL
 */
@Local
public interface ICirugiaFacadeLocal {
    Cirugia find(Object id);
    List<Cirugia> findAll();   
    public void addCirugiaCita(List<CitaCirugia> cita_cirugia);
    ArrayList<HashMap> findByCita(Long idcita);
    CitaCirugia findByCita_Ciru(Long idcita, Long idcirugia);
}
