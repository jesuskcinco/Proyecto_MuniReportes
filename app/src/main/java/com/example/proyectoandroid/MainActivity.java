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
}
