package com.example.proyectoandroid.Entidades;

public class TipoIncidente {
    public String descripcion;
    public String codigo;
    public String estado;
    public String dniregistra;

    public TipoIncidente(String descripcion, String codigo, String estado, String dniregistra) {
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.estado = estado;
        this.dniregistra = dniregistra;
    }
    public TipoIncidente(){

    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDniregistra() {
        return dniregistra;
    }

    public void setDniregistra(String dniregistra) {
        this.dniregistra = dniregistra;
    }
}
