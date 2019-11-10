package com.example.proyectoandroid.Entidades;

public class RegistroaSociedad {
    public Integer idregistrosociedad;
    public Integer idsociedad;
    public String placa_sociedad;
    public Integer usuario_sociedad;
    public String fecha_reg_socie;
    public String estado_sociedad2;


    public String descsociedad;

    public RegistroaSociedad(Integer idregistrosociedad, Integer idsociedad, String placa_sociedad, Integer usuario_sociedad, String fecha_reg_socie, String estado_sociedad2, String descsociedad) {
        this.idregistrosociedad = idregistrosociedad;
        this.idsociedad = idsociedad;
        this.placa_sociedad = placa_sociedad;
        this.usuario_sociedad = usuario_sociedad;
        this.fecha_reg_socie = fecha_reg_socie;
        this.estado_sociedad2 = estado_sociedad2;
        this.descsociedad = descsociedad;
    }

    public RegistroaSociedad() {
    }

    public Integer getIdregistrosociedad() {
        return idregistrosociedad;
    }

    public void setIdregistrosociedad(Integer idregistrosociedad) {
        this.idregistrosociedad = idregistrosociedad;
    }

    public Integer getIdsociedad() {
        return idsociedad;
    }

    public void setIdsociedad(Integer idsociedad) {
        this.idsociedad = idsociedad;
    }

    public String getPlaca_sociedad() {
        return placa_sociedad;
    }

    public void setPlaca_sociedad(String placa_sociedad) {
        this.placa_sociedad = placa_sociedad;
    }

    public Integer getUsuario_sociedad() {
        return usuario_sociedad;
    }

    public void setUsuario_sociedad(Integer usuario_sociedad) {
        this.usuario_sociedad = usuario_sociedad;
    }

    public String getFecha_reg_socie() {
        return fecha_reg_socie;
    }

    public void setFecha_reg_socie(String fecha_reg_socie) {
        this.fecha_reg_socie = fecha_reg_socie;
    }

    public String getEstado_sociedad2() {
        return estado_sociedad2;
    }

    public void setEstado_sociedad2(String estado_sociedad2) {
        this.estado_sociedad2 = estado_sociedad2;
    }
    public String getDescsociedad() {
        return descsociedad;
    }

    public void setDescsociedad(String descsociedad) {
        this.descsociedad = descsociedad;
    }

}
