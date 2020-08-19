/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import logica.Controlador;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author kil_5
 */
public class VistaAdmMueble extends JFrame implements ActionListener{

    private Controlador cont;
    private JDateChooser fechaInicio;
    private JDateChooser fechaFin;
    private JTextField costoTotal;
    private JTextField textFieldCantidad;
    private JComboBox cliente;
    private JComboBox comboBoxmuebles;
    private JComboBox comboBoxTipo;
    private JLabel inicio;
    private JLabel fin;
    private JLabel cTotal;
    private JLabel clien;
    private JLabel labelCantidad;
    private JLabel labelmueble;
    private JLabel labeltipo;
    private JButton buscar;
    private JButton botVolver;
    private JTextPane panelInfo;
    private JScrollPane scrollpaneInfo;

    public VistaAdmMueble(Controlador cont) {
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
        if(ae.getSource()==buscar){
            //cont.contarMuebles();
        }else if(ae.getSource()==botVolver){
            cont.irAMenu();
            this.dispose();
        }else if(ae.getSource()==comboBoxTipo){
            if(comboBoxTipo.getSelectedItem()!="Seleccionar"){
                System.out.println("si entra");
                comboBoxmuebles.removeAllItems();
                comboBoxmuebles.addItem("Seleccionar");
                for (String obj:cont.traerMuebles((String)comboBoxTipo.getSelectedItem())){
                    comboBoxmuebles.addItem(obj);
                }
            }
        }
    }
    
    private void initComponents(){
        clien= new JLabel("Cliente: ");
        clien.setBounds(110,50,50,40);
        
        cliente = new JComboBox();
        cliente.setBounds(170, 50, 200, 40);
        cliente.addItem("Seleccionar");
//        for(String obj:cont.traerClientes()){
//            cliente.addItem(obj);
//        }
        
        inicio = new JLabel("Fecha de inicio:");
        inicio.setBounds(65, 150, 100, 40);
        
        fechaInicio= new JDateChooser();
        fechaInicio.setBounds(170, 150, 200, 40);
        
        fin = new JLabel("Fecha de fin:");
        fin.setBounds(80, 250, 100, 40);
        
        fechaFin= new JDateChooser();
        fechaFin.setBounds(170, 250, 200, 40);
        
        cTotal = new JLabel("Costo total:");
        cTotal.setBounds(85, 350, 100, 40);
        
        costoTotal = new JTextField();
        costoTotal.setBounds(170, 350, 200, 40);
        
        labelCantidad = new JLabel("Cantidad:");
        labelCantidad.setBounds(450, 170, 90, 40);
        
        labeltipo = new JLabel("Tipo:");
        labeltipo.setBounds(470, 50, 30, 40);
        
        buscar= new JButton("Agregar");
        buscar.setBounds(740, 170 , 90,40 );
        //buscar.addActionListener(this);
        
        comboBoxTipo = new JComboBox();
        comboBoxTipo.setBounds(520, 50, 200, 40);
        comboBoxTipo.addItem("Seleccionar");
        for(String obj:cont.traerTipos()){
            comboBoxTipo.addItem(obj);
        }
        comboBoxTipo.addActionListener(this);
        
        labelmueble = new JLabel("Muebles:");
        labelmueble.setBounds(460, 110, 90, 40);
        
        comboBoxmuebles = new JComboBox();
        comboBoxmuebles.setBounds(520, 110, 200, 40);
        comboBoxmuebles.addItem("Seleccionar");
        
        textFieldCantidad = new JTextField();
        textFieldCantidad.setBounds(520, 170, 200, 40);
        
        botVolver= new JButton("Volver al menu");
        botVolver.setBounds(200, 460, 200, 40);
        botVolver.addActionListener(this);
        
        panelInfo = new JTextPane();
        panelInfo.setBounds(460, 250, 400, 250);
        
        this.add(cliente);
        this.add(clien);
        this.add(inicio);
        this.add(fechaInicio);
        this.add(fin);
        this.add(fechaFin);
        this.add(cTotal);
        this.add(costoTotal);
        this.add(buscar);
        this.add(labelCantidad);
        this.add(labelmueble);
        this.add(labeltipo);
        this.add(comboBoxTipo);
        this.add(comboBoxmuebles);
        this.add(textFieldCantidad);
        this.add(botVolver);
        this.add(panelInfo);
    }
}
