/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistas;

import GUI.VistaMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class VistaMenuAction implements ActionListener{

    private VistaMenu vista;
    private Controlador controller;
    public VistaMenuAction(Controlador controller){
        this.controller = controller;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getIngresarCliente()) {
            controller.elegirOpcion(1);
        } else if (ae.getSource() == vista.getIngresarMueble()) {
            controller.elegirOpcion(2);
        } else if (ae.getSource() == vista.getRetirarCliente()) {
            controller.elegirOpcion(3);
        } else if (ae.getSource() == vista.getRetirarMueble()) {
            controller.elegirOpcion(4);
        } else if (ae.getSource() == vista.getVerContrato()) {
            controller.elegirOpcion(5);
        } else {
            controller.elegirOpcion(6);
        }
        vista.dispose();
    }
    
    private void addListeners(){
        vista.getIngresarCliente().addActionListener(this);
        vista.getIngresarMueble().addActionListener(this);
        vista.getRetirarCliente().addActionListener(this);
        vista.getRetirarMueble().addActionListener(this);
        vista.getAdministrarMueble().addActionListener(this);
        vista.getVerContrato().addActionListener(this);
    }
    
    public void iniciarVista(){
        vista=new VistaMenu();
        addListeners();
        vista.setVisible(true);
    }
}
