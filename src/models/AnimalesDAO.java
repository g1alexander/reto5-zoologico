/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import config.Connect;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author Alexander Granados <alexander.granados.dev@gmail.com>
 */
public class AnimalesDAO {
    Connect conexion;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public AnimalesDAO() throws IOException, FileNotFoundException, ParseException {
        this.conexion = new Connect();
    }
    
    public int listarUltimo(){
        String sql = "SELECT id FROM animales a ORDER BY id DESC LIMIT 1";
        
        int id_animal = 0;
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
 
            while (rs.next()) {                
                id_animal = rs.getInt(1);

            } 
     
        } catch (Exception e) {
        }
        
        return id_animal;
        
    }
    
    public List listar(){
        List<Animales> datos = new ArrayList<>();
        String sql = "SELECT a.id, a.nombre, a.edad, a.familia, r.fecha, "
                + "i.nombre, r2.nombre FROM animales a "
                + "INNER JOIN registros r "
                + "ON r.animales_id = a.id "
                + "INNER JOIN inventarios i "
                + "ON i.id = r.inventario_id "
                + "INNER JOIN responsables r2 "
                + "ON r2.usuario = i.responsable_user ORDER BY r.fecha DESC";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
           
            while (rs.next()) {                
                Animales a = new Animales();
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setEdad(rs.getInt(3));
                a.setFamilia(rs.getString(4));
                a.setIngreso(rs.getDate(5));
                a.setEspecie(rs.getString(6));
                a.setResponsable(rs.getString(7));
                
                datos.add(a);
            } 
           
            
        } catch (Exception e) {
        }
        
        return datos;
    }
    
    public int agregar(Animales a){
        
        String sql = "INSERT INTO animales(nombre, edad, familia) values(?,?,?)";
        
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getNombre());
            ps.setInt(2, a.getEdad());
            ps.setString(3, a.getFamilia());
            ps.executeUpdate();
            
        } catch (Exception e) {
        }
        
        return 1;
    }
    /*
 
    public int actualizar (Animales p){
        int r = 0;
        String sql = "UPDATE persona SET dni=?, nombre=? WHERE id=?";
        
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getDni());
            ps.setString(2, p.getNombre());
            ps.setInt(3, p.getId());
            r = ps.executeUpdate();
            
           r = (r == 1) ? 1 : 0;
        
        } catch (Exception e) {
        }
        
        return r;
    }
    
    */
    public void eliminar (int id){
    
        String sql = "DELETE FROM animales WHERE id=" +id;
        
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
