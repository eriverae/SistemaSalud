/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.sisc_hc.shared;

import com.acme.sisc.agenda.entidades.CitaTratamiento;
import com.acme.sisc.agenda.entidades.Tratamiento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author GABRIEL
 */
@Remote
public interface ITratamientoFacadeRemote {
    Tratamiento find(Object id);
    List<Tratamiento> findAll();
    void addTratamientoCita(List<CitaTratamiento> listaTratamiento);
    ArrayList<HashMap> findByCita(Long idcita);
}
