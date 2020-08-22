/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class VistaRetCliente extends JFrame{

    private JLabel nombreEmp;
    private JButton buscar;
    private JButton volver;
    private JButton quitar;
    private JTextField empresa;
    private JTextArea descEmp;

    public VistaRetCliente() {
        this.setBounds(0, 0, 800, 550);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents(){
        nombreEmp= new JLabel("Nombre de la empresa: ");
        nombreEmp.setBounds(100, 50, 160, 40);
        
        empresa = new JTextField();
        empresa.setBounds(280, 50, 200, 40);
        
        buscar = new JButton("Buscar");
        buscar.setBounds(500, 50, 150, 40);
        
        descEmp = new JTextArea();
        descEmp.setEditable(false);
        descEmp.setBounds(100, 150, 400, 200);
        
        quitar = new JButton("Quitar empresa");
        quitar.setBounds(520, 300, 150, 40);
        quitar.setEnabled(false);
        
        volver = new JButton("Volver al menu");
        volver.setBounds(200, 400, 150, 40);
        
        this.add(nombreEmp);
        this.add(empresa);
        this.add(buscar);
        this.add(descEmp);        
        this.add(quitar);
        this.add(volver);
    }

    public JLabel getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(JLabel nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public JButton getBuscar() {
        return buscar;
    }

    public void setBuscar(JButton buscar) {
        this.buscar = buscar;
    }

    public JButton getVolver() {
        return volver;
    }

    public void setVolver(JButton volver) {
        this.volver = volver;
    }

    public JButton getQuitar() {
        return quitar;
    }

    public void setQuitar(JButton quitar) {
        this.quitar = quitar;
    }

    public JTextField getEmpresa() {
        return empresa;
    }

    public void setEmpresa(JTextField empresa) {
        this.empresa = empresa;
    }

    public JTextArea getDescEmp() {
        return descEmp;
    }

    public void setDescEmp(JTextArea descEmp) {
        this.descEmp = descEmp;
    }
    
}
