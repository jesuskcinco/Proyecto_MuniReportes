package com.example.proyectoandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.Entidades.NivelIncidente;
import com.example.proyectoandroid.Entidades.ReporteIncidente;
import com.example.proyectoandroid.Entidades.TipoIncidente;
import com.example.proyectoandroid.Entidades.Usuario;
import com.example.proyectoandroid.Entidades.Vehiculo;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegistrarIncidente extends AppCompatActivity {

    EditText asunto,descripcion,placaotro,modelootro,marcaotro,txtgps,referencia;

    Spinner tipo_inv, niv_inc,placas_moto;
    String dnipasado;
    ArrayList<String> listartipoinc,listarnivinc,listarplacas;
    ArrayList<TipoIncidente> tipoinclist;
    ArrayList<NivelIncidente> nivinclist;
    ArrayList<Vehiculo> placaslist;
    ConexionSQLiteHelper con;
    SQLiteDatabase db;
    Bundle datos2;
    LinearLayout ly;
    Integer cod_reporte;
    RadioButton rb1,rb2,rb3,rb4;
    Button btngps,btncanc;
    CheckBox chk1;
    ImageView img;
    Integer contimg=0;
    //Byte ;
    Bitmap varbitmap;
    byte[] byteArray,img1,img2,img3;
    Double longitud, latitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_incidente);
        con= new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        asunto = findViewById(R.id.edtasunto);
        datos2 = getIntent().getExtras();
        tipo_inv= (Spinner) findViewById(R.id.spn_tipo_reporte);
        niv_inc= (Spinner) findViewById(R.id.spn_nivel_reporte);
        placas_moto= (Spinner) findViewById(R.id.spn_placas);
        placaotro=findViewById(R.id.placaotro);
        modelootro=findViewById(R.id.modelootro);
        marcaotro=findViewById(R.id.marcaotro);
        descripcion=findViewById(R.id.edt_desc_reporte);
        dnipasado=datos2.getString("pasar_usuario");
        btngps=(Button) findViewById(R.id.btngps2);
        btncanc=(Button) findViewById(R.id.button17);
        btncanc.setVisibility(View.VISIBLE);
        referencia=findViewById(R.id.edtreferencia);
        txtgps= findViewById(R.id.edtgps);
        latitud=0.0;
        longitud=0.0;
        ly= findViewById(R.id.lyobjetos);
        ly.setVisibility(View.INVISIBLE);
        rb1= findViewById(R.id.rdbsi);
        rb2= findViewById(R.id.rdbno);
        rb3= findViewById(R.id.rdbmoto);
        rb4= findViewById(R.id.rdbotro);
        chk1=findViewById(R.id.chksindatos);
        img=findViewById(R.id.imageView3);
        if (dnipasado.equals("")){
            dnipasado="70546327";
        }


        btngps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager locationManager=(LocationManager) RegistrarIncidente.this.getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener=new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        //txtgps.setText("entro el texto");

                        txtgps.setText(""+location.getLatitude()+"-"+location.getLongitude());
                        longitud=location.getLatitude();
                        latitud=location.getLongitude();
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                };
                int permissionCheck= ContextCompat.checkSelfPermission(RegistrarIncidente.this, Manifest.permission.ACCESS_FINE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
            }
        });
        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck== PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }

        consultartipoinc();
        ArrayAdapter<CharSequence> adaptador1= new ArrayAdapter(this,android.R.layout.simple_spinner_item,listartipoinc);
        tipo_inv.setAdapter(adaptador1);
        consultarnivinc();
        ArrayAdapter<CharSequence> adaptador2= new ArrayAdapter(this,android.R.layout.simple_spinner_item,listarnivinc);
        niv_inc.setAdapter(adaptador2);
    }
    //funcion para llenar el spinner tipo incidente
    private void consultartipoinc() {


        db= con.getWritableDatabase();
        TipoIncidente clase_tipo_incidente= null;
        tipoinclist= new ArrayList<TipoIncidente>();
        //CONSULTA
        Cursor cursor=db.rawQuery("select codigo_incidente,tipo_incidente from " + Utilitario.TABLE_TIPO_INCIDENTE,null);

        while (cursor.moveToNext()){
            clase_tipo_incidente=new TipoIncidente();
            clase_tipo_incidente.setCodigo(cursor.getInt(0));
            clase_tipo_incidente.setDescripcion(cursor.getString(1));

            Log.i("id_tipo",clase_tipo_incidente.getCodigo().toString());
            Log.i("desc_tipo",clase_tipo_incidente.getDescripcion());

            tipoinclist.add(clase_tipo_incidente);

        }
        llenarspinner1();
        db.close();
    }
    private void llenarspinner1(){
        listartipoinc =new ArrayList<String>();
        listartipoinc.add("Seleccione");
        for(int i=0;i<tipoinclist.size();i++){
            listartipoinc.add(tipoinclist.get(i).getDescripcion());

        }

    }
    //metodo para llenar el spinner nivel incidente
    private void consultarnivinc() {


        db= con.getWritableDatabase();
        NivelIncidente clase_nivel_incidente= null;
        nivinclist= new ArrayList<NivelIncidente>();
        //CONSULTA
        Cursor cursor=db.rawQuery("select idnivinc,desc_niv_inc from " + Utilitario.TABLE_NIVEL_INCIDENTE,null);

        while (cursor.moveToNext()){
            clase_nivel_incidente=new NivelIncidente();
            clase_nivel_incidente.setIdnivinc(cursor.getInt(0));
            clase_nivel_incidente.setDescripcion(cursor.getString(1));

            Log.i("id_niv",clase_nivel_incidente.getIdnivinc().toString());
            Log.i("des_niv",clase_nivel_incidente.getDescripcion());

            nivinclist.add(clase_nivel_incidente);

        }
        llenarspinner2();
        db.close();
    }
    private void llenarspinner2() {
        listarnivinc = new ArrayList<String>();
        listarnivinc.add("Seleccione");
        for (int i = 0; i < nivinclist.size(); i++) {
            listarnivinc.add(nivinclist.get(i).getDescripcion());

        }
    }


    //método para grabar la cabecera del reporte
    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){

            case R.id.button18:
                grabarcabecera();
                break;
            case R.id.button17:
                miIntent= new Intent(RegistrarIncidente.this, MainActivity.class);
                miIntent.putExtra("pasar_usuario",dnipasado);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }

    private void grabarcabecera() {
        String var_asunto =asunto.getText().toString();
        String var_dni= dnipasado;
        db= con.getWritableDatabase();

        ContentValues values = new ContentValues();
        if(!var_asunto.isEmpty()){
            values.put(Utilitario.CAMPO_ASUNTO,var_asunto);

            //aqui se obtienen los datos del combo
            int idcombo = (int) tipo_inv.getSelectedItemId();
            int idcombo2 = (int) niv_inc.getSelectedItemId();
            if (idcombo != 0 && idcombo2!=0) {
                //valor de combo tipo incidente
                Log.i("TAMAÑO", tipoinclist.size() + "");
                Log.i("idcombo1", idcombo + "");
                Log.i("idcombo1 - 1", (idcombo - 1) + "");
                int idtipoincidente = tipoinclist.get(idcombo - 1).getCodigo();
                Log.i("idcombo1", idtipoincidente + "");
                values.put(Utilitario.CAMPO_CODIGO, idtipoincidente);

                //valor de combo nivel incidente
                Log.i("TAMAÑO", nivinclist.size() + "");
                Log.i("idcombo2", idcombo2 + "");
                Log.i("idcombo2 - 1", (idcombo2 - 1) + "");
                int idnivelincidente = nivinclist.get(idcombo2 - 1).getIdnivinc();
                Log.i("idcombo2", idnivelincidente + "");
                values.put(Utilitario.CAMPO_ID_NIV_INC, idnivelincidente);
                values.put(Utilitario.CAMPO_DNI,var_dni);
                values.put(Utilitario.CAMPO_ESTADO_REPORTE,"Incompleto");
                Long idResultante = db.insert(Utilitario.TABLE_REPORTE_INCIDENTE, Utilitario.CAMPO_ID_REPORTE, values);


                Cursor cr= db.rawQuery("select max (cod_reporte) from REPORTEINCIDENTE where dni_usuario="+var_dni,null);
                if(cr.moveToFirst()==true){
                    cod_reporte=cr.getInt(0);
                    ly.setVisibility(View.VISIBLE);

                    asunto.setEnabled(false);
                    tipo_inv.setEnabled(false);
                    niv_inc.setEnabled(false);
                    findViewById(R.id.button18).setVisibility(View.GONE);
                    findViewById(R.id.textView5).setVisibility(View.GONE);
                    findViewById(R.id.rbg2).setVisibility(View.GONE);
                    findViewById(R.id.chksindatos).setVisibility(View.GONE);
                    findViewById(R.id.spn_placas).setVisibility(View.GONE);
                    findViewById(R.id.placaotro).setVisibility(View.GONE);
                    findViewById(R.id.marcaotro).setVisibility(View.GONE);
                    findViewById(R.id.modelootro).setVisibility(View.GONE);
                }
                Toast.makeText(getApplicationContext(), "Se grabo la cabecera del reporte " + cod_reporte, Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    //Habilitar y deshabilitar radio gruoups
    public void Habilitarg2(View view) {
        findViewById(R.id.textView5).setVisibility(View.VISIBLE);
        findViewById(R.id.rbg2).setVisibility(View.VISIBLE);
    }
    public void Deshabilitarg2(View view) {
        findViewById(R.id.textView5).setVisibility(View.GONE);
        findViewById(R.id.rbg2).setVisibility(View.GONE);
        findViewById(R.id.chksindatos).setVisibility(View.GONE);
        findViewById(R.id.spn_placas).setVisibility(View.GONE);
        findViewById(R.id.placaotro).setVisibility(View.GONE);
        findViewById(R.id.marcaotro).setVisibility(View.GONE);
        findViewById(R.id.modelootro).setVisibility(View.GONE);
    }

    public void clicmoto(View view) {
        findViewById(R.id.spn_placas).setVisibility(View.VISIBLE);
        findViewById(R.id.chksindatos).setVisibility(View.GONE);
        findViewById(R.id.placaotro).setVisibility(View.GONE);
        findViewById(R.id.marcaotro).setVisibility(View.GONE);
        findViewById(R.id.modelootro).setVisibility(View.GONE);
        consultarplacas();
        ArrayAdapter<CharSequence> adaptador3= new ArrayAdapter(this,android.R.layout.simple_spinner_item,listarplacas);
        placas_moto.setAdapter(adaptador3);
    }
    //metodo para llenar el spinner nivel incidente
    private void consultarplacas() {


        db= con.getWritableDatabase();
        Vehiculo clase_placas= null;
        placaslist= new ArrayList<Vehiculo>();
        //CONSULTA
        Cursor cursor=db.rawQuery("select placa_vehiculo,placa_vehiculo from " + Utilitario.TABLE_MOTOTAXI,null);

        while (cursor.moveToNext()){
            clase_placas=new Vehiculo();
            clase_placas.setPlaca(cursor.getString(0));
            clase_placas.setPlaca(cursor.getString(1));

            Log.i("id_plac",clase_placas.getPlaca());
            Log.i("desc_plac",clase_placas.getPlaca());

            placaslist.add(clase_placas);

        }
        llenarspinner3();
        db.close();
    }
    private void llenarspinner3() {
        listarplacas = new ArrayList<String>();
        listarplacas.add("Seleccione");
        for (int i = 0; i < placaslist.size(); i++) {
            listarplacas.add(placaslist.get(i).getPlaca());

        }
    }
    //metodos para visualizar los campos de vehiculos
    public void clicotroveh(View view) {
        findViewById(R.id.spn_placas).setVisibility(View.GONE);
        findViewById(R.id.chksindatos).setVisibility(View.VISIBLE);
        findViewById(R.id.placaotro).setVisibility(View.VISIBLE);
        findViewById(R.id.marcaotro).setVisibility(View.VISIBLE);
        findViewById(R.id.modelootro).setVisibility(View.VISIBLE);
    }
    public void sindatoschk(View view) {
        if(chk1.isChecked()==true){
            findViewById(R.id.placaotro).setVisibility(View.GONE);
            findViewById(R.id.marcaotro).setVisibility(View.GONE);
            findViewById(R.id.modelootro).setVisibility(View.GONE);
        }else{
            findViewById(R.id.placaotro).setVisibility(View.VISIBLE);
            findViewById(R.id.marcaotro).setVisibility(View.VISIBLE);
            findViewById(R.id.modelootro).setVisibility(View.VISIBLE);
        }

    }
    //para cuando se hace clic en el boton subir imagen
    public void subirimg(View view) {
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
            img.setImageURI(path);
            varbitmap= img.getDrawingCache();
            //cambiar el tipo de la variable
            varbitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
            varbitmap.compress(Bitmap.CompressFormat.PNG, 0 , baos);
            byteArray = baos.toByteArray();


            contimg=contimg+1;
        }
        switch (contimg){
            case 1:
                img1=byteArray;
                break;
            case 2:
                img2=byteArray;
                break;
            case 3:
                img3=byteArray;
                break;
            case 4:
                Toast.makeText(this, "Alcanzó la máxima cantidad de imágenes", Toast.LENGTH_SHORT).show();
                findViewById(R.id.button19).setEnabled(false);
                break;
        }

    }


    //método para grabar el cuerpo del reporte
    public void onClickReg(View view) {
        String descripreporte= descripcion.getText().toString();
        String placaotro2= placaotro.getText().toString();
        String modelootro2= modelootro.getText().toString();
        String marcaotro2= marcaotro.getText().toString();
        String lattext= Double.toString(latitud);
        String longtext=Double.toString(longitud);
        //SE AGREGA PARA CUANDO EL USUARIO NO HACE CLIC EN REGIUSTRAR UBICACION
        if(lattext.equals("0.0") && longtext.equals("0.0")){
            lattext="null";
            longtext="null";
        }

        String direcciongps=referencia.getText().toString();

        db= con.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilitario.CAMPO_DESC_REPORTE,descripreporte);
        values.put(Utilitario.CAMPO_IMAGEN_1,img1);
        values.put(Utilitario.CAMPO_IMAGEN_2,img2);
        values.put(Utilitario.CAMPO_IMAGEN_3,img3);
        values.put(Utilitario.CAMPO_LATITUD_GPS,lattext);
        values.put(Utilitario.CAMPO_LONGITUD_GPS,longtext);
        values.put(Utilitario.CAMPO_DIRECCION_GPS,direcciongps);
        if(!descripreporte.isEmpty() && !direcciongps.isEmpty()){


        if (rb2.isChecked() == true) {
            values.put(Utilitario.CAMPO_PLACA,"null");
            values.put(Utilitario.CAMPO_PLACA_OTRO,"null");
            values.put(Utilitario.CAMPO_MARCA_OTRO,"null");
            values.put(Utilitario.CAMPO_MODELO_OTRO,"null");
        }
        //si esta marcado la opcion moto seleccionamos el valor del spinner
        if(rb3.isChecked()==true) {
            int idcombo3 = (int) placas_moto.getSelectedItemId();
            if (idcombo3 != 0) {
                //valor de combo tipo incidente
                Log.i("TAMAÑO", placaslist.size() + "");
                Log.i("idcombo1", idcombo3 + "");
                Log.i("idcombo1 - 1", (idcombo3 - 1) + "");
                String idplaca = placaslist.get(idcombo3 - 1).getPlaca();
                Log.i("idcombo1", idplaca + "");
                values.put(Utilitario.CAMPO_PLACA, idplaca);
                values.put(Utilitario.CAMPO_PLACA_OTRO,"null");
                values.put(Utilitario.CAMPO_MARCA_OTRO,"null");
                values.put(Utilitario.CAMPO_MODELO_OTRO,"null");
            }else{
                Toast.makeText(this, "Debe seleccionar la placa de la moto", Toast.LENGTH_SHORT).show();
            }
        //si  se selecciona otro vehiculo se registran los datos basicos obligatorios
        }else if(rb4.isChecked()==true){
            if(chk1.isChecked()==true){
                values.put(Utilitario.CAMPO_PLACA,"null");
                values.put(Utilitario.CAMPO_PLACA_OTRO,"null");
                values.put(Utilitario.CAMPO_MARCA_OTRO,"null");
                values.put(Utilitario.CAMPO_MODELO_OTRO,"null");
            }else {
                if(!placaotro2.isEmpty()&&!modelootro2.isEmpty()&&!marcaotro2.isEmpty()){
                    values.put(Utilitario.CAMPO_PLACA,"null");
                    values.put(Utilitario.CAMPO_PLACA_OTRO,placaotro2);
                    values.put(Utilitario.CAMPO_MARCA_OTRO,marcaotro2);
                    values.put(Utilitario.CAMPO_MODELO_OTRO,modelootro2);
                }else{
                    Toast.makeText(this, "Ingresar los datos del vehiculo", Toast.LENGTH_SHORT).show();
                }
            }
        }
        Date fechaActual = new Date();
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        //String fecha=(String) formatoFecha.format(fechaActual);
        values.put(Utilitario.CAMPO_FECHA,formatoFecha.format(fechaActual));
        values.put(Utilitario.CAMPO_ESTADO_REPORTE,"Abierto");
        int resultado= db.update("REPORTEINCIDENTE",values,"cod_reporte="+cod_reporte,null);
            Intent ven2= new Intent(RegistrarIncidente.this,RegistrarIncidente.class);
            //ven2.putExtra("pasar_placa",var_placa);
            ven2.putExtra("pasar_usuario",dnipasado);
            startActivity(ven2);
        //db.rawQuery("INSERT INTO REPORTEINCIDENTE (imagen1,imagen2,imagen3) VALUES(?,?,?) WHERE cod_reporte="+34,null);

       // SQLiteStatement insert = db.compileStatement("UPDATE REPORTEINCIDENTE SET imagen1=?, imagen2=?,imagen3=? WHERE cod_reporte=34");
       // insert.clearBindings();
       // insert.bindBlob(1, img1);
       // insert.bindBlob(2, img2);
       // insert.bindBlob(3, img3);
        //insert.executeInsert();

        if (resultado==1){
            Toast.makeText(this,"Se grabo el reporte "+cod_reporte,Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this,"El reporte no existe ",Toast.LENGTH_SHORT).show();
        }

        }else{
            Toast.makeText(this,"Ingrese la descripcion y referencia del reporte",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


}
