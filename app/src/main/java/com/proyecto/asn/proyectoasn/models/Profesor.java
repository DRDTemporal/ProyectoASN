package com.proyecto.asn.proyectoasn.models;

public class Profesor {
    private String nombre;
    private  String Apellido;
    private String emaill;

    public Profesor() {
    }

    public Profesor(String nombre, String apellido, String emaill) {
        this.nombre = nombre;
        Apellido = apellido;
        this.emaill = emaill;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

}
