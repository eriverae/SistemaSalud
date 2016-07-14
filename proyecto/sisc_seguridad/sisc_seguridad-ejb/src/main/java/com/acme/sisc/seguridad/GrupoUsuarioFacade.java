/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Grupo;
import com.acme.sisc.agenda.entidades.GrupoUsuario;
import com.acme.sisc.agenda.entidades.Usuario;
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
* Clase GrupoUsuarioFacade, es la fachada para las consultas, creación, modificación y 
* eliminacion de la relacion de perfiles y usuarios
* 
* @author  Julio 
* @version 1.0
* @since   2016-05-23
*/
@Stateless
public class GrupoUsuarioFacade implements GrupoUsuarioFacadeRemote, GrupoUsuarioFacadeLocal {

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(GrupoUsuarioFacade.class.getName());

    /**
    * Metodo crearGrupoUsuario, crea la relacion en la tabla de perfiles y usuarios con el objeto que llega del servicio, accediento a la entidad
    * @param GrupoUsuario grupoUsuario es el objeto a crear y persistir en la BD
    */
    @Override
    public void crearGrupoUsuario(GrupoUsuario grupoUsuario) throws SeguridadException {
        LOGGER.info("Inicia crearGrupoUsuario(...)" + grupoUsuario.getUsuario().getUsuaUsua() + " - " + grupoUsuario.getGrupo().getGrupGrup());

        GrupoUsuario a = findByGrupUsua(grupoUsuario.getUsuario().getUsuaUsua(), grupoUsuario.getGrupo().getGrupGrup());
        if (a != null) {
            LOGGER.log(Level.WARNING, "GrupoUsuario {0}-{1} ya existe !!", new Object[]{grupoUsuario.getUsuario().getUsuaUsua(), grupoUsuario.getGrupo().getGrupGrup()});
            throw new SeguridadException("El GrupoUsuario " + grupoUsuario.getUsuario().getUsuaUsua() + " - " + grupoUsuario.getGrupo().getGrupGrup() + " ya existe en el sistema");
        }

        em.persist(grupoUsuario);
    }
    
    /**
    * Metodo findByGrupUsua, devuelve un objeto de tipo GrupoUsuario consultado a partir de los parametros de entrada
    * @param Long usuario es el id de la tabla Usuario
    * @param Long grupo es el id de la tabla Grupo
    * @return retorna GrupoUsuario, el el objeto encontrado a partir del metodo de la busqueda
    */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public GrupoUsuario findByGrupUsua(Long usuario, Long grupo) {
        LOGGER.log(Level.FINE, "Consulta GrupoUsuario {0}-{1}", new Object[]{usuario, grupo});
        Query q = em.createNamedQuery("Grupusu.findGrupoUsuario");
        q.setParameter("usuaUsua", usuario);
        q.setParameter("grupGrup", grupo);
        try {
            return (GrupoUsuario) q.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "No se encontro GrupoUsuario {0}-{1}", new Object[]{usuario, grupo});
            return null;
        }
    }
    
    /**
    * Metodo modificarGrupoUsuario, devuelve un objeto de tipo GrupoUsuario una vez que se modifican sus atributos
    * @param GrupoUsuario grupoUsuario es el objeto a modififcar
    * @return retorna GrupoUsuario, el el objeto una vez modificado 
    */
    @Override
    public GrupoUsuario modificarGrupoUsuario(GrupoUsuario grupoUsuario) {
        LOGGER.log(Level.FINE, "Modificando GrupoUsuario {0}", new Object[]{grupoUsuario.getIdGrupusu()});
        grupoUsuario = em.merge(grupoUsuario);
        return grupoUsuario;
    }

    /**
    * Metodo findAll, retorna una lista de objetos de tipo GrupoUsuario
    * @return java.util.List<GrupoUsuario>, todos los perfiles con la relacion del usuario existentes en la BD
    */
    @Override
    public java.util.List<com.acme.sisc.agenda.entidades.GrupoUsuario> findAll() {
        Query q = em.createNamedQuery("Grupusu.findAll");
        return q.getResultList();
    }

    /**
    * Metodo remove, elimina el objeto con el metodo remove 
    * @param GrupoUsuario grupoUsuario es el objeto a eliminar en la BD
    */
    @Override
    public void remove(GrupoUsuario grupoUsuario) {
        em.remove(grupoUsuario);
    }
    
    /**
    * Metodo remove, elimina el objeto con el metodo remove, antes de ello crea un objeto a partir del id 
    * @param Long grupoUsuario id del GrupoUsuario a eliminar
    */
    @Override
    public void remove(long grupoUsuario) {

        LOGGER.log(Level.FINE, "Eliminar GrupoUsuario con id {0}", grupoUsuario);
        GrupoUsuario gu = this.find(grupoUsuario);
        if (gu != null) {
            remove(gu);
            LOGGER.log(Level.INFO, "GrupoUsuario eliminado correctamente");
        } else {
            LOGGER.log(Level.INFO, "GrupoUsuario con id {} no existe", grupoUsuario);
        }
    }

    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return GrupoUsuario es el objeto que trae a partir del metodo find
    */
    @Override
    public GrupoUsuario find(Object id) {
        return em.find(GrupoUsuario.class, id);
    }

    /**
    * Metodo count, devuelve el conteo de objetos en referencia
    * @return int con el numero de objetos en referencia
    */
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<GrupoUsuario> rt = cq.from(GrupoUsuario.class);
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
    * @return java.util.List<GrupoUsuario> la lista de objetos en referencia
    */
    @Override
    public java.util.List<com.acme.sisc.agenda.entidades.GrupoUsuario> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(GrupoUsuario.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }

    /**
    * Metodo findByUsuaUsua, devuelve un objeto de tipo Grupo consultado a partir del parametro de entrada
    * @param Long usuaUsua es el id del usuario a consultar los perfiles
    * @return retorna java.util.List<Grupo>, son una lista de los perfiles del usuario
    */
    @Override
    public java.util.List<Grupo> findByUsuaUsua(Long usuaUsua) {
        LOGGER.log(Level.FINE, "Consulta findByUsuaUsua {0}", new Object[]{usuaUsua});
        Query q = em.createNamedQuery("Grupo.findByUsuaUsua");
        q.setParameter("usuaUsua", usuaUsua);
        return q.getResultList();
    }

    /**
    * Metodo actualizaGrupoUsuario, crea y elimiana las relaciones de usuario y perfiles acorde a estado
    * @param Grupo grupGrup es el objeto de los perfiles
    * @param Usuario usuaUsua es el objeto de los usuarios
    * @param Boolean estado si llega true crea la relacion sino la elimina
    */
    @Override
    public void actualizaGrupoUsuario(Usuario usuaUsua, Grupo grupgrup, Boolean estado) {
        if (estado == true){
            GrupoUsuario a = new GrupoUsuario();
            a.setGrupo(grupgrup);
            a.setUsuario(usuaUsua);
            em.persist(a);
        }else{
//            GrupoUsuario a = findByGrupUsua(usuaUsua.getUsuaUsua(), grupgrup.getGrupGrup());
//            em.remove(a);
        Query q = em.createNativeQuery("delete from grupusu where usua_usua = ? and grup_grup = ?");
        q.setParameter(1, usuaUsua.getUsuaUsua());
        q.setParameter(2, grupgrup.getGrupGrup());
        q.executeUpdate();
        }
    }
}
