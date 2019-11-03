package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.proyectoandroid.Entidades.TipoIncidente;
import com.example.proyectoandroid.Entidades.Vehiculo;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.ArrayList;

public class RegistrarTipoIncidente extends AppCompatActivity {
    Bundle datos2;
    EditText descripcion,estado,desbus;
    String dni;
    RadioButton c1,c2;
    ConexionSQLiteHelper con,con2;
    ListView listviewtipoincidentes;
    ArrayList<String> listarinformacion;
    ArrayList<TipoIncidente> listartiposincidentes;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tipo_incidente);

        descripcion= (EditText) findViewById(R.id.edtdestipinc);
        //estado=(EditText) findViewById(R.id.txt_correo);
        desbus= (EditText) findViewById(R.id.edtbustipo);

        c1=(RadioButton) findViewById(R.id.rbtactivo);
        c2=(RadioButton) findViewById(R.id.rbtinactivo);

        datos2 = getIntent().getExtras();
        dni=datos2.getString("pasar_usuario");
        if (dni.equals("")){
            dni="70546327";
        }
        //CODIGO PARA LISTAR LOS INCIDENTES
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        listviewtipoincidentes= (ListView) findViewById(R.id.listviewtincidentes);
        consultarlistatipoincidentes();
        final ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listarinformacion);
        listviewtipoincidentes.setAdapter(adaptador);

        desbus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                adaptador.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void consultarlistatipoincidentes() {
        db= con.getWritableDatabase();
        TipoIncidente tipoinc= null;
        listartiposincidentes =new ArrayList<TipoIncidente>();
        Cursor cursor= db.rawQuery("select * from "+ Utilitario.TABLE_TIPO_INCIDENTE,null);

        while (cursor.moveToNext()){
            tipoinc= new TipoIncidente();
            tipoinc.setCodigo(cursor.getInt(0));
            tipoinc.setDescripcion(cursor.getString(1));
            tipoinc.setEstado(cursor.getString(2));
            tipoinc.setDniregistra(cursor.getString(3));

            listartiposincidentes.add(tipoinc);
        }
        obtenerinformacion();
        db.close();
    }
    private void obtenerinformacion(){
        listarinformacion= new ArrayList<String>();
        for(int i=0;i<listartiposincidentes.size();i++){
            listarinformacion.add(listartiposincidentes.get(i).getCodigo()+" - "+listartiposincidentes.get(i).getDescripcion());
        }
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.button9:
                registrartipoincidente();
                break;
            case R.id.button10:
                miIntent= new Intent(RegistrarTipoIncidente.this, MainActivity.class);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }

    private void registrartipoincidente() {
        String desc_tipo = descripcion.getText().toString();
        String usuario= dni;
        String estado2="";
        if(c1.isChecked()==true){
            estado2="Activo";
        }else if (c2.isChecked()==true){
            estado2="Inactivo";
        }
        con2= new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        db= con2.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (!desc_tipo.isEmpty() && !estado2.isEmpty()) {
            Cursor cursor2=db.rawQuery("select tipo_incidente from TIPOINCIDENTE where tipo_incidente='"+desc_tipo+"'",null);
            if(cursor2.moveToFirst()){
                Toast.makeText(getApplicationContext(), "El incidente con "+desc_tipo+" ya ha sido registrado", Toast.LENGTH_SHORT).show();

                //validar="";
                descripcion.setText("");
                db.close();

            }else {

                //values.put(Utilitario.CAMPO_CODIGO, codigo_tipo);
                values.put(Utilitario.CAMPO_DESCRIPCION, desc_tipo);
                values.put(Utilitario.CAMPO_ESTADO, estado2);
                values.put(Utilitario.CAMPO_DNI_REG, usuario);

                Long idResultante = db.insert(Utilitario.TABLE_TIPO_INCIDENTE, Utilitario.CAMPO_CODIGO, values);

                Toast.makeText(getApplicationContext(), "Registro exitoso " + idResultante, Toast.LENGTH_LONG).show();

                Intent ven2= new Intent(RegistrarTipoIncidente.this,RegistrarTipoIncidente.class);
                ven2.putExtra("pasar_usuario",usuario);
                startActivity(ven2);


                    descripcion.setText("");

            }

        }else {
            Toast.makeText(this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }}
