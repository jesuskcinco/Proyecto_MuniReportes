package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Bundle datos;
    TextView getdni,getclave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datos = getIntent().getExtras();
        String dniobt= datos.getString("pasar_dni");
        String claveobt= datos.getString("pasar_clave");
        getdni= (TextView) findViewById(R.id.dnipasado);
        getclave= (TextView) findViewById(R.id.clavepasada);
        getdni.setText(dniobt);
        getclave.setText(claveobt);
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
