/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 * 
 * @author Alexander Granados <alexander.granados.dev@gmail.com>
 */
public class Registros {
    int id;
    String fecha;
    int animales_id;
    int inventario_id;
    
    public Registros(){}
    
    public Registros(int id, String fecha, int animales_id, int inventario_id){
        this.id = id;
        this.fecha = fecha;
        this.animales_id = animales_id;
        this.inventario_id = inventario_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAnimales_id() {
        return animales_id;
    }

    public void setAnimales_id(int animales_id) {
        this.animales_id = animales_id;
    }

    public int getInventario_id() {
        return inventario_id;
    }

    public void setInventario_id(int inventario_id) {
        this.inventario_id = inventario_id;
    }
    
    
    
}
