package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.proyectoandroid.Utilidades.GlobalVariables;

public class MainActivity extends AppCompatActivity {
    GlobalVariables globalVariables;
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
        if (dniobt.isEmpty()){
            dniobt="70546327";
        }
        globalVariables = (GlobalVariables)getApplicationContext();
        globalVariables.setDni(dniobt);
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
                miIntent.putExtra("pasar_usuario",dniobt);
                break;
            case R.id.button11:
                miIntent= new Intent(MainActivity.this, RegistrarTipoIncidente.class);
                miIntent.putExtra("pasar_usuario",dniobt);

                break;
            case R.id.button14:
                miIntent= new Intent(MainActivity.this, RegistrarNivelncidente.class);
                //miIntent.putExtra("pasar_usuario",dniobt);
                break;
            case R.id.button15:
                miIntent= new Intent(MainActivity.this, RegistrarIncidente.class);
                miIntent.putExtra("pasar_usuario",dniobt);
                break;
            case R.id.button16:
                miIntent= new Intent(MainActivity.this, PanelIncidentes.class);

                //miIntent.putExtra("pasar_usuario",dniobt);
                break;
            case R.id.btnsalirfinal:
                miIntent= new Intent(MainActivity.this, IniciarSesion.class);

                //miIntent.putExtra("pasar_usuario",dniobt);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }

}
