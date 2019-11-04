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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrarAsociacion extends AppCompatActivity {
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    GlobalVariables globalVariables;
    EditText edtdesasoc,edtduenio,edtdistrito,edtzona;
    String dni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_asociacion);

        globalVariables = (GlobalVariables)getApplicationContext();
        dni=globalVariables.getDni();
        edtdesasoc=findViewById(R.id.edtdesasoc);
        edtduenio=findViewById(R.id.edtduenio);
        edtdistrito=findViewById(R.id.edtdistrito);
        edtzona=findViewById(R.id.edtzona);

    }

    public void OnClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btngraba:
                registrarasoc();
                break;
            case R.id.btncancel:
                miIntent= new Intent(RegistrarAsociacion.this, MainActivity.class);

                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }

    private void registrarasoc() {
        con= new ConexionSQLiteHelper (this,"bd_aplicativo",null, 1);

        db = con.getWritableDatabase();
        ContentValues values= new ContentValues();
        String var_desc=edtdesasoc.getText().toString();
        String var_duen=edtduenio.getText().toString();
        String var_dis=edtdistrito.getText().toString();
        String var_zon=edtzona.getText().toString();

        if(!var_desc.isEmpty() && !var_duen.isEmpty() && !var_dis.isEmpty()&& !var_zon.isEmpty()){
            Cursor cursor2=db.rawQuery("select nombresociedad from SOCIEDAD where nombresociedad='"+var_desc+"'",null);
            if(!cursor2.moveToFirst()){
                values.put(Utilitario.CAMPO_NOM_SOCIEDAD,var_desc);
                values.put(Utilitario.CAMPO_NOM_DUENIO,var_duen);
                values.put(Utilitario.CAMPO_DISTRITO,var_dis);
                values.put(Utilitario.CAMPO_ZONA,var_zon);

                values.put(Utilitario.CAMPO_DNI,dni);
                Date fechaActual = new Date();
                DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                values.put(Utilitario.CAMPO_FECHA_REG_SOC, formatoFecha.format(fechaActual));
                values.put(Utilitario.CAMPO_ESTADO_SOCIEDAD,"En espera");


                Long idResultante= db.insert(Utilitario.TABLE_SOCIEDAD,Utilitario.CAMPO_SOCIEDAD,values);
                //db.close();
                Toast.makeText(getApplicationContext(),"Registro exitoso "+idResultante,Toast.LENGTH_LONG).show();
                Intent ven2= new Intent(RegistrarAsociacion.this,RegistrarAsociacion.class);
                //ven2.putExtra("pasar_placa",var_placa);

                startActivity(ven2);

            }else {
                Toast.makeText(getApplicationContext(), "La sociedad con descripcion "+var_desc+" ya ha sido registrado", Toast.LENGTH_SHORT).show();

                db.close();
            }
        }else{
            Toast.makeText(this,"Debe completar los campos",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
