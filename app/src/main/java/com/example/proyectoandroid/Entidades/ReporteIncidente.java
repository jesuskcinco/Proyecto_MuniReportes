package com.example.proyectoandroid.Entidades;

public class ReporteIncidente {

    public Integer codreporte;
    public String asunto;
    public Integer tipoinc;
    public Integer nivelinc;
    public String descripcion;
    public byte[] image1;
    public byte[] image2;
    public byte[] image3;
    public String placamoto;
    public String placaotro;
    public String marcarotro;
    public String modelootro;
    public String latitud;
    public String longitud;
    public String direccion;
    public String usuario;
    public String fecha;
    public String estado;

    public ReporteIncidente() {

    }

    public ReporteIncidente(Integer codreporte, String asunto, Integer tipoinc, Integer nivelinc, String descripcion, byte[] image1, byte[] image2, byte[] image3, String placamoto, String placaotro, String marcarotro, String modelootro, String latitud, String longitud, String direccion, String usuario,String fecha, String estado) {
        this.codreporte = codreporte;
        this.asunto = asunto;
        this.tipoinc = tipoinc;
        this.nivelinc = nivelinc;
        this.descripcion = descripcion;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.placamoto = placamoto;
        this.placaotro = placaotro;
        this.marcarotro = marcarotro;
        this.modelootro = modelootro;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.usuario = usuario;
        this.fecha= fecha;
        this.estado=estado;
    }

    public Integer getCodreporte() {
        return codreporte;
    }

    public void setCodreporte(Integer codreporte) {
        this.codreporte = codreporte;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Integer getTipoinc() {
        return tipoinc;
    }

    public void setTipoinc(Integer tipoinc) {
        this.tipoinc = tipoinc;
    }

    public Integer getNivelinc() {
        return nivelinc;
    }

    public void setNivelinc(Integer nivelinc) {
        this.nivelinc = nivelinc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    public byte[] getImage3() {
        return image3;
    }

    public void setImage3(byte[] image3) {
        this.image3 = image3;
    }

    public String getPlacamoto() {
        return placamoto;
    }

    public void setPlacamoto(String placamoto) {
        this.placamoto = placamoto;
    }

    public String getPlacaotro() {
        return placaotro;
    }

    public void setPlacaotro(String placaotro) {
        this.placaotro = placaotro;
    }

    public String getMarcarotro() {
        return marcarotro;
    }

    public void setMarcarotro(String marcarotro) {
        this.marcarotro = marcarotro;
    }

    public String getModelootro() {
        return modelootro;
    }

    public void setModelootro(String modelootro) {
        this.modelootro = modelootro;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
