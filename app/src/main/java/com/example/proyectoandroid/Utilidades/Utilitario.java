package com.example.proyectoandroid.Utilidades;

public class Utilitario {

    //Constantes para crear la tabla usuario
    public static final String TABLE_NAME= "USUARIO";
    public static final String CAMPO_USUARIO= "nombre_usuario";
    public static final String CAMPO_CLAVE= "clave_usuario";
    public static final String CAMPO_CORREO= "correo_usuario";
    public static final String CAMPO_NOMBRES= "nombres_usuario";
    public static final String CAMPO_APELLIDOS= "apellidos_usuario";
    public static final String CAMPO_DNI= "dni_usuario";


    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLE_NAME+
            " ("+CAMPO_USUARIO+" TEXT,"+CAMPO_CLAVE+" TEXT,"+CAMPO_CORREO+" TEXT," +
            ""+CAMPO_NOMBRES+" TEXT,"+CAMPO_APELLIDOS+" TEXT,"+CAMPO_DNI+" TEXT primary key)";

    //Constantes para crear la tabla mototaxi
    public static final String TABLE_MOTOTAXI= "VEHICULO";
    public static final String CAMPO_PLACA= "placa_vehiculo";
    public static final String CAMPO_VEHICULO= "tipo_vehiculo";
    public static final String CAMPO_MODELO= "modelo_vehiculo";
    public static final String CAMPOCOLOR= "colorvehiculo";
    public static final String CAMPO_DUENIO= "dni_usuario";


    public static final String CREAR_TABLA_VEHICULO="CREATE TABLE "+TABLE_MOTOTAXI+
            " ("+CAMPO_PLACA+" TEXT primary key,"+CAMPO_VEHICULO+" TEXT,"+CAMPO_MODELO+" TEXT," +
            ""+CAMPOCOLOR+" TEXT,"+CAMPO_DUENIO+" TEXT)";

}
