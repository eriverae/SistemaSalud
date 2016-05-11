/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.entidades.Especialidad;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author BryanCFz-user
 */
@Stateless
public class FacadeUtilitariosAgenda extends AbstractFacade<Especialidad> {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeUtilitariosAgenda() {
        super(Especialidad.class);
    }

    @PersistenceContext(unitName = WebConstant.UNIT_NAME_PERSISTENCE)
    private EntityManager em;

    /*    public List<Especialidad> getEspecialidadesList(){
        List<Especialidad> listaEspecialidad = null;
        
        try { 
            log.log(Level.WARNING, "\n\n TRAYENDO ESPECIALIDADES\n");

            Query q = em.createNativeQuery("select distinct on (descripcion) * from especialidad");
            listaEspecialidad = (List<Especialidad>)q.getResultList();
            
            int resultado = q.executeUpdate();
            log.log(Level.INFO, "Cantidad de especialidades encontradas: " + resultado + "\n");
            
            return listaEspecialidad;

        } catch (Exception e) {
            log.log(Level.WARNING, "NO ENCONTRO NINGUNA ESPECIALIDAD, EN LA EPS");
            return listaEspecialidad;

        }
    }*/
    public List<Especialidad> getEspecialidadesList() {
        List<Especialidad> listaEspecialidad;

        try {
            log.log(Level.WARNING, "\n\n TRAYENDO ESPECIALIDADES\n");

//            Query q = em.createNativeQuery("select distinct on (descripcion) * from especialidad");
            Query q = em.createNamedQuery("Especialidad.findAll");
            listaEspecialidad = (List<Especialidad>) q.getResultList();

            return listaEspecialidad;

        } catch (Exception e) {
            log.log(Level.WARNING, "NO ENCONTRO NINGUNA ESPECIALIDAD, EN LA EPS");
            return null;

        }
    }
}
