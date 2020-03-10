package com.proyecto.asn.proyectoasn.models;

import java.util.List;

public class Clase {
    private  Profesor profesor;
    private List<String> alumnos;

    public Clase() {
    }

    public Clase(Profesor profesor, List<String> alumnos) {
        this.profesor = profesor;
        this.alumnos = alumnos;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<String> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<String> alumnos) {
        this.alumnos = alumnos;
    }
}
