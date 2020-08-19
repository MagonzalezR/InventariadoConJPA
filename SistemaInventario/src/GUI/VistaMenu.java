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
public class VistaMenu extends JFrame implements ActionListener {

    private JButton ingresarCliente;
    private JButton ingresarMueble;
    private JButton retirarMueble;
    private JButton retirarCliente;
    private JButton verContrato;
    private JButton administrarMueble;
    private Controlador cont;

    public VistaMenu(Controlador cont) {
        this.cont=cont;
        this.setBounds(0, 0, 900, 550);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ingresarCliente) {
            cont.elegirOpcion(1);
        } else if (ae.getSource() == ingresarMueble) {
            cont.elegirOpcion(2);
        } else if (ae.getSource() == retirarCliente) {
            cont.elegirOpcion(3);
        } else if (ae.getSource() == retirarMueble) {
            cont.elegirOpcion(4);
        } else if (ae.getSource() == verContrato) {
            cont.elegirOpcion(5);
        } else {
            cont.elegirOpcion(6);
        }
        this.dispose();
    }

    private void initComponents() {
        ingresarCliente = new JButton("Ingresar Cliente");
        ingresarCliente.setBounds(150, 100, 250, 50);
        ingresarCliente.addActionListener(this);
        
        retirarCliente = new JButton("Retirar Cliente");
        retirarCliente.setBounds(150, 216, 250, 50);
        retirarCliente.addActionListener(this);

        verContrato = new JButton("Ver Contrato");
        verContrato.setBounds(150, 333, 250, 50);
        verContrato.addActionListener(this);

        ingresarMueble = new JButton("Ingresar Mueble");
        ingresarMueble.setBounds(500, 100, 250, 50);
        ingresarMueble.addActionListener(this);

        retirarMueble = new JButton("Retirar Mueble");
        retirarMueble.setBounds(500, 216, 250, 50);
        retirarMueble.addActionListener(this);

        administrarMueble = new JButton("Generar Contrato");
        administrarMueble.setBounds(500, 333, 250, 50);
        administrarMueble.addActionListener(this);

        this.add(ingresarCliente);
        this.add(ingresarMueble);
        this.add(retirarMueble);
        this.add(retirarCliente);
        this.add(verContrato);
        this.add(administrarMueble);
    }
}
