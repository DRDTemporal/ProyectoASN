package com.proyecto.asn.proyectoasn.models;

public class Profesor {
    private int id;
    private String nombre;
    private  String Apellido;
    private String emaill;

    public Profesor() {
    }

    public Profesor(int id, String nombre, String apellido, String emaill) {
        this.id = id;
        this.nombre = nombre;
        Apellido = apellido;
        this.emaill = emaill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
