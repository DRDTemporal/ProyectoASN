package com.proyecto.asn.proyectoasn.models;

public class Alumno extends Persona{
    private int  puntuacion = 0;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, int puntuacion) {
        super(nombre, apellido);
        this.puntuacion = puntuacion;
    }
}

