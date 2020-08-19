/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad.controlador;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidad.Contrato;
import Entidad.Inventarioempresa;
import Entidad.Mueble;
import Entidad.controlador.exceptions.NonexistentEntityException;
import Entidad.controlador.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author kil_5
 */
public class MuebleJpaController implements Serializable {

    public MuebleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mueble mueble) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contrato contratoidContrato = mueble.getContratoidContrato();
            if (contratoidContrato != null) {
                contratoidContrato = em.getReference(contratoidContrato.getClass(), contratoidContrato.getIdContrato());
                mueble.setContratoidContrato(contratoidContrato);
            }
            Inventarioempresa inventarioEmpresaidInventarioEmpresa = mueble.getInventarioEmpresaidInventarioEmpresa();
            if (inventarioEmpresaidInventarioEmpresa != null) {
                inventarioEmpresaidInventarioEmpresa = em.getReference(inventarioEmpresaidInventarioEmpresa.getClass(), inventarioEmpresaidInventarioEmpresa.getIdInventarioEmpresa());
                mueble.setInventarioEmpresaidInventarioEmpresa(inventarioEmpresaidInventarioEmpresa);
            }
            em.persist(mueble);
            if (contratoidContrato != null) {
                contratoidContrato.getMuebleList().add(mueble);
                contratoidContrato = em.merge(contratoidContrato);
            }
            if (inventarioEmpresaidInventarioEmpresa != null) {
                inventarioEmpresaidInventarioEmpresa.getMuebleList().add(mueble);
                inventarioEmpresaidInventarioEmpresa = em.merge(inventarioEmpresaidInventarioEmpresa);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMueble(mueble.getIdMueble()) != null) {
                throw new PreexistingEntityException("Mueble " + mueble + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mueble mueble) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mueble persistentMueble = em.find(Mueble.class, mueble.getIdMueble());
            Contrato contratoidContratoOld = persistentMueble.getContratoidContrato();
            Contrato contratoidContratoNew = mueble.getContratoidContrato();
            Inventarioempresa inventarioEmpresaidInventarioEmpresaOld = persistentMueble.getInventarioEmpresaidInventarioEmpresa();
            Inventarioempresa inventarioEmpresaidInventarioEmpresaNew = mueble.getInventarioEmpresaidInventarioEmpresa();
            if (contratoidContratoNew != null) {
                contratoidContratoNew = em.getReference(contratoidContratoNew.getClass(), contratoidContratoNew.getIdContrato());
                mueble.setContratoidContrato(contratoidContratoNew);
            }
            if (inventarioEmpresaidInventarioEmpresaNew != null) {
                inventarioEmpresaidInventarioEmpresaNew = em.getReference(inventarioEmpresaidInventarioEmpresaNew.getClass(), inventarioEmpresaidInventarioEmpresaNew.getIdInventarioEmpresa());
                mueble.setInventarioEmpresaidInventarioEmpresa(inventarioEmpresaidInventarioEmpresaNew);
            }
            mueble = em.merge(mueble);
            if (contratoidContratoOld != null && !contratoidContratoOld.equals(contratoidContratoNew)) {
                contratoidContratoOld.getMuebleList().remove(mueble);
                contratoidContratoOld = em.merge(contratoidContratoOld);
            }
            if (contratoidContratoNew != null && !contratoidContratoNew.equals(contratoidContratoOld)) {
                contratoidContratoNew.getMuebleList().add(mueble);
                contratoidContratoNew = em.merge(contratoidContratoNew);
            }
            if (inventarioEmpresaidInventarioEmpresaOld != null && !inventarioEmpresaidInventarioEmpresaOld.equals(inventarioEmpresaidInventarioEmpresaNew)) {
                inventarioEmpresaidInventarioEmpresaOld.getMuebleList().remove(mueble);
                inventarioEmpresaidInventarioEmpresaOld = em.merge(inventarioEmpresaidInventarioEmpresaOld);
            }
            if (inventarioEmpresaidInventarioEmpresaNew != null && !inventarioEmpresaidInventarioEmpresaNew.equals(inventarioEmpresaidInventarioEmpresaOld)) {
                inventarioEmpresaidInventarioEmpresaNew.getMuebleList().add(mueble);
                inventarioEmpresaidInventarioEmpresaNew = em.merge(inventarioEmpresaidInventarioEmpresaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mueble.getIdMueble();
                if (findMueble(id) == null) {
                    throw new NonexistentEntityException("The mueble with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mueble mueble;
            try {
                mueble = em.getReference(Mueble.class, id);
                mueble.getIdMueble();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mueble with id " + id + " no longer exists.", enfe);
            }
            Contrato contratoidContrato = mueble.getContratoidContrato();
            if (contratoidContrato != null) {
                contratoidContrato.getMuebleList().remove(mueble);
                contratoidContrato = em.merge(contratoidContrato);
            }
            Inventarioempresa inventarioEmpresaidInventarioEmpresa = mueble.getInventarioEmpresaidInventarioEmpresa();
            if (inventarioEmpresaidInventarioEmpresa != null) {
                inventarioEmpresaidInventarioEmpresa.getMuebleList().remove(mueble);
                inventarioEmpresaidInventarioEmpresa = em.merge(inventarioEmpresaidInventarioEmpresa);
            }
            em.remove(mueble);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mueble> findMuebleEntities() {
        return findMuebleEntities(true, -1, -1);
    }

    public List<Mueble> findMuebleEntities(int maxResults, int firstResult) {
        return findMuebleEntities(false, maxResults, firstResult);
    }

    private List<Mueble> findMuebleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mueble.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Mueble findMueble(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mueble.class, id);
        } finally {
            em.close();
        }
    }

    public int getMuebleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mueble> rt = cq.from(Mueble.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
