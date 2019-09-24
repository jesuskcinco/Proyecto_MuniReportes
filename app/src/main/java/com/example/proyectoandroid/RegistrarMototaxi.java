package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectoandroid.Entidades.Usuario;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.ArrayList;

public class RegistrarMototaxi extends AppCompatActivity {
    Bundle datos2;
    EditText placa,vehiculo,modelo,color;
    Spinner combousuario;
    ArrayList<String> listausuarios;
    ArrayList<Usuario> usuarioslist;
    ConexionSQLiteHelper con,con2;
    String validar,usupas;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mototaxi);
        con= new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        placa= (EditText) findViewById(R.id.edt_placa);
        vehiculo= (EditText) findViewById(R.id.edt_vehiculo);
        modelo= (EditText) findViewById(R.id.edt_modelo);
        color= (EditText) findViewById(R.id.edt_color);
        combousuario= (Spinner) findViewById(R.id.spn_usuario);
        datos2 = getIntent().getExtras();
        usupas=datos2.getString("pasar_usuario");
        if (usupas.equals("")){
            usupas="70546327";
        }
        //consultarusuarios();
        //ArrayAdapter<CharSequence> adaptador= new ArrayAdapter(this,android.R.layout.simple_spinner_item,listausuarios);

       //combousuario.setAdapter(adaptador);

    }
    //para llenar el spinner
    private void consultarusuarios() {

        SQLiteDatabase db2= con.getWritableDatabase();
        Usuario clase_usuario= null;
        usuarioslist= new ArrayList<Usuario>();
        //CONSULTA
        Cursor cursor=db2.rawQuery("select dni_usuario,nombre_usuario from " +Utilitario.TABLE_NAME,null);

        while (cursor.moveToNext()){
            clase_usuario=new Usuario();
            clase_usuario.setDni(cursor.getInt(0));
            clase_usuario.setNom_usuario(cursor.getString(1));

            Log.i("dni",clase_usuario.getDni().toString());
            Log.i("dni",clase_usuario.getNom_usuario());

            usuarioslist.add(clase_usuario);

        }
        llenarspinner();
        db2.close();
    }
    private void llenarspinner(){
        listausuarios =new ArrayList<String>();
        listausuarios.add("Seleccione");
        for(int i=0;i<usuarioslist.size();i++){
            listausuarios.add(usuarioslist.get(i).getDni()+" - "+usuarioslist.get(i).getNom_usuario());

        }
    }


    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btn_grabar_moto:
                registrarvehiculo();
                break;
            case R.id.btn_salir_moto:
                miIntent= new Intent(RegistrarMototaxi.this, MainActivity.class);
                miIntent.putExtra("pasar_usuario",usupas);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);

    }

    private void registrarvehiculo() {

        String var_placa =placa.getText().toString();
        String var_vehiculo = vehiculo.getText().toString();
        String var_modelo = modelo.getText().toString();
        String var_color = color.getText().toString();

        con2= new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);

        db= con2.getWritableDatabase();


        ContentValues values = new ContentValues();

        if (!var_placa.isEmpty() && !var_vehiculo.isEmpty() && !var_modelo.isEmpty() && !var_color.isEmpty()) {

            Cursor cursor2=db.rawQuery("select placa_vehiculo from VEHICULO where placa_vehiculo='"+var_placa+"'",null);

            if(cursor2.moveToFirst()){
                Toast.makeText(getApplicationContext(), "El vehiculo con placa "+var_placa+" ya ha sido registrado", Toast.LENGTH_SHORT).show();

                validar="";
                placa.setText("");
                db.close();

            }else {

                values.put(Utilitario.CAMPO_PLACA, placa.getText().toString());
                values.put(Utilitario.CAMPO_VEHICULO, vehiculo.getText().toString());
                values.put(Utilitario.CAMPO_MODELO, modelo.getText().toString());
                values.put(Utilitario.CAMPOCOLOR, color.getText().toString());


                //aqui se obtienen los datos del combo
                //int idcombo = (int) combousuario.getSelectedItemId();
                //if (idcombo != 0) {
                    //Log.i("TAMAÑO", usuarioslist.size() + "");
                    //Log.i("id combo", idcombo + "");
                    //Log.i("id combo - 1", (idcombo - 1) + "");
                    //int idusuario = usuarioslist.get(idcombo - 1).getDni();
                    //String sidusuario= String.valueOf(idusuario);
                    //Log.i("id usuario", idusuario + "");

                    //values.put(Utilitario.CAMPO_DUENIO, idusuario);
                    values.put(Utilitario.CAMPO_DUENIO, usupas);

                    Long idResultante = db.insert(Utilitario.TABLE_MOTOTAXI, Utilitario.CAMPO_PLACA, values);

                    Toast.makeText(getApplicationContext(), "Registro exitoso " + idResultante, Toast.LENGTH_LONG).show();

                    Intent ven2= new Intent(RegistrarMototaxi.this,Consultar_Mototaxis.class);
                    //ven2.putExtra("pasar_placa",var_placa);
                    ven2.putExtra("pasar_usuario",usupas);
                    startActivity(ven2);

                    //db.close();
                    //Se setean los campos
                    placa.setText("");
                    vehiculo.setText("");
                    modelo.setText("");
                    color.setText("");
                    combousuario.setSelection(0);
                    validar="";

                //} else {
                 //   Toast.makeText(getApplicationContext(), "Debe seleccionar un usuario", Toast.LENGTH_SHORT).show();
                //}

            }

        } else {
            Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
