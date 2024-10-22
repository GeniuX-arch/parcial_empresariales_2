package com.parcial.app.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coordinadores") // Adding the annotation to map the entity to the "coordinadores" collection
public class Coordinadores {
    @Id
    private String id;

    private String cedula;
    private String contrasena;
    private String nombre;

    // Constructor por defecto
    public Coordinadores() {
    }

    // Constructor parametrizado
    public Coordinadores(String cedula, String contrasena, String nombre) {
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.nombre = nombre; // Inicializa el nombre
    }

    // Getter para cedula
    public String getId() {
        return id;
    }

// Setter para id
    public void setId(String id) {
        this.id = id;
    }
    // Getter para cedula
    public String getCedula() {
        return cedula;
    }

    // Setter para cedula
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    // Getter para contrasena
    public String getContrasena() {
        return contrasena;
    }

    // Setter para contrasena
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
