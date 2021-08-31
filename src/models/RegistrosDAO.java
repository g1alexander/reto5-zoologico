/*
 * Manejo de consultas sql para tabla intermedia (registros)
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
        // montado de conexion db
        this.conexion = new Connect();
    }
    
    public int agregar(Registros r){
        // retorna 1 si se inserta un campo en la tabla registros
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
        // elimina un campo de la tabla registros dependiendo del id recibido
    
        String sql = "DELETE FROM registros WHERE animales_id=" +animales_id;
        
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
