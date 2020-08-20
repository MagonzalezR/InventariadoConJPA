/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlVistas;

import GUI.VistaAgCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class VistaAgClienteAction implements ActionListener{
    private Controlador controller;
    private VistaAgCliente vista;
    
    public VistaAgClienteAction(Controlador controller){
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==vista.getVolver()){
            controller.irAMenu();
            vista.dispose();
        }else{
            if(!vista.getNomCont().getText().equals("") && !vista.getApeCont().getText().equals("") && !vista.getTelCont().getText().equals("") && !vista.getCorrCont().getText().equals("") && !vista.getNomEmp().getText().equals("") && !vista.getNitEmp().getText().equals("") && !vista.getDirEmp().getText().equals("") && !vista.getTelEmp().getText().equals("")){
                controller.agregarCliente(vista.getNomCont().getText(), vista.getApeCont().getText(), vista.getTelCont().getText(), vista.getCorrCont().getText(), vista.getNomEmp().getText(), vista.getNitEmp().getText(), vista.getDirEmp().getText(), vista.getTelEmp().getText());
            }
            else mensaje("Uno o varios campos estan vacios");
        }
    }
    private void addListeners(){
        vista.getIngresarDatos().addActionListener(this);
        vista.getVolver().addActionListener(this);
    }
    
    public void crearVista(){
        vista=new VistaAgCliente();
        addListeners();
        vista.setVisible(true);
    }
    
    public void mensaje(String mensaje){
        JOptionPane.showMessageDialog(vista, mensaje);
    }
}
