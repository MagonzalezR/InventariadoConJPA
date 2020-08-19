/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad.controlador;

import Entidad.Contratista;
import Entidad.ContratistaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidad.Empresacliente;
import Entidad.controlador.exceptions.NonexistentEntityException;
import Entidad.controlador.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author kil_5
 */
public class ContratistaJpaController implements Serializable {

    public ContratistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contratista contratista) throws PreexistingEntityException, Exception {
        if (contratista.getContratistaPK() == null) {
            contratista.setContratistaPK(new ContratistaPK());
        }
        contratista.getContratistaPK().setEmpresaClienteidEmpresaCliente(contratista.getEmpresacliente().getIdEmpresaCliente());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresacliente empresacliente = contratista.getEmpresacliente();
            if (empresacliente != null) {
                empresacliente = em.getReference(empresacliente.getClass(), empresacliente.getIdEmpresaCliente());
                contratista.setEmpresacliente(empresacliente);
            }
            em.persist(contratista);
            if (empresacliente != null) {
                empresacliente.getContratistaList().add(contratista);
                empresacliente = em.merge(empresacliente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContratista(contratista.getContratistaPK()) != null) {
                throw new PreexistingEntityException("Contratista " + contratista + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contratista contratista) throws NonexistentEntityException, Exception {
        contratista.getContratistaPK().setEmpresaClienteidEmpresaCliente(contratista.getEmpresacliente().getIdEmpresaCliente());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contratista persistentContratista = em.find(Contratista.class, contratista.getContratistaPK());
            Empresacliente empresaclienteOld = persistentContratista.getEmpresacliente();
            Empresacliente empresaclienteNew = contratista.getEmpresacliente();
            if (empresaclienteNew != null) {
                empresaclienteNew = em.getReference(empresaclienteNew.getClass(), empresaclienteNew.getIdEmpresaCliente());
                contratista.setEmpresacliente(empresaclienteNew);
            }
            contratista = em.merge(contratista);
            if (empresaclienteOld != null && !empresaclienteOld.equals(empresaclienteNew)) {
                empresaclienteOld.getContratistaList().remove(contratista);
                empresaclienteOld = em.merge(empresaclienteOld);
            }
            if (empresaclienteNew != null && !empresaclienteNew.equals(empresaclienteOld)) {
                empresaclienteNew.getContratistaList().add(contratista);
                empresaclienteNew = em.merge(empresaclienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ContratistaPK id = contratista.getContratistaPK();
                if (findContratista(id) == null) {
                    throw new NonexistentEntityException("The contratista with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ContratistaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contratista contratista;
            try {
                contratista = em.getReference(Contratista.class, id);
                contratista.getContratistaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contratista with id " + id + " no longer exists.", enfe);
            }
            Empresacliente empresacliente = contratista.getEmpresacliente();
            if (empresacliente != null) {
                empresacliente.getContratistaList().remove(contratista);
                empresacliente = em.merge(empresacliente);
            }
            em.remove(contratista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contratista> findContratistaEntities() {
        return findContratistaEntities(true, -1, -1);
    }

    public List<Contratista> findContratistaEntities(int maxResults, int firstResult) {
        return findContratistaEntities(false, maxResults, firstResult);
    }

    private List<Contratista> findContratistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contratista.class));
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

    public Contratista findContratista(ContratistaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contratista.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contratista> rt = cq.from(Contratista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
