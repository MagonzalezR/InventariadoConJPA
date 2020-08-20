/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistas;

import GUI.VistaAgMueble;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class VistaAgMuebleAction implements ActionListener{

    private Controlador controller;
    private VistaAgMueble vista;
    public VistaAgMuebleAction(Controlador controller){
        this.controller=controller;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getVolver()) {
            controller.irAMenu();
            vista.dispose();
        } else {
            if (!vista.getNomMueble().getText().equals("") && !vista.getTipoMueble().getText().equals("") && !vista.getCostoMueble().getText().equals("") && !vista.getCantMueble().getText().equals("")) {
                try {
                    controller.agregarMueble(vista.getNomMueble().getText(), vista.getTipoMueble().getText(), Integer.parseInt(vista.getCostoMueble().getText()), Integer.parseInt(vista.getCantMueble().getText()));
                } catch (NumberFormatException w) {
                    mensaje("El costo del mueble o el identificador no son validos");
                }
            } else {
                mensaje("Uno o varios campos estan vacios");
            }
        }
    }
    private void addListeners(){
        vista.getAgregar().addActionListener(this);
        vista.getVolver().addActionListener(this);
    }
    
    public void crearVista(){
        vista=new VistaAgMueble();
        addListeners();
        vista.setVisible(true);
    }
    
    public void mensaje(String mensaje){
        JOptionPane.showMessageDialog(vista, mensaje);
    }
}
