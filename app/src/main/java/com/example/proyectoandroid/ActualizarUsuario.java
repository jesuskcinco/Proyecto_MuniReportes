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
import android.widget.Toast;

import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.io.ByteArrayOutputStream;

public class ActualizarUsuario extends AppCompatActivity {

    EditText ls_dni,ls_nombre,ls_apellido,ls_clave,ls_usuario,ls_correo,ls_celu;
    String dniobt,numero;
    GlobalVariables globalVariables;
    ConexionSQLiteHelper con;
    SQLiteDatabase db;
    ImageView image;
    Bitmap varbitmap;
    byte[] byteArray,img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_usuario);
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        ls_usuario= (EditText) findViewById(R.id.txt_usuario3);
        ls_clave= (EditText) findViewById(R.id.txt_clave3);
        ls_correo=(EditText) findViewById(R.id.txt_correo3);
        ls_nombre= (EditText) findViewById(R.id.txt_nombres3);
        ls_apellido= (EditText) findViewById(R.id.txt_apellidos3);
        ls_dni= (EditText) findViewById(R.id.txt_dni3);
        ls_celu= (EditText) findViewById(R.id.txt_cel);
        image=findViewById(R.id.imgusuario);
        globalVariables = (GlobalVariables)getApplicationContext();
        dniobt=globalVariables.getDni();
        listardatos();
        ls_usuario.setVisibility(View.GONE);
        ls_clave.setVisibility(View.GONE);
    }

    private void listardatos() {
        db= con.getWritableDatabase();
        Cursor cursor2=null;
        cursor2= db.rawQuery("select dni_usuario,nombres_usuario,apellidos_usuario,celular,correo_usuario,image1 from usuario where dni_usuario='"+dniobt+"'",null);
        if (cursor2.moveToFirst()) {
            ls_dni.setText("DNI: "+cursor2.getString(0));
            ls_nombre.setText("Nombres: "+cursor2.getString(1));
            ls_apellido.setText("Apellidos: "+cursor2.getString(2));

            numero=cursor2.getString(3);
            if(numero==""){
                ls_celu.setText("");
            }else {
                ls_celu.setText(cursor2.getString(3));
            }

            ls_correo.setText(""+cursor2.getString(4));
            if(cursor2.getBlob(5)!=null){
                image.setImageBitmap(BitmapFactory.decodeByteArray(cursor2.getBlob(5),
                        0,cursor2.getBlob(5).length));
            }else {
                image.setImageResource(R.drawable.sinimagen3);
            }



        }else{
            Toast.makeText(ActualizarUsuario.this,"El dni "+dniobt+" no se encuentra registrado",Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void onClick5 (View view){
        Intent miIntent=null;
        switch (view.getId()){

            case R.id.imgusuario:
                cargarimagen();
                break;
            case R.id.btn_grabar3:
                actualizarusuario();
                break;
            case R.id.btn_canc:
                Intent cancelar= new Intent(ActualizarUsuario.this,MainActivity.class);
                //cancelar.putExtra("pasar_usuario",dnipasado);
                startActivity(cancelar);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);


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
    private void actualizarusuario(){
    con= new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
     db= con.getWritableDatabase();

        String var_usuario3=ls_usuario.getText().toString();
        String var_clave3=ls_clave.getText().toString();
        String var_correo3=ls_correo.getText().toString();
        String var_nombre3=ls_nombre.getText().toString();
        String var_apellido3=ls_apellido.getText().toString();
        String var_dni3=ls_dni.getText().toString();
        String var_celu3=ls_celu.getText().toString();

    if (!var_dni3.isEmpty() && !var_nombre3.isEmpty() && !var_apellido3.isEmpty()  &&
         !var_correo3.isEmpty()){
            ContentValues valores= new ContentValues();
        valores.put(Utilitario.CAMPO_CORREO,var_correo3);
            valores.put(Utilitario.CAMPO_CELULAR,var_celu3);
            valores.put(Utilitario.CAMPO_IMAGE,img1);
            int resultado= db.update("USUARIO",valores,"dni_usuario='"+dniobt+"'",null);
            if (resultado==1){
                Toast.makeText(this,"La cuenta se actualizo correctamente",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(this,"El usuario no existe",Toast.LENGTH_SHORT).show();
            }
    }else {
        Toast.makeText(this,"No se deben ingresar campos vacios",Toast.LENGTH_SHORT).show();
    }
    db.close();
    }
}
