package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.Adaptadores.AdaptadorReportes;
import com.example.proyectoandroid.Entidades.ReporteIncidente;
import com.example.proyectoandroid.Entidades.TipoIncidente;
import com.example.proyectoandroid.Entidades.Usuario;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.ArrayList;

public class PanelIncidentes extends AppCompatActivity {
    Bundle datos2;
    String usupas,codreporte,variab="";
    TextView codrep;
    RecyclerView mRecyclerview;
    ArrayList<ReporteIncidente> listdatosreportes;
    ArrayList<ReporteIncidente> listarreportesinc;
    //Array para los tipos de incidentes
    ArrayList<String> listatiposinc;
    ArrayList<TipoIncidente> tipoincidentelist;
    RecyclerView.Adapter madapter;
    RecyclerView.LayoutManager mlayoutmanager;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    GlobalVariables globalVariables;
    Spinner spntipos;
    int idtipoincidente,idcombo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_incidentes);
        codrep=(TextView) findViewById(R.id.codrep);
        globalVariables = (GlobalVariables)getApplicationContext();
        //asignamos el recycler
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        mRecyclerview= findViewById(R.id.idrecycler);
        //mRecyclerview.setHasFixedSize(true);
        mlayoutmanager= new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mlayoutmanager);
        spntipos =(Spinner) findViewById(R.id.spntipos);
        //madapter=new MyA
        consultartiposincidentes();
        //listdatosreportes=new ArrayList<ReporteIncidente>();
        consultarlistareportes();
       // for (int i=0;i<=10;i++){
         //   listdatosreportes.add("asunto"+i);
        //    listdatosreportes.add("estado"+i);
        //    listdatosreportes.add("desc"+i);
       // }
        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listatiposinc);
        spntipos.setAdapter(adaptador2);
        //metodo que realizar la actualizacion del panel de incidentes

        spntipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 idcombo = (int) spntipos.getSelectedItemId();
                if (idcombo != 0) {
                    //valor de combo tipo incidente
                    Log.i("id", tipoincidentelist.size() + "");
                    Log.i("desc", idcombo + "");
                    Log.i("desc - 1", (idcombo - 1) + "");
                    idtipoincidente = tipoincidentelist.get(idcombo - 1).getCodigo();
                    Log.i("idcombo-1", idtipoincidente + "");
                    reportexfiltro();
                    //Toast.makeText(getApplicationContext(), "Se grabo la cabecera del reporte " + idtipoincidente, Toast.LENGTH_LONG).show();
                }else{
                    reportexfiltro();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //if (idtipoincidente != 0) {
        //    reportexfiltro();
        //}

        AdaptadorReportes adaptadorprinc=new AdaptadorReportes(listarreportesinc);
        //adaptadorprinc.setOnClickListener(new View.OnClickListener() {
         //   @Override
        //    public void onClick(View v) {
         //       codreporte=codrep.toString();
         //       Toast.makeText(getApplicationContext(), "Se obtiene el reporte" +listarreportesinc.get(mRecyclerview.
         //               getChildAdapterPosition(v)).getCodreporte(),Toast.LENGTH_SHORT).show();
                //Intent detalle= new Intent(PanelIncidentes.this,DetalleIncidente.class);
                //detalle.putExtra("pasar_codigo",codreporte);
                //startActivity(detalle);
           // }
     //   });
        mRecyclerview.setAdapter(adaptadorprinc);


    }


    private void reportexfiltro() {
        switch (idcombo){

            case 0:
                consultarlistareportes();
                AdaptadorReportes adaptadora=new AdaptadorReportes(listarreportesinc);
                adaptadora.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //codreporte=codrep.toString();
                        Toast.makeText(getApplicationContext(), "Se obtiene el reporte" +listarreportesinc.get(mRecyclerview.
                                getChildAdapterPosition(v)).getCodreporte(),Toast.LENGTH_SHORT).show();
                        Intent detalle= new Intent(PanelIncidentes.this,DetalleIncidente.class);
                        variab=listarreportesinc.get(mRecyclerview.getChildAdapterPosition(v)).getCodreporte().toString();
                        detalle.putExtra("pasar_codigo",variab);
                        startActivity(detalle);
                    }
                });
                mRecyclerview.setAdapter(adaptadora);
                break;
            default:
                consultarlistareportesxiltro();
                AdaptadorReportes adaptadorb=new AdaptadorReportes(listarreportesinc);
                adaptadorb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //codreporte=codrep.toString();
                        Toast.makeText(getApplicationContext(), "Se obtiene el reporte" +listarreportesinc.get(mRecyclerview.
                                getChildAdapterPosition(v)).getCodreporte(),Toast.LENGTH_SHORT).show();
                        Intent detalle= new Intent(PanelIncidentes.this,DetalleIncidente.class);
                        variab=listarreportesinc.get(mRecyclerview.getChildAdapterPosition(v)).getCodreporte().toString();
                        detalle.putExtra("pasar_codigo",variab);
                        startActivity(detalle);
                    }
                });
                mRecyclerview.setAdapter(adaptadorb);
                break;
        }
    }
    //llenamos el arraylist con el query de los reportes generados
    private void consultarlistareportes() {
        db= con.getWritableDatabase();
        ReporteIncidente reporte= null;
        listarreportesinc =new ArrayList<ReporteIncidente>();
        Cursor cursor= db.rawQuery("select imagen1,asunto_reporte,estado_reporte,desc_reporte,cod_reporte from "+ Utilitario.TABLE_REPORTE_INCIDENTE+" where estado_reporte<>'Incompleto'",null);

        while (cursor.moveToNext()){
            reporte= new ReporteIncidente();
            reporte.setImage1(cursor.getBlob(0));
            reporte.setAsunto(cursor.getString(1));
            reporte.setEstado(cursor.getString(2));
            reporte.setDescripcion(cursor.getString(3));
            reporte.setCodreporte(cursor.getInt(4));
            listarreportesinc.add(reporte);
        }
        //obtenerinformacion();
        db.close();
    }
    private void consultarlistareportesxiltro() {
        db= con.getWritableDatabase();
        ReporteIncidente reporte= null;
        listarreportesinc =new ArrayList<ReporteIncidente>();
        Cursor cursor= db.rawQuery("select imagen1,asunto_reporte,estado_reporte,desc_reporte,cod_reporte from "+ Utilitario.TABLE_REPORTE_INCIDENTE+" where " +
                "codigo_incidente='"+idtipoincidente+"'and estado_reporte<>'Incompleto'",null);

        while (cursor.moveToNext()){
            reporte= new ReporteIncidente();
            reporte.setImage1(cursor.getBlob(0));
            reporte.setAsunto(cursor.getString(1));
            reporte.setEstado(cursor.getString(2));
            reporte.setDescripcion(cursor.getString(3));
            reporte.setCodreporte(cursor.getInt(4));
            listarreportesinc.add(reporte);
        }
        //obtenerinformacion();
        db.close();
    }
    //llenamos el spinner de tipos de incidentes
    private void consultartiposincidentes() {

        db= con.getWritableDatabase();
        TipoIncidente clase_tipo_incidente= null;
        tipoincidentelist= new ArrayList<TipoIncidente>();
        //CONSULTA
        Cursor cursor=db.rawQuery("select codigo_incidente,tipo_incidente from " +Utilitario.TABLE_TIPO_INCIDENTE,null);

        while (cursor.moveToNext()){
            clase_tipo_incidente=new TipoIncidente();
            clase_tipo_incidente.setCodigo(cursor.getInt(0));
            clase_tipo_incidente.setDescripcion(cursor.getString(1));

            Log.i("id",clase_tipo_incidente.getCodigo().toString());
            Log.i("desc",clase_tipo_incidente.getDescripcion());

            tipoincidentelist.add(clase_tipo_incidente);

        }
        llenarspinner();
        db.close();
    }
    private void llenarspinner() {
        listatiposinc =new ArrayList<String>();
        listatiposinc.add("Todos");
        for(int i=0;i<tipoincidentelist.size();i++){
            listatiposinc.add(tipoincidentelist.get(i).getDescripcion());

        }
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){

            case R.id.btncanpan:
                //usupas=datos2.getString("pasar_usuario");


                miIntent= new Intent(PanelIncidentes.this, MainActivity.class);
                miIntent.putExtra("pasar_usuario",globalVariables.getDni());
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }


    //private void obtenerinformacion(){
    //    listdatosreportes= new ArrayList<ReporteIncidente>();
     //   for(int i=0;i<listarreportesinc.size();i++){
    //        listdatosreportes.add(BitmapFactory.decodeByteArray(listarreportesinc.get(i).getImage1(),
     //               0,listarreportesinc.get(i).getImage1().length).toString());
    //        listdatosreportes.add(listarreportesinc.get(i).getAsunto());
    //        listdatosreportes.add(listarreportesinc.get(i).getEstado());
    //        listdatosreportes.add(listarreportesinc.get(i).getDescripcion());
     //   }
    //}

}
