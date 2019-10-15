package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    String cor_reportedet;
    TextView codrep;
    TextView asunto,descripcion,ubicacion,placaotro,modelootro,marcaotro;
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
        Cursor cursor= db.rawQuery("select imagen1,asunto_reporte,estado_reporte,desc_reporte,cod_reporte from "+Utilitario.TABLE_REPORTE_INCIDENTE +
                " where cod_reporte='"+cor_reportedet+"'",null);
        while (cursor.moveToNext()){
            reporte= new ReporteIncidente();
            reporte.setImage1(cursor.getBlob(0));
            reporte.setAsunto(cursor.getString(1));
            reporte.setEstado(cursor.getString(2));
            reporte.setDescripcion(cursor.getString(3));
            reporte.setCodreporte(cursor.getInt(4));
            listarreportesinc.add(reporte);
        }
        asunto.setText(listarreportesinc.get(0).getAsunto());
        descripcion.setText(listarreportesinc.get(0).getDescripcion());
        ubicacion.setText(listarreportesinc.get(0).getEstado());
        //obtenerinformacion();
        db.close();
    }

    public void onClick(View view) {


    }
}
