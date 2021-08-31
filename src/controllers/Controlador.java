/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Animales;
import models.AnimalesDAO;
import models.Registros;
import models.RegistrosDAO;
import org.json.simple.parser.ParseException;
import views.Main;

/**
 * 
 * @author Alexander Granados <alexander.granados.dev@gmail.com>
 */
public class Controlador implements ActionListener{
    
    AnimalesDAO animalesDao;
    RegistrosDAO registroDao;
    Animales a = new Animales();
    Registros r = new Registros();
    Main vista = new Main();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Controlador(Main v) throws IOException, FileNotFoundException, ParseException{
        this.animalesDao = new AnimalesDAO();
        this.registroDao = new RegistrosDAO();
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        
        listar(vista.tablaDatos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == vista.btnListar){
           limpiarTabla();
           listar(vista.tablaDatos);
       }
       if(e.getSource() == vista.btnEliminar){
          eliminar();
          limpiarTabla();
           listar(vista.tablaDatos);
       }
       if(e.getSource() == vista.btnAgregar){
           try {
               agregar();
           } catch (java.text.ParseException ex) {
               Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
           }
           limpiarTabla();
           listar(vista.tablaDatos);
       }
       /*
       
       
       if(e.getSource() == vista.btnModificar){
           int fila = vista.tablaDatos.getSelectedRow();
           
           if(fila == -1){
               JOptionPane.showMessageDialog(vista, "Debe seleccionar fila");
           }else{
               
               String dni = (String)vista.tablaDatos.getValueAt(fila, 1);
               String nombre = (String)vista.tablaDatos.getValueAt(fila, 2);
               
               vista.txtDni.setText(dni);
               vista.txtNombre.setText(nombre);
           }
       }
       
       if(e.getSource() == vista.btnEditar){
           editar();
           limpiarTabla();
           listar(vista.tablaDatos);
       }
       
       
       
       */
    }
    
    /*
       
    
    
    public void editar(){
        int fila = vista.tablaDatos.getSelectedRow();
        int id = (int)vista.tablaDatos.getValueAt(fila, 0);
        String dni = vista.txtDni.getText();
        String nombre = vista.txtNombre.getText();
        
        p.setId(id);
        p.setDni(dni);
        p.setNombre(nombre);
        
        int r = dao.actualizar(p);
        if(r == 1){
            JOptionPane.showMessageDialog(vista, "El usuario se actualizo");
        }else{
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }
    
    
    */
    
    public void limpiarTabla(){
        vista.txtEdad.setText("");
        vista.txtNombre.setText("");
        vista.txtFamilia.setText("");
        vista.txtFecha.setText("");
        for (int i = 0; i < vista.tablaDatos.getRowCount(); i++) {
            modelo.removeRow(i);
            
            i -= 1;
        }
    }
    
    
    public void listar(JTable tabla){
    
        modelo = (DefaultTableModel)tabla.getModel();
        List<Animales>lista = animalesDao.listar();
        Object[] object = new Object[7];
        for (int i = 0; i < lista.size(); i++){
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getEdad();
            object[3] = lista.get(i).getFamilia();
            object[4] = lista.get(i).getIngreso();
            object[5] = lista.get(i).getEspecie();
            object[6] = lista.get(i).getResponsable();
            modelo.addRow(object);
        }
        
        vista.tablaDatos.setModel(modelo);
    }
    
    public void agregar() throws java.text.ParseException{
        String nombre = vista.txtNombre.getText();
        int edad = Integer.parseInt(vista.txtEdad.getText());
        String familia = vista.txtFamilia.getText();
        
        String ingreso = vista.txtFecha.getText();
        
        String especie = (String) vista.boxEspecie.getSelectedItem();
        int especie_id = 4;
        
        a.setNombre(nombre);
        a.setEdad(edad);
        a.setFamilia(familia);
        
        int res_animales = animalesDao.agregar(a);
        
        int id_animal = animalesDao.listarUltimo();
        
        r.setFecha(ingreso);
        r.setAnimales_id(id_animal);
        
        switch(especie){
            case "Otros": especie_id = 4;
                break;
            case "Animales marinos": especie_id = 1;
                break;
            case "Animales terrestes": especie_id = 2;
                break;
            case "Aracnidos": especie_id = 3;
                break;
        }
        
        r.setInventario_id(especie_id);
        
        int res_inventario = registroDao.agregar(r);
        
        if(res_animales == 1 && res_inventario == 1){
            JOptionPane.showMessageDialog(vista, "Usuario agregado");
        }else{
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }
    
    public void eliminar(){
        int fila = vista.tablaDatos.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un animal");
        }else{
            int id = Integer.parseInt((String)vista.tablaDatos.getValueAt(fila, 0).toString());
            registroDao.eliminar(id);
            animalesDao.eliminar(id);
            JOptionPane.showMessageDialog(vista, "animal eliminado");
        }
    }
    
}
