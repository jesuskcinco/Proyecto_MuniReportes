package com.example.proyectoandroid.Entidades;

public class Vehiculo {

    public  String placa;
    public String vehiculo;
    public String modelo;
    public String color;
    public String dni_duenio;
    public byte[] image1;
    public Integer cantidadreportes;
    public Integer idsociedad;

    public Vehiculo() {
    }

    public Vehiculo(String placa, String vehiculo, String modelo, String color, String dni_duenio, byte[] image1, Integer cantidadreportes, Integer idsociedad) {
        this.placa = placa;
        this.vehiculo = vehiculo;
        this.modelo = modelo;
        this.color = color;
        this.dni_duenio = dni_duenio;
        this.image1 = image1;
        this.cantidadreportes = cantidadreportes;
        this.idsociedad = idsociedad;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDni_duenio() {
        return dni_duenio;
    }

    public void setDni_duenio(String dni_duenio) {
        this.dni_duenio = dni_duenio;
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public Integer getCantidadreportes() {
        return cantidadreportes;
    }

    public void setCantidadreportes(Integer cantidadreportes) {
        this.cantidadreportes = cantidadreportes;
    }

    public Integer getIdsociedad() {
        return idsociedad;
    }

    public void setIdsociedad(Integer idsociedad) {
        this.idsociedad = idsociedad;
    }
}
