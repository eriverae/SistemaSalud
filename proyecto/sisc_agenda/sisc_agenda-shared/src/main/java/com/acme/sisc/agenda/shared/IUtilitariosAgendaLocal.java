/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.shared;

import com.acme.sisc.agenda.entidades.Especialidad;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author BryanCFz-user
 */

@Local
public interface IUtilitariosAgendaLocal {
    
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<Especialidad> especialidadesMedicosEps();
    
}
