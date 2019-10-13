package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.Entidades.Vehiculo;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.ArrayList;

public class Consultar_Mototaxis extends AppCompatActivity {
    Bundle datos2;
    TextView getplaca,getusuario,tvplaca;
    ConexionSQLiteHelper con;
    ListView listviewvehiculos;
    ArrayList<String> listainformacion;
    ArrayList<Vehiculo> listarvehiculos;
    String placapas,usupas;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar__mototaxis);

        //Se programa la recepcion de variables de la actividad anterior
        datos2 = getIntent().getExtras();
        usupas=datos2.getString("pasar_usuario");
        if (usupas.equals("")){
            usupas="70546327";
        }
        tvplaca= (TextView) findViewById(R.id.etplaca);
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        listviewvehiculos= (ListView) findViewById(R.id.listviewmotos);
        consultarlistavehiculos();
        final ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        listviewvehiculos.setAdapter(adaptador);
        listviewvehiculos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                db=con.getWritableDatabase();
                String query="delete from VEHICULO where placa_vehiculo='"+listarvehiculos.get(i).getPlaca()+"'";
                db.execSQL(query);
                db.close();
                Intent recarga= new Intent(Consultar_Mototaxis.this,Consultar_Mototaxis.class);
                recarga.putExtra("pasar_usuario",usupas);
                startActivity(recarga);
                Toast.makeText(getApplicationContext(),"Se elimino",Toast.LENGTH_LONG).show();
                finish();

                return false;
            }});

        tvplaca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                    adaptador.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        listviewvehiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent actmoto= new Intent(Consultar_Mototaxis.this,ActualizarMototaxi.class);
                actmoto.putExtra("pasar_usuario",usupas);
                actmoto.putExtra("pasar_placa",listarvehiculos.get(i).getPlaca());
                startActivity(actmoto);
            }
        });

    }

    private void consultarlistavehiculos() {
        db= con.getWritableDatabase();
        Vehiculo vehiculo= null;
        listarvehiculos =new ArrayList<Vehiculo>();
        Cursor cursor= db.rawQuery("select * from "+ Utilitario.TABLE_MOTOTAXI+" where dni_usuario='"+usupas+"'",null);

        while (cursor.moveToNext()){
            vehiculo= new Vehiculo();
            vehiculo.setPlaca(cursor.getString(0));
            vehiculo.setVehiculo(cursor.getString(1));
            vehiculo.setModelo(cursor.getString(2));
            vehiculo.setColor(cursor.getString(3));

            listarvehiculos.add(vehiculo);
        }
        obtenerinformacion();
        db.close();
    }
    private void obtenerinformacion(){
        listainformacion= new ArrayList<String>();
        for(int i=0;i<listarvehiculos.size();i++){
            listainformacion.add(listarvehiculos.get(i).getPlaca()+" - "+listarvehiculos.get(i).getModelo());
        }
    }

    public void onClick(View view) {
        Intent nuevamoto= new Intent(Consultar_Mototaxis.this,RegistrarMototaxi.class);
        nuevamoto.putExtra("pasar_usuario",usupas);
        startActivity(nuevamoto);
    }
}
