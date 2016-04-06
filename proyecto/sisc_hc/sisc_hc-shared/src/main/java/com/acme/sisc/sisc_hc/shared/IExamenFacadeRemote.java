/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.sisc_hc.shared;

import com.acme.sisc.agenda.entidades.Examen;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author GABRIEL
 */
@Remote
public interface IExamenFacadeRemote {
    Examen find(Object id);
    List<Examen> findAll();
}
