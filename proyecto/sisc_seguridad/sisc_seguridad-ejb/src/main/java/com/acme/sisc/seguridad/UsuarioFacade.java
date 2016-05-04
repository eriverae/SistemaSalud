/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Usuario;
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

/**
 *
 * @author rm-rf
 */
@Stateless
public class UsuarioFacade implements UsuarioFacadeRemote, UsuarioFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioFacade.class.getName());
    
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void crearUsuario(Usuario usuario) throws SeguridadException{
        LOGGER.info("Inicia Usuario(...)");
        
        Usuario u = findByEmail(usuario.getUsuaEmail());
        if (u != null){
            LOGGER.warning("Usuario "+ usuario.getUsuaEmail() + " ya existe !!");
            throw new SeguridadException("El usuario " +usuario.getUsuaEmail() +" ya existe en el sistema");
        }
        
        String contrasenaEncriptada = "";
        
        try {
            contrasenaEncriptada = encriptar(usuario.getUsuaPass());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        usuario.setUsuaPass(contrasenaEncriptada);
        usuario.setUsuaBlock(true);
        usuario.setUsuaConta(0);
        usuario.setUsuaEsta("Activo");
        usuario.setUsuaUsucd(new Date());
        usuario.setUsuaUsucs("Administrador");
        usuario.setUsuaUsumd(new Date());
        usuario.setUsuaUsums("El usuario mismo");        
        em.persist(usuario);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Usuario findByEmail(String email) {
        LOGGER.log(Level.FINE,"Consulta cliente {0}",  email);
        Query  q = em.createNamedQuery("Usuario.findByEmail");
        q.setParameter("email", email);
        try{
            return (Usuario) q.getSingleResult();
        }catch(NoResultException e){
            LOGGER.log(Level.WARNING,"No se encontro usuario {0}", email);
            return null;
        }
    }
    
    public String encriptar(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    @Override
    public Usuario modificarUsuario(Usuario usuario) {
        LOGGER.log(Level.FINE,"Modificando usuario con nombre : {0} - Version: ", new Object[]{usuario.getUsuaEmail()} );
        usuario = em.merge(usuario);
        return usuario;
    }
    
    @Override
    public java.util.List<Usuario> findAll() {
        Query q = em.createNamedQuery("Usuario.findAll");
        return q.getResultList();
    }
    
    @Override
    public void remove(Usuario usuario) {
        em.remove(usuario);
    }
    
    
    @Override
    public void remove(Long usuario) {
        LOGGER.log(Level.FINE,"Eliminar usuario con id {0}", usuario);
      Usuario usu = this.find(usuario);
      if (usu!=null){
        remove(usu);
        LOGGER.log(Level.INFO,"Usuario eliminado correctamente");
      }else{
        LOGGER.log(Level.INFO, "Usuario con id {} no existe", usuario);
      }
    }
    
    @Override
    public Usuario find(Object id) {
        return em.find(Usuario.class, id);
    }
    

    @Override
    public java.util.List<Usuario> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Usuario.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }
    
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Usuario> rt = cq.from(Usuario.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

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
}
