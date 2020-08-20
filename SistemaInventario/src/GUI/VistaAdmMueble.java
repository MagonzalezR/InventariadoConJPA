
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;
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
public class VistaAdmMueble extends JFrame {

    private JDateChooser fechaInicio;
    private JDateChooser fechaFin;
    private JTextField costoTotal;
    private JTextField textFieldCantidad;
    private JTextField textFieldId;
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
    private JLabel labelID;
    private JButton buscar;
    private JButton botVolver;
    private JButton botGenerarCont;
    private JTextPane panelInfo;
    private JScrollPane scrollpaneInfo;

    public VistaAdmMueble() {
        this.setBounds(0, 0, 900, 550);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        clien = new JLabel("Cliente: ");
        clien.setBounds(110, 50, 50, 40);

        cliente = new JComboBox();
        cliente.setBounds(170, 50, 200, 40);
        cliente.addItem("Seleccionar");

        inicio = new JLabel("Fecha de inicio:");
        inicio.setBounds(65, 120, 100, 40);

        fechaInicio = new JDateChooser();
        fechaInicio.setBounds(170, 120, 200, 40);

        fin = new JLabel("Fecha de fin:");
        fin.setBounds(80, 210, 100, 40);

        fechaFin = new JDateChooser();
        fechaFin.setBounds(170, 210, 200, 40);

        cTotal = new JLabel("Costo total:");
        cTotal.setBounds(85, 300, 100, 40);

        costoTotal = new JTextField();
        costoTotal.setBounds(170, 300, 200, 40);
        
        labelID= new JLabel("Id del contrato");
        labelID.setBounds(79, 390, 100, 40);
        
        textFieldId = new JTextField();
        textFieldId.setBounds(170, 390, 100, 40);
        
        labelCantidad = new JLabel("Cantidad:");
        labelCantidad.setBounds(450, 170, 90, 40);

        labeltipo = new JLabel("Tipo:");
        labeltipo.setBounds(470, 50, 30, 40);

        buscar = new JButton("Agregar");
        buscar.setBounds(740, 170, 90, 40);

        comboBoxTipo = new JComboBox();
        comboBoxTipo.setBounds(520, 50, 200, 40);
        comboBoxTipo.addItem("Seleccionar");
        
        labelmueble = new JLabel("Muebles:");
        labelmueble.setBounds(460, 110, 90, 40);

        comboBoxmuebles = new JComboBox();
        comboBoxmuebles.setBounds(520, 110, 200, 40);
        comboBoxmuebles.addItem("Seleccionar");

        textFieldCantidad = new JTextField();
        textFieldCantidad.setBounds(520, 170, 200, 40);

        botVolver = new JButton("Volver al menu");
        botVolver.setBounds(40, 460, 160, 40);

        botGenerarCont = new JButton("Crear contrato");
        botGenerarCont.setBounds(220, 460, 160, 40);

        panelInfo = new JTextPane();
        scrollpaneInfo = new JScrollPane(panelInfo);
        scrollpaneInfo.setBounds(460, 230, 400, 250);
        panelInfo.setEditable(false);

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
        this.add(botGenerarCont);
        this.add(scrollpaneInfo);
        this.add(labelID);
        this.add(textFieldId);
    }
    
    public JDateChooser getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(JDateChooser fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public JDateChooser getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(JDateChooser fechaFin) {
        this.fechaFin = fechaFin;
    }

    public JTextField getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(JTextField costoTotal) {
        this.costoTotal = costoTotal;
    }

    public JTextField getTextFieldCantidad() {
        return textFieldCantidad;
    }

    public void setTextFieldCantidad(JTextField textFieldCantidad) {
        this.textFieldCantidad = textFieldCantidad;
    }

    public JComboBox getCliente() {
        return cliente;
    }

    public void setCliente(JComboBox cliente) {
        this.cliente = cliente;
    }

    public JComboBox getComboBoxmuebles() {
        return comboBoxmuebles;
    }

    public void setComboBoxmuebles(JComboBox comboBoxmuebles) {
        this.comboBoxmuebles = comboBoxmuebles;
    }

    public JComboBox getComboBoxTipo() {
        return comboBoxTipo;
    }

    public void setComboBoxTipo(JComboBox comboBoxTipo) {
        this.comboBoxTipo = comboBoxTipo;
    }

    public JLabel getInicio() {
        return inicio;
    }

    public void setInicio(JLabel inicio) {
        this.inicio = inicio;
    }

    public JLabel getFin() {
        return fin;
    }

    public void setFin(JLabel fin) {
        this.fin = fin;
    }

    public JLabel getcTotal() {
        return cTotal;
    }

    public void setcTotal(JLabel cTotal) {
        this.cTotal = cTotal;
    }

    public JLabel getClien() {
        return clien;
    }

    public void setClien(JLabel clien) {
        this.clien = clien;
    }

    public JLabel getLabelCantidad() {
        return labelCantidad;
    }

    public void setLabelCantidad(JLabel labelCantidad) {
        this.labelCantidad = labelCantidad;
    }

    public JLabel getLabelmueble() {
        return labelmueble;
    }

    public void setLabelmueble(JLabel labelmueble) {
        this.labelmueble = labelmueble;
    }

    public JLabel getLabeltipo() {
        return labeltipo;
    }

    public void setLabeltipo(JLabel labeltipo) {
        this.labeltipo = labeltipo;
    }

    public JButton getBuscar() {
        return buscar;
    }

    public void setBuscar(JButton buscar) {
        this.buscar = buscar;
    }

    public JButton getBotVolver() {
        return botVolver;
    }

    public void setBotVolver(JButton botVolver) {
        this.botVolver = botVolver;
    }

    public JButton getBotGenerarCont() {
        return botGenerarCont;
    }

    public void setBotGenerarCont(JButton botGenerarCont) {
        this.botGenerarCont = botGenerarCont;
    }

    public JTextPane getPanelInfo() {
        return panelInfo;
    }

    public void setPanelInfo(JTextPane panelInfo) {
        this.panelInfo = panelInfo;
    }

    public JScrollPane getScrollpaneInfo() {
        return scrollpaneInfo;
    }

    public void setScrollpaneInfo(JScrollPane scrollpaneInfo) {
        this.scrollpaneInfo = scrollpaneInfo;
    }

    public JTextField getTextFieldId() {
        return textFieldId;
    }

    public void setTextFieldId(JTextField textFieldId) {
        this.textFieldId = textFieldId;
    }

}
