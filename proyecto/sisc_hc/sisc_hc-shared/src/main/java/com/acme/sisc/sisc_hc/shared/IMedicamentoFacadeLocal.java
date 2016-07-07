/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.sisc_hc.shared;

import com.acme.sisc.agenda.entidades.CitaMedicamento;
import com.acme.sisc.agenda.entidades.Medicamento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import org.json.*;

/**
 *
 * @author GABRIEL
 */
@Local
public interface IMedicamentoFacadeLocal {
    Medicamento find(Object id);
    List<Medicamento> findAll();
    HashMap addMedicamentoCita(List<CitaMedicamento> listaMedicamentos);
    ArrayList<HashMap> findByCita(Long idcita);
    HashMap deleteMedicamentoCita(Long idcita, Long idmedicamento);
}
