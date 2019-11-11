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
        //datos = getIntent().getExtras();
        getdni= (TextView) findViewById(R.id.dnipasado);

        //dniobt= datos.getString("pasar_usuario");
        //String claveobt= datos.getString("pasar_clave");
        globalVariables = (GlobalVariables)getApplicationContext();
        if (!globalVariables.getDni().isEmpty()){
            dniobt=globalVariables.getDni();

        }else {
            dniobt="70546327";
            globalVariables.setDni(dniobt);
        }




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
            case R.id.btnmotos:
                miIntent= new Intent(MainActivity.this, ConsultarTodoMototaxis.class);

                //miIntent.putExtra("pasar_usuario",dniobt);
                break;
            case R.id.btnregistroconducmoto:
                miIntent= new Intent(MainActivity.this, RegistroMotoConductor.class);

                //miIntent.putExtra("pasar_usuario",dniobt);
                break;
            case R.id.btnregsociedad:
                miIntent= new Intent(MainActivity.this, RegistrarAsociacion.class);

                //miIntent.putExtra("pasar_usuario",dniobt);
                break;
            case R.id.btnbandeja:
                miIntent= new Intent(MainActivity.this, BandejaMensajes.class);

                //miIntent.putExtra("pasar_usuario",dniobt);
                break;
            case R.id.btneditarperfil:
                miIntent= new Intent(MainActivity.this, ActualizarUsuario.class);

                //miIntent.putExtra("pasar_usuario",dniobt);
                break;
            case R.id.btnmotosociedad:
                miIntent= new Intent(MainActivity.this, RegistrarMotoSociedad.class);

                //miIntent.putExtra("pasar_usuario",dniobt);
                break;
            case R.id.btnreporte1:
                miIntent= new Intent(MainActivity.this, ReporteVehiculos.class);


                break;
            case R.id.btnreporte2:
                miIntent= new Intent(MainActivity.this, ReporteIncidentesPlaca.class);

                ;
        }
        if (miIntent!=null) startActivity(miIntent);
    }

}
