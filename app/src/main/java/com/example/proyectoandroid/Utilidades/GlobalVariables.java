package com.example.proyectoandroid.Utilidades;

import android.app.Application;

public class GlobalVariables extends Application {
    private String dni;
private String nomusu;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomusu() {
        return nomusu;
    }

    public void setNomusu(String nomusu) { this.nomusu = nomusu;    }
}
