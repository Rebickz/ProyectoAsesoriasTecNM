package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DashboardEstadisticasActivity extends AppCompatActivity {

    //Inicializar variable
    //DrawerLayout drawerLayoutP;

    FirebaseDatabase database;
    DatabaseReference mref;
    DatabaseReference mrefi;
    DatabaseReference mRefRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_estadisticas);

        //Asignar la variable drawerLayout
        //drawerLayoutP = findViewById(R.id.profesor_drawer_layout);

        database = FirebaseDatabase.getInstance();
        mref = database.getReference().child("Analytics").child("Usr");
        mrefi = database.getReference().child("Analytics").child("dailyUser");
        mRefRates = database.getReference().child("tablaRating");
        int[] r = new int[5];




        LineChart ChartInt = (LineChart) findViewById(R.id.chartInt);
        HorizontalBarChart barChart = (HorizontalBarChart) findViewById(R.id.chartRates);

        List<Long> entIntl =  new ArrayList<>();
        List<Entry> entInt =  new ArrayList<>();
        List<BarEntry> rates = new ArrayList<>();


        ValueEventListener LoadRates = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                rates.clear();
                for(DataSnapshot rts:snapshot.getChildren()){
                    int usrRate = Integer.parseInt(rts.child("raten").getValue().toString());
                    Toast.makeText(DashboardEstadisticasActivity.this,Integer.toString(usrRate),Toast.LENGTH_LONG);
                    switch (usrRate){
                        case 1:
                            r[0]++;
                            break;
                        case 2:
                            r[1]++;
                            break;
                        case 3:
                            r[2]++;
                            break;
                        case 4:
                            r[3]++;
                            break;
                        case 5:
                            r[4]++;
                            break;
                    }
                    for(int i=0;i<r.length;i++){
                        rates.add(new BarEntry((i+1),r[i]));
                    }
                    BarDataSet barras = new BarDataSet(rates,"Rates");
                    BarData data = new BarData(barras);
                    barChart.setData(data);
                    barChart.invalidate();
                    XAxis xAxis = barChart.getXAxis();
                    YAxis leftAxis = barChart.getAxisLeft();
                    YAxis rightAxis = barChart.getAxisRight();
                    rightAxis.setEnabled(false);
                    xAxis.setEnabled(true);
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setDrawGridLines(false);
                    xAxis.setAxisMinimum((float) 0.5);
                    xAxis.setAxisMaximum((float) 5.5);
                    xAxis.setLabelCount(5);
                    leftAxis.setEnabled(false);
                    barChart.setDrawValueAboveBar(true);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        };


        ValueEventListener LoadInt = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    entIntl.clear();
                    entInt.clear();
                    for (DataSnapshot usr : snapshot.getChildren()) {
                        entIntl.add((Long) usr.getValue());
                    }
                    for (int i = 0; i < entIntl.size(); i++) {
                        entInt.add(new Entry(i, entIntl.get(i)));
                    }
                    LineDataSet datasetInt = new LineDataSet(entInt, "Usuarios");
                    datasetInt.setColor(R.color.purple_700);
                    datasetInt.setAxisDependency(YAxis.AxisDependency.LEFT);
                    final String[] dates = new String[]{ "22 Jul", "23 Jul", "24 Jul", "25 Jul", "26 Jul", "27 Jul", "28 Jul"};
                    ValueFormatter formatter = new ValueFormatter() {
                        @Override
                        public String getAxisLabel(float value, AxisBase axis) {
                            return dates[(int) value];
                        }
                    };
                    XAxis xAxis = ChartInt.getXAxis();
                    xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                    xAxis.setValueFormatter(formatter);

                    LineData data = new LineData(datasetInt);
                    data.setDrawValues(true);
                    Description des = new Description();
                    des.setText("Sesiones iniciadas por dia");
                    ChartInt.setDescription(des);
                    ChartInt.setData(data);
                    ChartInt.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        };
        mrefi.addValueEventListener(LoadInt);
        mRefRates.addValueEventListener(LoadRates);


    }

    /*public void ClickMenuP(View view){
        //Abrir Drawer
        ProfesorMenuActivity.openDrawer(drawerLayoutP);
    }

    public void ClickLogoP(View view){
        //Cerrar drawer
        ProfesorMenuActivity.closeDrawer(drawerLayoutP);
    }

    //Opciones del menu
    public void ClickHomeP(View view){
        //Recrear la activity
        ProfesorMenuActivity.redirectActivity(this, ProfesorMenuActivity.class);
    }
    public void ClickPerfilP(View view){
        //Redireccionar
        ProfesorMenuActivity.redirectActivity(this, ProfesorProfileActivity.class);
        //Toast.makeText(this, "Boton Perfil Profesor", Toast.LENGTH_LONG).show();

    }
    public void ClickDashboardP(View view){
        //Redireccionar
        ProfesorMenuActivity.redirectActivity(this, DashboardActivity.class);
        //Toast.makeText(ProfesorMenuActivity.this, "Dashboard", Toast.LENGTH_LONG).show();

    }

    public void ClickCitasP(View view){
        //Redireccionar
        ProfesorMenuActivity.redirectActivity(this, ProfesorCitasActivity.class);
        //Toast.makeText(this, "Boton Citas Profesor", Toast.LENGTH_LONG).show();

    }
    public void ClickLogoutP(View view){
        ProfesorMenuActivity.logout(this);
    }
    public void ClickAyudaP(View View){
        ProfesorMenuActivity.redirectActivity(this,ProfesorAyudaActivity.class);
    }

    public void ClickContactoP(View View){
        ProfesorMenuActivity.redirectActivity(this,ProfesorContactoActivity.class);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Cerramos drawer
        ProfesorMenuActivity.closeDrawer(drawerLayoutP);
    }*/
}