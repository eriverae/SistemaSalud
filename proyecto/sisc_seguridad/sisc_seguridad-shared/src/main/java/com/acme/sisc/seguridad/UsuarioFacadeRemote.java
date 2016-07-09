/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.seguridad;

import com.acme.sisc.agenda.entidades.Usuario;
import com.acme.sisc.seguridad.exceptions.SeguridadException;
import javax.ejb.Remote;

/**
 * Interface UsuarioFacadeRemote
 *
 * @author Julio
 * @version 1.0
 * @since 2016-05-28
 */
@Remote
public interface UsuarioFacadeRemote {

    /**
    * Metodo crearUsuario, crea el usuario con el objeto que llega del servicio, accediento a la entidad
    * @param Usuario usuario es el objeto a crear y persistir en la BD
    * @return Usuario 
    */
    Usuario crearUsuario(Usuario usuario) throws SeguridadException;

    /**
    * Metodo findByEmail, devuelve un objeto de tipo Usuario consultado a partir del parametro de entrada
    * @param email String es el correo del usuario, el cual va a buscar
    * @return retorna Usuario, el el objeto encontrado a partir del metodo de la busqueda
    */
    Usuario findByEmail(String email);

    /**
    * Metodo modificarUsuario, devuelve un objeto de tipo Usuario una vez que se modifican sus atributos
    * @param Usuario usuario es el objeto a modififcar
    * @return retorna Usuario, el el objeto una vez modificado 
    */
    Usuario modificarUsuario(Usuario usuario);

    /**
    * Metodo findAll, retorna una lista de objetos de tipo Usuario
    * @return java.util.List<Usuario>, todos los grupos existentes en la BD
    */
    java.util.List<Usuario> findAll();

    /**
    * Metodo remove, elimina el objeto con el metodo remove 
    * @param Usuario usuario es el objeto a eliminar en la BD
    */
    void remove(Usuario usuario);
    
    /**
    * Metodo remove, elimina el objeto con el metodo remove, antes de ello crea un objeto a partir del id 
    * @param Long usuario id del Usuario a eliminar
    */
    void remove(Long usuario);

    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return Usuario es el objeto que trae a partir del metodo find
    */
    Usuario find(Object id);

    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<Usuario> la lista de objetos en referencia
    */
    java.util.List<Usuario> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    /**
    * Metodo count, devuelve el conteo de objetos en referencia
    * @return int con el numero de objetos en referencia
    */
    int count();

    /**
    * Metodo autenticar, consulta que el usuario y contraseña existan
    * @param usuario es el mail del usuario
    * @param password es el password del usuario
    * @return boolean, true si es correcta la consulta y false por lo contrario
    */
    boolean autenticar(String usuario, String password);

    /**
    * Metodo cambiarContrasena, cambia la contraseña del usuario
    * @param usuario es el mail del usuario
    * @param passOld es el antiguo password del usuario
    * @param passNew es el nuevo password del usuario
    * @return String, "True" si el cambio es exitoso y false por lo contrario
    */
    String cambiarContrasena(Usuario usuario, String passOld, String passNew);

}
