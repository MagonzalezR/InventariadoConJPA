/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Entidad.Contratista;
import Entidad.ContratistaPK;
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
    private int numCont;
    private int numEmp;

    public ControlCliente() {

    }

    public boolean AgregarCliente(String nomCont, String apeCont, String telCont, String corrCont, String nomEmp, String nitEmp, String dirEmp, String telEmp) {
        try {
            ConexionBD.initEntityManager();
            controlContratista = new ContratistaJpaController(ConexionBD.getEmf());
            controlCliente = new EmpresaclienteJpaController(ConexionBD.getEmf());
            numEmp = controlCliente.getEmpresaclienteCount() + 1;
            numCont = controlContratista.getContratistaCount() + 1;
            Empresacliente empresa = new Empresacliente(numEmp, nomEmp, nitEmp, dirEmp, telEmp);
            controlCliente.create(empresa);
            ContratistaPK pkCont = new ContratistaPK(numCont, numEmp);
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
        controlContratista = new ContratistaJpaController(ConexionBD.getEmf());
        controlCliente = new EmpresaclienteJpaController(ConexionBD.getEmf());
        ContratistaPK pk = new ContratistaPK(empresa.getIdEmpresaCliente(), empresa.getIdEmpresaCliente());
        try {
            controlContratista.destroy(pk);
            controlCliente.destroy(empresa.getIdEmpresaCliente());
            ConexionBD.closeEntityManager();
            return true;
        } catch (NonexistentEntityException | IllegalOrphanException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConexionBD.closeEntityManager();
        return false;
    }
    
    public String[] clientes(){
        int i=0;
        ConexionBD.initEntityManager();
        controlCliente = new EmpresaclienteJpaController(ConexionBD.getEmf());
        String[] empresas = new String[controlCliente.getEmpresaclienteCount()];
        List<Empresacliente> empr = controlCliente.findEmpresaclienteEntities();
        for (Empresacliente empresa : empr) {
            empresas[i++]=empresa.getNombreEmpresa();
        }
        ConexionBD.closeEntityManager();
        return empresas;
    }
}
