package com.example.proyectoandroid.Entidades;

public class ComentarioIncidente {
    public Integer codcomentario;
    public String comentario;
    public String usucomenta;
    public String fechacomenta;
    public String visibilidad;
public String usuariocomen;
    public ComentarioIncidente() {
    }

    public ComentarioIncidente(Integer codcomentario, String comentario, String usucomenta, String fechacomenta, String visibilidad, String usuariocomen) {
        this.codcomentario = codcomentario;
        this.comentario = comentario;
        this.usucomenta = usucomenta;
        this.fechacomenta = fechacomenta;
        this.visibilidad = visibilidad;
        this.usuariocomen = usuariocomen;
    }

    public Integer getCodcomentario() {
        return codcomentario;
    }

    public String getUsuariocomen() {
        return usuariocomen;
    }

    public void setUsuariocomen(String usuariocomen) {
        this.usuariocomen = usuariocomen;
    }

    public void setCodcomentario(Integer codcomentario) {
        this.codcomentario = codcomentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUsucomenta() {
        return usucomenta;
    }

    public void setUsucomenta(String usucomenta) {
        this.usucomenta = usucomenta;
    }

    public String getFechacomenta() {
        return fechacomenta;
    }

    public void setFechacomenta(String fechacomenta) {
        this.fechacomenta = fechacomenta;
    }

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }
}
