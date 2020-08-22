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
import javax.swing.JTextField;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class VistaAgMueble extends JFrame {

    private JTextField nomMueble;
    private JTextField tipoMueble;
    private JTextField cantMueble;
    private JLabel nombre;
    private JLabel tipo;
    private JLabel cantidad;
    private JButton volver;
    private JButton agregar;

    public VistaAgMueble() {
        this.setBounds(0, 0, 500, 550);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        nombre = new JLabel("Nombre del mueble:");
        nombre.setBounds(100, 70, 125, 40);

        nomMueble = new JTextField();
        nomMueble.setBounds(235, 70, 200, 40);

        tipo = new JLabel("Tipo de mueble:");
        tipo.setBounds(100, 190, 125, 40);

        tipoMueble = new JTextField();
        tipoMueble.setBounds(235, 190, 200, 40);

        cantidad = new JLabel("Identificador de mueble:");
        cantidad.setBounds(80, 310, 140, 40);

        cantMueble = new JTextField();
        cantMueble.setBounds(235, 310, 200, 40);

        volver = new JButton("Volver al menu");
        volver.setBounds(80, 440, 150, 40);

        agregar = new JButton("Ingresar mueble");
        agregar.setBounds(250, 440, 150, 40);

        this.add(nombre);
        this.add(nomMueble);
        this.add(tipo);
        this.add(tipoMueble);
        this.add(cantidad);
        this.add(cantMueble);
        this.add(volver);
        this.add(agregar);
    }

    public JTextField getNomMueble() {
        return nomMueble;
    }

    public void setNomMueble(JTextField nomMueble) {
        this.nomMueble = nomMueble;
    }

    public JTextField getTipoMueble() {
        return tipoMueble;
    }

    public void setTipoMueble(JTextField tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    public JTextField getCantMueble() {
        return cantMueble;
    }

    public void setCantMueble(JTextField cantMueble) {
        this.cantMueble = cantMueble;
    }

    public JButton getVolver() {
        return volver;
    }

    public void setVolver(JButton volver) {
        this.volver = volver;
    }

    public JButton getAgregar() {
        return agregar;
    }

    public void setAgregar(JButton agregar) {
        this.agregar = agregar;
    }
    
}
