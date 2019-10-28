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
    public static final String CAMPO_IMAGE= "image1";
    public static final String CAMPO_FLGCOLABORADOR="flgcolaborador";
    public static final String CAMPO_FLGUSUARIO="flgusuario";
    public static final String CAMPO_FLGCONDUCTOR="flgconductor";
    public static final String CAMPO_FLGDUENIO="flgduenio";
    public static final String CAMPO_FLGBLOQUEADO="flgbloqueado";
    public static final String CAMPO_IDSOCIEDAD="idsociedad";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLE_NAME+
            " ("+CAMPO_USUARIO+" TEXT,"+CAMPO_CLAVE+" TEXT,"+CAMPO_CORREO+" TEXT," +
            ""+CAMPO_NOMBRES+" TEXT,"+CAMPO_APELLIDOS+" TEXT,"+CAMPO_DNI+" TEXT primary key,"+CAMPO_FLGCOLABORADOR+" TEXT,"
            +CAMPO_FLGUSUARIO+" TEXT,"+CAMPO_FLGCONDUCTOR+" TEXT,"+CAMPO_FLGDUENIO+" TEXT,"+CAMPO_FLGBLOQUEADO+" TEXT,"+CAMPO_IDSOCIEDAD+" TEXT,"+CAMPO_IMAGE+" blob)";

    //Constantes para crear la tabla mototaxi
    public static final String TABLE_MOTOTAXI= "VEHICULO";
    public static final String CAMPO_PLACA= "placa_vehiculo";
    public static final String CAMPO_VEHICULO= "tipo_vehiculo";
    public static final String CAMPO_MODELO= "modelo_vehiculo";
    public static final String CAMPOCOLOR= "colorvehiculo";
    public static final String CAMPO_DUENIO= "dni_usuario";


    public static final String CREAR_TABLA_VEHICULO="CREATE TABLE "+TABLE_MOTOTAXI+
            " ("+CAMPO_PLACA+" TEXT primary key,"+CAMPO_VEHICULO+" TEXT,"+CAMPO_MODELO+" TEXT," +
            ""+CAMPOCOLOR+" TEXT,"+CAMPO_DUENIO+" TEXT,"+CAMPO_IMAGE+" blob)";

    ///Constantes para crear la tabla Tipo Incidente
    public static final String TABLE_TIPO_INCIDENTE= "TIPOINCIDENTE";
    public static final String CAMPO_CODIGO= "codigo_incidente";
    public static final String CAMPO_DESCRIPCION= "tipo_incidente";
    public static final String CAMPO_ESTADO= "estado_incidente";
    public static final String CAMPO_DNI_REG= "dni_usuario";


    public static final String CREAR_TABLA_TIPO_INCIDENTE="CREATE TABLE "+TABLE_TIPO_INCIDENTE+
            " ("+CAMPO_CODIGO+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_DESCRIPCION+" TEXT,"+CAMPO_ESTADO+" TEXT," +
            ""+CAMPO_DNI_REG+" TEXT)";

    ///Constantes para crear la tabla Nivel Incidente
    public static final String TABLE_NIVEL_INCIDENTE= "NIVELINCIDENTE";
    public static final String CAMPO_ID_NIV_INC= "idnivinc";
    public static final String CAMPO_DESC_NIVEL_INC= "desc_niv_inc";


    public static final String CREAR_TABLA_NIVEL_INCIDENTE="CREATE TABLE "+TABLE_NIVEL_INCIDENTE+
            " ("+CAMPO_ID_NIV_INC+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_DESC_NIVEL_INC+" TEXT)";

    ///Constantes para crear la tabla Reporte Incidente
    public static final String TABLE_REPORTE_INCIDENTE= "REPORTEINCIDENTE";
    public static final String CAMPO_ID_REPORTE= "cod_reporte";
    public static final String CAMPO_ASUNTO= "asunto_reporte";
    //CAMPO_CODIGO
    //CAMPO_ID_NIV_INC
    public static final String CAMPO_DESC_REPORTE="desc_reporte";
    public static final String CAMPO_IMAGEN_1="imagen1";
    public static final String CAMPO_IMAGEN_2="imagen2";
    public static final String CAMPO_IMAGEN_3="imagen3";
    public static final String CAMPO_LATITUD_GPS="latitud";
    public static final String CAMPO_LONGITUD_GPS="longitud";
    public static final String CAMPO_DIRECCION_GPS="direccion";
    //CAMPO_PLACA_MOTO
    public static final String CAMPO_PLACA_OTRO="placa_otro";
    public static final String CAMPO_MARCA_OTRO="marca_otro";
    public static final String CAMPO_MODELO_OTRO="modelo_otro";
    public static final String CAMPO_FECHA="fecha_reporte";
    public static final String CAMPO_ESTADO_REPORTE="estado_reporte";
    //CAMPO_USUARIO

    public static final String CREAR_TABLA_REPORTE="CREATE TABLE "+TABLE_REPORTE_INCIDENTE+
            " ("+CAMPO_ID_REPORTE+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_ASUNTO+" TEXT,"+CAMPO_CODIGO+" TEXT," +
            CAMPO_ID_NIV_INC+" TEXT,"+CAMPO_DESC_REPORTE+" TEXT,"+CAMPO_IMAGEN_1+" BLOB,"+CAMPO_IMAGEN_2+" BLOB,"+
            CAMPO_IMAGEN_3+" BLOB,"+CAMPO_LATITUD_GPS+" TEXT,"+CAMPO_LONGITUD_GPS+" TEXT,"+CAMPO_DIRECCION_GPS+" TEXT,"+
            CAMPO_PLACA+" TEXT,"+CAMPO_PLACA_OTRO+" TEXT,"+CAMPO_MARCA_OTRO+" TEXT,"+CAMPO_MODELO_OTRO+" TEXT,"+CAMPO_FECHA+" TEXT,"
            +CAMPO_ESTADO_REPORTE+" TEXT,"+CAMPO_DNI+" TEXT)";

    //Constantes para crear la tabla comentarios
    public static final String TABLE_COMENTARIO="COMENTARIOS";
    public static final String CAMPO_COD_COMENTARIO="codcomentario";
    public static final String CAMPO_COMENTARIO="comentario";
    public static final String CAMPO_FECHA_COMENT="fechacomenta";
    public static final String CAMPO_VISIBILIDAD="visibilidad";
    public static final String CREAR_TABLA_COMENTARIO="CREATE TABLE "+TABLE_COMENTARIO+
            " ("+CAMPO_COD_COMENTARIO+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_COMENTARIO+" TEXT,"+
            CAMPO_DNI+" TEXT,"+CAMPO_FECHA_COMENT+" TEXT,"+CAMPO_VISIBILIDAD+" TEXT)";

    //constantes para crear la tabla mensajes
    public static final String TABLE_MENSAJES="MENSAJES";
    public static final String CAMPO_ID_MENSAJE="cod_mensaje";
    public static final String CAMPO_MENSAJE="mensaje_not";
    public static final String CAMPO_ELIMINAR="flgeliminar";

    public static final String CREAR_TABLA_MENSAJE="CREATE TABLE "+TABLE_MENSAJES+
            " ("+CAMPO_ID_MENSAJE+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_MENSAJE+" TEXT,"+CAMPO_FECHA+" TEXT," +CAMPO_DNI+" INTEGER,"+
            CAMPO_PLACA+" TEXT,"+CAMPO_ELIMINAR+" INTEGER)";

    //constantes para crear la tabla sociedad
    public static final String TABLE_SOCIEDAD="SOCIEDAD";
    public static final String CAMPO_SOCIEDAD="idsociedad";
    public static final String CAMPO_NOM_DUENIO="duenio";
    public static final String CAMPO_INTEGRANTES="cantidadintegrantes";
    public static final String CAMPO_NOM_SOCIEDAD="nombresociedad";
    public static final String CAMPO_FECHA_REG_SOC="fecha";
    public static final String CAMPO_DISTRITO="distrito";
    public static final String CAMPO_ESTADO_SOCIEDAD="estado_sociedad";

    public static final String CREAR_TABLA_SOCIEDAD="CREATE TABLE "+TABLE_SOCIEDAD+
            " ("+CAMPO_SOCIEDAD+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_NOM_DUENIO+" TEXT,"+CAMPO_INTEGRANTES+" INTEGER," +
            CAMPO_NOM_SOCIEDAD+" TEXT,"+CAMPO_FECHA_REG_SOC+" TEXT,"+CAMPO_DISTRITO+" TEXT,"+CAMPO_ESTADO_SOCIEDAD+" TEXT)";

    //CONSTANTES PARA CREAR LA TABLA USUARIO SOCIEDAD
    public static final String TABLE_USU_SOCIEDAD="USUARIOSOCIEDAD";
    public static final String CAMPO_ID_USU_SOC="idregistrosociedad";
    public static final String CAMPO_FECHA_REG_SO="fecha_reg_socie";
    public static final String CAMPO_ESTADO_SOCIEDAD2="estado_sociedad";

    public static final String CREAR_TABLA_USU_SOCIEDAD="CREATE TABLE "+TABLE_USU_SOCIEDAD+
            " ("+CAMPO_ID_USU_SOC+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_SOCIEDAD+" INTEGER,"+CAMPO_PLACA+" TEXT," +
            CAMPO_DNI+" INTEGER,"+CAMPO_FECHA_REG_SO+" TEXT,"+CAMPO_ESTADO_SOCIEDAD2+" TEXT)";

    //CONSTANES PARA CREAR LA TABLA USUARIO_MOTO
    public static final String TABLE_USU_MOTO="CONDUCTORMOTO";
    public static final String CAMPO_ID_USU_MOTO="cod_registro_moto_cond";
    public static final String CAMPO_OBSERVACION="observacion";
    public static final String CAMPO_FECHA_USU_MOTO="fecha_usu_moto";
    public static final String CAMPO_OK_DUENIO="ok_duenio";
    public static final String CAMPO_ESTADO_MOTO_COND="estado_moto_cond";
    public static final String CREAR_TABLA_MOTO_USUARIO="CREATE TABLE "+TABLE_USU_MOTO+
            " ("+CAMPO_ID_USU_MOTO+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_DNI+" INTEGER,"+CAMPO_PLACA+" TEXT," +
            CAMPO_OBSERVACION+" TEXT,"+CAMPO_FECHA_USU_MOTO+" TEXT,"+CAMPO_OK_DUENIO+" TEXT,"+CAMPO_ESTADO_MOTO_COND+" TEXT)";
}
