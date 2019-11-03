package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.Entidades.NivelIncidente;
import com.example.proyectoandroid.Entidades.TipoIncidente;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.ArrayList;

public class RegistrarNivelncidente extends AppCompatActivity {
    Bundle datos;
    Spinner combonivel;
    EditText descripcion,busnivel;
    ArrayList<String> listaniveles;
    ConexionSQLiteHelper con;
    String validar,usupas,dniobt;
    SQLiteDatabase db;
    GlobalVariables globalVariables;
    ListView listviewniveles;
    ArrayList<String> listarinformacion;
    ArrayList<NivelIncidente> listarnivelincidentes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_nivelncidente);
        descripcion= findViewById(R.id.edt_desnivinc);
        busnivel= (EditText) findViewById(R.id.edtbuscarnivel);
        //dniobt= datos.getString("pasar_usuario");
        globalVariables = (GlobalVariables)getApplicationContext();
        dniobt= globalVariables.getDni();
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        listviewniveles= (ListView) findViewById(R.id.listviewnivelinc);
        listarnivelincidentes();
        final ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listarinformacion);
        listviewniveles.setAdapter(adaptador);
        listviewniveles.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                db=con.getWritableDatabase();
                String query="delete from NIVELINCIDENTE where idnivinc='"+listarnivelincidentes.get(i).getIdnivinc()+"'";
                db.execSQL(query);
                db.close();
                Intent recarga= new Intent(RegistrarNivelncidente.this,RegistrarNivelncidente.class);

                startActivity(recarga);
                Toast.makeText(getApplicationContext(),"Se elimino",Toast.LENGTH_LONG).show();
                finish();

                return false;
            }});

        busnivel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptador.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    public void OnClick(View view) {

        Intent miIntent=null;
        switch (view.getId()){

            case R.id.button12:
                registrarnivelincidente();
                break;
            case R.id.button13:
                miIntent= new Intent(RegistrarNivelncidente.this, MainActivity.class);
                miIntent.putExtra("pasar_usuario",dniobt);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }

    private void registrarnivelincidente() {
        String descnivinc=descripcion.getText().toString();
        con= new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        db= con.getWritableDatabase();

        if (!descnivinc.isEmpty()){
            Cursor cursor2=db.rawQuery("select desc_niv_inc from NIVELINCIDENTE where desc_niv_inc='"+descnivinc+"'",null);
            ContentValues values = new ContentValues();
            if(!cursor2.moveToFirst()) {
                values.put(Utilitario.CAMPO_DESC_NIVEL_INC, descnivinc);
                Long idResultante = db.insert(Utilitario.TABLE_NIVEL_INCIDENTE, Utilitario.CAMPO_ID_NIV_INC, values);
                Toast.makeText(getApplicationContext(), "Registro exitoso " + idResultante, Toast.LENGTH_LONG).show();
                descripcion.setText("");
                Intent ven2= new Intent(RegistrarNivelncidente.this,RegistrarNivelncidente.class);
                startActivity(ven2);
            }else{
                Toast.makeText(this,"El nivel de incidente ya ha sido registrado",Toast.LENGTH_SHORT).show();

            }
        }else {
            Toast.makeText(this,"Debe ingresar la descripión",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


    private void listarnivelincidentes() {
        db= con.getWritableDatabase();
        NivelIncidente tipoinc= null;
        listarnivelincidentes =new ArrayList<NivelIncidente>();
        Cursor cursor= db.rawQuery("select * from "+ Utilitario.TABLE_NIVEL_INCIDENTE,null);

        while (cursor.moveToNext()){
            tipoinc= new NivelIncidente();
            tipoinc.setIdnivinc(cursor.getInt(0));
            tipoinc.setDescripcion(cursor.getString(1));

            listarnivelincidentes.add(tipoinc);
        }
        obtenerinformacion();
        db.close();
    }

    private void obtenerinformacion() {
        listarinformacion= new ArrayList<String>();
        for(int i=0;i<listarnivelincidentes.size();i++){
            listarinformacion.add(listarnivelincidentes.get(i).getIdnivinc()+" - "+listarnivelincidentes.get(i).getDescripcion());
        }
    }
}
