/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.shared;

import com.acme.sisc.agenda.dto.GeneralResponse;
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
    
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public Cita find(Long id);

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public void agendarCita(Cita cita) throws CitaException;

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<Cita> listaCitasPendientePaciente(long idPaciente);

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public GeneralResponse cancelarCita1(Long idCita);    
    
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<Cita> listaCitasHistorialPacienteEPS(long idPaciente);
    

    /////////////////////////////////////////////////////////////////
    //paginador- historial de citas
    int count();
    
    List<Cita> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);
    ////////////////////////////////////////////////////////////////   

    void remove(Long id);

    void remove(Cita entity);

}