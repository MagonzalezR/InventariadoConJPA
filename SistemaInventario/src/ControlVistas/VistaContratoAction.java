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
public class VistaContratoAction implements ActionListener {

    private Controlador controller;
    private VistaContrato vista;

    public VistaContratoAction(Controlador controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBotonVolver()) {
            controller.irAMenu();
            vista.dispose();
        } else if (ae.getSource() == vista.getBotonConsulta()) {
            try {
                if (Integer.valueOf(vista.getFieldConsulta().getText()) > 0) {
                    String consulta = controller.buscarContraro(Integer.valueOf(vista.getFieldConsulta().getText()));
                    if (!consulta.equals("")) {
                        vista.getAreaConsulta().setText(consulta);
                        vista.getBotonFinalContrato().setEnabled(true);
                    } else {
                        mensaje("No se ha encontrado el contrato");
                    }
                } else mensaje("numero de identificacion erroneo");
            } catch (NumberFormatException e) {
                mensaje("El campo es erroneo o está vacio");
            }
        } else {
            controller.borrarContrato(Integer.valueOf(vista.getFieldConsulta().getText()));
            mensaje("Contrato finalizad exitosamente");
            vista.getBotonFinalContrato().setEnabled(false);
        }
    }

    private void addListeners() {
        vista.getBotonConsulta().addActionListener(this);
        vista.getBotonFinalContrato().addActionListener(this);
        vista.getBotonVolver().addActionListener(this);
    }

    public void crearVista() {
        vista = new VistaContrato();
        addListeners();
        vista.setVisible(true);
    }

    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje);
    }
}
