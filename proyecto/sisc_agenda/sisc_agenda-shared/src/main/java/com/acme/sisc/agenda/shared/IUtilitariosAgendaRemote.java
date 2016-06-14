/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.shared;

import com.acme.sisc.agenda.entidades.Especialidad;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.agenda.entidades.PersonaNaturalEspecialidad;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author BryanCFz-user
 */

@Remote
public interface IUtilitariosAgendaRemote {
    
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<Especialidad> especialidadesEps();
    
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<PersonaNaturalEspecialidad> listaEspecialidadMedicosEps(String especialidad, String idEps);
    
    public PersonaNatural consultaPersonaNatural(String email);
    
}
