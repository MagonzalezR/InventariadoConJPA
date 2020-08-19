/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad.controlador;

import Entidad.Contrato;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidad.Empresacliente;
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
public class ContratoJpaController implements Serializable {

    public ContratoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contrato contrato) throws PreexistingEntityException, Exception {
        if (contrato.getMuebleList() == null) {
            contrato.setMuebleList(new ArrayList<Mueble>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresacliente empresaClienteidEmpresaCliente = contrato.getEmpresaClienteidEmpresaCliente();
            if (empresaClienteidEmpresaCliente != null) {
                empresaClienteidEmpresaCliente = em.getReference(empresaClienteidEmpresaCliente.getClass(), empresaClienteidEmpresaCliente.getIdEmpresaCliente());
                contrato.setEmpresaClienteidEmpresaCliente(empresaClienteidEmpresaCliente);
            }
            List<Mueble> attachedMuebleList = new ArrayList<Mueble>();
            for (Mueble muebleListMuebleToAttach : contrato.getMuebleList()) {
                muebleListMuebleToAttach = em.getReference(muebleListMuebleToAttach.getClass(), muebleListMuebleToAttach.getIdMueble());
                attachedMuebleList.add(muebleListMuebleToAttach);
            }
            contrato.setMuebleList(attachedMuebleList);
            em.persist(contrato);
            if (empresaClienteidEmpresaCliente != null) {
                empresaClienteidEmpresaCliente.getContratoList().add(contrato);
                empresaClienteidEmpresaCliente = em.merge(empresaClienteidEmpresaCliente);
            }
            for (Mueble muebleListMueble : contrato.getMuebleList()) {
                Contrato oldContratoidContratoOfMuebleListMueble = muebleListMueble.getContratoidContrato();
                muebleListMueble.setContratoidContrato(contrato);
                muebleListMueble = em.merge(muebleListMueble);
                if (oldContratoidContratoOfMuebleListMueble != null) {
                    oldContratoidContratoOfMuebleListMueble.getMuebleList().remove(muebleListMueble);
                    oldContratoidContratoOfMuebleListMueble = em.merge(oldContratoidContratoOfMuebleListMueble);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContrato(contrato.getIdContrato()) != null) {
                throw new PreexistingEntityException("Contrato " + contrato + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contrato contrato) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contrato persistentContrato = em.find(Contrato.class, contrato.getIdContrato());
            Empresacliente empresaClienteidEmpresaClienteOld = persistentContrato.getEmpresaClienteidEmpresaCliente();
            Empresacliente empresaClienteidEmpresaClienteNew = contrato.getEmpresaClienteidEmpresaCliente();
            List<Mueble> muebleListOld = persistentContrato.getMuebleList();
            List<Mueble> muebleListNew = contrato.getMuebleList();
            if (empresaClienteidEmpresaClienteNew != null) {
                empresaClienteidEmpresaClienteNew = em.getReference(empresaClienteidEmpresaClienteNew.getClass(), empresaClienteidEmpresaClienteNew.getIdEmpresaCliente());
                contrato.setEmpresaClienteidEmpresaCliente(empresaClienteidEmpresaClienteNew);
            }
            List<Mueble> attachedMuebleListNew = new ArrayList<Mueble>();
            for (Mueble muebleListNewMuebleToAttach : muebleListNew) {
                muebleListNewMuebleToAttach = em.getReference(muebleListNewMuebleToAttach.getClass(), muebleListNewMuebleToAttach.getIdMueble());
                attachedMuebleListNew.add(muebleListNewMuebleToAttach);
            }
            muebleListNew = attachedMuebleListNew;
            contrato.setMuebleList(muebleListNew);
            contrato = em.merge(contrato);
            if (empresaClienteidEmpresaClienteOld != null && !empresaClienteidEmpresaClienteOld.equals(empresaClienteidEmpresaClienteNew)) {
                empresaClienteidEmpresaClienteOld.getContratoList().remove(contrato);
                empresaClienteidEmpresaClienteOld = em.merge(empresaClienteidEmpresaClienteOld);
            }
            if (empresaClienteidEmpresaClienteNew != null && !empresaClienteidEmpresaClienteNew.equals(empresaClienteidEmpresaClienteOld)) {
                empresaClienteidEmpresaClienteNew.getContratoList().add(contrato);
                empresaClienteidEmpresaClienteNew = em.merge(empresaClienteidEmpresaClienteNew);
            }
            for (Mueble muebleListOldMueble : muebleListOld) {
                if (!muebleListNew.contains(muebleListOldMueble)) {
                    muebleListOldMueble.setContratoidContrato(null);
                    muebleListOldMueble = em.merge(muebleListOldMueble);
                }
            }
            for (Mueble muebleListNewMueble : muebleListNew) {
                if (!muebleListOld.contains(muebleListNewMueble)) {
                    Contrato oldContratoidContratoOfMuebleListNewMueble = muebleListNewMueble.getContratoidContrato();
                    muebleListNewMueble.setContratoidContrato(contrato);
                    muebleListNewMueble = em.merge(muebleListNewMueble);
                    if (oldContratoidContratoOfMuebleListNewMueble != null && !oldContratoidContratoOfMuebleListNewMueble.equals(contrato)) {
                        oldContratoidContratoOfMuebleListNewMueble.getMuebleList().remove(muebleListNewMueble);
                        oldContratoidContratoOfMuebleListNewMueble = em.merge(oldContratoidContratoOfMuebleListNewMueble);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contrato.getIdContrato();
                if (findContrato(id) == null) {
                    throw new NonexistentEntityException("The contrato with id " + id + " no longer exists.");
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
            Contrato contrato;
            try {
                contrato = em.getReference(Contrato.class, id);
                contrato.getIdContrato();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contrato with id " + id + " no longer exists.", enfe);
            }
            Empresacliente empresaClienteidEmpresaCliente = contrato.getEmpresaClienteidEmpresaCliente();
            if (empresaClienteidEmpresaCliente != null) {
                empresaClienteidEmpresaCliente.getContratoList().remove(contrato);
                empresaClienteidEmpresaCliente = em.merge(empresaClienteidEmpresaCliente);
            }
            List<Mueble> muebleList = contrato.getMuebleList();
            for (Mueble muebleListMueble : muebleList) {
                muebleListMueble.setContratoidContrato(null);
                muebleListMueble = em.merge(muebleListMueble);
            }
            em.remove(contrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contrato> findContratoEntities() {
        return findContratoEntities(true, -1, -1);
    }

    public List<Contrato> findContratoEntities(int maxResults, int firstResult) {
        return findContratoEntities(false, maxResults, firstResult);
    }

    private List<Contrato> findContratoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contrato.class));
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

    public Contrato findContrato(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contrato.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contrato> rt = cq.from(Contrato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
