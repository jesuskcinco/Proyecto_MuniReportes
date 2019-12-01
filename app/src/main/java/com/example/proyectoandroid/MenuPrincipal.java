package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.Utilidades.GlobalVariables;

public class MenuPrincipal extends AppCompatActivity {
    GlobalVariables globalVariables;
    TextView getdni,cantreport;
    String dniobt;
    ImageView image,image2,image3,image4;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        getdni= (TextView) findViewById(R.id.dnimenu);
        cantreport= (TextView) findViewById(R.id.cantreport);

        globalVariables = (GlobalVariables)getApplicationContext();
        if (!globalVariables.getDni().isEmpty()){
            dniobt=globalVariables.getNomusu();

        }else {
            dniobt="70546327";
            globalVariables.setDni(dniobt);
        }
        getdni.setText("Hola "+dniobt+"!");
        image = findViewById(R.id.imageView19);
        image2 = findViewById(R.id.imageView20);
        image3 = findViewById(R.id.imageView21);
        image4 = findViewById(R.id.imageView22);

        image.setImageResource(R.drawable.perfil);
        image2.setImageResource(R.drawable.mantenimiento);
        image3.setImageResource(R.drawable.icon);
        image4.setImageResource(R.drawable.buscaricono);
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        db= con.getWritableDatabase();
        Cursor cursor2=null;
        cursor2= db.rawQuery("select count(*) as cantidad from REPORTEINCIDENTE where estado_reporte='Activo'",null);
        if (cursor2.moveToFirst()) {
            cantreport.setText(""+cursor2.getString(0)+" reportes activos");
        }else{
            cantreport.setText("No existen reportes");
        }
        db.close();
    }
}
