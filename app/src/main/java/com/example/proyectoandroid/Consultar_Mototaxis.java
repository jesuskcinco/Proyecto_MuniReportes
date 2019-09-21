package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Consultar_Mototaxis extends AppCompatActivity {
    Bundle datos2;
    TextView getplaca,getusuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar__mototaxis);
        datos2 = getIntent().getExtras();
        String placapas= datos2.getString("pasar_placa");
        String usupas=datos2.getString("pasar_usuario");
        getplaca= (TextView) findViewById(R.id.placapasada);
        getusuario= (TextView) findViewById(R.id.usuariopasado);
        getplaca.setText(placapas);
        getusuario.setText(usupas);
    }
}
