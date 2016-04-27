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
import javax.ejb.Remote;

/**
 *
 * @author GABRIEL
 */
@Remote
public interface ICirugiaFacadeRemote {
    Cirugia find(Object id);
    List<Cirugia> findAll();   
    public void addCirugiaCita(List<CitaCirugia> cita_cirugia);
    ArrayList<HashMap> findByCita(Long idcita);
    
}
