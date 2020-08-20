/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Entidad.Contrato;
import Entidad.Inventarioempresa;
import Entidad.Mueble;
import Entidad.controlador.ContratoJpaController;
import Entidad.controlador.MuebleJpaController;
import Entidad.controlador.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kil_5
 */
public class ControlMueble {

    private MuebleJpaController controlMueble;
    private ContratoJpaController controlContrato;

    public ControlMueble() {

    }

    public boolean agregar(String nom, String tipo, int costo, int id) {
        ConexionBD.initEntityManager();
        ConexionBD.getEm().getTransaction().begin();
        controlMueble = new MuebleJpaController(ConexionBD.getEmf());
        Inventarioempresa inventario = ConexionBD.getEm().find(Inventarioempresa.class, 1);
        Mueble mueble = new Mueble(id, nom, tipo, costo);
        mueble.setInventarioEmpresaidInventarioEmpresa(inventario);
        try {
            controlMueble.create(mueble);
            inventario.setDisponibleEnBodega(inventario.getDisponibleEnBodega() + 1);
            inventario.setTotalExistencias(inventario.getTotalExistencias() + 1);
            ConexionBD.getEm().getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(ControlMueble.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        ConexionBD.closeEntityManager();
        return true;
    }

    public String buscarMueble(int id) {
        ConexionBD.initEntityManager();
        controlMueble = new MuebleJpaController(ConexionBD.getEmf());
        Mueble mueble = controlMueble.findMueble(id);
        ConexionBD.closeEntityManager();
        return mueble.toString();
    }

    public boolean borrarMueble(int id) {
        try {
            ConexionBD.initEntityManager();
            ConexionBD.getEm().getTransaction().begin();
            controlMueble = new MuebleJpaController(ConexionBD.getEmf());
            if (controlMueble.findMueble(id).getContratoidContrato() == null) {
                controlMueble.destroy(id);
                Inventarioempresa inventario = ConexionBD.getEm().find(Inventarioempresa.class, 1);
                inventario.setDisponibleEnBodega(inventario.getDisponibleEnBodega() - 1);
                inventario.setTotalExistencias(inventario.getTotalExistencias() - 1);
                ConexionBD.getEm().getTransaction().commit();
                ConexionBD.closeEntityManager();
                return true;
            }
            ConexionBD.closeEntityManager();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControlMueble.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public String contarMuebles(String mueble) {
        ConexionBD.initEntityManager();
        ConexionBD.getEm().getTransaction().begin();
        Inventarioempresa empresa= ConexionBD.getEm().find(Inventarioempresa.class, 1);
        Long result =ConexionBD.getEm().createNamedQuery("Mueble.countAllTypes", Long.class).setParameter("tipoMueble", mueble).setParameter("num", empresa).getSingleResult();
        ConexionBD.closeEntityManager();
        return String.valueOf(result);
    }
    
    public List tipos(){
        List retorno;
        ConexionBD.initEntityManager();
        ConexionBD.getEm().getTransaction().begin();
        retorno = ConexionBD.getEm().createNamedQuery("Mueble.getAllTypes").getResultList();
        ConexionBD.closeEntityManager();
        return retorno;
    }
    public List muebles(String tipo){
        List retorno;
        ConexionBD.initEntityManager();
        ConexionBD.getEm().getTransaction().begin();
        retorno = ConexionBD.getEm().createNamedQuery("Mueble.getAllMuebles").setParameter("tipoMueble", tipo).getResultList();
        ConexionBD.closeEntityManager();
        return retorno;
    }
    
    public void añadirAContrato(String nombre, int cantidad, int contrato){
        List<Mueble> muebles;
        ConexionBD.initEntityManager();
        ConexionBD.getEm().getTransaction().begin();
        controlContrato= new ContratoJpaController(ConexionBD.getEmf());
        Contrato cont = controlContrato.findContrato(contrato);
        Inventarioempresa empresa= ConexionBD.getEm().find(Inventarioempresa.class, 1);
        muebles = ConexionBD.getEm().createNamedQuery("Mueble.getAllInventory", Mueble.class).setParameter("nombreMueble", nombre).setParameter("num",empresa).getResultList().subList(0,cantidad);
        for(Mueble m:muebles){
            empresa.setDisponibleEnBodega(empresa.getDisponibleEnBodega()-1);
            empresa.setCantidadAlquilada(empresa.getCantidadAlquilada()+1);
            m.setInventarioEmpresaidInventarioEmpresa(null);
            m.setContratoidContrato(cont);
        }
        ConexionBD.getEm().getTransaction().commit();
        ConexionBD.closeEntityManager();
    }
}
