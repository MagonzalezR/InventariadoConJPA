/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Entidad.Contratista;
import Entidad.ContratistaPK;
import Entidad.Contrato;
import Entidad.Empresacliente;
import Entidad.controlador.ContratistaJpaController;
import Entidad.controlador.EmpresaclienteJpaController;
import Entidad.controlador.exceptions.IllegalOrphanException;
import Entidad.controlador.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kil_5
 */
public class ControlCliente {

    private ContratistaJpaController controlContratista;
    private EmpresaclienteJpaController controlCliente;

    public ControlCliente() {

    }

    public boolean AgregarCliente(String nomCont, String apeCont, String telCont, String corrCont, String nomEmp, String nitEmp, String dirEmp, String telEmp) {
        try {
            int numEmp;
            ConexionBD.initEntityManager();
            controlContratista = new ContratistaJpaController(ConexionBD.getEmf());
            controlCliente = new EmpresaclienteJpaController(ConexionBD.getEmf());
            numEmp = buscarVacio();
            Empresacliente empresa = new Empresacliente(numEmp, nomEmp, nitEmp, dirEmp, telEmp);
            controlCliente.create(empresa);
            ContratistaPK pkCont = new ContratistaPK(numEmp, numEmp);
            Contratista cont = new Contratista(pkCont, nomCont, apeCont, telCont, corrCont);
            cont.setEmpresacliente(empresa);
            controlContratista.create(cont);
            ConexionBD.closeEntityManager();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Empresacliente buscarPorNombre(String nombre) {
        ConexionBD.initEntityManager();
        controlContratista = new ContratistaJpaController(ConexionBD.getEmf());
        controlCliente = new EmpresaclienteJpaController(ConexionBD.getEmf());
        List<Empresacliente> empresas = controlCliente.findEmpresaclienteEntities();
        for (Empresacliente empresa : empresas) {
            if (empresa.getNombreEmpresa().equals(nombre)) {
                ConexionBD.closeEntityManager();
                return empresa;
            }
        }
        ConexionBD.closeEntityManager();
        return null;
    }

    public boolean retirarCliente(String nombre) {
        Empresacliente empresa = buscarPorNombre(nombre);
        ConexionBD.initEntityManager();
        ConexionBD.getEm().getTransaction().begin();
        controlContratista = new ContratistaJpaController(ConexionBD.getEmf());
        controlCliente = new EmpresaclienteJpaController(ConexionBD.getEmf());
        ContratistaPK pk = new ContratistaPK(empresa.getIdEmpresaCliente(), empresa.getIdEmpresaCliente());
        List<Contrato> contrato = ConexionBD.getEm().createNamedQuery("Contrato.findByEmpresa", Contrato.class).setParameter("empresa", empresa).getResultList();
        try {
            if (contrato.isEmpty()) {
                controlContratista.destroy(pk);
                controlCliente.destroy(empresa.getIdEmpresaCliente());
                ConexionBD.getEm().getTransaction().commit();
                ConexionBD.closeEntityManager();
                return true;
            }
        } catch (NonexistentEntityException | IllegalOrphanException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConexionBD.getEm().getTransaction().commit();
        ConexionBD.closeEntityManager();
        return false;
    }

    public String[] clientes() {
        int i = 0;
        ConexionBD.initEntityManager();
        controlCliente = new EmpresaclienteJpaController(ConexionBD.getEmf());
        String[] empresas = new String[controlCliente.getEmpresaclienteCount()];
        List<Empresacliente> empr = controlCliente.findEmpresaclienteEntities();
        for (Empresacliente empresa : empr) {
            empresas[i++] = empresa.getNombreEmpresa();
        }
        ConexionBD.closeEntityManager();
        return empresas;
    }
    
    private int buscarVacio(){
        int loop = controlCliente.getEmpresaclienteCount();
        int retorno=1;
        for(int i=1; i<loop+1;i++){
            if(controlCliente.findEmpresacliente(i)!=null){
                retorno++;
            }else {
                return i;
            }
        }
        return retorno;
    }
}
