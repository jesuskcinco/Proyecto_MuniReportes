package com.example.proyectoandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.Entidades.ReporteIncidente;
import com.example.proyectoandroid.Entidades.Vehiculo;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.io.ByteArrayOutputStream;

public class ActualizarMototaxi extends AppCompatActivity {
    Bundle datos;
    String placa_moto;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    Vehiculo vehiculo;
    TextView placa,modelo,tipovehiculo,dniusu;
    EditText color;
    ImageView image;
    GlobalVariables globalVariables;
    String dnipasado;
    Bitmap varbitmap;
    byte[] byteArray,img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_actualizar_mototaxi);
        datos = getIntent().getExtras();
        placa_moto= datos.getString("pasar_placa");
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        globalVariables = (GlobalVariables)getApplicationContext();
        placa= findViewById(R.id.edtplacave);
        modelo= findViewById(R.id.edtmodel);
        tipovehiculo= findViewById(R.id.edttipove);
        dniusu= findViewById(R.id.edtusu);
        image = findViewById(R.id.imageve);
        color= findViewById(R.id.edtcolor);

        if(!globalVariables.getDni().isEmpty()){
            dnipasado=globalVariables.getDni();
        }else {
            Toast.makeText(ActualizarMototaxi.this,"No se inicio sesion",Toast.LENGTH_SHORT).show();
        }
        if(!placa_moto.isEmpty()){
            lista_datos();
        }else{
            Toast.makeText(ActualizarMototaxi.this,"No se envió la placa",Toast.LENGTH_SHORT).show();
        }




    }

    private void lista_datos() {
        db= con.getWritableDatabase();
        Cursor cursor2=null;
        cursor2= db.rawQuery("select placa_vehiculo,tipo_vehiculo,modelo_vehiculo,colorvehiculo,dni_usuario,image1 from VEHICULO where placa_vehiculo='"+placa_moto+"'",null);
        if (cursor2.moveToFirst()) {
            placa.setText("Placa: "+cursor2.getString(0));
            tipovehiculo.setText("Tipo de Vehiculo: "+cursor2.getString(1));
            modelo.setText("Modelo: "+cursor2.getString(2));
            color.setText(cursor2.getString(3));
            dniusu.setText(cursor2.getString(4));
            if(cursor2.getBlob(5)!=null){
                image.setImageBitmap(BitmapFactory.decodeByteArray(cursor2.getBlob(5),
                        0,cursor2.getBlob(5).length));
            }else {
                image.setImageResource(R.drawable.sinimagen3);
            }



        }else{
            Toast.makeText(ActualizarMototaxi.this,"La placa "+placa_moto+" no se encuentra registrada",Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){

            case R.id.imageve:
                cargarimagen();
                break;
            case R.id.button8:
                actualizardatos();
                break;
            case R.id.button9:
                Intent cancelar= new Intent(ActualizarMototaxi.this,Consultar_Mototaxis.class);
                cancelar.putExtra("pasar_usuario",dnipasado);
                startActivity(cancelar);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }

    private void actualizardatos() {
        String color2= color.getText().toString();
        db= con.getWritableDatabase();
        ContentValues values= new ContentValues();
        if(!color2.isEmpty()){
            values.put(Utilitario.CAMPOCOLOR,color2);
            values.put(Utilitario.CAMPO_IMAGE,img1);
            int resultado= db.update("VEHICULO",values,"placa_vehiculo='"+placa_moto+"'",null);
            if (resultado==1){
                Toast.makeText(this,"El registro se actualizo correctamente",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this,"La moto no existe",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(ActualizarMototaxi.this,"No puede dejar el campo color sin datos",Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    private void cargarimagen() {
        Intent inten=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //inten.setAction(Intent.ACTION_GET_CONTENT);
        inten.setType("image/");
        startActivityForResult(inten.createChooser(inten,"Seleccione imagen"),10);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path= data.getData();
            image.setImageURI(path);
            varbitmap= image.getDrawingCache();
            //cambiar el tipo de la variable
            varbitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
            varbitmap.compress(Bitmap.CompressFormat.PNG, 0 , baos);
            byteArray = baos.toByteArray();
            img1=byteArray;

            //contimg=contimg+1;
        }
    }


}
