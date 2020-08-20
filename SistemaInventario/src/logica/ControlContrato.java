/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Entidad.Contrato;
import Entidad.Empresacliente;
import Entidad.Inventarioempresa;
import Entidad.Mueble;
import Entidad.controlador.ContratoJpaController;
import Entidad.controlador.EmpresaclienteJpaController;
import Entidad.controlador.InventarioempresaJpaController;
import Entidad.controlador.MuebleJpaController;
import Entidad.controlador.exceptions.NonexistentEntityException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kil_5
 */
public class ControlContrato {
    private ContratoJpaController controlContrato;
    private EmpresaclienteJpaController controlEmpresa;
    private MuebleJpaController controlMueble;
    private InventarioempresaJpaController controlInventario;
    
    public int crearContrato(Date inicio, Date fin, int costo, int id){
        try {
            int duracion= fin.getMonth()-inicio.getMonth();
            ConexionBD.initEntityManager();
            controlContrato= new ContratoJpaController(ConexionBD.getEmf());
            Contrato contrato= new Contrato(id, inicio, fin, costo, duracion);
            controlContrato.create(contrato);
            ConexionBD.closeEntityManager();
            return id;
        } catch (Exception ex) {
            return 0;
        }
    }
    
    public void contratoEmpresa(int contrato, String empresa){
        ConexionBD.initEntityManager();
        ConexionBD.getEm().getTransaction().begin();
        controlContrato=  new ContratoJpaController(ConexionBD.getEmf());
        Contrato contratoNuevo = controlContrato.findContrato(contrato);
        Empresacliente empresaContrato = ConexionBD.getEm().createNamedQuery("Empresacliente.findByNombreEmpresa", Empresacliente.class).setParameter("nombreEmpresa", empresa).getSingleResult();
        contratoNuevo.setEmpresaClienteidEmpresaCliente(empresaContrato);
        try {
            controlContrato.destroy(contrato);
            controlContrato.create(contratoNuevo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControlContrato.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControlContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConexionBD.getEm().getTransaction().commit();
        ConexionBD.closeEntityManager();
        
    }
    
    public void borrarContrato(int contrato){
        List<Mueble> muebles;
        ConexionBD.initEntityManager();
        ConexionBD.getEm().getTransaction().begin();
        controlContrato=  new ContratoJpaController(ConexionBD.getEmf());
        controlInventario = new InventarioempresaJpaController(ConexionBD.getEmf());
        Contrato contratoB = controlContrato.findContrato(contrato);
        Inventarioempresa amo = ConexionBD.getEm().find(Inventarioempresa.class, 1);
        muebles = ConexionBD.getEm().createNamedQuery("Mueble.findByContrato").setParameter("idContrato", contratoB).getResultList();
        for(Mueble mueble: muebles){
            amo.setDisponibleEnBodega(amo.getDisponibleEnBodega()+1);
            amo.setCantidadAlquilada(amo.getCantidadAlquilada()-1);
            mueble.setContratoidContrato(null);
            mueble.setInventarioEmpresaidInventarioEmpresa(amo);
        }
        try {
            controlContrato.destroy(contrato);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControlContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConexionBD.getEm().getTransaction().commit();
        ConexionBD.closeEntityManager();
    }
}
