package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.example.proyectoandroid.Adaptadores.AdaptadorReportes;
import com.example.proyectoandroid.Entidades.ReporteIncidente;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.ArrayList;

public class PanelIncidentes extends AppCompatActivity {
    Bundle datos2;
    String usupas;
    RecyclerView mRecyclerview;
    ArrayList<ReporteIncidente> listdatosreportes;
    ArrayList<ReporteIncidente> listarreportesinc;
    RecyclerView.Adapter madapter;
    RecyclerView.LayoutManager mlayoutmanager;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    GlobalVariables globalVariables;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_incidentes);
        globalVariables = (GlobalVariables)getApplicationContext();
        //asignamos el recycler
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        mRecyclerview= findViewById(R.id.idrecycler);
        //mRecyclerview.setHasFixedSize(true);
        mlayoutmanager= new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mlayoutmanager);
        //madapter=new MyA

        //listdatosreportes=new ArrayList<ReporteIncidente>();
        consultarlistareportes();
       // for (int i=0;i<=10;i++){
         //   listdatosreportes.add("asunto"+i);
        //    listdatosreportes.add("estado"+i);
        //    listdatosreportes.add("desc"+i);
       // }
        AdaptadorReportes adaptador=new AdaptadorReportes(listarreportesinc);
        mRecyclerview.setAdapter(adaptador);
    }
    //llenamos el arraylist con el query de los reportes generados
    private void consultarlistareportes() {
        db= con.getWritableDatabase();
        ReporteIncidente reporte= null;
        listarreportesinc =new ArrayList<ReporteIncidente>();
        Cursor cursor= db.rawQuery("select imagen1,asunto_reporte,estado_reporte,desc_reporte,cod_reporte from "+ Utilitario.TABLE_REPORTE_INCIDENTE,null);

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
