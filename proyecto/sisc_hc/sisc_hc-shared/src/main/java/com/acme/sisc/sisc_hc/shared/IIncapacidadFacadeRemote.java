/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.sisc_hc.shared;

import com.acme.sisc.agenda.entidades.Incapacidad;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author GABRIEL
 */
@Remote
public interface IIncapacidadFacadeRemote {
    Incapacidad find(Object id);
    List<Incapacidad> findAll();
    void addIncapacidad(Long cita , String motivo, String periodo);
    ArrayList<HashMap> findByCita(Long idcita);
    ArrayList<HashMap> isIncapacitado(String idpaciente);
}
