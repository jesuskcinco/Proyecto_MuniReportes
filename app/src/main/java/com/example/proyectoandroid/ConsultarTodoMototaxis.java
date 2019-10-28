package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectoandroid.Adaptadores.AdaptadorMotos;
import com.example.proyectoandroid.Adaptadores.AdaptadorReportes;
import com.example.proyectoandroid.Entidades.ReporteIncidente;
import com.example.proyectoandroid.Entidades.TipoIncidente;
import com.example.proyectoandroid.Entidades.Vehiculo;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.ArrayList;

public class ConsultarTodoMototaxis extends AppCompatActivity {
    RecyclerView mRecyclerview2;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    RecyclerView.LayoutManager mlayoutmanager;
    Spinner spntipos2;
    ArrayList<String> listamotos;
    ArrayList<String> listamotos2;
    ArrayList<Vehiculo> listaclasemotos;
    ArrayList<Vehiculo> listaclasemotos2;
    int idcombo;
    String idtipovehiculo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_todo_mototaxis);

        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        mRecyclerview2= findViewById(R.id.idrecyclermotos);
        //mRecyclerview.setHasFixedSize(true);
        mlayoutmanager= new LinearLayoutManager(this);
        mRecyclerview2.setLayoutManager(mlayoutmanager);
        spntipos2 =(Spinner) findViewById(R.id.spntiposmotos);
        consultarmodelosmoto();
        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listamotos2);
        spntipos2.setAdapter(adaptador2);
        consultarlistamotos();
        AdaptadorMotos adaptadorprinc=new AdaptadorMotos(listaclasemotos);
        mRecyclerview2.setAdapter(adaptadorprinc);

        spntipos2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                idcombo = (int) spntipos2.getSelectedItemId();
                if (idcombo != 0) {
                    //valor de combo tipo incidente
                    Log.i("id", listaclasemotos2.size() + "");
                    Log.i("desc", idcombo + "");
                    Log.i("desc - 1", (idcombo - 1) + "");
                    idtipovehiculo = listaclasemotos2.get(idcombo - 1).getVehiculo();
                    Log.i("idcombo-1", idtipovehiculo + "");
                    motosxfiltro();
                    //Toast.makeText(getApplicationContext(), "Se grabo la cabecera del reporte " + idtipoincidente, Toast.LENGTH_LONG).show();
                }else{
                    motosxfiltro();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    private void motosxfiltro() {
        switch (idcombo){

            case 0:
                consultarlistamotos();
                AdaptadorMotos adaptadora=new AdaptadorMotos(listaclasemotos);

                //ESTE CODIGO SE USA CUANDO SE QUIERE INGRESAR AL EVENTO ON CLICKLISTENER
                //adaptadora.setOnClickListener(new View.OnClickListener() {
                 //   @Override
                 //   public void onClick(View v) {
                //        //codreporte=codrep.toString();
                 //       Toast.makeText(getApplicationContext(), "Se obtiene el reporte" +listarreportesinc.get(mRecyclerview.
                //                getChildAdapterPosition(v)).getCodreporte(),Toast.LENGTH_SHORT).show();
                 //       Intent detalle= new Intent(PanelIncidentes.this,DetalleIncidente.class);
                 //       variab=listarreportesinc.get(mRecyclerview.getChildAdapterPosition(v)).getCodreporte().toString();
                  //      detalle.putExtra("pasar_codigo",variab);
                  //      startActivity(detalle);
                 //   }
                //});
                mRecyclerview2.setAdapter(adaptadora);
                break;
            default:
                consultarlistamotosxfiltro();
                AdaptadorMotos adaptadorb=new AdaptadorMotos(listaclasemotos);

                //ESTE CODIGO SE USA CUANDO SE QUIERE INGRESAR AL EVENTO ON CLICKLISTENER
                //adaptadorb.setOnClickListener(new View.OnClickListener() {
                  //  @Override
                    //public void onClick(View v) {
                        //codreporte=codrep.toString();
                      //  Toast.makeText(getApplicationContext(), "Se obtiene el reporte" +listarreportesinc.get(mRecyclerview.
                        //        getChildAdapterPosition(v)).getCodreporte(),Toast.LENGTH_SHORT).show();
                       // Intent detalle= new Intent(PanelIncidentes.this,DetalleIncidente.class);
                       // variab=listarreportesinc.get(mRecyclerview.getChildAdapterPosition(v)).getCodreporte().toString();
                       // detalle.putExtra("pasar_codigo",variab);
                       // startActivity(detalle);
                   // }
                //});
                mRecyclerview2.setAdapter(adaptadorb);
                break;
        }
    }

    public void onClick(View view) {
    }
    //EVENTO PARA LLENAR EL COMBO DE TIPOS DE MOTO
    private void consultarmodelosmoto() {
        db= con.getWritableDatabase();
        Vehiculo clasetiposmoto= null;
        listaclasemotos2= new ArrayList<Vehiculo>();
        //CONSULTA
        Cursor cursor=db.rawQuery("select DISTINCT tipo_vehiculo from VEHICULO",null);

        while (cursor.moveToNext()){
            clasetiposmoto=new Vehiculo();
            clasetiposmoto.setVehiculo(cursor.getString(0));

            Log.i("id",clasetiposmoto.getVehiculo().toString());
            Log.i("desc",clasetiposmoto.getVehiculo());

            listaclasemotos2.add(clasetiposmoto);

        }
        llenarspinner();
        db.close();
    }
    private void llenarspinner() {
        listamotos2 =new ArrayList<String>();
        listamotos2.add("Todos");
        for(int i=0;i<listaclasemotos2.size();i++){
            listamotos2.add(listaclasemotos2.get(i).getVehiculo());

        }
    }

    private void consultarlistamotos() {
        db= con.getWritableDatabase();
        Vehiculo clasemotos= null;
        listaclasemotos= new ArrayList<Vehiculo>();
        Cursor cursor= db.rawQuery("select v.image1,v.placa_vehiculo,v.tipo_vehiculo,v.modelo_vehiculo,v.colorvehiculo,(select count(placa_vehiculo) from REPORTEINCIDENTE where placa_vehiculo= v.placa_vehiculo) reportes from VEHICULO v",null);

        while (cursor.moveToNext()){
            clasemotos= new Vehiculo();
            clasemotos.setImage1(cursor.getBlob(0));
            clasemotos.setPlaca(cursor.getString(1));
            clasemotos.setVehiculo(cursor.getString(2));
            clasemotos.setModelo(cursor.getString(3));
            clasemotos.setColor(cursor.getString(4));
            clasemotos.setCantidadreportes(cursor.getInt(5));
            listaclasemotos.add(clasemotos);
        }
        //obtenerinformacion();
        db.close();
    }
    private void consultarlistamotosxfiltro() {
        db= con.getWritableDatabase();
        Vehiculo clasemotos= null;
        listaclasemotos= new ArrayList<Vehiculo>();
        Cursor cursor= db.rawQuery("select v.image1,v.placa_vehiculo,v.tipo_vehiculo,v.modelo_vehiculo,v.colorvehiculo,(select count(placa_vehiculo) from REPORTEINCIDENTE where placa_vehiculo= v.placa_vehiculo) reportes from VEHICULO v where v.tipo_vehiculo='"+idtipovehiculo+"'",null);

        while (cursor.moveToNext()){
            clasemotos= new Vehiculo();
            clasemotos.setImage1(cursor.getBlob(0));
            clasemotos.setPlaca(cursor.getString(1));
            clasemotos.setVehiculo(cursor.getString(2));
            clasemotos.setModelo(cursor.getString(3));
            clasemotos.setColor(cursor.getString(4));
            clasemotos.setCantidadreportes(cursor.getInt(5));
            listaclasemotos.add(clasemotos);
        }
        //obtenerinformacion();
        db.close();
    }

}
