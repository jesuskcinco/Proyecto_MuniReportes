package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.proyectoandroid.Utilidades.GlobalVariables;

public class MotoSociedad extends AppCompatActivity {
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    GlobalVariables globalVariables;
    String dni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moto_sociedad);
        globalVariables = (GlobalVariables)getApplicationContext();
        dni=globalVariables.getDni();
    }
}
