/*
 * controlador que se encarga de interactuar con los modelos y la vista (interfaz grafica)
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
        // montado de objectos AnimalesDAO y RegistrosDAO para interactuar con la db
        // montado de referencia de la interfaz grafica y inicializar eventos de "btn"
        this.animalesDao = new AnimalesDAO();
        this.registroDao = new RegistrosDAO();
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        
        //lista la tabla al momento de cargar el proyecto
        listar(vista.tablaDatos);
    }

    // capturar eventos de la interfaz grafica
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == vista.btnListar){
           // evento del btn listar
           limpiarTabla();
           listar(vista.tablaDatos);
       }
       if(e.getSource() == vista.btnEliminar){
          // evento del btn eliminar
          eliminar();
          limpiarTabla();
           listar(vista.tablaDatos);
       }
       if(e.getSource() == vista.btnAgregar){
          // evento del btn agregar
           try {
               agregar();
           } catch (java.text.ParseException ex) {
               Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
           }
           limpiarTabla();
           listar(vista.tablaDatos);
       }
       
       if(e.getSource() == vista.btnModificar){
           // evento del btn modificar (captura los datos de la tabla y 
           // los pone en los inputs)
           int fila = vista.tablaDatos.getSelectedRow();
           
           if(fila == -1){
               JOptionPane.showMessageDialog(vista, "Debe seleccionar fila");
           }else{
               
               String nombre = (String)vista.tablaDatos.getValueAt(fila, 1);
               String edad = (String)vista.tablaDatos.getValueAt(fila, 2).toString();
               String familia = (String)vista.tablaDatos.getValueAt(fila, 3);
               String ingreso = (String)vista.tablaDatos.getValueAt(fila, 4).toString();
               String especieTabla = (String)vista.tablaDatos.getValueAt(fila, 5);
               int especie = 0;
               
               vista.txtNombre.setText(nombre);
               vista.txtEdad.setText(edad);
               vista.txtFamilia.setText(familia);
               vista.txtFecha.setText(ingreso);
              
               
               switch(especieTabla){
                case "Otros": especie = 0;
                    break;
                case "Animales marinos": especie = 1;
                    break;
                case "Animales terrestres": especie = 2;
                    break;
                case "Aracnidos": especie = 3;
                    break;
               }
               
                vista.boxEspecie.setSelectedIndex(especie); //funciona :D
           }
       }
       
       
       
       if(e.getSource() == vista.btnEditar){
           editar();
           limpiarTabla();
           listar(vista.tablaDatos);
       }
       
       
       
       
    }
    
    
       
    
    
    public void editar(){
        int fila = vista.tablaDatos.getSelectedRow();
        int id = (int)vista.tablaDatos.getValueAt(fila, 0);

        String nombre = vista.txtNombre.getText();
        int edad = Integer.parseInt(vista.txtEdad.getText());
        String familia = vista.txtFamilia.getText();
        String fecha = vista.txtFecha.getText();
        String especie_box = (String)vista.boxEspecie.getSelectedItem();
        
        int especie = 1;
        switch(especie_box){
            case "Otros": especie = 4;
                break;
            case "Animales marinos": especie = 1;
                break;
            case "Animales terrestres": especie = 2;
                break;
            case "Aracnidos": especie = 3;
                break;
        }
        
        a.setId(id);
        a.setEdad(edad);
        a.setFamilia(familia);
        a.setNombre(nombre);
        r.setFecha(fecha);
        r.setAnimales_id(id);
        r.setInventario_id(especie);
        
        int resp_animal = animalesDao.actualizar(a);
        int resp_registro = registroDao.actualizar(r);
        
        if(resp_animal == 1 && resp_registro == 1){
            JOptionPane.showMessageDialog(vista, "El animal se actualizo");
        }else{
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }
    
    
    
    
    
    // metodos que se llaman en el metodo actionPerformed
    
    
    public void limpiarTabla(){
        // limpia la tabla para evitar duplicacion cada vez que hay peticion
        // a la db
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
        // monta la lista de animales en la tabla de la interfaz grafica
        
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
        // agrega un animal extrayendo los datos del formulario
        
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
        // elimina un campo dependiendo de la fila seleccionada
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
