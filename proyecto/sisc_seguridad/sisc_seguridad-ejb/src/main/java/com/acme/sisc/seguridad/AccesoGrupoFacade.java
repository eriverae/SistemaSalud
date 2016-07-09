/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Acceso;
import com.acme.sisc.agenda.entidades.AccesoGrupo;
import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
* Clase AccesoGrupoFacade, es la fachada para las consultas, creación, modificación y 
* eliminacion de la relacion de perfiles y accesos
* 
* @author  Julio 
* @version 1.0
* @since   2016-05-23
*/
@Stateless
public class AccesoGrupoFacade implements AccesoGrupoFacadeRemote, AccesoGrupoFacadeLocal {

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(AccesoGrupoFacade.class.getName());

    /**
    * Metodo crearAccesoGrupo, crea la relacion en la tabla de perfiles accesos con el objeto que llega del servicio, accediento a la entidad
    * @param AccesoGrupo accesoGrupo es el objeto a crear y persistir en la BD
    */
    @Override
    public void crearAccesoGrupo(AccesoGrupo accesoGrupo) throws SeguridadException {
        LOGGER.info("Inicia Acceso(...)" + accesoGrupo.getAcceso().getAcceAcce() + " - " + accesoGrupo.getGrupo().getGrupGrup());

        AccesoGrupo a = findByAcceGrup(accesoGrupo.getAcceso().getAcceAcce(), accesoGrupo.getGrupo().getGrupGrup());
        if (a != null) {
            LOGGER.log(Level.WARNING, "AccesoGrupo {0}-{1} ya existe !!", new Object[]{accesoGrupo.getAcceso().getAcceAcce(), accesoGrupo.getGrupo().getGrupGrup()});
            throw new SeguridadException("El AccesoGrupo " + accesoGrupo.getAcceso().getAcceAcce() + "-" + accesoGrupo.getGrupo().getGrupGrup() + " ya existe en el sistema");
        }

        em.persist(accesoGrupo);
    }

    /**
    * Metodo findByAcceGrup, devuelve un objeto de tipo AccesoGrupo consultado a partir de los parametros de entrada
    * @param Long acceso es el id de la tabla Acceso
    * @param Long grupo es el id de la tabla Grupo
    * @return retorna AccesoGrupo, el el objeto encontrado a partir del metodo de la busqueda
    */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public AccesoGrupo findByAcceGrup(Long acceso, Long grupo) {
        LOGGER.log(Level.FINE, "Consulta AccesoGrupo {0}-{1}", new Object[]{acceso, grupo});
        Query q = em.createNamedQuery("Accgrup.findAccesoGrupo");
        q.setParameter("acceAcce", acceso);
        q.setParameter("grupGrup", grupo);
        try {
            return (AccesoGrupo) q.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "No se encontro AccesoGrupo {0}-{1}", new Object[]{acceso, grupo});
            return null;
        }
    }

    /**
    * Metodo modificarGrupoAcceso, devuelve un objeto de tipo AccesoGrupo una vez que se modifican sus atributos
    * @param AccesoGrupo accesoGrupo es el objeto a modififcar
    * @return retorna AccesoGrupo, el el objeto una vez modificado 
    */
    @Override
    public AccesoGrupo modificarGrupoAcceso(AccesoGrupo accesoGrupo) {
        LOGGER.log(Level.FINE, "Modificando AccesoGrupo {0}", new Object[]{accesoGrupo.getIdAccgrup()});
        accesoGrupo = em.merge(accesoGrupo);
        return accesoGrupo;
    }

    /**
    * Metodo findAll, retorna una lista de obbjetos de tipo AccesoGrupo
    * @return java.util.List<AccesoGrupo>, todos los accesos con la relacion de sus perfiles existentes en la BD
    */
    @Override
    public java.util.List<com.acme.sisc.agenda.entidades.AccesoGrupo> findAll() {
        Query q = em.createNamedQuery("Accgrup.findAll");
        return q.getResultList();
    }

    /**
    * Metodo remove, elimina el objeto con el metodo remove 
    * @param AccesoGrupo accesoGrupo es el objeto a eliminar en la BD
    */
    @Override
    public void remove(AccesoGrupo accesoGrupo) {
        em.remove(accesoGrupo);
    }

    /**
    * Metodo remove, elimina el objeto con el metodo remove, antes de ello crea un objeto a partir del id 
    * @param Long accesoGrupo id del AccesoGrupo a eliminar
    */
    @Override
    public void remove(long accesoGrupo) {

        LOGGER.log(Level.FINE, "Eliminar accesoGrupo con id {0}", accesoGrupo);
        AccesoGrupo acc = this.find(accesoGrupo);
        if (acc != null) {
            remove(acc);
            LOGGER.log(Level.INFO, "Acceso eliminado correctamente");
        } else {
            LOGGER.log(Level.INFO, "Acceso con id {} no existe", accesoGrupo);
        }
    }

    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return AccesoGrupo es el objeto que trae a partir del metodo find
    */
    @Override
    public AccesoGrupo find(Object id) {
        return em.find(AccesoGrupo.class, id);
    }

    /**
    * Metodo count, devuelve el conteo de objetos en referencia
    * @return int con el numero de objetos en referencia
    */
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<AccesoGrupo> rt = cq.from(AccesoGrupo.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    /**
    * Metodo getEntityManager, es el punto de acceso para persistir las entidades desde la BD
    * @return EntityManager em, retorna el objeto de EntityManager
    */
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<AccesoGrupo> la lista de objetos en referencia
    */
    @Override
    public java.util.List<com.acme.sisc.agenda.entidades.AccesoGrupo> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(AccesoGrupo.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }

    /**
    * Metodo findByGrupGrup, devuelve un objeto de tipo Acceso consultado a partir del parametro de entrada
    * @param Long grupGrup es el id del perfil a consultar los accesos
    * @return retorna java.util.List<Acceso>, son una lista de accesos del perfil
    */
    @Override
    public java.util.List<Acceso> findByGrupGrup(Long grupGrup) {
        LOGGER.log(Level.FINE, "Consulta findByGrupGrup {0}", new Object[]{grupGrup});
        Query q = em.createNamedQuery("Acceso.findByGrupGrup");
        q.setParameter("grupGrup", grupGrup);
        return q.getResultList();
    }
    
    /**
    * Metodo actualizaAccesoGrupo, crea y elimiana las relaciones de acceso y perfiles acorde a estado
    * @param Grupo grupGrup es el objeto de los perfiles
    * @param Acceso acceAcce es el objeto de los accesos
    * @param Boolean estado si llega true crea la relacion sino la elimina
    */
    @Override
    public void actualizaAccesoGrupo(Grupo grupGrup, Acceso acceAcce, Boolean estado) {
        if (estado == true){
            AccesoGrupo a = new AccesoGrupo();
            a.setGrupo(grupGrup);
            a.setAcceso(acceAcce);
            em.persist(a);
        }else{
//            AccesoGrupo a = findByAcceGrup(acceAcce.getAcceAcce(), grupGrup.getGrupGrup());
//            em.remove(a);
        Query q = em.createNativeQuery("delete from accgrup where acce_acce = ? and grup_grup = ?");
        q.setParameter(1, acceAcce.getAcceAcce());
        q.setParameter(2, grupGrup.getGrupGrup());
        q.executeUpdate();
        }
    }
    
    
}
