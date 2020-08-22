/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Entidad.Empresacliente;
import ControlVistas.*;
import java.sql.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author kil_5
 */
public class Controlador {

    private VistaAction login;
    private VistaMenuAction menu;
    private VistaAgClienteAction agrCliente;
    private VistaAgMuebleAction agrMueble;
    private VistaRetClienteAction retCliente;
    private VistaRetMuebleAction retMueble;
    private VistaContratoAction contr;
    private VistaAdmMuebleAction admMueble;
    private ControlCliente contCliente;
    private ControlMueble contMueble;
    private ControlContrato contContrato;

    public Controlador() {

        login = new VistaAction(this);
//        login.crearVista();
//        ConexionBD.initEntityManager();
//        ConexionBD.closeEntityManager();
        contCliente = new ControlCliente();
        contMueble = new ControlMueble();
        contContrato = new ControlContrato();
        menu = new VistaMenuAction(this);
        agrCliente = new VistaAgClienteAction(this);
        agrMueble = new VistaAgMuebleAction(this);
        retCliente = new VistaRetClienteAction(this);
        retMueble = new VistaRetMuebleAction(this);
        contr = new VistaContratoAction(this);
        admMueble = new VistaAdmMuebleAction(this);
        contr.crearVista();
    }

    public boolean validarClave(String nombre, String contr) {
        return nombre.equals("DianaAdmin") && contr.equals("AdminPass");
    }

    public void elegirOpcion(int opc) {
        switch (opc) {
            case 1:
                agrCliente.crearVista();
                break;
            case 2:
                agrMueble.crearVista();
                break;
            case 3:
                retCliente.crearVista();
                break;
            case 4:
                retMueble.crearVista();
                break;
            case 5:
                contr.crearVista();
                break;
            case 6:
                admMueble.crearVista();
                break;
            default:
                break;
        }
    }

    public void irAMenu() {
        menu.iniciarVista();
    }

    public void agregarCliente(String nomCont, String apeCont, String telCont, String corrCont, String nomEmp, String nitEmp, String dirEmp, String telEmp) {
        if (contCliente.AgregarCliente(nomCont, apeCont, telCont, corrCont, nomEmp, nitEmp, dirEmp, telEmp)) {
            agrCliente.mensaje("El cliente fue agregado exitosamente");
        } else {
            agrCliente.mensaje("El cliente no ha podido ser agregado");
        }
    }

    public boolean buscarCliente(String nombre) {
        Empresacliente empresa = contCliente.buscarPorNombre(nombre);
        if (empresa != null) {
            retCliente.setEmpresa(empresa.toString());
            return true;
        }
        return false;
    }

    public void quitarCliente(String nombre) {
        if (contCliente.retirarCliente(nombre)) {
            retCliente.mensaje("Cliente retirado exitosamente");
        } else {
            retCliente.mensaje("No se ha podido retirar el cliente");
        }
    }

    public void agregarMueble(String nom, String tipo, int id) {
        if (contMueble.agregar(nom, tipo, id)) {
            agrMueble.mensaje("El mueble ha sido agregado correctamente");
        } else {
            agrMueble.mensaje("No se ha podido agregar el mueble");
        }
    }

    public boolean buscarMueble(int id) {
        try {
            String ret = contMueble.buscarMueble(id);
            retMueble.descripcionMueble(ret);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void quitarMueble(int id) {
        if (contMueble.borrarMueble(id)) {
            retMueble.mensaje("el mueble ha sido retirado exitosamente");
        } else {
            retMueble.mensaje("el mueble no ha sido retirado, compruebe que no aparezca en ningun contrato");
        }
    }

    public String[] traerClientes() {
        return contCliente.clientes();
    }

    public String contarMuebles(String mueble) {
        return contMueble.contarMuebles(mueble);
    }

    public String[] traerTipos() {
        List<String> muebles = contMueble.tipos();
        String[] retorno = new String[muebles.size()];
        for (int i = 0; i < muebles.size(); i++) {
            retorno[i] = muebles.get(i);
            System.out.println(retorno[i]);
        }

        return retorno;
    }

    public String[] traerMuebles(String tipo) {
        List<String> muebles = contMueble.muebles(tipo);
        String[] retorno = new String[muebles.size()];
        System.out.println(retorno + tipo);
        for (int i = 0; i < muebles.size(); i++) {
            retorno[i] = muebles.get(i);
            System.out.println(retorno[i]);
        }

        return retorno;
    }

    public int contratoProvisional(Date inicio, Date fin, int costo, int id) {
        return contContrato.crearContrato(inicio, fin, costo, id);
    }

    public void añadirMuebles(String nombre, int cantidad, int contrato) {
        contMueble.añadirAContrato(nombre, cantidad, contrato);
    }

    public void crearContrato(int contrato, String empresa) {
        System.out.println(contrato + "  "+ empresa);
        contContrato.contratoEmpresa(contrato, empresa);
    }

    public void borrarContrato(int contrato) {
        contContrato.borrarContrato(contrato);
    }
    
    public String buscarContraro(int id){
        return contContrato.buscarContrato(id);
    }
}
