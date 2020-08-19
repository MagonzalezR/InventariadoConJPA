/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Entidad.Empresacliente;
import GUI.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author kil_5
 */
public class Controlador {

    private Vista login;
    private VistaMenu menu;
    private VistaAgCliente agrCliente;
    private VistaAgMueble agrMueble;
    private VistaRetCliente retCliente;
    private VistaRetMueble retMueble;
    private VistaContrato contr;
    private VistaAdmMueble admMueble;
    private ControlCliente contCliente;
    private ControlMueble contMueble;

    public Controlador() {
        
        //login = new Vista(this);
        contCliente = new ControlCliente();
        contMueble = new ControlMueble();
        admMueble = new VistaAdmMueble(this);
    }

    public void validarClave(JFrame ventana, String nombre, String contr) {
        if (nombre.equals("DianaAdmin") && contr.equals("AdminPass")) {
            JOptionPane.showMessageDialog(ventana, "Acceso concedido");
            login.dispose();
            menu = new VistaMenu(this);
            menu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(ventana, "Acceso denegado");
        }
    }

    public void elegirOpcion(int opc) {
        switch (opc) {
            case 1:
                agrCliente = new VistaAgCliente(this);
                agrCliente.setVisible(true);
                break;
            case 2:
                agrMueble = new VistaAgMueble(this);
                agrMueble.setVisible(true);
                break;
            case 3:
                retCliente = new VistaRetCliente(this);
                retCliente.setVisible(true);
                break;
            case 4:
                retMueble = new VistaRetMueble(this);
                retMueble.setVisible(true);
                break;
            case 5:
                contr = new VistaContrato(this);
                contr.setVisible(true);
                break;
            case 6:
                admMueble = new VistaAdmMueble(this);
                admMueble.setVisible(true);
                break;
            default:
                break;
        }
    }

    public void irAMenu() {
        menu = new VistaMenu(this);
        menu.setVisible(true);
    }

    public void agregarCliente(String nomCont, String apeCont, String telCont, String corrCont, String nomEmp, String nitEmp, String dirEmp, String telEmp) {
        if (contCliente.AgregarCliente(nomCont, apeCont, telCont, corrCont, nomEmp, nitEmp, dirEmp, telEmp)) {
            JOptionPane.showMessageDialog(agrCliente, "El cliente fue agregado exitosamente");
        } else {
            JOptionPane.showMessageDialog(agrCliente, "El cliente no ha podido ser agregado");
        }
    }
    
    public boolean buscarCliente(String nombre){
        Empresacliente empresa = contCliente.buscarPorNombre(nombre);
        if (empresa!=null){
            retCliente.setEmpresa(empresa.toString());
            return true;
        }
        return false;
    }
    
    public void quitarCliente(String nombre){
        if(contCliente.retirarCliente(nombre)){
            JOptionPane.showMessageDialog(retCliente, "Cliente retirado exitosamente");
        }
    }

    public void agregarMueble(String nom, String tipo, int costo, int id) {
        if (contMueble.agregar(nom, tipo, costo, id)) {
            JOptionPane.showMessageDialog(agrMueble, "El mueble ha sido agregado correctamente");
        } else {
            JOptionPane.showMessageDialog(agrMueble, "No se ha podido agregar el mueble");
        }
    }

    public boolean buscarMueble(int id) {
        try {
            String ret = contMueble.buscarMueble(id);
            retMueble.actDesc(ret);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void quitarMueble(int id) {
        if (contMueble.borrarMueble(id)) {
            JOptionPane.showMessageDialog(retMueble, "el mueble ha sido retirado exitosamente");
        }
    }
    
    public String[] traerClientes(){
        return contCliente.clientes();
    }
    
    public void contarMuebles(String mueble){
        contMueble.contarMuebles(mueble);
    }
    public String[] traerTipos(){
        List<String> muebles =contMueble.tipos(); 
        String[] retorno=new String[muebles.size()];
        for(int i=0;i<muebles.size();i++){
            retorno[i]= muebles.get(i);
            System.out.println(retorno[i]);
        }
        
        return retorno;
    }
    public String[] traerMuebles(String tipo){
        List<String> muebles =contMueble.muebles(tipo); 
        String[] retorno=new String[muebles.size()];
        for(int i=0;i<muebles.size();i++){
            retorno[i]= muebles.get(i);
            System.out.println(retorno[i]);
        }
        
        return retorno;
    }
    
}
