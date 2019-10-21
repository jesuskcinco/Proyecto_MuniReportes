package com.example.proyectoandroid.Entidades;

public class ComentarioIncidente {
    public Integer codcomentario;
    public String comentario;
    public String usucomenta;
    public String fechacomenta;

    public ComentarioIncidente(Integer codcomentario, String comentario, String usucomenta, String fechacomenta) {
        this.codcomentario = codcomentario;
        this.comentario = comentario;
        this.usucomenta = usucomenta;
        this.fechacomenta = fechacomenta;
    }
    public ComentarioIncidente(){

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

    public void setUsucomenta(String usuario) {
        this.usucomenta = usuario;
    }

    public String getFecha() {
        return fechacomenta;
    }

    public void setFecha(String fecha) {
        this.fechacomenta = fecha;
    }
}
