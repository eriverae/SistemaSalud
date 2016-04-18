/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb;

import com.acme.sisc.agenda.entidades.Cita;
import com.acme.sisc.agenda.shared.ICitaLocal;
import com.acme.sisc.agenda.shared.ICitaRemote;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author BryanCFz-user
 */

@Stateless
public class SessionBeanCitaPaciente implements ICitaLocal, ICitaRemote{

    @Override
    public List<Cita> listaCitasPaciente(long idPaciente) {
        
        
        
        
        return null;
    }
    
}
