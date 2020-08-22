/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import logica.Controlador;

/**
 *
 * @author kil_5
 */
public class Vista extends JFrame {
    
    private JButton ingreso;
    private JTextField nombre;
    private JTextField contraseña;
    private JLabel bienvenida;
    private JLabel nom;
    private JLabel cont;
    
    public Vista(){
        this.setBounds(0,0,650,350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        initComponents();        
    }
    
    private void initComponents(){
        ingreso=new JButton("Ingresar");
        ingreso.setBounds(270,250,100,30);
        
        nombre= new JTextField("");
        nombre.setBounds(200, 110, 250, 40);
        
        contraseña=new JPasswordField();
        contraseña.setBounds(200, 170, 250, 40);
        
        bienvenida=new JLabel("Bienvenido");
        bienvenida.setBounds(250, 30, 150, 60);
        bienvenida.setFont(new Font("Lato Black", 0, 28));
        
        nom=new JLabel("Nombre: ");
        
        this.add(ingreso);
        this.add(nombre);
        this.add(contraseña);
        this.add(bienvenida);
    }

    public JButton getIngreso() {
        return ingreso;
    }

    public void setIngreso(JButton ingreso) {
        this.ingreso = ingreso;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public void setNombre(JTextField nombre) {
        this.nombre = nombre;
    }

    public JTextField getContraseña() {
        return contraseña;
    }

    public void setContraseña(JTextField contraseña) {
        this.contraseña = contraseña;
    }

    public JLabel getBienvenida() {
        return bienvenida;
    }

    public void setBienvenida(JLabel bienvenida) {
        this.bienvenida = bienvenida;
    }

    public JLabel getNom() {
        return nom;
    }

    public void setNom(JLabel nom) {
        this.nom = nom;
    }

    public JLabel getCont() {
        return cont;
    }

    public void setCont(JLabel cont) {
        this.cont = cont;
    }
    
}
