package com.example.proyectoandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectoandroid.Utilidades.Utilitario;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilitario.CREAR_TABLA_USUARIO);
        db.execSQL(Utilitario.CREAR_TABLA_VEHICULO);
        db.execSQL(Utilitario.CREAR_TABLA_TIPO_INCIDENTE);
        db.execSQL(Utilitario.CREAR_TABLA_NIVEL_INCIDENTE);
        db.execSQL(Utilitario.CREAR_TABLA_REPORTE);
        db.execSQL(Utilitario.CREAR_TABLA_COMENTARIO);
        db.execSQL(Utilitario.CREAR_TABLA_SOCIEDAD);
        db.execSQL(Utilitario.CREAR_TABLA_MENSAJE);
        db.execSQL(Utilitario.CREAR_TABLA_USU_SOCIEDAD);
        db.execSQL(Utilitario.CREAR_TABLA_MOTO_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionNueva, int versionAntigua) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_MOTOTAXI);
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_TIPO_INCIDENTE);
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_NIVEL_INCIDENTE);
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_REPORTE_INCIDENTE);

        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_COMENTARIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_SOCIEDAD);
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_MENSAJES);
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_USU_SOCIEDAD);
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_USU_MOTO);
        onCreate(db);
    }
}
