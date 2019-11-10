package com.example.proyectoandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoandroid.Entidades.Mensajes;
import com.example.proyectoandroid.Entidades.ReporteIncidente;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.example.proyectoandroid.Utilidades.Utilitario;

import java.util.ArrayList;

public class AceptarConductor extends AppCompatActivity {
    GlobalVariables globalVariables;
    String cor_reportedet,dni,flgact,placa,dnimsj;
    Bundle datos;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    ArrayList<Mensajes> listarmensajeacep;
    TextView asunto,msj,fecha;
    Button aceptar,rechazar;
    String tipo,idregistro;
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
        aceptar=findViewById(R.id.button23);
        rechazar=findViewById(R.id.button22);
        cargardatos();
        validarsolicitud();
    }

    private void validarsolicitud() {
        db= con.getWritableDatabase();

        Cursor cursor0=null;
        cursor0= db.rawQuery("select tipo_mensaje from MENSAJES m where m.cod_mensaje='"+cor_reportedet+"'",null);
        while (cursor0.moveToNext()){
            tipo=cursor0.getString(0);
            if (tipo.equals("Moto-Conductor")){
                Cursor cursor=null;
                cursor= db.rawQuery("select c.ok_duenio from MENSAJES m  inner join CONDUCTORMOTO c on c.dni_usuario=m.dni_usuario and m.placa_vehiculo=c.placa_vehiculo " +
                        "where m.cod_mensaje='"+cor_reportedet+"'",null);

                while (cursor.moveToNext()){
                    if (cursor.getString(0).equals("0") ){
                        aceptar.setVisibility(View.VISIBLE);
                        rechazar.setVisibility(View.VISIBLE);
                    }else{
                        aceptar.setVisibility(View.GONE);
                        rechazar.setVisibility(View.GONE);
                        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                        dialogo1.setTitle("Aviso");
                        if (cursor.getString(0).equals("1")){
                            dialogo1.setMessage("Usted ya aceptó la solicitud del usuario");
                        }else {
                            dialogo1.setMessage("Usted ya rechazó la solicitud del usuario");
                        }

                        dialogo1.setCancelable(false);
                        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                                //aceptar();
                            }
                        });
                        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                                //cancelar();
                            }
                        });
                        dialogo1.show();
                    }
                }
            }else  if(tipo.equals("Moto-Sociedad")){
                Cursor cursor2=null;
                cursor2= db.rawQuery("select c.idregistrosociedad,c.estado_sociedad from MENSAJES m  inner join USUARIOSOCIEDAD c on c.dni_usuario=m.dni_usuario " +
                        "and m.placa_vehiculo=c.placa_vehiculo where m.cod_mensaje='"+cor_reportedet+"'",null);
                while (cursor2.moveToNext()){
                    idregistro=cursor2.getString(0);
                    if (cursor2.getString(1).equals("Pendiente") ){
                        aceptar.setVisibility(View.VISIBLE);
                        rechazar.setVisibility(View.VISIBLE);
                    }else{
                        aceptar.setVisibility(View.GONE);
                        rechazar.setVisibility(View.GONE);
                        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                        dialogo1.setTitle("Aviso");
                        if (cursor2.getString(0).equals("Aceptado")){
                            dialogo1.setMessage("Usted ya aceptó la solicitud del usuario");
                        }else {
                            dialogo1.setMessage("Usted ya rechazó la solicitud del usuario");
                        }

                        dialogo1.setCancelable(false);
                        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                                //aceptar();
                            }
                        });
                        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                                //cancelar();
                            }
                        });
                        dialogo1.show();
                    }
                }
            }

        }

        db.close();
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


            asunto.setText("Solicitud de vehiculo "+listarmensajeacep.get(i).getPlaca());
            fecha.setText(""+listarmensajeacep.get(i).getFecha());
            msj.setText(""+listarmensajeacep.get(i).getMensaje_not());
            dnimsj=listarmensajeacep.get(i).getDni_mensaje().toString();
            placa=listarmensajeacep.get(i).getPlaca();
        }
    }

    public void OnClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){

            case R.id.button23:
                flgact="SI";
                funcionactualizar();
                break;
            case R.id.button22:
                flgact="NO";
                funcionactualizar();
                break;
            case R.id.button25:
                miIntent= new Intent(AceptarConductor.this,BandejaMensajes.class);
                //startActivity(cancelar);
                break;
        }
        if (miIntent!=null) startActivity(miIntent);
    }


    private void funcionactualizar() {

        if(flgact.equals("SI")){
            if(tipo.equals("Moto-Conductor")){
                db= con.getWritableDatabase();
                ContentValues values= new ContentValues();
                ContentValues values2= new ContentValues();
                values.put(Utilitario.CAMPO_ESTADO_MOTO_COND,"Activo");
                values.put(Utilitario.CAMPO_OK_DUENIO,"1");
                values2.put(Utilitario.CAMPO_FLGCONDUCTOR,"1");
                int resultado= db.update("CONDUCTORMOTO",values,"dni_usuario='"+dnimsj+"' and placa_vehiculo='"+placa+"' and estado_moto_cond='Inactivo'" ,null);
                int resultado2= db.update("USUARIO",values2,"dni_usuario='"+dnimsj+"'" ,null);

                if (resultado==1 && resultado2==1){
                    Toast.makeText(this,"Se aceptó al usuario como conductor de su vehiculo",Toast.LENGTH_SHORT).show();
                    Intent miIntent=null;
                    miIntent= new Intent(AceptarConductor.this,BandejaMensajes.class);
                    startActivity(miIntent);
                }else {
                    Toast.makeText(this,"No se actualizo correctamente intente de nuevo",Toast.LENGTH_SHORT).show();
                }
                db.close();
            }else if (tipo.equals("Moto-Sociedad")){
                db= con.getWritableDatabase();
                ContentValues values= new ContentValues();
                values.put(Utilitario.CAMPO_ESTADO_SOCIEDAD2,"Activo");
                int resultado= db.update("USUARIOSOCIEDAD",values,"idregistrosociedad='"+idregistro+"'" ,null);


                if (resultado==1){
                    Toast.makeText(this,"Se aceptó el vehiculo como integrante de su sociedad",Toast.LENGTH_SHORT).show();
                    Intent miIntent=null;
                    miIntent= new Intent(AceptarConductor.this,BandejaMensajes.class);
                    startActivity(miIntent);
                }else {
                    Toast.makeText(this,"No se actualizo correctamente intente de nuevo",Toast.LENGTH_SHORT).show();
                }
                db.close();
            }


        }else {
            db= con.getWritableDatabase();
            ContentValues values= new ContentValues();
            ContentValues values2= new ContentValues();
            values.put(Utilitario.CAMPO_ESTADO_MOTO_COND,"Rechazado");
            values.put(Utilitario.CAMPO_OK_DUENIO,"2");
            int resultado= db.update("CONDUCTORMOTO",values,"dni_usuario='"+dnimsj+"' and placa_vehiculo='"+placa+"' and estado_moto_cond='Inactivo'" ,null);

            if (resultado==1 ){
                Toast.makeText(this,"Se rechazo la solicitud del usuario",Toast.LENGTH_SHORT).show();
                Intent miIntent=null;
                miIntent= new Intent(AceptarConductor.this,BandejaMensajes.class);
                startActivity(miIntent);
            }else {
                Toast.makeText(this,"No se actualizo correctamente intente de nuevo",Toast.LENGTH_SHORT).show();
            }
            db.close();
        }


    }
}
