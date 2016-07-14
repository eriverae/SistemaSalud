/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Usuario;
import com.acme.sisc.common.util.JMSUtil;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Order;

/**
* Clase UsuarioFacade, es clase implementada para la colda de mensajerias
* 
* @author  Julio
* @version 1.0
* @since   2016-05-28
*/
@Stateless
public class UsuarioFacade implements UsuarioFacadeRemote, UsuarioFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(UsuarioFacade.class.getName());

    /**
    * Metodo getEntityManager, es el punto de acceso para persistir las entidades desde la BD
    * @return EntityManager em, retorna el objeto de EntityManager
    */
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
    * Metodo crearUsuario, crea el usuario con el objeto que llega del servicio, accediento a la entidad
    * @param Usuario usuario es el objeto a crear y persistir en la BD
    * @return Usuario 
    */
    @Override
    public Usuario crearUsuario(Usuario usuario) throws SeguridadException {
        LOGGER.info("Inicia Usuario(...)");

        Usuario u = findByEmail(usuario.getUsuaEmail());
        if (u != null) {
            LOGGER.warning("Usuario " + usuario.getUsuaEmail() + " ya existe !!");
            throw new SeguridadException("El usuario " + usuario.getUsuaEmail() + " ya existe en el sistema");
        }

        String contrasenaEncriptada = "";

        try {
            contrasenaEncriptada = encriptar(usuario.getUsuaPass());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        usuario.setUsuaPass(contrasenaEncriptada);
        usuario.setUsuaBlock(false);
        usuario.setUsuaConta(0);
        usuario.setUsuaEsta("Activo");
        usuario.setUsuaUsucd(new Date());
        usuario.setUsuaUsucs("Administrador");
        usuario.setUsuaUsumd(new Date());
        usuario.setUsuaUsums("El usuario mismo");
        em.persist(usuario);

        JMSUtil.sendMessage(usuario, "java:/jms/queue/SiscQueue");
        LOGGER.info("Finaliza crearUsuario(...)");

        em.refresh(usuario);
        return usuario;
    }

    /**
    * Metodo findByEmail, devuelve un objeto de tipo Usuario consultado a partir del parametro de entrada
    * @param email String es el correo del usuario, el cual va a buscar
    * @return retorna Usuario, el el objeto encontrado a partir del metodo de la busqueda
    */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Usuario findByEmail(String email) {
        LOGGER.log(Level.FINE, "Consulta cliente {0}", email);
        Query q = em.createNamedQuery("Usuario.findByEmail");
        q.setParameter("email", email);
        try {
            return (Usuario) q.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, "No se encontro usuario {0}", email);
            return null;
        }
    }

    /**
    * Metodo encriptar, encripta un String en este caso la contraseña con el algoritmo MD5
    * @param password String es el password del usuario a encriptar
    * @return String con el password encriptado 
    */
    public String encriptar(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    /**
    * Metodo modificarUsuario, devuelve un objeto de tipo Usuario una vez que se modifican sus atributos
    * @param Usuario usuario es el objeto a modififcar
    * @return retorna Usuario, el el objeto una vez modificado 
    */
    @Override
    public Usuario modificarUsuario(Usuario usuario) {
        LOGGER.log(Level.FINE, "Modificando usuario con nombre : {0} - Version: ", new Object[]{usuario.getUsuaEmail()});
        String contrasenaEncriptada = "";

        try {
            contrasenaEncriptada = encriptar(usuario.getUsuaPass());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        usuario.setUsuaPass(contrasenaEncriptada);
        usuario = em.merge(usuario);
        return usuario;
    }

    /**
    * Metodo findAll, retorna una lista de objetos de tipo Usuario
    * @return java.util.List<Usuario>, todos los grupos existentes en la BD
    */
    @Override
    public java.util.List<Usuario> findAll() {
        Query q = em.createNamedQuery("Usuario.findAll");
        return q.getResultList();
    }

    /**
    * Metodo remove, elimina el objeto con el metodo remove 
    * @param Usuario usuario es el objeto a eliminar en la BD
    */
    @Override
    public void remove(Usuario usuario) {
        em.remove(usuario);
    }

    /**
    * Metodo remove, elimina el objeto con el metodo remove, antes de ello crea un objeto a partir del id 
    * @param Long usuario id del Usuario a eliminar
    */
    @Override
    public void remove(Long usuario) {
        LOGGER.log(Level.FINE, "Eliminar usuario con id {0}", usuario);
        Usuario usu = this.find(usuario);
        if (usu != null) {
            usu.setUsuaEsta("Inactivo");
            em.merge(usu);
            //remove(usu);
            LOGGER.log(Level.INFO, "Usuario eliminado correctamente");
        } else {
            LOGGER.log(Level.INFO, "Usuario con id {} no existe", usuario);
        }
    }

    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return Usuario es el objeto que trae a partir del metodo find
    */
    @Override
    public Usuario find(Object id) {
        return em.find(Usuario.class, id);
    }

    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<Usuario> la lista de objetos en referencia
    */
    @Override
    public java.util.List<Usuario> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        Query q = em.createNamedQuery("Usuario.findAllOrderByEstado");
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(Usuario.class));
//        javax.persistence.Query q = em.createQuery(cq);
//        javax.persistence.Query q = em.createNativeQuery("Select * from Usuario where Usuario.usua_esta = 'Activo'");
//        q.setFirstResult(startPosition);
//        q.setMaxResults(maxResults);

        return q.getResultList();
    }

    /**
    * Metodo count, devuelve el conteo de objetos en referencia
    * @return int con el numero de objetos en referencia
    */
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Usuario> rt = cq.from(Usuario.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
    * Metodo autenticar, consulta que el usuario y contraseña existan
    * @param usuario es el mail del usuario
    * @param password es el password del usuario
    * @return boolean, true si es correcta la consulta y false por lo contrario
    */
    @Override
    public boolean autenticar(String usuario, String password) {
        try {
            Query q = em.createNamedQuery("Usuario.findByEmail");
            q.setParameter("email", usuario);
            Usuario u = (Usuario) q.getSingleResult();
            String pwdEncry = encriptar(password);
            return u.getUsuaPass().indexOf(pwdEncry) == 0;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    /**
    * Metodo cambiarContrasena, cambia la contraseña del usuario
    * @param usuario es el mail del usuario
    * @param passOld es el antiguo password del usuario
    * @param passNew es el nuevo password del usuario
    * @return String, "True" si el cambio es exitoso y false por lo contrario
    */
    @Override
    public String cambiarContrasena(Usuario usuario, String passOld, String passNew) {
        LOGGER.info("Inicia cambiarContrasena(...)");
        LOGGER.info(usuario.getUsuaUsua() + " - " + passOld + " - " + passNew);
        String contrasenaEcriptadaNew = "";
        String contrasenaEcriptadaOld = "";
        try {
            contrasenaEcriptadaNew = encriptar(passNew);
            contrasenaEcriptadaOld = encriptar(passOld);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (passOld.equalsIgnoreCase("")) {
            Query q = em.createNativeQuery("UPDATE usuario SET usua_pass = ? where usua_usua = ?");
            q.setParameter(1, contrasenaEcriptadaNew);
            q.setParameter(2, usuario.getUsuaUsua());
            q.executeUpdate();
            return "True";
        } 
        else {
            Query p = em.createNamedQuery("Usuario.findPassword");
            p.setParameter("usuaUsua", usuario.getUsuaUsua());
            String passOldBD = (String) p.getSingleResult();
            
            if (contrasenaEcriptadaOld.equalsIgnoreCase(passOldBD)){
                Query q = em.createNativeQuery("UPDATE usuario SET usua_pass = ? where usua_usua = ?");
                q.setParameter(1, contrasenaEcriptadaNew);
                q.setParameter(2, usuario.getUsuaUsua());
                q.executeUpdate();
                return "True";
            }else{
                return "False";
            }
        }
    }
}
