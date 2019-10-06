package com.example.proyectoandroid.Entidades;

public class NivelIncidente {


    public String idnivinc;
    public String descripcion;

    public NivelIncidente(String idnivinc, String descripcion) {
        this.idnivinc = idnivinc;
        this.descripcion = descripcion;
    }
    public NivelIncidente() {

    }
    public String getIdnivinc() {
        return idnivinc;
    }

    public void setIdnivinc(String idnivinc) {
        this.idnivinc = idnivinc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
