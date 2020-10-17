package com.proyecto.asn.proyectoasn.models;

public class Alumno extends Persona{
    private String id;
    private int  puntuacion = 0;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido) {
        super(nombre, apellido);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}

