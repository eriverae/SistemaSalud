/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.sisc_hc.shared;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Remote;

/**
 *
 * @author GABRIEL
 */
@Remote
public interface IHistoriaFacadeRemote {
    ArrayList<HashMap> findAll();
    ArrayList<HashMap> findByCita(Long idcita);
    ArrayList<HashMap> find_last_cita();
}
