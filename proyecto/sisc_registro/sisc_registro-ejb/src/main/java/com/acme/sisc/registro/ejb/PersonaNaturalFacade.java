/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.sisc.registro.ejb;

import com.acme.sisc.agenda.entidades.Alergia;
import com.acme.sisc.agenda.entidades.Enfermedad;
import com.acme.sisc.agenda.entidades.Operacion;
import com.acme.sisc.agenda.entidades.PersonaEps;
import com.acme.sisc.agenda.entidades.PersonaJuridica;
import com.acme.sisc.agenda.entidades.PersonaNatural;
import com.acme.sisc.agenda.entidades.PersonaNaturalAlergia;
import com.acme.sisc.agenda.entidades.PersonaNaturalBeneficiario;
import com.acme.sisc.agenda.entidades.PersonaNaturalEnfermedad;
import com.acme.sisc.agenda.entidades.PersonaNaturalOperacion;
import com.acme.sisc.agenda.entidades.TipoIdentificacion;
import com.acme.sisc.common.exceptions.CustomException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rreedd
 */
@Stateless
public class PersonaNaturalFacade implements IPersonaNaturalFacadeRemote, IPersonaNaturalFacadeLocal {

    @PersistenceContext(unitName = "SistemaSaludPU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(PersonaNaturalFacade.class.getName());

    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaNaturalFacade() {
    }

    /**
     * Buscar una persona según su tipo y número de identificación.
     * @param tId Tipo identificación.
     * @param identificacion Número identificación.
     * @return Objeto Persona, si existe.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public PersonaNatural findByIdentificacion(TipoIdentificacion tId, long identificacion) throws CustomException {
        LOGGER.log(Level.FINE, "Consulta persona {0}", identificacion);
        Query q = em.createNamedQuery("Persona.findByIdentificacion");
        q.setParameter("tipoIdentificacion", tId);
        q.setParameter("numeroIdentificacion", identificacion);
        try {
            return ((PersonaNatural) q.getSingleResult());
        } catch (NoResultException nre) {
            String msgError = "No se encontró persona con identificación " + tId + " - " + identificacion;
            LOGGER.log(Level.INFO, msgError);
        }
        return null;
    }

    /**
     * Buscar persona por número de identificación.
     * @param identificacion Número de identificación.
     * @return Objeto Persona, si existe.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public PersonaNatural findByNumeroIdentificacion(long identificacion) throws CustomException {
        LOGGER.log(Level.FINE, "Consulta persona {0}", identificacion);
        Query q = em.createNamedQuery("Persona.findByNumeroIdentificacion");
        q.setParameter("numeroIdentificacion", identificacion);
        try {
            return ((PersonaNatural) q.getSingleResult());
        } catch (NoResultException nre) {
            String msgError = "No se encontró persona con identificación " + identificacion;
            LOGGER.log(Level.WARNING, msgError);
            throw new CustomException(-1, -1, msgError);
        }
    }

    /**
     * Buscar una persona según su correo electrónico.
     * @param email Correo electrónico a buscar.
     * @return Objeto Persona, si existe.
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public PersonaNatural findByEmail(String email) {
        LOGGER.log(Level.FINE, "Consulta persona, email {0}", email);
        Query q = em.createNamedQuery("PersonaNatural.findByCorreoElectronico");
        q.setParameter("correoElectronico", email);
        try {
            return ((PersonaNatural) q.getSingleResult());
        } catch (NoResultException nre) {
            LOGGER.log(Level.WARNING, "No se encontró persona con correo {0}", email);
            return null;
        }
    }

    /**
     * Actualizar una persona natural.
     * @param p Persona natural.
     * @return Persona natural.
     */
    @Override
    public PersonaNatural modificarPersonaNatural(PersonaNatural p) {
        LOGGER.log(Level.FINE, "Modificando persona natural con nombre : {0} - Versión: {1}", new Object[]{p.getNombres(), p.getVersion()});
        p = em.merge(p);
        return p;
    }

    /**
     * Listar todas las personas naturales.
     * @return Lista con objetos PersonaNatural.
     */
    @Override
    public List<PersonaNatural> findAll() {
        Query q = em.createQuery("SELECT p FROM PersonaNatural p");
        return q.getResultList();
    }

    /**
     * Buscar personas naturales según un rol.
     * @param startPosition Posición inicial.
     * @param maxResults Máximo de resultados.
     * @param sortFields Campo por el cual ordenar la lista.
     * @param sortDirections Dirección de orden.
     * @param rol Rol a buscar.
     * @return Listado de personas.
     */
    @Override
    public List<PersonaNatural> findPersonaNaturalPorRol(int startPosition, int maxResults, String sortFields,
            String sortDirections, String rol) {
        Query q = em.createQuery("SELECT p FROM PersonaNatural p WHERE p.rolPersonaNatural = '" + rol + "' ");
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);
        return q.getResultList();
    }

    /**
     * Eliminar una persona natural.
     * @param entity Persona natural.
     */
    @Override
    public void remove(PersonaNatural entity) {
        em.remove(entity);
    }

    /**
     * Eliminar una persona natural según su identificador único.
     * @param id Identificador único.
     */
    public void remove(Long id) throws CustomException {
        LOGGER.log(Level.FINE, "Eliminar persona natural con id {0}", id);
        PersonaNatural p = this.find(id);
        if (p != null) {
            remove(p);
            LOGGER.log(Level.INFO, "Persona natural eliminado correctamente");
        } else {
            String msgError = "Persona natural con id " + id + "no existe";
            LOGGER.log(Level.INFO, msgError);
            throw new CustomException(-1, -1, msgError);
        }
    }

    /**
     * Consultar una persona natural según su identificador único.
     * @param id Identificador único.
     * @return Objeto PersonaNatural, si existe.
     */
    @Override
    public PersonaNatural find(Object id) {
        return em.find(PersonaNatural.class, id);
    }

    /**
     * Buscar un listado de personas naturales.
     * @param startPosition Posición inicial.
     * @param maxResults Máximo de resultados.
     * @param sortFields Campo por el cual ordenar la lista.
     * @param sortDirections Dirección de orden.
     * @return Listado de personas naturales.
     */
    @Override
    public List<PersonaNatural> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(PersonaNatural.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }

    /**
     * Cantidad de personas naturales registradas en el sistema.
     * @return Cantidad.
     */
    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<PersonaNatural> rt = cq.from(PersonaNatural.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * Crear una persona natural.
     * @param personaNatural Datos de la persona natural.
     * @return La misma persona natural con identificador único.
     * @throws CustomException 
     */
    @Override
    public PersonaNatural crearPersonaNatural(PersonaNatural personaNatural) throws CustomException {
        personaNatural.setIdPersona(null);
        LOGGER.info("Inicia crearPersonaNatural(...)");
        //Se verifica si ya existe
        PersonaNatural p = findByIdentificacion(personaNatural.getTipoIdentificacion(), personaNatural.getNumeroIdentificacion());
        if (p != null) {
            em.lock(p, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
            String msgError = "La persona natural " + personaNatural.getTipoIdentificacion() + "-"
                    + personaNatural.getNumeroIdentificacion() + " ya existe en el sistema";
            LOGGER.log(Level.INFO, msgError);
            throw new CustomException(-1, -1, msgError);
        }
        em.persist(personaNatural);
        LOGGER.log(Level.INFO, "Persona natural creada con id: {0}", personaNatural.getIdPersona());
        LOGGER.info("Finaliza crearPersonaNatural despues(...)");
        
        return personaNatural;
    }

    /**
     * Consultar médicos según una EPS y una especialidad.
     * @param startPosition Posición inicial.
     * @param maxResults Máximo de resultados.
     * @param sortFields Campo por el cual ordenar la lista.
     * @param sortDirections Dirección de orden.
     * @param especialidad Identificador único de la especialidad.
     * @param eps Identificador único de la EPS.
     * @return Listado de los médicos que cumplan con los filtros.
     */
    @Override
    public List<PersonaNatural> medicosPorEspecialidadFindRange(int startPosition, int maxResults, String sortFields,
            String sortDirections, Long especialidad, Long eps) {
        Query q = em.createQuery("SELECT p FROM PersonaNatural p INNER JOIN FETCH p.listaEspecialidadesMedico e " 
                               + " INNER JOIN p.listaPersonasEps m "
                               + " WHERE e.especialidad.idEspecialidad = :idesp "
                               + " AND m.eps.idPersona = :ideps");
        
        q.setParameter("idesp", especialidad);
        q.setParameter("ideps", eps);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);
        List<PersonaNatural> r = q.getResultList();
        return r;
    }

    /**
     * Listado de beneficiarios de un cotizante.
     * @param startPosition Posición inicial.
     * @param maxResults Máximo de resultados.
     * @param sortFields Campo por el cual ordenar la lista.
     * @param sortDirections Dirección de orden.
     * @param cotizante Persona natural asociada a cierta EPS.
     * @return Listado de beneficiarios.
     */
    @Override
    public List<PersonaNaturalBeneficiario> findBeneficiarios(int startPosition, int maxResults, String sortFields,
            String sortDirections, long cotizante) throws CustomException {
        LOGGER.log(Level.FINE, "Consulta beneficiarios, cotizante {0}", cotizante);
        try {
            Query q = em.createNamedQuery("PersonaNaturalBeneficiario.findAllByCotizante");
            q.setParameter("idCotizante", cotizante);
            q.setFirstResult(startPosition);
            q.setMaxResults(maxResults);
            return q.getResultList();
        } catch (NoResultException nre) {
            String msgError = "No se lograron consultar los beneficiarios del cotizante " + cotizante;
            LOGGER.log(Level.INFO, msgError);
            throw new CustomException(-1, -1, msgError);
        }
    }

    /**
     * Asociar una persona natural como beneficiario de determinado 
     * cotizante.
     * @param cotizante Persona natural asociada a una EPS.
     * @param beneficiario Familiar del cotizante.
     * @param parentezco Tipo de familiar.
     * @throws CustomException 
     */
    @Override
    public void asociarBeneficiario(Long cotizante, Long beneficiario, int parentezco) throws CustomException {
        LOGGER.info("Inicia asociarBeneficiario(...)");

        Query q = em.createQuery("SELECT b FROM PersonaNaturalBeneficiario b "
                               + "WHERE b.cotizante.idPersona=:cotizante "
                               + "AND b.beneficiario.idPersona=:beneficiario");
        q.setParameter("cotizante", cotizante);
        q.setParameter("beneficiario", cotizante);
        if (q.getResultList().size() > 0){
            throw new CustomException(-1, -1, "Ya existe la relación cotizante-beneficiario");
        }            
        PersonaNaturalBeneficiario b = new PersonaNaturalBeneficiario();
        b.setCotizante(em.find(PersonaNatural.class, cotizante));
        b.setBeneficiario(em.find(PersonaNatural.class, beneficiario));
        b.setParentezco(parentezco);
        em.persist(b);
        LOGGER.info("Finaliza asociarBeneficiario despues(...)");
    }

    /**
     * Eliminar relación entre beneficiario y cotizante.
     * @param beneficiario 
     */
    @Override
    public void removerBeneficiario(PersonaNaturalBeneficiario beneficiario) throws CustomException {
        LOGGER.log(Level.FINE, "removerBeneficiario con id {0}", beneficiario.getIdPersonaNaturalBeneficiario());
        PersonaNatural p = this.find(beneficiario.getIdPersonaNaturalBeneficiario());
        if (p != null) {
            remove(p);
            LOGGER.log(Level.INFO, "Beneficiario eliminado correctamente");
        } else {
            String msgError = "Beneficiario con id " + beneficiario.getIdPersonaNaturalBeneficiario() + " no existe";
            LOGGER.log(Level.WARNING, msgError);
            throw new CustomException(-1, -1, msgError);
        }
    }

    /**
     * Listar todas las EPS del sistema.
     * @return Lista de objetos PersonaJuridica.
     */
    @Override
    public List<PersonaJuridica> listaEPS() {
        Query q = em.createQuery("SELECT p FROM PersonaJuridica p");
        return q.getResultList();
    }

    /**
     * Listar todas las alergias del sistema.
     * @return Listado de objetos Alergia.
     */
    @Override
    public List<Alergia> listaAlergias() {
        Query q = em.createQuery("SELECT a FROM Alergia a");
        return q.getResultList();
    }

    /**
     * Listar todas las enfermedades del sistema.
     * @return Listado de objetos Enfermedad.
     */
    @Override
    public List<Enfermedad> listaEnfermedades() {
        Query q = em.createQuery("SELECT e FROM Enfermedad e");
        return q.getResultList();
    }

    /**
     * Listar todas las operaciones del sistema.
     * @return Listado de objetos Operacion.
     */
    @Override
    public List<Operacion> listaOperaciones() {
        Query q = em.createQuery("SELECT o FROM Operacion o");
        return q.getResultList();
    }

    /**
     * Cambiar la EPS actual de determinado paciente.
     * @param paciente Identificador único del paciente.
     * @param eps Identificador único de la EPS.
     * @throws CustomException 
     */
    @Override
    public void asociarPaciente_EPS(Long paciente, Long eps) throws CustomException {
        try {
            LOGGER.info("Inicia asociarPacienteEPS(...)");
            Query q = em.createQuery("SELECT a FROM PersonaEps a WHERE a.persona.idPersona=:paciente AND a.fechaFin=NULL");
            q.setParameter("paciente", paciente);
            List listaEps = q.getResultList();
            if (listaEps != null) {
                if (!listaEps.isEmpty()) {
                    for (Object o : listaEps) {
                        PersonaEps pe = (PersonaEps) o;
                        pe.setFechaFin(new Date());
                        em.persist(pe);
                    }
                }
            }
            PersonaEps pe = new PersonaEps();
            pe.setEps(em.find(PersonaJuridica.class, eps));
            pe.setPersona(em.find(PersonaNatural.class, paciente));
            pe.setFechaInicio(new Date());
            em.persist(pe);
            LOGGER.info("Finaliza asociarPacienteEPS(...)");
        } catch (Exception ex) {
            String msgError = "Error al asociar paciente-eps " + paciente + "-" + eps;
            LOGGER.log(Level.WARNING, msgError + "\n" + ex.getLocalizedMessage());
            throw new CustomException(-1, -1, msgError);
        }
    }

    /**
     * Consultar la EPS actual del paciente.
     * @param paciente Identificador único del paciente.
     * @return Objeto PersonaJuridica.
     * @throws CustomException 
     */
    @Override
    public PersonaJuridica getPaciente_EPS(Long paciente) throws CustomException {
        PersonaJuridica response = null;
        try {
            LOGGER.info("Inicia getPaciente_EPS(...)");
            Query q = em.createQuery("SELECT a FROM PersonaEps a WHERE a.persona.idPersona=:paciente AND a.fechaFin=NULL");
            q.setParameter("paciente", paciente);
            List listaEps = q.getResultList();
            if (listaEps != null) {
                if (!listaEps.isEmpty()) {
                    PersonaEps eps = (PersonaEps)listaEps.get(0);
                    if (eps != null) {
                        response = eps.getEps();
                    }
                }
            }
            LOGGER.info("Finaliza getPaciente_EPS(...)");
        } catch (Exception ex) {
            String msgError = "Error al obtener EPS del paciente";
            LOGGER.log(Level.WARNING, msgError + "\n" + ex.getLocalizedMessage());
            throw new CustomException(-1, -1, msgError);
        }
        return response;
    }

    /**
     * Asociar un médico a una o más EPS.
     * @param medico Identificador único del médico.
     * @param eps Identificador único de la EPS.
     * @throws CustomException 
     */
    @Override
    public void asociarMedico_EPS(Long medico, List<Long> eps) throws CustomException {
        try {
            LOGGER.info("*** ID Médico: " + medico);
            String msg = "";
            boolean existe = false;
            LOGGER.info("Inicia asociarMedico_EPS(...)");
            for (Long e : eps) {
                Query q = em.createQuery("SELECT a FROM PersonaEps a WHERE a.persona.idPersona=:paciente AND a.eps.idPersona=:eps");
                q.setParameter("paciente", medico);
                q.setParameter("eps", e);
                List listaEps = q.getResultList();
                if (listaEps != null) {
                    if (!listaEps.isEmpty()) {
                        existe = true;
                        msg += "Ya existe la relación del medico con EPS Id " + e;
                    }
                }
                if (!existe){
                    PersonaEps pe = new PersonaEps();
                    pe.setEps(em.find(PersonaJuridica.class, e));
                    pe.setPersona(em.find(PersonaNatural.class, medico));
                    pe.setFechaInicio(new Date());
                    em.persist(pe);
                }
            }
            if(!msg.isEmpty()){
                throw new CustomException(-1, -1, msg);
            }
            LOGGER.log(Level.INFO, "Finaliza asociarMedico_EPS(...) {0}", msg);
        } catch (Exception ex) {
            String msgError = "No se lograron asociar las EPS al médico";
            LOGGER.log(Level.WARNING, msgError + " - Exception: " + ex.getLocalizedMessage());
            throw new CustomException(-1, -1, msgError);
        }
    }

    /**
     * Obtener el listado de EPS asociadas a determinado médico.
     * @param medico Identificador único del médico.
     * @return Listado de PersonaJuridica.
     * @throws CustomException 
     */
    @Override
    public List<PersonaJuridica> getMedico_EPS(Long medico) throws CustomException {
        List<PersonaJuridica> response = null;
        try {
            LOGGER.info("Inicia getMedico_EPS(...)");
            Query q = em.createQuery("SELECT j.eps FROM PersonaEps j " 
                    + "WHERE j.persona.idPersona=:medico AND j.fechaFin=NULL");
            q.setParameter("medico", medico);
            response = q.getResultList();
            
            LOGGER.info("Finaliza getMedico_EPS(...)");
        } catch (Exception ex) {
            String msgError = "No se lograron obtener las EPS del médico";
            LOGGER.log(Level.WARNING, msgError + " - Exception: " + ex.getLocalizedMessage());
            throw new CustomException(-1, -1, msgError);
        }
        return response;
    }

    /**
     * Asociar determinado paciente a una o más alergias.
     * @param paciente Identificador único del paciente.
     * @param alergias Listado de identificadores de las alergias.
     * @throws CustomException 
     */
    @Override
    public void asociarPaciente_Alergias(Long paciente, List<Long> alergias) throws CustomException {
        try {
            String msg = "";
            boolean existe = false;
            LOGGER.info("Inicia asociarPaciente_Alergias(...)");
            for (Long a : alergias) {
                Query q = em.createQuery("SELECT a FROM PersonaNaturalAlergia a WHERE a.paciente.idPersona=:paciente "
                        + "AND a.alergia.idAlergia=:alergia");
                q.setParameter("paciente", paciente);
                q.setParameter("alergia", a);
                List listaAlergias = q.getResultList();
                if (listaAlergias != null) {
                    if (!listaAlergias.isEmpty()) {
                        existe = true;
                        msg += "Ya existe la relación del paciente con alergia Id " + a;
                    }
                }
                if (!existe){
                    PersonaNaturalAlergia pna = new PersonaNaturalAlergia();
                    pna.setAlergia(em.find(Alergia.class, a));
                    pna.setPaciente(em.find(PersonaNatural.class, paciente));
                    em.persist(pna);
                }
            }
            if(!msg.isEmpty()){
                throw new CustomException(-1, -1, msg);
            }
            LOGGER.log(Level.INFO, "Finaliza asociarPaciente_Alergias(...) {0}", msg);
        } catch (Exception ex) {
            String msgError = "No se lograron asociar una o más alergias al paciente";
            LOGGER.log(Level.WARNING, msgError + " - Exception: " + ex.getLocalizedMessage());
            throw new CustomException(-1, -1, msgError);
        }
    }

    /**
     * Asociar determinado paciente a una o más enfermedades.
     * @param paciente Identificador único del paciente.
     * @param enfermedades Listado de identificadores de las enfermedades.
     * @throws CustomException 
     */
    @Override
    public void asociarPaciente_Enfermedades(Long paciente, List<Long> enfermedades) throws CustomException {
        try {
            String msg = "";
            boolean existe = false;
            LOGGER.info("Inicia asociarPaciente_Enfermedades(...)");
            for (Long e : enfermedades) {
                Query q = em.createQuery("SELECT a FROM PersonaNaturalEnfermedad a WHERE a.paciente.idPersona=:paciente " 
                        + "AND a.enfermedad.idEnfermedad =:enfermedad");
                q.setParameter("paciente", paciente);
                q.setParameter("enfermedad", e);
                List listaEnf = q.getResultList();
                if (listaEnf != null) {
                    if (!listaEnf.isEmpty()) {
                        existe = true;
                        msg += "Ya existe la relación del paciente con enfermedad Id " + e;
                    }
                }
                if (!existe){
                    PersonaNaturalEnfermedad pne = new PersonaNaturalEnfermedad();
                    pne.setEnfermedad(em.find(Enfermedad.class, e));
                    pne.setPaciente(em.find(PersonaNatural.class, paciente));
                    em.persist(pne);
                }
            }
            if(!msg.isEmpty()){
                throw new CustomException(-1, -1, msg);
            }
            LOGGER.log(Level.INFO, "Finaliza asociarPaciente_Enfermedades(...) {0}", msg);
        } catch (Exception ex) {
            String msgError = "No se lograron asociar una o más enfermedades al paciente";
            LOGGER.log(Level.WARNING, msgError + " - Exception: " + ex.getLocalizedMessage());
            throw new CustomException(-1, -1, msgError);
        }
    }

    /**
     * Asociar determinado paciente a una o más operaciones.
     * @param paciente Identificador único del paciente.
     * @param operaciones Listado de identificadores de las operaciones.
     * @throws CustomException 
     */
    @Override
    public void asociarPaciente_Operaciones(Long paciente, List<Long> operaciones) throws CustomException {
        try {
            String msg = "";
            boolean existe = false;
            LOGGER.info("Inicia asociarPaciente_Operaciones(...)");
            for (Long o : operaciones) {
                Query q = em.createQuery("SELECT a FROM PersonaNaturalOperacion a WHERE a.paciente.idPersona=:paciente " 
                        + "AND a.operacion.idOperacion =:operacion");
                q.setParameter("paciente", paciente);
                q.setParameter("operacion", o);
                List listaOpe = q.getResultList();
                if (listaOpe != null) {
                    if (!listaOpe.isEmpty()) {
                        existe = true;
                        msg += "Ya existe la relación del paciente con operacion Id " + o;
                    }
                }
                if (!existe){
                    PersonaNaturalOperacion pno = new PersonaNaturalOperacion();
                    pno.setOperacion(em.find(Operacion.class, o));
                    pno.setPaciente(em.find(PersonaNatural.class, paciente));
                    em.persist(pno);
                }
            }
            if(!msg.isEmpty()){
                throw new CustomException(-1, -1, msg);
            }
            LOGGER.log(Level.INFO, "Finaliza asociarPaciente_Operaciones(...) {0}", msg);
        } catch (Exception ex) {
            String msgError = "No se lograron asociar una o más operaciones al paciente";
            LOGGER.log(Level.WARNING, msgError + " - Exception: " + ex.getLocalizedMessage());
            throw new CustomException(-1, -1, msgError);
        }
    }

    /**
     * Obtener el listado de alergias asociadas a determinado paciente.
     * @param paciente Identificador único del paciente.
     * @return Listadop de Alergia.
     * @throws CustomException 
     */
    @Override
    public List<Alergia> getAlergiasPaciente(Long paciente) throws CustomException {
        List<Alergia> response = null;
        try {
            LOGGER.info("Inicia getAlergiasPaciente(...)");
            Query q = em.createQuery("SELECT a.alergia FROM PersonaNaturalAlergia a " 
                    + "WHERE a.paciente.idPersona=:paciente");
            q.setParameter("paciente", paciente);
            response = q.getResultList();
            
            LOGGER.info("Finaliza getAlergiasPaciente(...)");
        } catch (Exception ex) {
            String msgError = "No se lograron obtener las alergias del paciente";
            LOGGER.log(Level.WARNING, msgError + " - Exception: " + ex.getLocalizedMessage());
            throw new CustomException(-1, -1, msgError);
        }
        return response;
    }

    /**
     * Obtener el listado de enfermedades asociadas a determinado paciente.
     * @param paciente Identificador único del paciente.
     * @return Listado de Enfermedad.
     * @throws CustomException 
     */
    @Override
    public List<Enfermedad> getEnfermedadesPaciente(Long paciente) throws CustomException {
        List<Enfermedad> response = null;
        try {
            LOGGER.info("Inicia getEnfermedadesPaciente(...)");
            Query q = em.createQuery("SELECT e.enfermedad FROM PersonaNaturalEnfermedad e " 
                    + "WHERE e.paciente.idPersona=:paciente");
            q.setParameter("paciente", paciente);
            response = q.getResultList();
            
            LOGGER.info("Finaliza getEnfermedadesPaciente(...)");
        } catch (Exception ex) {
            String msgError = "No se lograron obtener las enfermedades del paciente";
            LOGGER.log(Level.WARNING, msgError + " - Exception: " + ex.getLocalizedMessage());
            throw new CustomException(-1, -1, msgError);
        }
        return response;
    }

    /**
     * Obtener el listado de operaciones asociadas a determinado paciente.
     * @param paciente Identificador único del paciente.
     * @return Listado de Operacion.
     * @throws CustomException 
     */
    @Override
    public List<Operacion> getOperacionesPaciente(Long paciente) throws CustomException {
        List<Operacion> response = null;
        try {
            LOGGER.info("Inicia getOperacionesPaciente(...)");
            Query q = em.createQuery("SELECT o.operacion FROM PersonaNaturalOperacion o " 
                    + "WHERE o.paciente.idPersona=:paciente");
            q.setParameter("paciente", paciente);
            response = q.getResultList();
            
            LOGGER.info("Finaliza getOperacionesPaciente(...)");
        } catch (Exception ex) {
            String msgError = "No se lograron obtener las operaciones del paciente";
            LOGGER.log(Level.WARNING, msgError + " - Exception: " + ex.getLocalizedMessage());
            throw new CustomException(-1, -1, msgError);
        }
        return response;
    }
}
