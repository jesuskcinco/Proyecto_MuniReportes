package com.example.proyectoandroid.Entidades;

public class NivelIncidente {


    public Integer idnivinc;
    public String descripcion;


    public NivelIncidente(Integer idnivinc, String descripcion) {
        this.idnivinc = idnivinc;
        this.descripcion = descripcion;
    }
    public NivelIncidente() {

    }
    public Integer getIdnivinc() {
        return idnivinc;
    }

    public void setIdnivinc(Integer idnivinc) {
        this.idnivinc = idnivinc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
