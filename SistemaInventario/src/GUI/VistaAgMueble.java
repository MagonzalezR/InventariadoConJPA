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
public class VistaAgMueble extends JFrame implements ActionListener {

    private Controlador cont;
    private JTextField nomMueble;
    private JTextField tipoMueble;
    private JTextField costoMueble;
    private JTextField cantMueble;
    private JLabel nombre;
    private JLabel tipo;
    private JLabel costo;
    private JLabel cantidad;
    private JButton volver;
    private JButton agregar;

    public VistaAgMueble(Controlador cont) {
        this.cont = cont;
        this.setBounds(0, 0, 500, 550);
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
        } else {
            if (!nomMueble.getText().equals("") && !tipoMueble.getText().equals("") && !costoMueble.getText().equals("") && !cantMueble.getText().equals("")) {
                try {
                    cont.agregarMueble(nomMueble.getText(), tipoMueble.getText(), Integer.parseInt(costoMueble.getText()), Integer.parseInt(cantMueble.getText()));
                } catch (NumberFormatException w) {
                    JOptionPane.showMessageDialog(this, "El costo del mueble o el identificador no son validos");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Uno o varios campos estan vacios");
            }
        }
    }

    private void initComponents() {
        nombre = new JLabel("Nombre del mueble:");
        nombre.setBounds(100, 50, 125, 40);

        nomMueble = new JTextField();
        nomMueble.setBounds(235, 50, 200, 40);

        tipo = new JLabel("Tipo de mueble:");
        tipo.setBounds(100, 150, 125, 40);

        tipoMueble = new JTextField();
        tipoMueble.setBounds(235, 150, 200, 40);

        costo = new JLabel("Costo de alquiler:");
        costo.setBounds(100, 250, 125, 40);

        costoMueble = new JTextField();
        costoMueble.setBounds(235, 250, 200, 40);

        cantidad = new JLabel("Identificador de mueble:");
        cantidad.setBounds(80, 350, 140, 40);

        cantMueble = new JTextField();
        cantMueble.setBounds(235, 350, 200, 40);

        volver = new JButton("Volver al menu");
        volver.setBounds(80, 440, 150, 40);
        volver.addActionListener(this);

        agregar = new JButton("Ingresar mueble(s)");
        agregar.setBounds(250, 440, 150, 40);
        agregar.addActionListener(this);

        this.add(nombre);
        this.add(nomMueble);
        this.add(tipo);
        this.add(tipoMueble);
        this.add(costo);
        this.add(costoMueble);
        this.add(cantidad);
        this.add(cantMueble);
        this.add(volver);
        this.add(agregar);
    }
}
