/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.agenda.ejb.facade;

import com.acme.sisc.agenda.constant.WebConstant;
import com.acme.sisc.agenda.entidades.Especialidad;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.agenda.entidades.PersonaNaturalEspecialidad;
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

    
        public List<PersonaNaturalEspecialidad> getMedicosList(String especialidad) {
        List<PersonaNaturalEspecialidad> listaMedicosEspecialidad;

        try {
            log.log(Level.WARNING, "\n\n <FACADE CITA> .. TRAYENDO MEDICOS de la especialidad: " + especialidad + "\n");
            
            //1. obtener el id_especialidad
            Query q = em.createNamedQuery("Especialidad.findByDescripcion");
            q.setParameter("descripcion", especialidad);
            Especialidad especialidadEntidad =  (Especialidad)q.getSingleResult();
            log.log(Level.WARNING, "\n\n id_especialidad: " + especialidadEntidad.getIdEspecialidad() + "\n");
            
            //2. obtener una lista de los medicos que contengan  id_especialidad
            Query q2 = em.createNamedQuery("PersonaNaturalEspecialidad.findAllMedicosEspecialidad");
            q2.setParameter("idEspecialidad", especialidadEntidad.getIdEspecialidad());
            listaMedicosEspecialidad = (List<PersonaNaturalEspecialidad>) q2.getResultList();
            //log.log(Level.WARNING, "\n\n nombre medico[0]: " + listaMedicosEspecialidad.get(0).getMedico().getNombres() + "\n");
            return listaMedicosEspecialidad;

        } catch (Exception e) {
            log.log(Level.WARNING, "NO ENCONTRO NINGUNA ESPECIALIDAD, EN LA EPS");
            return null;

        }
    }
    
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
