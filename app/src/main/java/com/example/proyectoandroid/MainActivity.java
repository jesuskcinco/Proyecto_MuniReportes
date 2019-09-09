package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn= new ConexionSQLiteHelper (this,"bd_aplicativo",null, 1);
    }

    public void onClick (View view){
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.button:
                miIntent= new Intent(MainActivity.this, RegistrarUsuario.class);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);

    }
    public void onClick2 (View view){
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.button2:
                miIntent= new Intent(MainActivity.this, BuscarUsuario.class);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }
    public void onClick3 (View view){
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.button3:
                miIntent= new Intent(MainActivity.this, EliminarUsuario.class);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }
    public void onClick4 (View view){
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.button4:
                miIntent= new Intent(MainActivity.this, ActualizarUsuario.class);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }

}
