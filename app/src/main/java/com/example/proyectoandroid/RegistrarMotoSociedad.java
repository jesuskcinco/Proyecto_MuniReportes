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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectoandroid.Entidades.RegistroaSociedad;
import com.example.proyectoandroid.Entidades.Sociedad;
import com.example.proyectoandroid.Entidades.Vehiculo;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegistrarMotoSociedad extends AppCompatActivity {
    ConexionSQLiteHelper con;
    SQLiteDatabase db;
    ArrayList<String> listamotos;
    ArrayList<Vehiculo> listaclasemotos;
    ArrayList<String> listasociedades;
    ArrayList<Sociedad> listaclasesociedad;
    GlobalVariables globalVariables;
    ListView listviewmotosoc;
    ArrayList<String> listainformacion;
    ArrayList<RegistroaSociedad> listaclasesociedad2;
    String dni;
    Spinner spnsociedad,spnplacas;
    int idcombo1,idcombo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_moto_sociedad);
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        globalVariables = (GlobalVariables)getApplicationContext();

        dni=globalVariables.getDni();
        spnsociedad =(Spinner) findViewById(R.id.spnsociedades);
        spnplacas =(Spinner) findViewById(R.id.spnmisplacas);
        listarplacas();
        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listamotos);
        spnplacas.setAdapter(adaptador2);
        listarsociedes();
        ArrayAdapter<CharSequence> adaptador3=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listasociedades);
        spnsociedad.setAdapter(adaptador3);


//LLENAMOS EL LISTVIEW
        listviewmotosoc= (ListView) findViewById(R.id.listviewmotos);
        validarmotosociedad();


    }

    private void validarmotosociedad() {
        db= con.getWritableDatabase();

        Cursor cursor= db.rawQuery("select count(placa_vehiculo) from USUARIOSOCIEDAD where dni_usuario='"+dni+"' and estado_sociedad='Aceptado'",null);
        while (cursor.moveToNext()){
            Integer coun= cursor.getInt(0);
            if(coun.equals(1)){
                consultarlistavehiculos();
                final ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
                listviewmotosoc.setAdapter(adaptador);
            }else{
                Toast.makeText(RegistrarMotoSociedad.this,"No tiene ninguna moto asignada a alguna sociedad",Toast.LENGTH_SHORT).show();
            }
        }


        db.close();
    }

    private void consultarlistavehiculos() {
        db= con.getWritableDatabase();
        RegistroaSociedad registro= null;
        listaclasesociedad2 =new ArrayList<RegistroaSociedad>();
        Cursor cursor= db.rawQuery("select placa_vehiculo,s.nombresociedad from USUARIOSOCIEDAD a inner join SOCIEDAD s on a.idsociedad=s.idsociedad" +
                " where a.dni_usuario='"+dni+"' and a.estado_sociedad='Aceptado'",null);

        while (cursor.moveToNext()){
            registro= new RegistroaSociedad();
            registro.setPlaca_sociedad(cursor.getString(0));
            registro.setDescsociedad(cursor.getString(1));


            listaclasesociedad2.add(registro);
        }
        obtenerinformacion();
        db.close();
    }

    private void obtenerinformacion() {
        listainformacion= new ArrayList<String>();

        for(int i=0;i<listaclasesociedad2.size();i++){
                listainformacion.add(listaclasesociedad2.get(i).getPlaca_sociedad()+" - "+listaclasesociedad2.get(i).getDescsociedad());

        }

    }

    private void listarplacas() {
        db= con.getWritableDatabase();
        Vehiculo clasetiposmoto= null;
        listaclasemotos= new ArrayList<Vehiculo>();
        //CONSULTA
        Cursor cursor=db.rawQuery("select placa_vehiculo from vehiculo where idsociedad is null and dni_usuario='"+dni+"' and placa_vehiculo not in " +
                "(select placa_vehiculo from USUARIOSOCIEDAD where estado_sociedad in ('Aceptado','Pendiente'))",null);

        while (cursor.moveToNext()){
            clasetiposmoto=new Vehiculo();
            clasetiposmoto.setPlaca(cursor.getString(0));

            Log.i("id",clasetiposmoto.getPlaca().toString());
            Log.i("desc",clasetiposmoto.getPlaca());

            listaclasemotos.add(clasetiposmoto);

        }
        llenarspinner();
        db.close();
    }

    private void llenarspinner() {
        listamotos =new ArrayList<String>();
        if(listaclasemotos.size()==0){
            listamotos.add("No tiene vehiculos disponibles");
        }else{
            listamotos.add("Seleccione");
        }

        for(int i=0;i<listaclasemotos.size();i++){
            listamotos.add(listaclasemotos.get(i).getPlaca());

        }
    }

    private void  listarsociedes() {
        db= con.getWritableDatabase();
        Sociedad clasesociedad= null;
        listaclasesociedad= new ArrayList<Sociedad>();
        //CONSULTA
        Cursor cursor=db.rawQuery("select idsociedad, nombresociedad from SOCIEDAD",null);

        while (cursor.moveToNext()){
            clasesociedad=new Sociedad();
            clasesociedad.setIdsociedad(cursor.getInt(0));
            clasesociedad.setNombresociedad(cursor.getString(1));

            Log.i("id",clasesociedad.getIdsociedad().toString());
            Log.i("desc",clasesociedad.getNombresociedad());

            listaclasesociedad.add(clasesociedad);

        }
        llenarspinner2();
        db.close();
    }

    private void llenarspinner2() {
        listasociedades =new ArrayList<String>();
        listasociedades.add("Seleccione");


        for(int i=0;i<listaclasesociedad.size();i++){
            listasociedades.add(listaclasesociedad.get(i).getNombresociedad());

        }
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.button24:
                registrarmotoasociedad();
                break;
            case R.id.button26:
                miIntent= new Intent(RegistrarMotoSociedad.this, MainActivity.class);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }

    private void registrarmotoasociedad() {
        db= con.getWritableDatabase();
        idcombo1 = (int) spnplacas.getSelectedItemId();
        idcombo2 = (int) spnsociedad.getSelectedItemId();
        ContentValues values = new ContentValues();
        if (idcombo1 != 0 && idcombo2!=0 ) {
            values.put(Utilitario.CAMPO_DNI, dni);

            String idplacavehiculo = listaclasemotos.get(idcombo1 - 1).getPlaca();
            values.put(Utilitario.CAMPO_PLACA, idplacavehiculo);

            Integer idsociedad = listaclasesociedad.get(idcombo2- 1).getIdsociedad();
            values.put(Utilitario.CAMPO_IDSOCIEDAD, idsociedad);

            Date fechaActual = new Date();
            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            values.put(Utilitario.CAMPO_FECHA_REG_SO, formatoFecha.format(fechaActual));
            values.put(Utilitario.CAMPO_ESTADO_SOCIEDAD, "Pendiente");

            Long idResultante= db.insert(Utilitario.TABLE_USU_SOCIEDAD,Utilitario.CAMPO_ID_USU_SOC,values);

            ContentValues values2 = new ContentValues();
            values2.put(Utilitario.CAMPO_MENSAJE, "El usuario con DNI "+ dni+" solicita unir su vehiculo "+idplacavehiculo+" a su sociedad.");
            values2.put(Utilitario.CAMPO_FECHA, formatoFecha.format(fechaActual));
            values2.put(Utilitario.CAMPO_DNI, dni);
            values2.put(Utilitario.CAMPO_ELIMINAR, 0);
            values2.put(Utilitario.CAMPO_TIPO_MENSAJE, "Moto-Sociedad");
            values2.put(Utilitario.CAMPO_PLACA, idplacavehiculo);
            Long idResultante2= db.insert(Utilitario.TABLE_MENSAJES,Utilitario.CAMPO_ID_MENSAJE,values2);

            Toast.makeText(getApplicationContext(), "Se grabó el registro y se envió un mensaje al representante de la sociedad " + idResultante, Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), "Registro exitoso " + idResultante, Toast.LENGTH_LONG).show();
            db.close();
            Intent ven2= new Intent(RegistrarMotoSociedad.this,RegistrarMotoSociedad.class);
            startActivity(ven2);
        }else{
            Toast.makeText(RegistrarMotoSociedad.this,"Debe seleccionar la sociedad y mototaxi",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
