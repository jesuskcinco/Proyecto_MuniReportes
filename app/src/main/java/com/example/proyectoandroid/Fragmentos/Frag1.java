package com.example.proyectoandroid.Fragmentos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoandroid.R;

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
        View view= inflater.inflate(R.layout.activity_registrar_nivelncidente, container, false);
        return inflater.inflate(R.layout.activity_registrar_nivelncidente, container, false);
    }

}
