/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Entidad.Inventarioempresa;
import Entidad.Mueble;
import Entidad.controlador.InventarioempresaJpaController;
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
    private InventarioempresaJpaController controlInv;
    private int numMueble;

    public ControlMueble() {

    }

    public boolean agregar(String nom, String tipo, int costo, int id) {
        ConexionBD.initEntityManager();
        ConexionBD.getEm().getTransaction().begin();
        controlMueble = new MuebleJpaController(ConexionBD.getEmf());
        controlInv = new InventarioempresaJpaController(ConexionBD.getEmf());
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
            if (controlMueble.findMueble(id).getContratoidContrato() != null) {
                controlMueble.destroy(id);
                controlInv = new InventarioempresaJpaController(ConexionBD.getEmf());
                Inventarioempresa inventario = ConexionBD.getEm().find(Inventarioempresa.class, 1);
                inventario.setDisponibleEnBodega(inventario.getDisponibleEnBodega() - 1);
                inventario.setTotalExistencias(inventario.getTotalExistencias() - 1);
                ConexionBD.closeEntityManager();
                return true;
            } else {
                ConexionBD.closeEntityManager();
                return false;
            }

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControlMueble.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void contarMuebles(String mueble) {
        ConexionBD.initEntityManager();
        ConexionBD.getEm().getTransaction().begin();
        System.out.println(ConexionBD.getEm().createNamedQuery("Mueble.countAllTypes").setParameter("tipoMueble", mueble).getSingleResult());
        ConexionBD.closeEntityManager();
        
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
}
