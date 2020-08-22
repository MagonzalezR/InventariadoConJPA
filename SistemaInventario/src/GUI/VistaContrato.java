/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author kil_5
 */
public class VistaContrato extends JFrame {

    private JButton botonConsulta;
    private JButton botonVolver;
    private JButton botonFinalContrato;
    private JLabel labelConsulta;
    private JTextField fieldConsulta;
    private JTextArea areaConsulta;
    private JScrollPane scrollConsulta;

    public VistaContrato() {
        this.setBounds(0, 0, 900, 550);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        labelConsulta = new JLabel("Ingrese el numero de contrato:");
        labelConsulta.setBounds(150, 50, 180, 40);

        fieldConsulta = new JTextField();
        fieldConsulta.setBounds(350, 50, 200, 40);

        botonConsulta = new JButton("Buscar");
        botonConsulta.setBounds(580, 50, 100, 40);

        areaConsulta = new JTextArea();
        areaConsulta.setEditable(false);

        scrollConsulta = new JScrollPane(areaConsulta);
        scrollConsulta.setBounds(100, 100, 700, 300);
        
        botonVolver = new JButton("volver");
        botonVolver.setBounds(100, 420, 100, 40);

        botonFinalContrato= new JButton("Finalizar contrato");
        botonFinalContrato.setBounds(650, 420, 150, 40);
        botonFinalContrato.setEnabled(false);
        
        this.add(labelConsulta);
        this.add(fieldConsulta);
        this.add(botonConsulta);
        this.add(scrollConsulta);
        this.add(botonVolver);
        this.add(botonFinalContrato);
    }

    public JButton getBotonConsulta() {
        return botonConsulta;
    }

    public void setBotonConsulta(JButton botonConsulta) {
        this.botonConsulta = botonConsulta;
    }

    public JTextField getFieldConsulta() {
        return fieldConsulta;
    }

    public void setFieldConsulta(JTextField fieldConsulta) {
        this.fieldConsulta = fieldConsulta;
    }

    public JScrollPane getScrollConsulta() {
        return scrollConsulta;
    }

    public void setScrollConsulta(JScrollPane scrollConsulta) {
        this.scrollConsulta = scrollConsulta;
    }

    public JTextArea getAreaConsulta() {
        return areaConsulta;
    }

    public void setAreaConsulta(JTextArea areaConsulta) {
        this.areaConsulta = areaConsulta;
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }

    public void setBotonVolver(JButton botonVolver) {
        this.botonVolver = botonVolver;
    }

    public JButton getBotonFinalContrato() {
        return botonFinalContrato;
    }

    public void setBotonFinalContrato(JButton botonFinalContrato) {
        this.botonFinalContrato = botonFinalContrato;
    }
    
}
