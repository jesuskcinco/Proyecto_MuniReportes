package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.proyectoandroid.Entidades.Mensajes;
import com.example.proyectoandroid.Entidades.ReporteIncidente;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.ArrayList;

public class AceptarConductor extends AppCompatActivity {
    GlobalVariables globalVariables;
    String cor_reportedet,dni;
    Bundle datos;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    ArrayList<Mensajes> listarmensajeacep;
    TextView asunto,msj,fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceptar_conductor);
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        datos = getIntent().getExtras();
        cor_reportedet=datos.getString("pasar_codigo");
        globalVariables = (GlobalVariables)getApplicationContext();

        dni=globalVariables.getDni();
        asunto=(TextView) findViewById(R.id.edtasumsj);
        msj=(TextView) findViewById(R.id.edtmsjmsj);
        fecha=(TextView) findViewById(R.id.edtfecha);
        asunto.setEnabled(false);
        msj.setEnabled(false);
        fecha.setEnabled(false);
        cargardatos();
    }

    private void cargardatos() {
        db= con.getWritableDatabase();
        Mensajes mensaje= null;

        listarmensajeacep =new ArrayList<Mensajes>();
        Cursor cursor=null;
        cursor= db.rawQuery("select placa_vehiculo,mensaje_not,dni_usuario,fecha_reporte from MENSAJES where cod_mensaje='"+cor_reportedet+"'",null);
        while (cursor.moveToNext()){
            mensaje= new Mensajes();
            mensaje.setPlaca(cursor.getString(0));
            //reporte.setImage2(cursor.getBlob(1));
            //reporte.setImage3(cursor.getBlob(2));
            mensaje.setMensaje_not(cursor.getString(1));
            mensaje.setDni_mensaje(cursor.getInt(2));
            mensaje.setFecha(cursor.getString(3));
            listarmensajeacep.add(mensaje);
        }
        obtenerinformacion();
        db.close();
    }

    private void obtenerinformacion() {
        for(int i=0;i<listarmensajeacep.size();i++){


            asunto.setText("Solicitud de conductor para su vehiculo con placa "+listarmensajeacep.get(i).getPlaca());
            fecha.setText(""+listarmensajeacep.get(i).getFecha());
            msj.setText(""+listarmensajeacep.get(i).getMensaje_not());

        }
    }
}
