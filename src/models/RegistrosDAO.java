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
import org.json.simple.parser.ParseException;

/**
 * 
 * @author Alexander Granados <alexander.granados.dev@gmail.com>
 */
public class RegistrosDAO {
    Connect conexion;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public RegistrosDAO() throws IOException, FileNotFoundException, ParseException {
        this.conexion = new Connect();
    }
    
    public int agregar(Registros r){
        
        String sql = "INSERT INTO registros(fecha, animales_id, inventario_id) values(?,?,?)";
        
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, r.getFecha());
            ps.setInt(2, r.getAnimales_id());
            ps.setInt(3, r.getInventario_id());
            ps.executeUpdate();
            
        } catch (Exception e) {
        }
        
        return 1;
    }
    
    public void eliminar (int animales_id){
    
        String sql = "DELETE FROM registros WHERE animales_id=" +animales_id;
        
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
