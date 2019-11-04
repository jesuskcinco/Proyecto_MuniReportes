package com.example.proyectoandroid.Entidades;

public class Sociedad {
    public Integer idsociedad;
    public String duenio;
    public Integer cantidadintegrantes;
    public String nombresociedad;
    public String fecha;
    public String distrito;
    public String zona;
    public String estado_sociedad;
    public Integer usuregistra;

    public Sociedad() {
    }

    public Sociedad(Integer idsociedad, String duenio, Integer cantidadintegrantes, String nombresociedad, String fecha, String distrito, String zona, String estado_sociedad, Integer usuregistra) {
        this.idsociedad = idsociedad;
        this.duenio = duenio;
        this.cantidadintegrantes = cantidadintegrantes;
        this.nombresociedad = nombresociedad;
        this.fecha = fecha;
        this.distrito = distrito;
        this.zona = zona;
        this.estado_sociedad = estado_sociedad;
        this.usuregistra = usuregistra;
    }

    public Integer getIdsociedad() {
        return idsociedad;
    }

    public void setIdsociedad(Integer idsociedad) {
        this.idsociedad = idsociedad;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public Integer getCantidadintegrantes() {
        return cantidadintegrantes;
    }

    public void setCantidadintegrantes(Integer cantidadintegrantes) {
        this.cantidadintegrantes = cantidadintegrantes;
    }

    public String getNombresociedad() {
        return nombresociedad;
    }

    public void setNombresociedad(String nombresociedad) {
        this.nombresociedad = nombresociedad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getEstado_sociedad() {
        return estado_sociedad;
    }

    public void setEstado_sociedad(String estado_sociedad) {
        this.estado_sociedad = estado_sociedad;
    }

    public Integer getUsuregistra() {
        return usuregistra;
    }

    public void setUsuregistra(Integer usuregistra) {
        this.usuregistra = usuregistra;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
