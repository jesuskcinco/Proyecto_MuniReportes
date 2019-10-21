package com.example.proyectoandroid.Entidades;

public class Mensajes {
    public Integer cod_mensaje;
    public String mensaje_not;
    public String fecha;
    public Integer flgeliminar;
    public Integer dni_mensaje;

    public Mensajes() {
    }

    public Mensajes(Integer cod_mensaje, String mensaje_not, String fecha, Integer flgeliminar, Integer dni_mensaje) {
        this.cod_mensaje = cod_mensaje;
        this.mensaje_not = mensaje_not;
        this.fecha = fecha;
        this.flgeliminar = flgeliminar;
        this.dni_mensaje = dni_mensaje;
    }

    public Integer getDni_mensaje() {
        return dni_mensaje;
    }

    public void setDni_mensaje(Integer dni_mensaje) {
        this.dni_mensaje = dni_mensaje;
    }

    public Integer getCod_mensaje() {
        return cod_mensaje;
    }

    public void setCod_mensaje(Integer cod_mensaje) {
        this.cod_mensaje = cod_mensaje;
    }

    public String getMensaje_not() {
        return mensaje_not;
    }

    public void setMensaje_not(String mensaje_not) {
        this.mensaje_not = mensaje_not;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getFlgeliminar() {
        return flgeliminar;
    }

    public void setFlgeliminar(Integer flgeliminar) {
        this.flgeliminar = flgeliminar;
    }
}
