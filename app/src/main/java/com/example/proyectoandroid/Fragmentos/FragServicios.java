package com.example.proyectoandroid.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.proyectoandroid.R;

public class FragServicios extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstancestate){
        return  inflater.inflate(R.layout.fragment_gallery,container,false);
    }
}
