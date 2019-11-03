package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.Entidades.ComentarioIncidente;
import com.example.proyectoandroid.Entidades.ReporteIncidente;
import com.example.proyectoandroid.Entidades.Vehiculo;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetalleIncidente extends AppCompatActivity {
    Bundle datos;
    String cor_reportedet,latit="0.0",longit="0.0",placa2;
    TextView codrep;
    TextView asunto,descripcion,ubicacion,placa,placaotro,modelootro,marcaotro,estado,tipo,nivel,comenta;
    ImageView img1,img2,img3;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    ArrayList<ReporteIncidente> listarreportesinc;
    ArrayList<ComentarioIncidente> listacomentarios;
    ArrayList<String> listainformacion;
    Button btn;
    GlobalVariables globalVariables;
    String dniobt;
    ListView listviewcomentarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_incidente);
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        datos = getIntent().getExtras();
        globalVariables = (GlobalVariables)getApplicationContext();
        if (!globalVariables.getDni().isEmpty()){
            dniobt=globalVariables.getDni();

        }else {
            dniobt="70546327";
            globalVariables.setDni(dniobt);
        }
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
        comenta=findViewById(R.id.edtcomentario);
        btn=findViewById(R.id.btnverub);
        ubicacion=(TextView) findViewById(R.id.edtgps);
        cor_reportedet=datos.getString("pasar_codigo");
        Toast.makeText(getApplicationContext(), "Se obtiene el reporte " + cor_reportedet, Toast.LENGTH_LONG).show();
        cargardatos();
        listarcomentarios();
        listviewcomentarios= (ListView) findViewById(R.id.listviewcomentarios);
        final ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        listviewcomentarios.setAdapter(adaptador);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }



    private void cargardatos() {
        db= con.getWritableDatabase();
        ReporteIncidente reporte= null;

        listarreportesinc =new ArrayList<ReporteIncidente>();
        Cursor cursor=null;
        cursor= db.rawQuery("select imagen1,asunto_reporte,estado_reporte,desc_reporte,cod_reporte,placa_vehiculo,placa_otro,modelo_otro,marca_otro,latitud,longitud,direccion from "+Utilitario.TABLE_REPORTE_INCIDENTE +
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
            reporte.setDireccion(cursor.getString(11));
            listarreportesinc.add(reporte);
        }
        obtenerinformacion();
        db.close();
    }
//obtener informacion de reporte
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

           asunto.setText("Asunto: "+listarreportesinc.get(i).getAsunto());
           estado.setText("Estado: "+listarreportesinc.get(i).getEstado());
           descripcion.setText("Descripcion: "+listarreportesinc.get(i).getDescripcion());
           placa.setText("Placa: "+listarreportesinc.get(i).getPlacamoto());
           placaotro.setText("Placa del vehiculo: "+listarreportesinc.get(i).getPlacaotro());
           modelootro.setText("Modelo del vehiculo: "+listarreportesinc.get(i).getModelootro());
           marcaotro.setText("Marca del vehiculo: "+listarreportesinc.get(i).getMarcarotro());

           if(placa.getText().equals("Placa: null")){
               placa.setVisibility(View.GONE);
           }else{
               placa.setVisibility(View.VISIBLE);
           }
           if(placaotro.getText().equals("Placa del vehiculo: null")){
               placaotro.setVisibility(View.GONE);
               modelootro.setVisibility(View.GONE);
               marcaotro.setVisibility(View.GONE);
           }else{
               placaotro.setVisibility(View.VISIBLE);
               modelootro.setVisibility(View.VISIBLE);
               marcaotro.setVisibility(View.VISIBLE);
           }

          latit=listarreportesinc.get(i).getLatitud();
          longit=listarreportesinc.get(i).getLongitud();
          ubicacion.setText("Referencia: "+listarreportesinc.get(i).getDireccion());



          if(latit.equals("null")){
              btn.setVisibility(View.GONE);
          }else {
              btn.setVisibility(View.VISIBLE);
          }
      }
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){

            case R.id.btncancelar:
                miIntent= new Intent(DetalleIncidente.this, PanelIncidentes.class);
                cor_reportedet="";
                break;
            case R.id.btnagregarcomentarios:
                grabarcomentario();
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

    private void grabarcomentario() {
        db= con.getWritableDatabase();
        String come= comenta.getText().toString();
        String documento= dniobt;
        ContentValues values = new ContentValues();

        values.put(Utilitario.CAMPO_COMENTARIO, come);
        values.put(Utilitario.CAMPO_DNI, documento);
        Date fechaActual = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        values.put(Utilitario.CAMPO_FECHA_COMENT, formatoFecha.format(fechaActual));
        values.put(Utilitario.CAMPO_VISIBILIDAD, "1");
        values.put(Utilitario.CAMPO_ID_REPORTE, cor_reportedet);
        Long idResultante= db.insert(Utilitario.TABLE_COMENTARIO,Utilitario.CAMPO_COD_COMENTARIO,values);

        Toast.makeText( DetalleIncidente.this, "Se agrego el comentario",Toast.LENGTH_LONG).show();

        Intent detalle= new Intent(DetalleIncidente.this,DetalleIncidente.class);

        detalle.putExtra("pasar_codigo",cor_reportedet);
        startActivity(detalle);

        db.close();
    }

    private void listarcomentarios() {
        db= con.getWritableDatabase();
        ComentarioIncidente comentario= null;
        listacomentarios =new ArrayList<ComentarioIncidente>();
        Cursor cursor= db.rawQuery("select comentario,c.dni_usuario,fechacomenta,visibilidad,(nombres_usuario|| ' ' ||apellidos_usuario) as usuario from COMENTARIOS c inner join USUARIO u on c.dni_usuario=u.dni_usuario where cod_reporte='"+cor_reportedet+"'",null);

        while (cursor.moveToNext()){
            comentario= new ComentarioIncidente();
            comentario.setComentario(cursor.getString(0));
            comentario.setUsucomenta(cursor.getString(1));
            comentario.setFechacomenta(cursor.getString(2));
            comentario.setVisibilidad(cursor.getString(3));
            comentario.setUsuariocomen(cursor.getString(4));
            listacomentarios.add(comentario);
        }
        obtenerinformacion2();
        db.close();
    }

    private void obtenerinformacion2(){
        listainformacion= new ArrayList<String>();
        for(int i=0;i<listacomentarios.size();i++){
            listainformacion.add(listacomentarios.get(i).getUsuariocomen()+": "+listacomentarios.get(i).getComentario()+" el "+listacomentarios.get(i).getFechacomenta());
        }
    }
}
