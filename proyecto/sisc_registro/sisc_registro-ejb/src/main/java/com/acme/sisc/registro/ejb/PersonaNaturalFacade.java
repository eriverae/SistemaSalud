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
import com.acme.sisc.agenda.entidades.PersonaNaturalBeneficiario;
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

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public PersonaNatural findByIdentificacion(TipoIdentificacion tId, long identificacion) {
        LOGGER.log(Level.FINE, "Consulta persona {0}", identificacion);
        Query q = em.createNamedQuery("Persona.findByIdentificacion");
        q.setParameter("tipoIdentificacion", tId);
        q.setParameter("numeroIdentificacion", identificacion);
        try {
            return ((PersonaNatural) q.getSingleResult());
        } catch (NoResultException nre) {
            LOGGER.log(Level.WARNING, "No se encontró persona con identificación " + tId + " - " + identificacion);
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public PersonaNatural findByNumeroIdentificacion(long identificacion) {
        LOGGER.log(Level.FINE, "Consulta persona {0}", identificacion);
        Query q = em.createNamedQuery("Persona.findByNumeroIdentificacion");
        q.setParameter("numeroIdentificacion", identificacion);
        try {
            return ((PersonaNatural) q.getSingleResult());
        } catch (NoResultException nre) {
            LOGGER.log(Level.WARNING, "No se encontró persona con identificación {0}", identificacion);
            return null;
        }
    }

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

    @Override
    public PersonaNatural modificarPersonaNatural(PersonaNatural p) {
        LOGGER.log(Level.FINE, "Modificando persona natural con nombre : {0} - Versión: {1}", new Object[]{p.getNombres(), p.getVersion()});
        p = em.merge(p);
        return p;
    }

    @Override
    public List<PersonaNatural> findAll() {
        Query q = em.createQuery("SELECT p FROM PersonaNatural p");
        return q.getResultList();
    }

    @Override
    public List<PersonaNatural> findPersonaNaturalPorRol(int startPosition, int maxResults, String sortFields,
            String sortDirections, String rol) {
        Query q = em.createQuery("SELECT p FROM PersonaNatural p WHERE p.rolPersonaNatural = '" + rol + "' ");
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);
        return q.getResultList();
    }

    @Override
    public void remove(PersonaNatural entity) {
        em.remove(entity);
    }

    public void remove(Long id) {
        LOGGER.log(Level.FINE, "Eliminar persona natural con id {0}", id);
        PersonaNatural p = this.find(id);
        if (p != null) {
            remove(p);
            LOGGER.log(Level.INFO, "Persona natural eliminado correctamente");
        } else {
            LOGGER.log(Level.INFO, "Cliente con id {} no existe", id);
        }
    }

    @Override
    public PersonaNatural find(Object id) {
        return em.find(PersonaNatural.class, id);
    }

    @Override
    public List<PersonaNatural> findRange(int startPosition, int maxResults, String sortFields, String sortDirections) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(PersonaNatural.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setFirstResult(startPosition);
        q.setMaxResults(maxResults);

        return q.getResultList();
    }

    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<PersonaNatural> rt = cq.from(PersonaNatural.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public PersonaNatural crearPersonaNatural(PersonaNatural personaNatural) throws CustomException {
        try {
            LOGGER.info("Inicia crearPersonaNatural(...)");
            //Se verifica si ya existe
            PersonaNatural p = findByIdentificacion(personaNatural.getTipoIdentificacion(), personaNatural.getNumeroIdentificacion());
            if (p != null) {
                em.lock(p, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
                LOGGER.log(Level.WARNING, "Persona natural {0} ya existe !!", personaNatural.getNumeroIdentificacion());
                throw new CustomException("La persona natural " + personaNatural.getTipoIdentificacion() + "-"
                        + personaNatural.getNumeroIdentificacion() + " ya existe en el sistema");
            }
            em.persist(personaNatural);
            LOGGER.log(Level.INFO, "Persona natural creada con id: {0}", personaNatural.getIdPersona());
            //Prueba queues
            //JMSUtil.sendMessage(personaNatural,"java:/jms/queue/BancoQueue");
            LOGGER.info("Finaliza crearPersonaNatural despues(...)");
        } catch (CustomException ex) {
            LOGGER.log(Level.WARNING, "No se encontró persona {0}", personaNatural.getTipoIdentificacion() + " "
                    + personaNatural.getNumeroIdentificacion() + " Exception: " + ex.getLocalizedMessage());
        }
        return personaNatural;
    }

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

    @Override
    public List<PersonaNaturalBeneficiario> findBeneficiarios(int startPosition, int maxResults, String sortFields,
            String sortDirections, long cotizante) {
        LOGGER.log(Level.FINE, "Consulta beneficiarios, cotizante {0}", cotizante);
        try {
            Query q = em.createNamedQuery("PersonaNaturalBeneficiario.findAllByCotizante");
            q.setParameter("idCotizante", cotizante);
            q.setFirstResult(startPosition);
            q.setMaxResults(maxResults);
            return q.getResultList();
        } catch (NoResultException nre) {
            LOGGER.log(Level.WARNING, "No se lograron consultar los beneficiarios del cotizante {0}", cotizante);
            return null;
        }
    }

    @Override
    public void asociarBeneficiario(PersonaNatural cotizante, PersonaNatural beneficiario, int parentezco) {
        try {
            LOGGER.info("Inicia asociarBeneficiario(...)");
            PersonaNaturalBeneficiario b = new PersonaNaturalBeneficiario();
            b.setCotizante(cotizante);
            b.setBeneficiario(beneficiario);
            b.setParentezco(parentezco);
            em.persist(b);
            LOGGER.info("Finaliza asociarBeneficiario despues(...)");
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "Error al guardar beneficiario {0}", cotizante.getCorreoElectronico() + " "
                    + beneficiario.getCorreoElectronico() + " Exception: " + ex.getLocalizedMessage());
        }
    }

    @Override
    public void removerBeneficiario(PersonaNaturalBeneficiario beneficiario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PersonaJuridica> listaEPS() {
        Query q = em.createQuery("SELECT p FROM PersonaJuridica p");
        return q.getResultList();
    }

    @Override
    public List<Alergia> listaAlergias() {
        Query q = em.createQuery("SELECT a FROM Alergia a");
        return q.getResultList();
    }

    @Override
    public List<Enfermedad> listaEnfermedades() {
        Query q = em.createQuery("SELECT e FROM Enfermedad e");
        return q.getResultList();
    }

    @Override
    public List<Operacion> listaOperaciones() {
        Query q = em.createQuery("SELECT o FROM Operacion o");
        return q.getResultList();
    }

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
            LOGGER.log(Level.WARNING, "Error al asociar paciente-eps {0}", paciente + "-"
                    + eps + " Exception: " + ex.getLocalizedMessage());
        }
    }

    @Override
    public PersonaJuridica getPaciente_EPS(Long paciente) throws CustomException {
        PersonaJuridica response = null;
        try {
            LOGGER.info("Inicia getPaciente_EPS(...)");
            Query q = em.createQuery("SELECT a FROM PersonaEps a WHERE a.persona.idPersona=:paciente AND a.fechaFin=NULL");
            q.setParameter("paciente", paciente);
            PersonaEps eps = (PersonaEps)q.getSingleResult();
            if (eps != null) {
                response = eps.getEps();
            }
            
            LOGGER.info("Finaliza getPaciente_EPS(...)");
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "Error en getPaciente_EPS {0}", paciente + "-"
                    + " Exception: " + ex.getLocalizedMessage());
        }
        return response;
    }

    @Override
    public void asociarMedico_EPS(Long medico, List<Long> eps) throws CustomException {
        try {
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
                        msg += "Ya existe la relación del paciente con " + ((PersonaEps)listaEps.get(0)).getEps().getRazonSocial();
                    }
                }
                if (!existe){
                    PersonaEps pe = new PersonaEps();
                    pe.setEps(em.find(PersonaJuridica.class, eps));
                    pe.setPersona(em.find(PersonaNatural.class, medico));
                    pe.setFechaInicio(new Date());
                    em.persist(pe);
                }
            }
            
            LOGGER.log(Level.INFO, "Finaliza asociarPacienteEPS(...) {0}", msg);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "Error al asociar medico {0}", medico 
                    + " a eps - Exception: " + ex.getLocalizedMessage());
        }
    }

    @Override
    public List<PersonaJuridica> getMedico_EPS(Long paciente) throws CustomException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
