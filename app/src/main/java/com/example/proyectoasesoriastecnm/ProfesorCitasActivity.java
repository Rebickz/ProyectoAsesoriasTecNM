package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProfesorCitasActivity extends AppCompatActivity {
    DrawerLayout drawerLayoutP;
    ListView myListView;
    ArrayList<String> myArrayList;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database;
    DatabaseReference mref;
    Cita cita;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_citas);

        //Asignar la variable drawerLayout
        drawerLayoutP = findViewById(R.id.profesor_drawer_layout);

        cita = new Cita();
        myListView = (ListView) findViewById(R.id.ListViewMaterias);
        database = FirebaseDatabase.getInstance();
        mref = database.getReference("citas");

        myArrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.materia_info,R.id.nombre_materia, myArrayList);

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    cita = ds.getValue(Cita.class);
                    //myArrayList.add(cita.getMateria().toString() + " " + cita.getHorario() + " " + cita.getProfesor());
                    myArrayList.add("Datos cita\nMateria:Fundamentos de programación\nCarrera:TICS\nDepartamento:Sistemas y computacion\nHorario:12:00-13:00hrs\nLugar:Aula 45");
                }

                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }

    public void ClickMenuP(View view){
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
        //redirectActivity(this, ProfileActivity.class);
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
        //redirectActivity(this, );
        recreate();
    }
    public void ClickLogoutP(View view){
        ProfesorMenuActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Cerramos drawer
        ProfesorMenuActivity.closeDrawer(drawerLayoutP);
    }
}