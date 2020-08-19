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
public class VistaAgCliente extends JFrame implements ActionListener{

    private JTextField nomCont;
    private JTextField apeCont;
    private JTextField telCont;
    private JTextField corrCont;
    private JTextField nomEmp;
    private JTextField nitEmp;
    private JTextField dirEmp;
    private JTextField telEmp;
    private JLabel contNomb;
    private JLabel contApe;
    private JLabel contTel;
    private JLabel contCorr;
    private JLabel empNomb;
    private JLabel empNit;
    private JLabel empDir;
    private JLabel empTel;
    private JButton volver;
    private JButton ingresarDatos;
    private Controlador cont;

    public VistaAgCliente(Controlador cont) {
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
        if (ae.getSource()==volver){
            cont.irAMenu();
            this.dispose();
        }else{
            if(!nomCont.getText().equals("") && !apeCont.getText().equals("") && !telCont.getText().equals("") && !corrCont.getText().equals("") && !nomEmp.getText().equals("") && !nitEmp.getText().equals("") && !dirEmp.getText().equals("") && !telEmp.getText().equals("")){
                cont.agregarCliente(nomCont.getText(), apeCont.getText(), telCont.getText(), corrCont.getText(), nomEmp.getText(), nitEmp.getText(), dirEmp.getText(), telEmp.getText());
            }
            else JOptionPane.showMessageDialog(this, "Uno o varios campos estan vacios");
        }
    }
    
    private void initComponents(){
        contNomb=new JLabel("Nombre del contacto:");
        contNomb.setBounds(60, 70, 130, 40);
        
        nomCont=new JTextField();
        nomCont.setBounds(210, 70, 200, 40);
        
        contApe = new JLabel("Apellido del contacto:");
        contApe.setBounds(56, 160, 130, 40);
        
        apeCont=new JTextField();
        apeCont.setBounds(210, 160, 200, 40);
        
        contTel = new JLabel("Telefono del contacto:");
        contTel.setBounds(56, 250, 130, 40);
        
        telCont=new JTextField();
        telCont.setBounds(210, 250, 200, 40);
        
        contCorr = new JLabel("Correo del contacto:");
        contCorr.setBounds(58, 330, 130, 40);
        
        corrCont=new JTextField();
        corrCont.setBounds(210, 330, 200, 40);
        
        empNomb=new JLabel("Nombre de la empresa:");
        empNomb.setBounds(490, 70, 140, 40);
        
        nomEmp=new JTextField();
        nomEmp.setBounds(640, 70, 200, 40);
        
        empNit = new JLabel("NIT de la Empresa:");
        empNit.setBounds(515, 160, 110, 40);
        
        nitEmp=new JTextField();
        nitEmp.setBounds(640, 160, 200, 40);
        
        empDir = new JLabel("Direccion de la empresa:");
        empDir.setBounds(480, 250, 145, 40);
        
        dirEmp=new JTextField();
        dirEmp.setBounds(640, 250, 200, 40);
        
        empTel = new JLabel("Telefono de la empresa:");
        empTel.setBounds(486, 330, 145, 40);
        
        telEmp=new JTextField();
        telEmp.setBounds(640, 330, 200, 40);
        
        volver= new JButton("Volver Al Menu");
        volver.setBounds(60, 400, 140, 50);
        volver.addActionListener(this);
        
        ingresarDatos= new JButton("Ingresar Cliente");
        ingresarDatos.setBounds(680, 400, 160, 50);
        ingresarDatos.addActionListener(this);
        
        this.add(contNomb);
        this.add(nomCont);
        this.add(contApe);
        this.add(apeCont);
        this.add(contTel);
        this.add(telCont);
        this.add(contCorr);
        this.add(corrCont);
        this.add(empNomb);
        this.add(empNit);
        this.add(empDir);
        this.add(empTel);
        this.add(nomEmp);
        this.add(nitEmp);
        this.add(dirEmp);
        this.add(telEmp);
        this.add(volver);
        this.add(ingresarDatos);
    }
}
