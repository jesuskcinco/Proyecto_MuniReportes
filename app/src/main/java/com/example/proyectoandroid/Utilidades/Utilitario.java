package com.example.proyectoandroid.Utilidades;

public class Utilitario {

    public static final String TABLE_NAME= "USUARIO";
    public static final String CAMPO_USUARIO= "nombre_usuario";
    public static final String CAMPO_CLAVE= "clave_usuario";
    public static final String CAMPO_CORREO= "correo_usuario";
    public static final String CAMPO_NOMBRES= "nombres_usuario";
    public static final String CAMPO_APELLIDOS= "apellidos_usuario";
    public static final String CAMPO_DNI= "dni_usuario";


    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLE_NAME+
            " ("+CAMPO_USUARIO+" TEXT,"+CAMPO_CLAVE+" TEXT,"+CAMPO_CORREO+" TEXT," +
            ""+CAMPO_NOMBRES+" TEXT,"+CAMPO_APELLIDOS+" TEXT,"+CAMPO_DNI+" int primary key)";
}
