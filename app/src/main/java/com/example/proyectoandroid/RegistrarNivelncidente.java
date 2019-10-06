package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.ArrayList;

public class RegistrarNivelncidente extends AppCompatActivity {

    Spinner combonivel;
    EditText descripcion;
    ArrayList<String> listaniveles;
    ConexionSQLiteHelper con;
    String validar,usupas;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_nivelncidente);
        descripcion= findViewById(R.id.edt_desnivinc);

    }

    public void OnClick(View view) {
        String descnivinc=descripcion.getText().toString();
        con= new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        db= con.getWritableDatabase();

        if (!descnivinc.isEmpty()){
            Cursor cursor2=db.rawQuery("select desc_niv_inc from NIVELINCIDENTE where desc_niv_inc='"+descnivinc+"'",null);
            ContentValues values = new ContentValues();
            if(!cursor2.moveToFirst()) {
                values.put(Utilitario.CAMPO_DESC_NIVEL_INC, descnivinc);
                Long idResultante = db.insert(Utilitario.TABLE_NIVEL_INCIDENTE, Utilitario.CAMPO_ID_NIV_INC, values);
                Toast.makeText(getApplicationContext(), "Registro exitoso " + idResultante, Toast.LENGTH_LONG).show();
                descripcion.setText("");
            }else{
                Toast.makeText(this,"El nivel de incidente ya ha sido registrado",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Debe ingresar la descripión",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
