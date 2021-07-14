package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.provider.ContactsContract;
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

public class CitasV2 extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ListView myListView;
    ArrayList<String> myArrayList;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database;
    DatabaseReference mref;
    Cita cita;

    private FirebaseUser user;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        drawerLayout = findViewById(R.id.drawer_layout);

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

    public void ClickMenu(View view){
        //Abrir drawer
        MenuActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        //Cerrar drawer
        MenuActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        //Redireccionar al Home
        MenuActivity.redirectActivity(this,MenuActivity.class);
    }

    public void ClickPerfil(View view){
        //Recrear actividad
        MenuActivity.redirectActivity(this, ProfileActivity.class);
        //Toast.makeText(this, "Boton Perfil", Toast.LENGTH_LONG).show();

    }

    public void ClickAgendar(View view){
        //Redireccionar
        MenuActivity.redirectActivity(this, AgendarActivity.class);
        //recreate();


    }

    public void ClickCitas(View view){
        //Redireccionar
        //redirectActivity(this, );
        //Toast.makeText(this, "Boton Citas", Toast.LENGTH_LONG).show();
        recreate();
    }

    public void ClickLogout(View view){
        //Cerrar app
        MenuActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Cerrar drawer
        MenuActivity.closeDrawer(drawerLayout);
    }



}