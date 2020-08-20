/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistas;

import GUI.VistaContrato;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class VistaContratoAction implements ActionListener{

    private Controlador controller;
    private VistaContrato vista;
    public VistaContratoAction(Controlador controller){
        this.controller = controller;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {

    }
    private void addListeners(){
        
    }
    
    public void crearVista(){
        vista=new VistaContrato();
        addListeners();
        vista.setVisible(true);
    }
    
    public void mensaje(String mensaje){
        JOptionPane.showMessageDialog(vista, mensaje);
    }
}
