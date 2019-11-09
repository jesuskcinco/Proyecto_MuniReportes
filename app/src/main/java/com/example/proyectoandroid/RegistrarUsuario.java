package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.regex.Pattern;

public class RegistrarUsuario extends AppCompatActivity {
    GlobalVariables globalVariables;
    EditText ls_dni,ls_nombre,ls_apellido,ls_clave,ls_usuario,ls_correo,ls_celu;
    String validar;
    SQLiteDatabase db;
    String dni;
    String pater="^(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z]).{8,}$";
    //private static final Pattern PASSWORD_PATTERN=Pattern.compile(  "^"+"(?=.*[0-9])"+
    //                                                                //"(?=.*[a-z])"+
    //                                                                "(?=.*[A-Z])"+"$");
                                                                    //".{6,}"); si se requiere que sea con cierta cantidad de digitos

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
        ls_celu=(EditText) findViewById(R.id.txt_celular);
        globalVariables = (GlobalVariables)getApplicationContext();

    }
    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btn_grabar:
                registrarUsuario();
                break;
            case R.id.btn_salir:
                miIntent= new Intent(RegistrarUsuario.this, IniciarSesion.class);

                break;
        }
        if (miIntent!=null) startActivity(miIntent);

    }
    private void registrarUsuario() {
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper (this,"bd_aplicativo",null, 1);

         db = conn.getWritableDatabase();
        ContentValues values= new ContentValues();

        String var_usuario=ls_usuario.getText().toString();
        String var_clave=ls_clave.getText().toString();
        String var_correo=ls_correo.getText().toString();
        String var_nombre=ls_nombre.getText().toString();
        String var_apellido=ls_apellido.getText().toString();
        String var_dni=ls_dni.getText().toString();
        String var_celu=ls_celu.getText().toString();


        if(!var_usuario.isEmpty()&& !var_clave.isEmpty() && !var_correo.isEmpty() && !var_nombre.isEmpty() && !var_apellido.isEmpty()
                && !var_dni.isEmpty()&& !var_celu.isEmpty()){

            if(var_usuario.equals(var_clave)){

                if(var_usuario.length()>7){
                    //if(!PASSWORD_PATTERN.matcher(var_usuario).matches()){
                    if(!var_usuario.matches(pater)){
                        Toast.makeText(this,"La clave debe contener números,mayúsculas y minúsculas",Toast.LENGTH_SHORT).show();
                    }else{
                        Cursor cursor2=db.rawQuery("select dni_usuario from USUARIO where dni_usuario='"+var_dni+"'",null);

                        if(cursor2.moveToFirst()){
                            Toast.makeText(getApplicationContext(), "El usuario con DNI "+var_dni+" ya ha sido registrado", Toast.LENGTH_SHORT).show();
                            validar="";
                            ls_dni.setText("");
                            db.close();

                        }else {
                            values.put(Utilitario.CAMPO_USUARIO,ls_usuario.getText().toString());
                            values.put(Utilitario.CAMPO_CLAVE,ls_clave.getText().toString());
                            values.put(Utilitario.CAMPO_CORREO,ls_correo.getText().toString());
                            values.put(Utilitario.CAMPO_NOMBRES,ls_nombre.getText().toString());
                            values.put(Utilitario.CAMPO_APELLIDOS,ls_apellido.getText().toString());
                            values.put(Utilitario.CAMPO_DNI,ls_dni.getText().toString());
                            values.put(Utilitario.CAMPO_CELULAR,ls_celu.getText().toString());
                            values.put(Utilitario.CAMPO_FLGUSUARIO,"1");
                            Long idResultante= db.insert(Utilitario.TABLE_NAME,Utilitario.CAMPO_DNI,values);
                            //db.close();
                            Toast.makeText(getApplicationContext(),"Registro exitoso"+idResultante,Toast.LENGTH_LONG).show();
                            Intent ven2= new Intent(RegistrarUsuario.this,IniciarSesion.class);
                            //ven2.putExtra("pasar_placa",var_placa);

                            startActivity(ven2);

                            ls_usuario.setText("");
                            ls_clave.setText("");
                            ls_correo.setText("");
                            ls_nombre.setText("");
                            ls_apellido.setText("");
                            ls_dni.setText("");
                            ls_celu.setText("");
                        }
                    }

                }else{
                    Toast.makeText(this,"La clave debe ser mayor a 8 digitos",Toast.LENGTH_SHORT).show();
                }


            }else{
                Toast.makeText(this,"Las claves no coinciden",Toast.LENGTH_SHORT).show();
            }

        }else{
                Toast.makeText(this,"Debe completar los campos",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
