/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.sisc_hc.shared;

import com.acme.sisc.agenda.entidades.Incapacidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author GABRIEL
 */
@Local
public interface IIncapacidadFacadeLocal {
    Incapacidad find(Object id);
    List<Incapacidad> findAll();
    void addIncapacidad(Object incapacidad);
}
