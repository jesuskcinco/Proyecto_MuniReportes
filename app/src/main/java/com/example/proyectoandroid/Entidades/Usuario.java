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
    public Integer idsociedad;
    public byte[] image1;

    public Usuario() {
    }

    public Usuario(String nom_usuario, String clave, String correo, String nombres, String apellidos, Integer dni, Integer flgcolaborador, Integer flgusuario, Integer flgconductor, Integer flgduenio, Integer flgbloqueado, Integer idsociedad, byte[] image1) {
        this.nom_usuario = nom_usuario;
        this.clave = clave;
        this.correo = correo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.flgcolaborador = flgcolaborador;
        this.flgusuario = flgusuario;
        this.flgconductor = flgconductor;
        this.flgduenio = flgduenio;
        this.flgbloqueado = flgbloqueado;
        this.idsociedad = idsociedad;
        this.image1 = image1;
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

    public Integer getFlgcolaborador() {
        return flgcolaborador;
    }

    public void setFlgcolaborador(Integer flgcolaborador) {
        this.flgcolaborador = flgcolaborador;
    }

    public Integer getFlgusuario() {
        return flgusuario;
    }

    public void setFlgusuario(Integer flgusuario) {
        this.flgusuario = flgusuario;
    }

    public Integer getFlgconductor() {
        return flgconductor;
    }

    public void setFlgconductor(Integer flgconductor) {
        this.flgconductor = flgconductor;
    }

    public Integer getFlgduenio() {
        return flgduenio;
    }

    public void setFlgduenio(Integer flgduenio) {
        this.flgduenio = flgduenio;
    }

    public Integer getFlgbloqueado() {
        return flgbloqueado;
    }

    public void setFlgbloqueado(Integer flgbloqueado) {
        this.flgbloqueado = flgbloqueado;
    }

    public Integer getIdsociedad() {
        return idsociedad;
    }

    public void setIdsociedad(Integer idsociedad) {
        this.idsociedad = idsociedad;
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

}
