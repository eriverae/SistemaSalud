/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.shared;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.exceptions.CitaException;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author BryanCFz-user
 */

@Local
public interface ICitaLocal {
    
    public List<Cita> listaCitasPaciente(long idPaciente);
    
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public boolean cancelarCita(Cita cita);
    
    public Cita find(Long id);

    
    
    
    //void crearCita(Cita cita) throws CitaException;

    void remove(Long id);

    void remove(Cita entity);

}