package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarUsuario extends AppCompatActivity {

    EditText ls_dni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario);
        ls_dni= (EditText)findViewById(R.id.et_dni);

    }
    public void onClick(View view){
        eliminarusuario();

    }
    public void eliminarusuario(){
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        String dni= ls_dni.getText().toString();

        if (!dni.isEmpty()){
            int cantidad= db.delete("USUARIO","dni_usuario="+dni,null);
            db.close();
            ls_dni.setText("");

            if (cantidad==1){
                Toast.makeText(this,"El usuario ha sido eliminado",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"El usuario no existe",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Se debe ingresar un DNI",Toast.LENGTH_LONG).show();

        }

    }

}
