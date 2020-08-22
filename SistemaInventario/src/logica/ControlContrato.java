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
    
    public int crearContrato(Date inicio, Date fin, int costo, int id){
        try {
            long duracion= fin.getTime()-inicio.getTime();
            if(duracion<=0){
                return 0;
            }else{
                duracion = costo/(fin.getMonth()-inicio.getMonth() +1);
            }
            ConexionBD.initEntityManager();
            controlContrato= new ContratoJpaController(ConexionBD.getEmf());
            Contrato contrato= new Contrato(id, inicio, fin, costo, (int)duracion);
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
        Contrato contratoNuevo = ConexionBD.getEm().find(Contrato.class, contrato);
        Empresacliente empresaContrato = ConexionBD.getEm().createNamedQuery("Empresacliente.findByNombreEmpresa", Empresacliente.class).setParameter("nombreEmpresa", empresa).getSingleResult();
        System.out.println(empresaContrato.toString());
        contratoNuevo.setEmpresaClienteidEmpresaCliente(empresaContrato);
        ConexionBD.getEm().getTransaction().commit();
        ConexionBD.closeEntityManager();
    }
    
    public void borrarContrato(int contrato){
        List<Mueble> muebles;
        ConexionBD.initEntityManager();
        ConexionBD.getEm().getTransaction().begin();
        controlContrato=  new ContratoJpaController(ConexionBD.getEmf());
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
    
    public String buscarContrato(int id){
        String retorno;
        ConexionBD.initEntityManager();
        controlContrato = new ContratoJpaController(ConexionBD.getEmf());
        Contrato contrato= controlContrato.findContrato(id);
        if(contrato==null){ 
            ConexionBD.closeEntityManager();
            return "";
        }
        retorno = contrato.toString();
        for (Mueble mueble: ConexionBD.getEm().createNamedQuery("Mueble.findByContrato", Mueble.class).setParameter("idContrato", contrato).getResultList()){
            retorno+= mueble.toString();
        }
        ConexionBD.closeEntityManager();
        return retorno;
    }
}
