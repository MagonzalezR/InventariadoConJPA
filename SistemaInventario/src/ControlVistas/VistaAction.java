/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistas;

import GUI.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class VistaAction implements ActionListener{
    private Controlador controller;
    private Vista vista;
    
    public VistaAction(Controlador controller){
        this.controller =controller;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(controller.validarClave(vista.getNombre().getText(), vista.getContraseña().getText())){
            vista.dispose();
            controller.irAMenu();
        }else mensaje("Clave invalida");
            
    }
    private void addListeners(){
        vista.getIngreso().addActionListener(this);
    }
    
    public void crearVista(){
        vista=new Vista();
        addListeners();
        vista.setVisible(true);
    }
    
    public void mensaje(String mensaje){
        JOptionPane.showMessageDialog(vista, mensaje);
    }
}
