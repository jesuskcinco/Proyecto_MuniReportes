package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleIncidente extends AppCompatActivity {
    Bundle datos;
    String cor_reportedet;
    TextView codrep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_incidente);
        datos = getIntent().getExtras();
        //cor_reportedet=datos.getString("pasar_codigo");
        Toast.makeText(getApplicationContext(), "Se obtiene el reporte " + cor_reportedet, Toast.LENGTH_LONG).show();
    }
}
