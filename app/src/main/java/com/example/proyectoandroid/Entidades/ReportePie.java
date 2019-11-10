package com.example.proyectoandroid.Entidades;

public class ReportePie {


    public String descripciontipo;
    public String cantidad;
    public String nommes;
    public String codigotipo;


    public ReportePie() {
    }

    public ReportePie(String descripciontipo, String cantidad, String nommes, String codigotipo) {
        this.descripciontipo = descripciontipo;
        this.cantidad = cantidad;
        this.nommes = nommes;
        this.codigotipo = codigotipo;
    }

    public String getDescripciontipo() {
        return descripciontipo;
    }

    public void setDescripciontipo(String descripciontipo) {
        this.descripciontipo = descripciontipo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
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
