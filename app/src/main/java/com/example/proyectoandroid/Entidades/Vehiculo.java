package com.example.proyectoandroid.Entidades;

public class Vehiculo {

    public  String placa;
    public String vehiculo;
    public String modelo;
    public String color;
    public String dni_duenio;

    public Vehiculo(String placa, String vehiculo, String modelo, String color, String dni_duenio) {
        this.placa = placa;
        this.vehiculo = vehiculo;
        this.modelo = modelo;
        this.color = color;
        this.dni_duenio = dni_duenio;
    }

    public Vehiculo(){

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
}
