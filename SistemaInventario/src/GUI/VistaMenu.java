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
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class VistaMenu extends JFrame {

    private JButton ingresarCliente;
    private JButton ingresarMueble;
    private JButton retirarMueble;
    private JButton retirarCliente;
    private JButton verContrato;
    private JButton administrarMueble;

    public VistaMenu() {
        this.setBounds(0, 0, 900, 550);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();

    }

    private void initComponents() {
        ingresarCliente = new JButton("Ingresar Cliente");
        ingresarCliente.setBounds(150, 100, 250, 50);
        
        retirarCliente = new JButton("Retirar Cliente");
        retirarCliente.setBounds(150, 216, 250, 50);

        verContrato = new JButton("Ver Contrato");
        verContrato.setBounds(150, 333, 250, 50);

        ingresarMueble = new JButton("Ingresar Mueble");
        ingresarMueble.setBounds(500, 100, 250, 50);

        retirarMueble = new JButton("Retirar Mueble");
        retirarMueble.setBounds(500, 216, 250, 50);

        administrarMueble = new JButton("Generar Contrato");
        administrarMueble.setBounds(500, 333, 250, 50);

        this.add(ingresarCliente);
        this.add(ingresarMueble);
        this.add(retirarMueble);
        this.add(retirarCliente);
        this.add(verContrato);
        this.add(administrarMueble);
    }

    public JButton getIngresarCliente() {
        return ingresarCliente;
    }

    public void setIngresarCliente(JButton ingresarCliente) {
        this.ingresarCliente = ingresarCliente;
    }

    public JButton getIngresarMueble() {
        return ingresarMueble;
    }

    public void setIngresarMueble(JButton ingresarMueble) {
        this.ingresarMueble = ingresarMueble;
    }

    public JButton getRetirarMueble() {
        return retirarMueble;
    }

    public void setRetirarMueble(JButton retirarMueble) {
        this.retirarMueble = retirarMueble;
    }

    public JButton getRetirarCliente() {
        return retirarCliente;
    }

    public void setRetirarCliente(JButton retirarCliente) {
        this.retirarCliente = retirarCliente;
    }

    public JButton getVerContrato() {
        return verContrato;
    }

    public void setVerContrato(JButton verContrato) {
        this.verContrato = verContrato;
    }

    public JButton getAdministrarMueble() {
        return administrarMueble;
    }

    public void setAdministrarMueble(JButton administrarMueble) {
        this.administrarMueble = administrarMueble;
    }
    
}
