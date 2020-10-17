package com.proyecto.asn.proyectoasn.models;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends Persona{

    private String emaill;
    private List<Curso> curso = new ArrayList<>();

    public Profesor() {
    }

    public Profesor(String nombre, String apellido, String emaill, List<Curso> curso) {
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

    public List<Curso> getCurso() {
        return curso;
    }

}
