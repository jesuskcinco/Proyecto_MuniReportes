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
public class FragNivelIncidente extends Fragment {


    public FragNivelIncidente() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_nivel_incidente, container, false);
    }

}
