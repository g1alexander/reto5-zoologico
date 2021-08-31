/*
 * Modelo para manejar la tabla de animales y 
 * datos adicionales (ingreso, especie, responsable)
 */

package models;

import java.sql.Date;

/**
 * 
 * @author Alexander Granados <alexander.granados.dev@gmail.com>
 */
public class Animales {
    int id;
    String nombre;
    int edad;
    String familia;
    Date ingreso;
    String especie;
    String responsable;
    
    
    public Animales(){}
    
    public Animales(
        int id, 
        String nombre,
        int edad,
        String familia,
        Date ingreso,
        String especie,
        String responsable
    ){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.familia = familia;
        this.ingreso = ingreso;
        this.especie = especie;
        this.responsable = responsable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    
    
    
    
    
}
