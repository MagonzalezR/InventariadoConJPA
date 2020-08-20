/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistas;

import GUI.VistaRetCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class VistaRetClienteAction implements ActionListener{

    private VistaRetCliente vista;
    private Controlador controller;
    public VistaRetClienteAction(Controlador controller){
        this.controller= controller;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==vista.getVolver()){
            controller.irAMenu();
            vista.dispose();
        }else if (ae.getSource()== vista.getBuscar()){
            if (controller.buscarCliente(vista.getEmpresa().getText())){
                mensaje( "Cliente encontrado");
                vista.getQuitar().setEnabled(true);
            }else{
                mensaje("Cliente no encontrado");
            }
        }else{
            controller.quitarCliente(vista.getEmpresa().getText());
            vista.getQuitar().setEnabled(false);
            vista.getDescEmp().setText("");
        }
    }
    private void addListeners(){
        vista.getBuscar().addActionListener(this);
        vista.getVolver().addActionListener(this);
        vista.getQuitar().addActionListener(this);
    }
    public void crearVista(){
        vista=new VistaRetCliente();
        addListeners();
        vista.setVisible(true);
    }
    
    public void mensaje(String mensaje){
        JOptionPane.showMessageDialog(vista, mensaje);
    }
    
    public void setEmpresa(String empresa){
        vista.getDescEmp().setText(empresa);
    }
}
