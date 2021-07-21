package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_estadisticas);

        //Asignar la variable drawerLayout
        //drawerLayoutP = findViewById(R.id.profesor_drawer_layout);

        database = FirebaseDatabase.getInstance();
        mref = database.getReference().child("Analytics").child("Usr");
        mrefi = database.getReference().child("Analytics").child("Int");



        LineChart ChartUsr = (LineChart) findViewById(R.id.chartUsr);
        LineChart ChartInt = (LineChart) findViewById(R.id.chartInt);

        List<Entry> entUsr1 = new ArrayList<>();
        List<Entry> entUsr2 = new ArrayList<>();
        List<Entry> entUsr3 = new ArrayList<>();
        List<Long> entUsr1l = new ArrayList<>();
        List<Long> entUsr2l = new ArrayList<>();
        List<Long> entUsr3l = new ArrayList<>();
        List<Long> entIntl =  new ArrayList<>();
        List<Entry> entInt =  new ArrayList<>();


        /*entUsr1.add(new Entry(0,0));
        entUsr2.add(new Entry(0,2));
        entUsr3.add(new Entry(0,3));
        entUsr1.add(new Entry(1,1));
        entUsr2.add(new Entry(1,3));
        entUsr3.add(new Entry(1,4));
        entUsr1.add(new Entry(2,1));
        entUsr2.add(new Entry(2,2));
        entUsr3.add(new Entry(2,4));
        entUsr1.add(new Entry(3,6));
        entUsr2.add(new Entry(3,6));
        entUsr3.add(new Entry(3,7));
        */
        ValueEventListener LoadData = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    entUsr1.clear();
                    entUsr2.clear();
                    entUsr3.clear();
                    for(DataSnapshot usr:snapshot.getChildren()){
                        Long msj = (Long)usr.child("dia1").getValue();
                        entUsr1l.add(msj);
                        msj = (Long)usr.child("dia7").getValue();
                        entUsr2l.add(msj);
                        msj = (Long)usr.child("dia28").getValue();
                        entUsr3l.add(msj);
                    }
                    for(int i=0;i< entUsr1l.size();i++){
                        //Toast.makeText(DashboardEstadisticasActivity.this, entUsr1l.get(i).toString()+","+entUsr2l.get(i).toString()+","+entUsr3l.get(i).toString(), Toast.LENGTH_LONG).show();
                        entUsr1.add(new Entry(i,entUsr1l.get(i)));
                        entUsr2.add(new Entry(i,entUsr2l.get(i)));
                        entUsr3.add(new Entry(i,entUsr3l.get(i)));
                    }
                    LineDataSet datasetusr1 = new LineDataSet(entUsr1,"1 dia");
                    datasetusr1.setColor(R.color.purple_700);
                    datasetusr1.setAxisDependency(YAxis.AxisDependency.LEFT);
                    LineDataSet datasetusr2 = new LineDataSet(entUsr2,"7 dias");
                    datasetusr2.setColor(R.color.black);
                    datasetusr2.setAxisDependency(YAxis.AxisDependency.LEFT);
                    LineDataSet datasetusr3 = new LineDataSet(entUsr3,"28 dia");
                    datasetusr3.setColor(R.color.teal_200);
                    datasetusr3.setAxisDependency(YAxis.AxisDependency.LEFT);

                    final String[] quarters = new String[] { "18 Jul", "19 Jul", "20 Jul", "21 Jul" };
                    ValueFormatter formatter = new ValueFormatter() {
                        @Override
                        public String getAxisLabel(float value, AxisBase axis) {
                            return quarters[(int) value];
                        }
                    };
                    XAxis xAxis = ChartUsr.getXAxis();
                    xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                    xAxis.setValueFormatter(formatter);

                    List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                    dataSets.add(datasetusr1);
                    dataSets.add(datasetusr2);
                    dataSets.add(datasetusr3);
                    LineData data = new LineData(dataSets);
                    data.setDrawValues(true);
                    Description des = new Description();
                    des.setText("Usuarios Activos");
                    ChartUsr.setDescription(des);
                    ChartUsr.setData(data);
                    ChartUsr.invalidate();
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
                    LineDataSet datasetInt = new LineDataSet(entInt, "Promedio");
                    datasetInt.setColor(R.color.purple_700);
                    datasetInt.setAxisDependency(YAxis.AxisDependency.LEFT);

                    final String[] dates = new String[]{"13 Jul", "14 Jul", "15 Jul", "16 Jul", "17 Jul", "18 Jul", "19 Jul", "20 Jul", "21 Jul"};
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
                    des.setText("Eventos por usuario");
                    ChartInt.setDescription(des);
                    ChartInt.setData(data);
                    ChartInt.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        };
        mref.addValueEventListener(LoadData);
        mrefi.addValueEventListener(LoadInt);


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