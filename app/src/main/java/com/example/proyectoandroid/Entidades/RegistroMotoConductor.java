package com.example.proyectoandroid.Entidades;

public class RegistroMotoConductor {
    public Integer cod_registro_moto_cond;
    public Integer id_usuario_cond;
    public String placa_conductor;
    public String observacion;
    public String fecha;
    public String ok_duenio;
    public String estado_moto_cond;

    public RegistroMotoConductor() {
    }

    public RegistroMotoConductor(Integer cod_registro_moto_cond, Integer id_usuario_cond, String placa_conductor, String observacion, String fecha, String ok_duenio, String estado_moto_cond) {
        this.cod_registro_moto_cond = cod_registro_moto_cond;
        this.id_usuario_cond = id_usuario_cond;
        this.placa_conductor = placa_conductor;
        this.observacion = observacion;
        this.fecha = fecha;
        this.ok_duenio = ok_duenio;
        this.estado_moto_cond = estado_moto_cond;
    }

    public Integer getCod_registro_moto_cond() {
        return cod_registro_moto_cond;
    }

    public void setCod_registro_moto_cond(Integer cod_registro_moto_cond) {
        this.cod_registro_moto_cond = cod_registro_moto_cond;
    }

    public Integer getId_usuario_cond() {
        return id_usuario_cond;
    }

    public void setId_usuario_cond(Integer id_usuario_cond) {
        this.id_usuario_cond = id_usuario_cond;
    }

    public String getPlaca_conductor() {
        return placa_conductor;
    }

    public void setPlaca_conductor(String placa_conductor) {
        this.placa_conductor = placa_conductor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getOk_duenio() {
        return ok_duenio;
    }

    public void setOk_duenio(String ok_duenio) {
        this.ok_duenio = ok_duenio;
    }

    public String getEstado_moto_cond() {
        return estado_moto_cond;
    }

    public void setEstado_moto_cond(String estado_moto_cond) {
        this.estado_moto_cond = estado_moto_cond;
    }
}
