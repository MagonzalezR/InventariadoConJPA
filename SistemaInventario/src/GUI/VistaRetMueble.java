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
public class VistaRetMueble extends JFrame implements ActionListener {

    private Controlador cont;
    private JLabel idMueble;
    private JButton buscar;
    private JButton volver;
    private JButton quitar;
    private JTextField mueble;
    private JTextArea descMueble;

    public VistaRetMueble(Controlador cont) {
        this.cont = cont;
        this.setBounds(0, 0, 900, 550);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == volver) {
            cont.irAMenu();
            this.dispose();
        } else if (ae.getSource() == buscar) {
            try {
                if (cont.buscarMueble(Integer.parseInt(mueble.getText()))) {
                    JOptionPane.showMessageDialog(this, "Mueble encontrado");
                    quitar.setEnabled(true);
                    mueble.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Mueble no encontrado");
                }
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(this, "Identificador no valido");
            }
        } else {
            try {
                cont.quitarMueble(Integer.parseInt(mueble.getText()));
                quitar.setEnabled(false);
                descMueble.setText("");
                mueble.setEditable(true);
            } catch (NumberFormatException w) {
                JOptionPane.showMessageDialog(this, "Identificador no valido");
            }
        }
    }

    private void initComponents() {
        idMueble = new JLabel("Identificacion del mueble: ");
        idMueble.setBounds(100, 50, 160, 40);

        mueble = new JTextField();
        mueble.setBounds(280, 50, 200, 40);

        buscar = new JButton("Buscar");
        buscar.setBounds(500, 50, 150, 40);
        buscar.addActionListener(this);

        descMueble = new JTextArea();
        descMueble.setEditable(false);
        descMueble.setBounds(100, 150, 400, 200);

        quitar = new JButton("Borrar mueble");
        quitar.setBounds(520, 300, 150, 40);
        quitar.setEnabled(false);
        quitar.addActionListener(this);

        volver = new JButton("Volver al menu");
        volver.setBounds(200, 400, 150, 40);
        volver.addActionListener(this);

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
}
