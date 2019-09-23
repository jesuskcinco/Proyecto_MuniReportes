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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionNueva, int versionAntigua) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_MOTOTAXI);
        db.execSQL("DROP TABLE IF EXISTS "+Utilitario.TABLE_TIPO_INCIDENTE);
        onCreate(db);
    }
}
