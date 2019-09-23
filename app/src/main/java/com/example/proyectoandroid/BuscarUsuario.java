package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuscarUsuario extends AppCompatActivity {

    EditText ls_dni2;
    TextView ls_nombres2,ls_apellidos2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuario);

        ls_dni2= (EditText) findViewById(R.id.txt_dni2);
        ls_nombres2= (TextView) findViewById(R.id.txt_nombres2);
        ls_apellidos2= (TextView) findViewById(R.id.txt_apellidos2);
    }

    public void onClick(View view) {

        consultarusuario();
    }

    public void consultarusuario(){

        ConexionSQLiteHelper conn= new ConexionSQLiteHelper (this,"bd_aplicativo",null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

         String var_dni= ls_dni2.getText().toString();

        if(!var_dni.isEmpty()){
            Cursor fila = db.rawQuery
                    ("select nombres_usuario,apellidos_usuario from USUARIO where dni_usuario ='" + var_dni+"'", null);

            if(fila.moveToFirst()){
                ls_nombres2.setText(fila.getString(0));
                ls_apellidos2.setText(fila.getString(1));
                db.close();

            } else {
                Toast.makeText(this,"El usuario no se encuentra registrado", Toast.LENGTH_SHORT).show();
                db.close();
            }

        } else {
            Toast.makeText(this, "Se debe introducir el dni a buscar", Toast.LENGTH_SHORT).show();

        }
    }
}
