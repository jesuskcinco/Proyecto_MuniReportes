package com.example.proyectoandroid.Entidades;

public class Usuario {

    public String nom_usuario;
    public String clave;
    public String correo;
    public String nombres;
    public String apellidos;
    public Integer dni;
    public Integer flgcolaborador;
    public Integer flgusuario;
    public Integer flgconductor;
    public Integer flgduenio;
    public Integer flgbloqueado;


    public Usuario(String nom_usuario, String clave, String correo, String nombres, String apellidos, Integer dni) {
        this.nom_usuario = nom_usuario;
        this.clave = clave;
        this.correo = correo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public Usuario() {

    }
    public String getNom_usuario() {
        return nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        this.nom_usuario = nom_usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }
}
