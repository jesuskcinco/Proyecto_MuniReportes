package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.proyectoandroid.Entidades.ReportePie;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ReporteIncidentesPlaca extends AppCompatActivity {
    PieChart pieChart;
    BarChart barChart;
    ArrayList<PieEntry> pieEntries;
    ArrayList<String> listameses;
    ArrayList<String> listareporte;
    ArrayList<ReportePie> reportePies;
    ArrayList<ReportePie> reportePies2;
    GlobalVariables globalVariables;
    Spinner spnmeses;
    SQLiteDatabase db;
    ConexionSQLiteHelper con;
    ReportePie clasetipopie2;
    String valorcombo;
    int idcombo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_incidentes_placa);
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);

        barChart=findViewById(R.id.barChart);
        spnmeses=findViewById(R.id.spnmeses2);
        creararreglos();
        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,listameses);
        spnmeses.setAdapter(adaptador2);
        creararreglo2();
        spnmeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                idcombo = (int) spnmeses.getSelectedItemId();
                if (idcombo != 0) {
                    //valor de combo tipo incidente
                    Log.i("id", reportePies.size() + "");
                    Log.i("desc", idcombo + "");
                    Log.i("desc - 1", (idcombo - 1) + "");
                    Log.i("desc - 1", reportePies.get(idcombo - 1).getNummes()+ "");
                    valorcombo=reportePies.get(idcombo - 1).getNummes();

                    creararreglo2();
                    //Toast.makeText(getApplicationContext(), "Se grabo la cabecera del reporte " + idtipoincidente, Toast.LENGTH_LONG).show();
                }else{
                    //valorcombo=reportePies.get(idcombo-1).getNummes();
                    creararreglo2();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        createCharts();
    }
    private void creararreglos() {
        db= con.getWritableDatabase();
        ReportePie clasetipopie= null;
        reportePies= new ArrayList<ReportePie>();
        //CONSULTA
        Cursor cursor=db.rawQuery("select   distinct(case  (substr( (fecha_reporte), -7,2 )) " +
                "when '01' then 'Enero' " +
                "when '02' then 'Febrero' " +
                "when '03' then 'Marzo' " +
                "when '04' then 'Abril' " +
                "when '05' then 'Mayo' " +
                "when '06' then 'Junio' " +
                "when '07' then 'Julio' " +
                "when '08' then 'Agosto' " +
                "when '09' then 'Septiembre' " +
                "when '10' then 'Octubre' " +
                "when '11' then 'Noviembre' " +
                "when '12' then 'Diciembre' " +
                "end )as mes ,substr( (fecha_reporte), -7,2 ) " +
                "from REPORTEINCIDENTE",null);

        while (cursor.moveToNext()){
            clasetipopie=new ReportePie();
            clasetipopie.setNommes(cursor.getString(0));
            clasetipopie.setNummes(cursor.getString(1));

            reportePies.add(clasetipopie);

        }

        llenarspinnermeses();
        db.close();
    }
    private void llenarspinnermeses() {
        listameses =new ArrayList<String>();

        listameses.add("Todos los meses");


        for(int i=0;i<reportePies.size();i++){
            listameses.add(reportePies.get(i).getNommes());

        }
    }
    private void creararreglo2() {
        db= con.getWritableDatabase();
        idcombo = (int) spnmeses.getSelectedItemId();

        reportePies2= new ArrayList<ReportePie>();
        Cursor cursor2=null;
        clasetipopie2= null;
        if(idcombo==0){
            cursor2=db.rawQuery("select (case placa_vehiculo when 'null' then 'Reportes sin placa' else placa_vehiculo end) as placa, count(*) from REPORTEINCIDENTE\n" +
                    "group by placa_vehiculo ",null);
        }else {
            //valorcombo=reportePies.get(idcombo-1).getNummes();
            cursor2=db.rawQuery("select (case placa_vehiculo when 'null' then 'Reportes sin placa' else placa_vehiculo end) as placa, count(*) from REPORTEINCIDENTE " +
                    "where substr( (fecha_reporte), -7,2) ='"+valorcombo+"' " +
                    "group by placa_vehiculo ",null);
        }


        while (cursor2.moveToNext()){
            clasetipopie2=new ReportePie();
            clasetipopie2.setCodigotipo(cursor2.getString(0));
            //clasetipopie2.setDescripciontipo(cursor2.getString(1));
            clasetipopie2.setCantidad(cursor2.getInt(1));
            reportePies2.add(clasetipopie2);

        }
        db.close();
        createCharts();
    }
    public void createCharts(){
        Description description=new Description();
        description.setText("Gráfico de Vehiculos en incidente por Mes");
        description.setEnabled(true);
        description.setTextSize(14);
        barChart.setDescription(description);
        legend(barChart);
        barChart.animateY(3000);
        //barChart=(BarChart)getSameChart(barChart, Color.RED,Color.CYAN,5000);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(true);
        barChart.setData(getBarData());
        barChart.invalidate();
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRigth(barChart.getAxisRight());
    }
    private void axisX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //for(int i=0;i<reportePies2.size();i++){
        //  axis.setValueFormatter(new IAxisValueFormatter(reportePies2.get(i).setDescripciontipo()) {
        //    @Override
        //  public String getFormattedValue(float value, AxisBase axis) {
        //    return null;
        //}
        //});
        //}
    }
    private void axisLeft(YAxis axis){
        axis.setSpaceTop(30);
        axis.setAxisMinimum(0);
    }
    private void axisRigth(YAxis axis){
        axis.setEnabled(false);
    }

    private void legend(Chart chart){
        Legend legend=chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries =new ArrayList<>();
        for(int i=0;i<reportePies2.size();i++){
            LegendEntry entry=new LegendEntry();
            entry.formColor= ColorTemplate.COLORFUL_COLORS[i];
            entry.label=reportePies2.get(i).getCodigotipo();
            entries.add(entry);
        }
        legend.setCustom(entries);
    }
    private BarData getBarData(){
        BarDataSet barDataSet=(BarDataSet) getData(new BarDataSet(getBarEntries(),""));
        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData=new BarData(barDataSet);
        barData.setBarWidth(0.45f);

        return barData;
    }
    private DataSet getData(DataSet dataSet){
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private ArrayList<BarEntry> getBarEntries(){
        ArrayList<BarEntry> entries= new ArrayList<>();
        for(int i=0;i<reportePies2.size();i++){
            entries.add(new BarEntry(i,reportePies2.get(i).getCantidad()));
        }
        return entries;
    }
}
