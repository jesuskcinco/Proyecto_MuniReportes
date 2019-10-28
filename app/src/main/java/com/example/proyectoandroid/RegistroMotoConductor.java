package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.Entidades.Vehiculo;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegistroMotoConductor extends AppCompatActivity {

    ArrayList<String> listamotos;
    GlobalVariables globalVariables;
    ArrayList<Vehiculo> listaclasemotos;
    String dniobt;
    Spinner spnplacas;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    TextView tv_dni,tv_observa;
    Button btngrabar;
    int idcombo;
    String idtipovehiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_moto_conductor);
        spnplacas =(Spinner) findViewById(R.id.spnmotos);
        tv_dni=(TextView) findViewById(R.id.edtconductor);
        tv_observa=(TextView) findViewById(R.id.edtobservacion);
        btngrabar=findViewById(R.id.btngrabarregistro);
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        globalVariables = (GlobalVariables)getApplicationContext();

        dniobt=globalVariables.getDni();

        if(!dniobt.isEmpty()){
            tv_dni.setText(dniobt);
        }else {
            tv_dni.setText("No se ha logueado");
            btngrabar.setEnabled(false);
        }
        listarplacas();
        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listamotos);
        spnplacas.setAdapter(adaptador2);

    }
    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btngrabarregistro:
                registrarmotoconduc();
                break;
            case R.id.btncancela:
                miIntent= new Intent(RegistroMotoConductor.this, MainActivity.class);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }

    private void registrarmotoconduc() {
        db= con.getWritableDatabase();
        String dni = tv_dni.getText().toString();
        String obser = tv_observa.getText().toString();
        idcombo = (int) spnplacas.getSelectedItemId();
        ContentValues values = new ContentValues();
        if (idcombo != 0) {
            values.put(Utilitario.CAMPO_DNI, dni);

            //valor de combo tipo incidente
            Log.i("id", listaclasemotos.size() + "");
            Log.i("desc", idcombo + "");
            Log.i("desc - 1", (idcombo - 1) + "");
            idtipovehiculo = listaclasemotos.get(idcombo - 1).getPlaca();
            String placa =idtipovehiculo;
            Log.i("idcombo-1", idtipovehiculo + "");

            values.put(Utilitario.CAMPO_PLACA, idtipovehiculo);
            values.put(Utilitario.CAMPO_OBSERVACION, obser);
            Date fechaActual = new Date();
            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            values.put(Utilitario.CAMPO_FECHA_USU_MOTO, formatoFecha.format(fechaActual));
            values.put(Utilitario.CAMPO_OK_DUENIO, "0");
            values.put(Utilitario.CAMPO_ESTADO_MOTO_COND, "Inactivo");

            Long idResultante= db.insert(Utilitario.TABLE_USU_MOTO,Utilitario.CAMPO_ID_USU_MOTO,values);


            ContentValues values2 = new ContentValues();
            values2.put(Utilitario.CAMPO_MENSAJE, "El usuario con DNI "+ dni+" solicita manejar su moto con placa "+ idtipovehiculo+".");
            Date fechaActual2 = new Date();
            DateFormat formatoFecha2 = new SimpleDateFormat("dd/MM/yyyy");
            values2.put(Utilitario.CAMPO_FECHA, formatoFecha2.format(fechaActual2));
            values2.put(Utilitario.CAMPO_DNI, dni);
            values2.put(Utilitario.CAMPO_ELIMINAR, 0);
            values2.put(Utilitario.CAMPO_PLACA, idtipovehiculo);
            Long idResultante2= db.insert(Utilitario.TABLE_MENSAJES,Utilitario.CAMPO_ID_MENSAJE,values2);

            Toast.makeText(getApplicationContext(), "Se grabo el registro y se envio un mensaje al dueño del vehiculo " + idResultante, Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), "Registro exitoso " + idResultante, Toast.LENGTH_LONG).show();
            Intent ven2= new Intent(RegistroMotoConductor.this,RegistroMotoConductor.class);

            startActivity(ven2);
        }else{
            Toast.makeText(getApplicationContext(), "No se ha seleccionado ninguna placa ", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    private void listarplacas() {
        db= con.getWritableDatabase();
        Vehiculo clasetiposmoto= null;
        listaclasemotos= new ArrayList<Vehiculo>();
        //CONSULTA
        Cursor cursor=db.rawQuery("select placa_vehiculo from VEHICULO where placa_vehiculo not in (select placa_vehiculo from CONDUCTORMOTO where dni_usuario='"
                +dniobt+"' and estado_moto_cond not in('ACTIVO','INACTIVO'))",null);

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
            listamotos.add("Seleccionó todas las placas");
        }else{
            listamotos.add("Seleccione");
        }

        for(int i=0;i<listaclasemotos.size();i++){
            listamotos.add(listaclasemotos.get(i).getPlaca());

        }
    }

}
