/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistas;

import GUI.VistaAdmMueble;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class VistaAdmMuebleAction implements ActionListener{
    private Controlador controller;
    private VistaAdmMueble vista;
    private int enExistencia;
    private int contrato;
    
    public VistaAdmMuebleAction(Controlador controller){
        this.controller = controller;
        enExistencia =0;
        contrato=0;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBuscar()) {
            try {
                if (vista.getFechaInicio().getDate() != null && vista.getFechaFin().getDate() != null && Integer.valueOf(vista.getCostoTotal().getText()) > 0 && Integer.valueOf(vista.getTextFieldId().getText())>0 && !"Seleccionar".equals((String) (vista.getCliente().getSelectedItem())) && !"Seleccionar".equals((String) (vista.getComboBoxTipo().getSelectedItem())) && !"Seleccionar".equals((String) (vista.getComboBoxmuebles().getSelectedItem()))) {
                    if (Integer.valueOf(vista.getTextFieldCantidad().getText()) <= enExistencia && Integer.valueOf(vista.getTextFieldCantidad().getText()) > 0) {
                        if (contrato == 0) {
                            long ini = vista.getFechaInicio().getDate().getTime();
                            long fini = vista.getFechaFin().getDate().getTime();
                            Date sqlIni = new Date(ini);
                            Date sqlFin = new Date(fini);
                            contrato = controller.contratoProvisional(sqlIni, sqlFin, Integer.valueOf(vista.getCostoTotal().getText()), Integer.valueOf(vista.getTextFieldId().getText()));
                        }
                        if(contrato!=0){
                            controller.añadirMuebles((String) vista.getComboBoxmuebles().getSelectedItem(), Integer.valueOf(vista.getTextFieldCantidad().getText()), contrato);
                            vista.getPanelInfo().setText(vista.getPanelInfo().getText() + vista.getComboBoxmuebles().getSelectedItem() + "  Cantidad  " + vista.getTextFieldCantidad().getText() + "\n");
                            vista.getComboBoxmuebles().removeActionListener(this);
                            vista.getComboBoxmuebles().setSelectedIndex(0);
                            vista.getComboBoxmuebles().addActionListener(this);
                        }else{
                            mensaje("Verifique las fechas del contrato");
                        }
                    } else {
                        mensaje("Verifique que la cantidad de muebles y el identificador esten bien");
                    }
                } else {
                    mensaje("Hay campos vacios");
                }
            } catch (NumberFormatException e) {
                mensaje("Hay campos con datos erroneos");
            }
        } else if (ae.getSource() == vista.getBotVolver()) {
            if (contrato != 0) {
                controller.borrarContrato(contrato);
            }
            contrato=0;
            controller.irAMenu();
            vista.dispose();
        } else if (ae.getSource() == vista.getComboBoxTipo()) {
            if (vista.getComboBoxTipo().getSelectedItem() != "Seleccionar") {
                vista.getComboBoxmuebles().removeActionListener(this);
                vista.getComboBoxmuebles().removeAllItems();
                vista.getComboBoxmuebles().addItem("Seleccionar");
                for (String obj : controller.traerMuebles((String) vista.getComboBoxTipo().getSelectedItem())) {
                    vista.getComboBoxmuebles().addItem(obj);
                }
                vista.getComboBoxmuebles().addActionListener(this);
            }
        } else if (ae.getSource() == vista.getComboBoxmuebles()) {
            if (vista.getComboBoxmuebles().getSelectedItem() != "Seleccionar") {
                enExistencia = Integer.valueOf(controller.contarMuebles((String) vista.getComboBoxmuebles().getSelectedItem()));
                 mensaje("Hay " + enExistencia + " " + (String) vista.getComboBoxmuebles().getSelectedItem() + " en bodega");
            } else {
                enExistencia = 0;
            }
        } else {
            try {
                if (vista.getFechaInicio().getDate() != null && vista.getFechaFin().getDate() != null && Integer.valueOf(vista.getCostoTotal().getText()) > 0) {
                    if (contrato == 0) {
                         mensaje("Ingrese muebles en el contrato");
                    } else {
                        controller.crearContrato(contrato, (String) vista.getCliente().getSelectedItem());
                         mensaje("Contrato generado");
                        controller.irAMenu();
                        vista.dispose();
                    }
                } else {
                     mensaje("Hay campos vacios");
                }
            } catch (NumberFormatException e) {
                mensaje("Hay campos con datos erroneos");
            }
        }
    }
    private void addListeners(){
        for (String obj : controller.traerClientes()) {
            vista.getCliente().addItem(obj);
        }
        for (String obj : controller.traerTipos()) {
            vista.getComboBoxTipo().addItem(obj);
        }
        vista.getBotVolver().addActionListener(this);
        vista.getBuscar().addActionListener(this);
        vista.getComboBoxTipo().addActionListener(this);
        vista.getBotGenerarCont().addActionListener(this);

    }
    
    public void crearVista(){
        vista=new VistaAdmMueble();
        addListeners();
        vista.setVisible(true);
    }
    
    public void mensaje(String mensaje){
        JOptionPane.showMessageDialog(vista, mensaje);
    }
}
