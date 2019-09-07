package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoandroid.Utilidades.Utilitario;

public class RegistrarUsuario extends AppCompatActivity {

    EditText ls_dni,ls_nombre,ls_apellido,ls_clave,ls_usuario,ls_correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        ls_usuario= (EditText) findViewById(R.id.txt_usuario);
        ls_clave= (EditText) findViewById(R.id.txt_clave);
        ls_correo=(EditText) findViewById(R.id.txt_correo);
        ls_nombre= (EditText) findViewById(R.id.txt_nombres);
        ls_apellido= (EditText) findViewById(R.id.txt_apellidos);
        ls_dni= (EditText) findViewById(R.id.txt_dni);

    }
    public void onClick(View view) {

        registrarUsuario();
    }
    private void registrarUsuario() {
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper (this,"bd_aplicativo",null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values= new ContentValues();

        String var_usuario=ls_usuario.getText().toString();
        String var_clave=ls_clave.getText().toString();
        String var_correo=ls_correo.getText().toString();
        String var_nombre=ls_nombre.getText().toString();
        String var_apellido=ls_apellido.getText().toString();
        String var_dni=ls_dni.getText().toString();

        if(!var_usuario.isEmpty()&& !var_clave.isEmpty() && !var_correo.isEmpty() && !var_nombre.isEmpty() && !var_apellido.isEmpty()
                && !var_dni.isEmpty()){

        values.put(Utilitario.CAMPO_USUARIO,ls_usuario.getText().toString());
        values.put(Utilitario.CAMPO_CLAVE,ls_clave.getText().toString());
        values.put(Utilitario.CAMPO_CORREO,ls_correo.getText().toString());
        values.put(Utilitario.CAMPO_NOMBRES,ls_nombre.getText().toString());
        values.put(Utilitario.CAMPO_APELLIDOS,ls_apellido.getText().toString());
        values.put(Utilitario.CAMPO_DNI,ls_dni.getText().toString());

            Long idResultante= db.insert(Utilitario.TABLE_NAME,Utilitario.CAMPO_DNI,values);

            Toast.makeText(getApplicationContext(),"Registro exitoso"+idResultante,Toast.LENGTH_LONG).show();
            db.close();

            ls_usuario.setText("");
            ls_clave.setText("");
            ls_correo.setText("");
            ls_nombre.setText("");
            ls_apellido.setText("");
            ls_dni.setText("");

        }else{
            Toast.makeText(this,"Debe completar los campos",Toast.LENGTH_SHORT).show();
        }

    }
}
