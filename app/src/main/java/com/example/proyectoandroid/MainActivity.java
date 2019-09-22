package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Bundle datos;
    TextView getdni,getclave;
    String dniobt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datos = getIntent().getExtras();
        dniobt= datos.getString("pasar_usuario");
        //String claveobt= datos.getString("pasar_clave");
        if (dniobt.equals("")){
            dniobt="70546327";
        }
            getdni= (TextView) findViewById(R.id.dnipasado);
            //getclave= (TextView) findViewById(R.id.clavepasada);
            getdni.setText(dniobt);
            //getclave.setText(claveobt);
            ConexionSQLiteHelper conn= new ConexionSQLiteHelper (this,"bd_aplicativo",null, 1);


    }
    public void onClick (View view){
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.button:
                miIntent= new Intent(MainActivity.this, RegistrarUsuario.class);
                break;
            case R.id.button2:
                miIntent= new Intent(MainActivity.this, BuscarUsuario.class);
                break;
            case R.id.button3:
                miIntent= new Intent(MainActivity.this, EliminarUsuario.class);
                break;
            case R.id.button4:
                miIntent= new Intent(MainActivity.this, ActualizarUsuario.class);
                break;
            case R.id.button6:
                miIntent= new Intent(MainActivity.this, RegistrarMototaxi.class);
                Intent nuevamoto= new Intent(MainActivity.this,RegistrarMototaxi.class);
                nuevamoto.putExtra("pasar_usuario",dniobt);
                startActivity(nuevamoto);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }

}
