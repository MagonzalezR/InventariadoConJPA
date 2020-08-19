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
public class VistaRetCliente extends JFrame implements ActionListener{

    private Controlador cont;
    private JLabel nombreEmp;
    private JButton buscar;
    private JButton volver;
    private JButton quitar;
    private JTextField empresa;
    private JTextArea descEmp;

    public VistaRetCliente(Controlador cont) {
        this.cont=cont;
        this.setBounds(0, 0, 800, 550);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==volver){
            cont.irAMenu();
            this.dispose();
        }else if (ae.getSource()== buscar){
            if (cont.buscarCliente(empresa.getText())){
                JOptionPane.showMessageDialog(this, "Cliente encontrado");
                quitar.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(this, "Cliente no encontrado");
            }
        }else{
            cont.quitarCliente(empresa.getText());
            quitar.setEnabled(false);
            descEmp.setText("");
        }
    }
    
    private void initComponents(){
        nombreEmp= new JLabel("Nombre de la empresa: ");
        nombreEmp.setBounds(100, 50, 160, 40);
        
        empresa = new JTextField();
        empresa.setBounds(280, 50, 200, 40);
        
        buscar = new JButton("Buscar");
        buscar.setBounds(500, 50, 150, 40);
        buscar.addActionListener(this);
        
        descEmp = new JTextArea();
        descEmp.setEditable(false);
        descEmp.setBounds(100, 150, 400, 200);
        
        quitar = new JButton("Quitar empresa");
        quitar.setBounds(520, 300, 150, 40);
        quitar.setEnabled(false);
        quitar.addActionListener(this);
        
        volver = new JButton("Volver al menu");
        volver.setBounds(200, 400, 150, 40);
        volver.addActionListener(this);
        
        this.add(nombreEmp);
        this.add(empresa);
        this.add(buscar);
        this.add(descEmp);        
        this.add(quitar);
        this.add(volver);
    }
    public void setEmpresa(String texto){
        descEmp.setText(texto);
    }
}
