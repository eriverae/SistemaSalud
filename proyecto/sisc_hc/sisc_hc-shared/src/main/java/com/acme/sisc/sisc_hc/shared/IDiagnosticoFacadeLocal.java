/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.sisc_hc.shared;

import com.acme.sisc.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author TOSHIBA
 */
public interface IDiagnosticoFacadeLocal {
    public void addDiagnosticoCita(Long id_cita , String diagnostico);
    ArrayList<HashMap> findByCita(Long idcita);
}
