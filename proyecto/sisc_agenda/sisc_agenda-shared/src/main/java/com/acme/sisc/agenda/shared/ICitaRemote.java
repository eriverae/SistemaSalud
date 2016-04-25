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

/**
 *
 * @author BryanCFz-user
 */

@Local
public interface ICitaRemote {
    
    public List<Cita> listaCitasPaciente(long idPaciente);
    
    public void cancelarCita_porPaciente(Cita cita);
       
    public Cita find(Long id);

    
    
    
    //void crearCita(Cita cita) throws CitaException;

    void remove(Long id);

    void remove(Cita entity);

}