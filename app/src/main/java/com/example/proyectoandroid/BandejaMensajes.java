package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.proyectoandroid.Adaptadores.AdaptadorMensajes;
import com.example.proyectoandroid.Entidades.Mensajes;
import com.example.proyectoandroid.Utilidades.GlobalVariables;

import java.util.ArrayList;

public class BandejaMensajes extends AppCompatActivity {
    RecyclerView mRecyclerview3;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    RecyclerView.LayoutManager mlayoutmanager;
    ArrayList<String> listamensajes;
    ArrayList<Mensajes> listaclasemensajes;
    GlobalVariables globalVariables;
    String dniobt,variab="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandeja_mensajes);
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        globalVariables = (GlobalVariables)getApplicationContext();
        dniobt=globalVariables.getDni();
        mRecyclerview3= findViewById(R.id.idrecyclermensajes);
        mlayoutmanager= new LinearLayoutManager(this);
        mRecyclerview3.setLayoutManager(mlayoutmanager);
        consultarlistabandeja();
        AdaptadorMensajes adaptadorprinc=new AdaptadorMensajes(listaclasemensajes);
        adaptadorprinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //codreporte=codrep.toString();
                Toast.makeText(getApplicationContext(), "Se obtiene el reporte" +listaclasemensajes.get(mRecyclerview3.
                        getChildAdapterPosition(v)).getCod_mensaje(),Toast.LENGTH_SHORT).show();
                Intent detalle= new Intent(BandejaMensajes.this,AceptarConductor.class);
                variab=listaclasemensajes.get(mRecyclerview3.getChildAdapterPosition(v)).getCod_mensaje().toString();
                detalle.putExtra("pasar_codigo",variab);
                startActivity(detalle);
            }
        });
        mRecyclerview3.setAdapter(adaptadorprinc);
    }

    private void consultarlistabandeja() {
        db= con.getWritableDatabase();
        Mensajes clasemensajes= null;
        listaclasemensajes= new ArrayList<Mensajes>();
        Cursor cursor= db.rawQuery("select placa_vehiculo,mensaje_not,cod_mensaje from MENSAJES where placa_vehiculo in (select placa_vehiculo from VEHICULO where dni_usuario='"+dniobt+"')",null);
        //Cursor cursor= db.rawQuery("select placa_vehiculo,mensaje_not from MENSAJES",null);
        while (cursor.moveToNext()){
            clasemensajes= new Mensajes();
            clasemensajes.setPlaca(cursor.getString(0));
            clasemensajes.setMensaje_not(cursor.getString(1));
            clasemensajes.setCod_mensaje(cursor.getInt(2));
            listaclasemensajes.add(clasemensajes);
        }
        //obtenerinformacion();
        db.close();
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){

            case R.id.button27:
                miIntent= new Intent(BandejaMensajes.this, MainActivity.class);

                break;

        }
        if (miIntent!=null) startActivity(miIntent);
    }
}
