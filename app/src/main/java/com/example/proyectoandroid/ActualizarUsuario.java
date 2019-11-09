package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoandroid.Utilidades.Utilitario;

public class ActualizarUsuario extends AppCompatActivity {

    EditText ls_dni,ls_nombre,ls_apellido,ls_clave,ls_usuario,ls_correo,ls_celu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_usuario);
        ls_usuario= (EditText) findViewById(R.id.txt_usuario3);
        ls_clave= (EditText) findViewById(R.id.txt_clave3);
        ls_correo=(EditText) findViewById(R.id.txt_correo3);
        ls_nombre= (EditText) findViewById(R.id.txt_nombres3);
        ls_apellido= (EditText) findViewById(R.id.txt_apellidos3);
        ls_dni= (EditText) findViewById(R.id.txt_dni3);
        ls_celu= (EditText) findViewById(R.id.txt_cel);
    }

    public void onClick5 (View view){
        actualizarusuario();

    }
    private void actualizarusuario(){
    ConexionSQLiteHelper cn= new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
    SQLiteDatabase db= cn.getWritableDatabase();

        String var_usuario3=ls_usuario.getText().toString();
        String var_clave3=ls_clave.getText().toString();
        String var_correo3=ls_correo.getText().toString();
        String var_nombre3=ls_nombre.getText().toString();
        String var_apellido3=ls_apellido.getText().toString();
        String var_dni3=ls_dni.getText().toString();
        String var_celu3=ls_celu.getText().toString();

    if (!var_dni3.isEmpty() && !var_nombre3.isEmpty() && !var_apellido3.isEmpty() && !var_usuario3.isEmpty() &&
        !var_clave3.isEmpty()&& !var_correo3.isEmpty()){
            ContentValues valores= new ContentValues();
            valores.put(Utilitario.CAMPO_DNI,var_dni3);
            valores.put(Utilitario.CAMPO_NOMBRES,var_nombre3);
            valores.put(Utilitario.CAMPO_APELLIDOS,var_apellido3);
            valores.put(Utilitario.CAMPO_USUARIO,var_usuario3);
            valores.put(Utilitario.CAMPO_CLAVE,var_clave3);
            valores.put(Utilitario.CAMPO_CORREO,var_correo3);
            valores.put(Utilitario.CAMPO_CELULAR,var_celu3);

            int resultado= db.update("USUARIO",valores,"dni_usuario="+var_dni3,null);
            if (resultado==1){
                Toast.makeText(this,"La cuenta se actualizo correctamente",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this,"El usuario no existe",Toast.LENGTH_SHORT).show();
            }
    }else {
        Toast.makeText(this,"No se deben ingresar campos vacios",Toast.LENGTH_SHORT).show();
    }
    }
}
