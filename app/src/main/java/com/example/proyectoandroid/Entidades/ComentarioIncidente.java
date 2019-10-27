package com.example.proyectoandroid.Entidades;

public class ComentarioIncidente {
    public Integer codcomentario;
    public String comentario;
    public String usucomenta;
    public String fechacomenta;
    public String visibilidad;

    public ComentarioIncidente() {
    }

    public ComentarioIncidente(Integer codcomentario, String comentario, String usucomenta, String fechacomenta, String visibilidad) {
        this.codcomentario = codcomentario;
        this.comentario = comentario;
        this.usucomenta = usucomenta;
        this.fechacomenta = fechacomenta;
        this.visibilidad = visibilidad;
    }

    public Integer getCodcomentario() {
        return codcomentario;
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
