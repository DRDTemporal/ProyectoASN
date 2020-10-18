package com.proyecto.asn.proyectoasn.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Curso {
    private String id;
    private String nombre;
    private Map<String ,Alumno> alumnos = new HashMap<>();

    public Curso() {
    }

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Map<String, Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
