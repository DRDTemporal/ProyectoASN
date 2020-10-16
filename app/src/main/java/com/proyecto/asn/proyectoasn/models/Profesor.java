package com.proyecto.asn.proyectoasn.models;

public class Profesor extends Persona{

    private String emaill;

    public Profesor() {
    }

    public Profesor(String nombre, String apellido, String emaill) {
        super(nombre, apellido);
        this.emaill = emaill;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }
}
