package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proyectoandroid.Entidades.ReportePie;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ReporteVehiculos extends AppCompatActivity {
    PieChart pieChart;
    ArrayList<PieEntry> pieEntries;
    ArrayList<ReportePie> reportePies;
    GlobalVariables globalVariables;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_vehiculos);
        pieChart=findViewById(R.id.graficopastel);
        creargraficopastel();
        creararreglo();
    }

    private void creararreglo() {
    }

    private void creargraficopastel() {
        Description description=new Description();
        description.setText("Grásico de Vehiculos en incidente por Mes");
        description.setTextSize(14);
        pieChart.setDescription(description);
        pieEntries=new ArrayList<>();

        pieEntries.add(new PieEntry(1,6));
        pieEntries.add(new PieEntry(3,1));
        pieEntries.add(new PieEntry(4,4));

        PieDataSet pieDataSet=new PieDataSet(pieEntries,"Texto descripc");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
    }
}
