package com.example.proyectoandroid.Entidades;

public class ReportePie {


    public String descripciontipo;
    public Integer cantidad;
    public String nommes;
    public String codigotipo;
    public String nummes;

    public ReportePie() {
    }

    public ReportePie(String descripciontipo, Integer cantidad, String nommes, String codigotipo, String nummes) {
        this.descripciontipo = descripciontipo;
        this.cantidad = cantidad;
        this.nommes = nommes;
        this.codigotipo = codigotipo;
        this.nummes = nummes;
    }

    public String getNummes() {
        return nummes;
    }

    public void setNummes(String nummes) {
        this.nummes = nummes;
    }

    public String getDescripciontipo() {
        return descripciontipo;
    }

    public void setDescripciontipo(String descripciontipo) {
        this.descripciontipo = descripciontipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNommes() {
        return nommes;
    }

    public void setNommes(String nommes) {
        this.nommes = nommes;
    }

    public String getCodigotipo() {
        return codigotipo;
    }

    public void setCodigotipo(String codigotipo) {
        this.codigotipo = codigotipo;
    }
}
