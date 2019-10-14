package com.example.proyectoandroid.Utilidades;

import android.app.Application;

public class GlobalVariables extends Application {
    private String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
