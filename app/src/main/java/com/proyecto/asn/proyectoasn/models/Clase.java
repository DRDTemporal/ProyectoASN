package com.proyecto.asn.proyectoasn.models;

import java.util.ArrayList;
import java.util.List;

public class Clase {
    private  Profesor profesor;
    private List<Alumno> alumnos = new ArrayList<>();

    public Clase() {
    }

    public Clase(Profesor profesor, List<Alumno> alumnos) {
        this.profesor = profesor;
        this.alumnos = alumnos;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
