/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad.controlador;

import Entidad.Inventarioempresa;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidad.Mueble;
import Entidad.controlador.exceptions.NonexistentEntityException;
import Entidad.controlador.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author kil_5
 */
public class InventarioempresaJpaController implements Serializable {

    public InventarioempresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Inventarioempresa inventarioempresa) throws PreexistingEntityException, Exception {
        if (inventarioempresa.getMuebleList() == null) {
            inventarioempresa.setMuebleList(new ArrayList<Mueble>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Mueble> attachedMuebleList = new ArrayList<Mueble>();
            for (Mueble muebleListMuebleToAttach : inventarioempresa.getMuebleList()) {
                muebleListMuebleToAttach = em.getReference(muebleListMuebleToAttach.getClass(), muebleListMuebleToAttach.getIdMueble());
                attachedMuebleList.add(muebleListMuebleToAttach);
            }
            inventarioempresa.setMuebleList(attachedMuebleList);
            em.persist(inventarioempresa);
            for (Mueble muebleListMueble : inventarioempresa.getMuebleList()) {
                Inventarioempresa oldInventarioEmpresaidInventarioEmpresaOfMuebleListMueble = muebleListMueble.getInventarioEmpresaidInventarioEmpresa();
                muebleListMueble.setInventarioEmpresaidInventarioEmpresa(inventarioempresa);
                muebleListMueble = em.merge(muebleListMueble);
                if (oldInventarioEmpresaidInventarioEmpresaOfMuebleListMueble != null) {
                    oldInventarioEmpresaidInventarioEmpresaOfMuebleListMueble.getMuebleList().remove(muebleListMueble);
                    oldInventarioEmpresaidInventarioEmpresaOfMuebleListMueble = em.merge(oldInventarioEmpresaidInventarioEmpresaOfMuebleListMueble);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInventarioempresa(inventarioempresa.getIdInventarioEmpresa()) != null) {
                throw new PreexistingEntityException("Inventarioempresa " + inventarioempresa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Inventarioempresa inventarioempresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inventarioempresa persistentInventarioempresa = em.find(Inventarioempresa.class, inventarioempresa.getIdInventarioEmpresa());
            List<Mueble> muebleListOld = persistentInventarioempresa.getMuebleList();
            List<Mueble> muebleListNew = inventarioempresa.getMuebleList();
            List<Mueble> attachedMuebleListNew = new ArrayList<Mueble>();
            for (Mueble muebleListNewMuebleToAttach : muebleListNew) {
                muebleListNewMuebleToAttach = em.getReference(muebleListNewMuebleToAttach.getClass(), muebleListNewMuebleToAttach.getIdMueble());
                attachedMuebleListNew.add(muebleListNewMuebleToAttach);
            }
            muebleListNew = attachedMuebleListNew;
            inventarioempresa.setMuebleList(muebleListNew);
            inventarioempresa = em.merge(inventarioempresa);
            for (Mueble muebleListOldMueble : muebleListOld) {
                if (!muebleListNew.contains(muebleListOldMueble)) {
                    muebleListOldMueble.setInventarioEmpresaidInventarioEmpresa(null);
                    muebleListOldMueble = em.merge(muebleListOldMueble);
                }
            }
            for (Mueble muebleListNewMueble : muebleListNew) {
                if (!muebleListOld.contains(muebleListNewMueble)) {
                    Inventarioempresa oldInventarioEmpresaidInventarioEmpresaOfMuebleListNewMueble = muebleListNewMueble.getInventarioEmpresaidInventarioEmpresa();
                    muebleListNewMueble.setInventarioEmpresaidInventarioEmpresa(inventarioempresa);
                    muebleListNewMueble = em.merge(muebleListNewMueble);
                    if (oldInventarioEmpresaidInventarioEmpresaOfMuebleListNewMueble != null && !oldInventarioEmpresaidInventarioEmpresaOfMuebleListNewMueble.equals(inventarioempresa)) {
                        oldInventarioEmpresaidInventarioEmpresaOfMuebleListNewMueble.getMuebleList().remove(muebleListNewMueble);
                        oldInventarioEmpresaidInventarioEmpresaOfMuebleListNewMueble = em.merge(oldInventarioEmpresaidInventarioEmpresaOfMuebleListNewMueble);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = inventarioempresa.getIdInventarioEmpresa();
                if (findInventarioempresa(id) == null) {
                    throw new NonexistentEntityException("The inventarioempresa with id " + id + " no longer exists.");
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
            Inventarioempresa inventarioempresa;
            try {
                inventarioempresa = em.getReference(Inventarioempresa.class, id);
                inventarioempresa.getIdInventarioEmpresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inventarioempresa with id " + id + " no longer exists.", enfe);
            }
            List<Mueble> muebleList = inventarioempresa.getMuebleList();
            for (Mueble muebleListMueble : muebleList) {
                muebleListMueble.setInventarioEmpresaidInventarioEmpresa(null);
                muebleListMueble = em.merge(muebleListMueble);
            }
            em.remove(inventarioempresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Inventarioempresa> findInventarioempresaEntities() {
        return findInventarioempresaEntities(true, -1, -1);
    }

    public List<Inventarioempresa> findInventarioempresaEntities(int maxResults, int firstResult) {
        return findInventarioempresaEntities(false, maxResults, firstResult);
    }

    private List<Inventarioempresa> findInventarioempresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Inventarioempresa.class));
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

    public Inventarioempresa findInventarioempresa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inventarioempresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getInventarioempresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Inventarioempresa> rt = cq.from(Inventarioempresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
