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
import Entidad.Contratista;
import java.util.ArrayList;
import java.util.List;
import Entidad.Contrato;
import Entidad.Empresacliente;
import Entidad.controlador.exceptions.IllegalOrphanException;
import Entidad.controlador.exceptions.NonexistentEntityException;
import Entidad.controlador.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author kil_5
 */
public class EmpresaclienteJpaController implements Serializable {

    public EmpresaclienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresacliente empresacliente) throws PreexistingEntityException, Exception {
        if (empresacliente.getContratistaList() == null) {
            empresacliente.setContratistaList(new ArrayList<Contratista>());
        }
        if (empresacliente.getContratoList() == null) {
            empresacliente.setContratoList(new ArrayList<Contrato>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Contratista> attachedContratistaList = new ArrayList<Contratista>();
            for (Contratista contratistaListContratistaToAttach : empresacliente.getContratistaList()) {
                contratistaListContratistaToAttach = em.getReference(contratistaListContratistaToAttach.getClass(), contratistaListContratistaToAttach.getContratistaPK());
                attachedContratistaList.add(contratistaListContratistaToAttach);
            }
            empresacliente.setContratistaList(attachedContratistaList);
            List<Contrato> attachedContratoList = new ArrayList<Contrato>();
            for (Contrato contratoListContratoToAttach : empresacliente.getContratoList()) {
                contratoListContratoToAttach = em.getReference(contratoListContratoToAttach.getClass(), contratoListContratoToAttach.getIdContrato());
                attachedContratoList.add(contratoListContratoToAttach);
            }
            empresacliente.setContratoList(attachedContratoList);
            em.persist(empresacliente);
            for (Contratista contratistaListContratista : empresacliente.getContratistaList()) {
                Empresacliente oldEmpresaclienteOfContratistaListContratista = contratistaListContratista.getEmpresacliente();
                contratistaListContratista.setEmpresacliente(empresacliente);
                contratistaListContratista = em.merge(contratistaListContratista);
                if (oldEmpresaclienteOfContratistaListContratista != null) {
                    oldEmpresaclienteOfContratistaListContratista.getContratistaList().remove(contratistaListContratista);
                    oldEmpresaclienteOfContratistaListContratista = em.merge(oldEmpresaclienteOfContratistaListContratista);
                }
            }
            for (Contrato contratoListContrato : empresacliente.getContratoList()) {
                Empresacliente oldEmpresaClienteidEmpresaClienteOfContratoListContrato = contratoListContrato.getEmpresaClienteidEmpresaCliente();
                contratoListContrato.setEmpresaClienteidEmpresaCliente(empresacliente);
                contratoListContrato = em.merge(contratoListContrato);
                if (oldEmpresaClienteidEmpresaClienteOfContratoListContrato != null) {
                    oldEmpresaClienteidEmpresaClienteOfContratoListContrato.getContratoList().remove(contratoListContrato);
                    oldEmpresaClienteidEmpresaClienteOfContratoListContrato = em.merge(oldEmpresaClienteidEmpresaClienteOfContratoListContrato);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpresacliente(empresacliente.getIdEmpresaCliente()) != null) {
                throw new PreexistingEntityException("Empresacliente " + empresacliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresacliente empresacliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresacliente persistentEmpresacliente = em.find(Empresacliente.class, empresacliente.getIdEmpresaCliente());
            List<Contratista> contratistaListOld = persistentEmpresacliente.getContratistaList();
            List<Contratista> contratistaListNew = empresacliente.getContratistaList();
            List<Contrato> contratoListOld = persistentEmpresacliente.getContratoList();
            List<Contrato> contratoListNew = empresacliente.getContratoList();
            List<String> illegalOrphanMessages = null;
            for (Contratista contratistaListOldContratista : contratistaListOld) {
                if (!contratistaListNew.contains(contratistaListOldContratista)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contratista " + contratistaListOldContratista + " since its empresacliente field is not nullable.");
                }
            }
            for (Contrato contratoListOldContrato : contratoListOld) {
                if (!contratoListNew.contains(contratoListOldContrato)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contrato " + contratoListOldContrato + " since its empresaClienteidEmpresaCliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Contratista> attachedContratistaListNew = new ArrayList<Contratista>();
            for (Contratista contratistaListNewContratistaToAttach : contratistaListNew) {
                contratistaListNewContratistaToAttach = em.getReference(contratistaListNewContratistaToAttach.getClass(), contratistaListNewContratistaToAttach.getContratistaPK());
                attachedContratistaListNew.add(contratistaListNewContratistaToAttach);
            }
            contratistaListNew = attachedContratistaListNew;
            empresacliente.setContratistaList(contratistaListNew);
            List<Contrato> attachedContratoListNew = new ArrayList<Contrato>();
            for (Contrato contratoListNewContratoToAttach : contratoListNew) {
                contratoListNewContratoToAttach = em.getReference(contratoListNewContratoToAttach.getClass(), contratoListNewContratoToAttach.getIdContrato());
                attachedContratoListNew.add(contratoListNewContratoToAttach);
            }
            contratoListNew = attachedContratoListNew;
            empresacliente.setContratoList(contratoListNew);
            empresacliente = em.merge(empresacliente);
            for (Contratista contratistaListNewContratista : contratistaListNew) {
                if (!contratistaListOld.contains(contratistaListNewContratista)) {
                    Empresacliente oldEmpresaclienteOfContratistaListNewContratista = contratistaListNewContratista.getEmpresacliente();
                    contratistaListNewContratista.setEmpresacliente(empresacliente);
                    contratistaListNewContratista = em.merge(contratistaListNewContratista);
                    if (oldEmpresaclienteOfContratistaListNewContratista != null && !oldEmpresaclienteOfContratistaListNewContratista.equals(empresacliente)) {
                        oldEmpresaclienteOfContratistaListNewContratista.getContratistaList().remove(contratistaListNewContratista);
                        oldEmpresaclienteOfContratistaListNewContratista = em.merge(oldEmpresaclienteOfContratistaListNewContratista);
                    }
                }
            }
            for (Contrato contratoListNewContrato : contratoListNew) {
                if (!contratoListOld.contains(contratoListNewContrato)) {
                    Empresacliente oldEmpresaClienteidEmpresaClienteOfContratoListNewContrato = contratoListNewContrato.getEmpresaClienteidEmpresaCliente();
                    contratoListNewContrato.setEmpresaClienteidEmpresaCliente(empresacliente);
                    contratoListNewContrato = em.merge(contratoListNewContrato);
                    if (oldEmpresaClienteidEmpresaClienteOfContratoListNewContrato != null && !oldEmpresaClienteidEmpresaClienteOfContratoListNewContrato.equals(empresacliente)) {
                        oldEmpresaClienteidEmpresaClienteOfContratoListNewContrato.getContratoList().remove(contratoListNewContrato);
                        oldEmpresaClienteidEmpresaClienteOfContratoListNewContrato = em.merge(oldEmpresaClienteidEmpresaClienteOfContratoListNewContrato);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empresacliente.getIdEmpresaCliente();
                if (findEmpresacliente(id) == null) {
                    throw new NonexistentEntityException("The empresacliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresacliente empresacliente;
            try {
                empresacliente = em.getReference(Empresacliente.class, id);
                empresacliente.getIdEmpresaCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresacliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Contratista> contratistaListOrphanCheck = empresacliente.getContratistaList();
            for (Contratista contratistaListOrphanCheckContratista : contratistaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresacliente (" + empresacliente + ") cannot be destroyed since the Contratista " + contratistaListOrphanCheckContratista + " in its contratistaList field has a non-nullable empresacliente field.");
            }
            List<Contrato> contratoListOrphanCheck = empresacliente.getContratoList();
            for (Contrato contratoListOrphanCheckContrato : contratoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresacliente (" + empresacliente + ") cannot be destroyed since the Contrato " + contratoListOrphanCheckContrato + " in its contratoList field has a non-nullable empresaClienteidEmpresaCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empresacliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresacliente> findEmpresaclienteEntities() {
        return findEmpresaclienteEntities(true, -1, -1);
    }

    public List<Empresacliente> findEmpresaclienteEntities(int maxResults, int firstResult) {
        return findEmpresaclienteEntities(false, maxResults, firstResult);
    }

    private List<Empresacliente> findEmpresaclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresacliente.class));
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

    public Empresacliente findEmpresacliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresacliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresaclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresacliente> rt = cq.from(Empresacliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
