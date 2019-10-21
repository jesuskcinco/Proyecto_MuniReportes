package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.Entidades.ReporteIncidente;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.ArrayList;

public class DetalleIncidente extends AppCompatActivity {
    Bundle datos;
    String cor_reportedet,latit,longit;
    TextView codrep;
    TextView asunto,descripcion,ubicacion,placa,placaotro,modelootro,marcaotro,estado,tipo,nivel;
    ImageView img1,img2,img3;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    ArrayList<ReporteIncidente> listarreportesinc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_incidente);
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        datos = getIntent().getExtras();

        asunto=(TextView) findViewById(R.id.edtasunto);
        tipo=(TextView) findViewById(R.id.edttipo);
        nivel=(TextView) findViewById(R.id.edtnivel);
        estado=(TextView) findViewById(R.id.edtestado);
        nivel.setVisibility(View.GONE);
        tipo.setVisibility(View.GONE);
        placa=(TextView) findViewById(R.id.edtplaca);
        placaotro=(TextView) findViewById(R.id.placaotro);
        modelootro=(TextView) findViewById(R.id.modelootro);
        marcaotro=(TextView) findViewById(R.id.marcaotro);
        img1=(ImageView) findViewById(R.id.imageView3);
        img2=(ImageView) findViewById(R.id.imageView4);
        img3=(ImageView) findViewById(R.id.imageView5);
        descripcion=(TextView) findViewById(R.id.edt_desc_reporte);

        ubicacion=(TextView) findViewById(R.id.edtgps);
        cor_reportedet=datos.getString("pasar_codigo");
        Toast.makeText(getApplicationContext(), "Se obtiene el reporte " + cor_reportedet, Toast.LENGTH_LONG).show();
        cargardatos();
    }

    private void cargardatos() {
        db= con.getWritableDatabase();
        ReporteIncidente reporte= null;

        listarreportesinc =new ArrayList<ReporteIncidente>();
        Cursor cursor=null;
        cursor= db.rawQuery("select imagen1,asunto_reporte,estado_reporte,desc_reporte,cod_reporte,placa_vehiculo,placa_otro,modelo_otro,marca_otro,latitud,longitud from "+Utilitario.TABLE_REPORTE_INCIDENTE +
                " where cod_reporte='"+cor_reportedet+"'",null);
        while (cursor.moveToNext()){
            reporte= new ReporteIncidente();
            reporte.setImage1(cursor.getBlob(0));
            //reporte.setImage2(cursor.getBlob(1));
            //reporte.setImage3(cursor.getBlob(2));
            reporte.setAsunto(cursor.getString(1));
            reporte.setEstado(cursor.getString(2));
            reporte.setDescripcion(cursor.getString(3));
            reporte.setCodreporte(cursor.getInt(4));
            reporte.setPlacamoto(cursor.getString(5));
            reporte.setPlacaotro(cursor.getString(6));
            reporte.setModelootro(cursor.getString(7));
            reporte.setMarcarotro(cursor.getString(8));
            reporte.setLatitud(cursor.getString(9));
            reporte.setLongitud(cursor.getString(10));
            listarreportesinc.add(reporte);
        }
        obtenerinformacion();
        db.close();
    }

    private void obtenerinformacion(){
        //listdatosreportes= new ArrayList<ReporteIncidente>();
       for(int i=0;i<listarreportesinc.size();i++){
           if(listarreportesinc.get(i).getImage1()!=null){
               img1.setImageBitmap(BitmapFactory.decodeByteArray(listarreportesinc.get(i).getImage1(),
                       i,listarreportesinc.get(i).getImage1().length));
           }else {
               img1.setImageResource(R.drawable.sinimagen3);
           }
           if(listarreportesinc.get(i).getImage2()!=null){
               img2.setImageBitmap(BitmapFactory.decodeByteArray(listarreportesinc.get(i).getImage2(),
                       i,listarreportesinc.get(i).getImage2().length));
           }else {
               img2.setImageResource(R.drawable.sinimagen3);
           }
           if(listarreportesinc.get(i).getImage3()!=null){
               img3.setImageBitmap(BitmapFactory.decodeByteArray(listarreportesinc.get(i).getImage3(),
                       i,listarreportesinc.get(i).getImage3().length));
           }else {
               img3.setImageResource(R.drawable.sinimagen3);
           }

           asunto.setText(""+listarreportesinc.get(i).getAsunto());
           estado.setText(""+listarreportesinc.get(i).getAsunto());
           descripcion.setText(""+listarreportesinc.get(i).getDescripcion());
           placa.setVisibility(View.VISIBLE);
           placaotro.setVisibility(View.VISIBLE);
           modelootro.setVisibility(View.VISIBLE);
           marcaotro.setVisibility(View.VISIBLE);
           placa.setText(""+listarreportesinc.get(i).getPlacamoto());
           placaotro.setText(""+listarreportesinc.get(i).getPlacaotro());
           modelootro.setText(""+listarreportesinc.get(i).getModelootro());
           marcaotro.setText(""+listarreportesinc.get(i).getMarcarotro());
          if(placa.getText()!="null"){
              placa.setVisibility(View.VISIBLE);
              placaotro.setVisibility(View.GONE);
              modelootro.setVisibility(View.GONE);
              marcaotro.setVisibility(View.GONE);
          }else if (placaotro.getText()!="null"){
              placa.setVisibility(View.GONE);
              placaotro.setVisibility(View.VISIBLE);
              modelootro.setVisibility(View.VISIBLE);
              marcaotro.setVisibility(View.VISIBLE);
          }else{
              placa.setVisibility(View.GONE);
              placaotro.setVisibility(View.GONE);
              modelootro.setVisibility(View.GONE);
              marcaotro.setVisibility(View.GONE);
          }
          latit=listarreportesinc.get(i).getLatitud();
          longit=listarreportesinc.get(i).getLongitud();
      }
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){

            case R.id.btncancelar:
                miIntent= new Intent(DetalleIncidente.this, PanelIncidentes.class);
                cor_reportedet="";
                break;
            case R.id.btnverub:
                miIntent= new Intent(DetalleIncidente.this, MapsActivity.class);
                miIntent.putExtra("pasar_latitud",latit);
                miIntent.putExtra("pasar_longitud",longit);
                String reporte =cor_reportedet.toString();
                miIntent.putExtra("pasar_codrep",reporte);

                break;
        }
        if (miIntent!=null) startActivity(miIntent);

    }
}
