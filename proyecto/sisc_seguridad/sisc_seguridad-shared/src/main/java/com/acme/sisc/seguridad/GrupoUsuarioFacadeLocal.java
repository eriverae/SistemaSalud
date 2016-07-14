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
import javax.ejb.Local;

/**
 * Interface GrupoUsuarioFacadeLocal
 *
 * @author Julio
 * @version 1.0
 * @since 2016-05-14
 */
@Local
public interface GrupoUsuarioFacadeLocal {
    
    /**
    * Metodo crearGrupoUsuario, crea la relacion en la tabla de perfiles y usuarios con el objeto que llega del servicio, accediento a la entidad
    * @param GrupoUsuario grupoUsuario es el objeto a crear y persistir en la BD
    */
    void crearGrupoUsuario(GrupoUsuario grupoUsuario) throws SeguridadException;

    /**
    * Metodo findByGrupUsua, devuelve un objeto de tipo GrupoUsuario consultado a partir de los parametros de entrada
    * @param Long usuario es el id de la tabla Usuario
    * @param Long grupo es el id de la tabla Grupo
    * @return retorna GrupoUsuario, el el objeto encontrado a partir del metodo de la busqueda
    */
    GrupoUsuario findByGrupUsua(Long usuario, Long grupo);

    /**
    * Metodo modificarGrupoUsuario, devuelve un objeto de tipo GrupoUsuario una vez que se modifican sus atributos
    * @param GrupoUsuario grupoUsuario es el objeto a modififcar
    * @return retorna GrupoUsuario, el el objeto una vez modificado 
    */
    GrupoUsuario modificarGrupoUsuario(GrupoUsuario grupoUsuario);

    /**
    * Metodo findAll, retorna una lista de objetos de tipo GrupoUsuario
    * @return java.util.List<GrupoUsuario>, todos los perfiles con la relacion del usuario existentes en la BD
    */
    java.util.List<com.acme.sisc.agenda.entidades.GrupoUsuario> findAll();

    /**
    * Metodo remove, elimina el objeto con el metodo remove 
    * @param GrupoUsuario grupoUsuario es el objeto a eliminar en la BD
    */
    void remove(GrupoUsuario grupoUsuario);

    /**
    * Metodo remove, elimina el objeto con el metodo remove, antes de ello crea un objeto a partir del id 
    * @param Long grupoUsuario id del GrupoUsuario a eliminar
    */
    void remove(long grupoUsuario);

    /**
    * Metodo find, consulta el objeto en referencia a partir del id
    * @param Object id es el objeto en referencia a consultar
    * @return GrupoUsuario es el objeto que trae a partir del metodo find
    */
    GrupoUsuario find(Object id);

    /**
    * Metodo count, devuelve el conteo de objetos en referencia
    * @return int con el numero de objetos en referencia
    */
    int count();

    /**
    * Metodo findRange, consulta y devuelve una lista de objetos en referencia
    * @param startPosition es la posicion inicial de la lista de los objetos
    * @param maxResults es la cantidad de los objetos existentes
    * @param sortFields son los capos del objetos
    * @param sortDirections es el orden con el que re retorna la lista de objetos
    * @return java.util.List<GrupoUsuario> la lista de objetos en referencia
    */
    java.util.List<com.acme.sisc.agenda.entidades.GrupoUsuario> findRange(int startPosition, int maxResults, String sortFields, String sortDirections);

    /**
    * Metodo findByUsuaUsua, devuelve un objeto de tipo Grupo consultado a partir del parametro de entrada
    * @param Long usuaUsua es el id del usuario a consultar los perfiles
    * @return retorna java.util.List<Grupo>, son una lista de los perfiles del usuario
    */
    java.util.List<Grupo> findByUsuaUsua(Long usuaUsua);

    /**
    * Metodo actualizaGrupoUsuario, crea y elimiana las relaciones de usuario y perfiles acorde a estado
    * @param Grupo grupGrup es el objeto de los perfiles
    * @param Usuario usuaUsua es el objeto de los usuarios
    * @param Boolean estado si llega true crea la relacion sino la elimina
    */
    void actualizaGrupoUsuario(Usuario usuaUsua, Grupo grupgrup, Boolean estado);
    
}
