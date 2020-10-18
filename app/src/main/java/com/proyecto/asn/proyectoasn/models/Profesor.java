package com.proyecto.asn.proyectoasn.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profesor extends Persona{

    private String emaill;
    private Map<String ,Curso> curso = new HashMap<>();

    public Profesor() {
    }

    public Profesor(String nombre, String apellido, String emaill, Map<String, Curso> curso) {
        super(nombre, apellido);
        this.emaill = emaill;
        this.curso = curso;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public Map<String, Curso> getCurso() {
        return curso;
    }

    public void setCurso(Map<String, Curso> curso) {
        this.curso = curso;
    }
}
