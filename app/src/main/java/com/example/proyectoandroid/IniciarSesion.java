package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.sql.SQLClientInfoException;

public class IniciarSesion extends AppCompatActivity {
    GlobalVariables globalVariables;
    EditText edclave,eddni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        eddni= (EditText) findViewById(R.id.eddni1);
        edclave= (EditText) findViewById(R.id.edclave1);
        findViewById(R.id.btnolvidar).setVisibility(View.GONE);
    }
    public void onClick7 (View view){
        Intent miIntent=null;
        switch (view.getId()){

            case R.id.button5:
                logueo();
                break;
            case R.id.btncrear:
                miIntent= new Intent(IniciarSesion.this, RegistrarUsuario.class);
                //miIntent.putExtra("pasar_usuario",dnipasado);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);

    }
    public void logueo(){
        ConexionSQLiteHelper cn= new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);
        SQLiteDatabase bd= cn.getWritableDatabase();

        String dni= eddni.getText().toString();
        String clave=edclave.getText().toString();

        if (!dni.isEmpty() && !clave.isEmpty()){
            Cursor cr= bd.rawQuery("SELECT dni_usuario,clave_usuario,nombres_usuario from USUARIO WHERE dni_usuario='"+dni+ "' and " +
                    "clave_usuario='"+clave+"'",null);
            if(cr.moveToFirst()==true){
                String dnibd=cr.getString(0);
                String clavebd=cr.getString(1);
                String nomusu=cr.getString(2);

                if (dni.equals(dnibd) && clave.equals(clavebd)){
                    Intent ven= new Intent(this,MenuPrincipal.class);
                    globalVariables = (GlobalVariables)getApplicationContext();
                    globalVariables.setDni(dnibd);
                    globalVariables.setNomusu(nomusu);
                    ven.putExtra("pasar_usuario",dnibd);
                    ven.putExtra("pasar_clave",clavebd);
                    startActivity(ven);
                    eddni.setText("");
                    edclave.setText("");
                }else{
                    eddni.setText("");
                    edclave.setText("");
                    Toast.makeText(this,"El DNI o clave son incorrectos",Toast.LENGTH_SHORT).show();

                }
            }else {
                eddni.setText("");
                edclave.setText("");
                Toast.makeText(this,"El usuario no existe",Toast.LENGTH_SHORT).show();

            }
        }else {
            Toast.makeText(this,"Debe ingresar el DNI y Clave",Toast.LENGTH_SHORT).show();

        }
        bd.close();
    }
    public void onClick8 (View view){
        String dni2= "";
        String clave2="";
        Intent ven2= new Intent(IniciarSesion.this,MainActivity.class);
        globalVariables = (GlobalVariables)getApplicationContext();
        globalVariables.setDni("70546327");
        //ven2.putExtra("pasar_usuario",dni2);
        //ven2.putExtra("pasar_clave",clave2);
        startActivity(ven2);

    }
}
