/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistas;

import GUI.VistaRetMueble;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class VistaRetMuebleAction implements ActionListener{

    private VistaRetMueble vista;
    private Controlador controller;
    
    public VistaRetMuebleAction(Controlador controller){
        this.controller=controller;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       if (ae.getSource() == vista.getVolver()) {
            controller.irAMenu();
            vista.dispose();
        } else if (ae.getSource() == vista.getBuscar()) {
            try {
                if (controller.buscarMueble(Integer.parseInt(vista.getMueble().getText()))) {
                    JOptionPane.showMessageDialog(vista, "Mueble encontrado");
                    vista.getQuitar().setEnabled(true);
                    vista.getMueble().setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(vista, "Mueble no encontrado");
                }
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(vista, "Identificador no valido");
            }
        } else {
            try {
                controller.quitarMueble(Integer.parseInt(vista.getMueble().getText()));
                vista.getQuitar().setEnabled(false);
                vista.getDescMueble().setText("");
                vista.getMueble().setEditable(true);
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(vista, "Identificador no valido");
            }
        }
    }
    
    private void addListeners(){
        vista.getBuscar().addActionListener(this);
        vista.getVolver().addActionListener(this);
        vista.getQuitar().addActionListener(this);
    }
    public void crearVista(){
        vista=new VistaRetMueble();
        addListeners();
        vista.setVisible(true);
    }
    
    public void mensaje(String mensaje){
        JOptionPane.showMessageDialog(vista, mensaje);
    }
    
    public void descripcionMueble(String texto){
        vista.actDesc(texto);
    }
}
