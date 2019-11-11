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

import com.example.proyectoandroid.Entidades.RegistroaSociedad;
import com.example.proyectoandroid.Entidades.ReportePie;
import com.example.proyectoandroid.Utilidades.GlobalVariables;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.maps.model.Circle;

import java.util.ArrayList;

public class ReporteVehiculos extends AppCompatActivity {
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
        setContentView(R.layout.activity_reporte_vehiculos);
        con=new ConexionSQLiteHelper(this,"bd_aplicativo",null,1);

        pieChart=findViewById(R.id.graficopastel);
        spnmeses=findViewById(R.id.spnmeses);

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
        //creargraficopastel();
        createCharts2();
    }
//CREA ARREGLO PARA EL TIPO INCIDENTE Y SU CANTIDAD
    private void creararreglo2() {
        db= con.getWritableDatabase();
        idcombo = (int) spnmeses.getSelectedItemId();

        reportePies2= new ArrayList<ReportePie>();
        Cursor cursor2=null;
        clasetipopie2= null;
        if(idcombo==0){
            cursor2=db.rawQuery("SELECT a.codigo_incidente,b.tipo_incidente,count(*)  FROM REPORTEINCIDENTE a " +
                    "INNER JOIN TIPOINCIDENTE b on a.codigo_incidente=b.codigo_incidente " +
                    "group by a.codigo_incidente,b.tipo_incidente",null);
        }else {
            //valorcombo=reportePies.get(idcombo-1).getNummes();
            cursor2=db.rawQuery("SELECT a.codigo_incidente,b.tipo_incidente,count(*)  FROM REPORTEINCIDENTE a " +
                    "INNER JOIN TIPOINCIDENTE b on a.codigo_incidente=b.codigo_incidente" +
                    " where substr( (fecha_reporte), -7,2) ='"+valorcombo+"' " +
                    "group by a.codigo_incidente,b.tipo_incidente",null);
        }


        while (cursor2.moveToNext()){
            clasetipopie2=new ReportePie();
            clasetipopie2.setCodigotipo(cursor2.getString(0));
            clasetipopie2.setDescripciontipo(cursor2.getString(1));
            clasetipopie2.setCantidad(cursor2.getInt(2));
            reportePies2.add(clasetipopie2);

        }
        db.close();
        createCharts2();
    }
//CREA ARREGLO PARA OBTENER LOS MESES DE CADA INCIDENTE
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

    private void creargraficopastel() {


        Description description=new Description();
        description.setText("Grásico de Vehiculos en incidente por Mes");
        description.setEnabled(true);

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
//FORMATO AL CHART---LLAMA A LEGEND
    private Chart getSameChart (Chart chart, int textColor, int background, int animateY){

        //Description descripcion=new Description();
        //descripcion.setText("Grásico de Vehiculos en incidente por Mes");
        //descripcion.setEnabled(true);

        //chart.getDescription().setText(description);
        //chart.setDescription(descripcion);
        //chart.getDescription().setTextSize(15);
        //chart.setBackgroundColor(background);
        //chart.animateY(animateY);
        //legend(chart);
        return chart;
    }
    //AGREGAR LEYENDA AL CHART
    private void legend(Chart chart){
        Legend legend=chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries =new ArrayList<>();
        for(int i=0;i<reportePies2.size();i++){
            LegendEntry entry=new LegendEntry();
            entry.formColor=ColorTemplate.COLORFUL_COLORS[i];
            entry.label=reportePies2.get(i).getDescripciontipo();
            entries.add(entry);
        }
        legend.setCustom(entries);
    }
//Se agregan los valores del eje y
    private ArrayList<BarEntry> getBarEntries(){
        ArrayList<BarEntry> entries= new ArrayList<>();
        for(int i=0;i<reportePies2.size();i++){
            entries.add(new BarEntry(i,reportePies2.get(i).getCantidad()));
        }
        return entries;
    }
    //Se agregan los porcentajes al pie
    private ArrayList<PieEntry> getPieEntries(){
        ArrayList<PieEntry> entries= new ArrayList<>();
        for(int i=0;i<reportePies2.size();i++){
            entries.add(new PieEntry(reportePies2.get(i).getCantidad()));
        }
        return entries;
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
    //LLENAR GRAFICO BARRA
    public void createCharts(){

        //barChart=(BarChart)getSameChart(barChart, Color.RED,Color.CYAN,5000);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(true);
        barChart.setData(getBarData());
        barChart.invalidate();
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRigth(barChart.getAxisRight());
    }
    //LLENA GRAFICO BARRA
    public void createCharts2(){

        //pieChart=(PieChart) getSameChart(barChart, Color.RED,Color.CYAN,5000);
        //Description descripcion=new Description();
        //descripcion.setText("Grásico de Vehiculos en incidente por Mes");
        //descripcion.setEnabled(true);

        //chart.getDescription().setText(description);
        //pieChart.setDescription(descripcion);
        Description description=new Description();
        description.setText("Gráfico de Vehiculos en incidente por Mes");
        description.setEnabled(true);
        description.setTextSize(14);
        pieChart.setDescription(description);
        legend(pieChart);

        //pieChart.setBackgroundColor(background);
        pieChart.animateY(3000);


        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData());
        pieChart.invalidate();
    }

    //dar formato a los graficos
    private DataSet getData(DataSet dataSet){
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;
    }
    //LLENAR EL GRAFICO BARRA CON DATA
    private BarData getBarData(){
        BarDataSet barDataSet=(BarDataSet) getData(new BarDataSet(getBarEntries(),""));
        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData=new BarData(barDataSet);
        barData.setBarWidth(0.45f);

        return barData;
    }
    //LLENAR EL GRAFICO PIE CON DATA
    private PieData getPieData(){
        PieDataSet pieDataSet=(PieDataSet) getData(new PieDataSet(getPieEntries(),""));
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }
}
