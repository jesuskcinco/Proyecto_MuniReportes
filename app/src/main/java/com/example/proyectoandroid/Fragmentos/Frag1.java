package com.example.proyectoandroid.Fragmentos;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoandroid.MainActivity;
import com.example.proyectoandroid.R;
import com.example.proyectoandroid.Utilidades.GlobalVariables;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frag1 extends Fragment {


    public Frag1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //EN ESTAS LINEAS DE CODIGO SE REALIZA EL INTEN PARA ABRIR LA VENTANA PRINCIPAL
        View view= inflater.inflate(R.layout.fragment_frag1, container, false);
        Intent ven= new Intent(getActivity(), MainActivity.class);
        //globalVariables = (GlobalVariables)getApplicationContext();
        //globalVariables.setDni(dnibd);
        //ven.putExtra("pasar_usuario",dnibd);
        //ven.putExtra("pasar_clave",clavebd);
        startActivity(ven);
        return inflater.inflate(R.layout.fragment_frag1, container, false);
    }

}
