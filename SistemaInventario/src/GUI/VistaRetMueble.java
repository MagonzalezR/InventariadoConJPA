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
public class VistaRetMueble extends JFrame {

    private JLabel idMueble;
    private JButton buscar;
    private JButton volver;
    private JButton quitar;
    private JTextField mueble;
    private JTextArea descMueble;

    public VistaRetMueble() {
        this.setBounds(0, 0, 900, 550);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        idMueble = new JLabel("Identificacion del mueble: ");
        idMueble.setBounds(100, 50, 160, 40);

        mueble = new JTextField();
        mueble.setBounds(280, 50, 200, 40);

        buscar = new JButton("Buscar");
        buscar.setBounds(500, 50, 150, 40);

        descMueble = new JTextArea();
        descMueble.setEditable(false);
        descMueble.setBounds(100, 150, 400, 200);

        quitar = new JButton("Borrar mueble");
        quitar.setBounds(520, 300, 150, 40);
        quitar.setEnabled(false);

        volver = new JButton("Volver al menu");
        volver.setBounds(200, 400, 150, 40);

        this.add(idMueble);
        this.add(mueble);
        this.add(buscar);
        this.add(descMueble);
        this.add(quitar);
        this.add(volver);
    }

    public void actDesc(String texto) {
        descMueble.setText(texto);
    }

    public JLabel getIdMueble() {
        return idMueble;
    }

    public void setIdMueble(JLabel idMueble) {
        this.idMueble = idMueble;
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

    public JTextField getMueble() {
        return mueble;
    }

    public void setMueble(JTextField mueble) {
        this.mueble = mueble;
    }

    public JTextArea getDescMueble() {
        return descMueble;
    }

    public void setDescMueble(JTextArea descMueble) {
        this.descMueble = descMueble;
    }
    
}
