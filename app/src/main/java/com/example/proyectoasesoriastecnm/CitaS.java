package com.example.proyectoasesoriastecnm;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CitaS extends AppCompatActivity  {

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
        setContentView(R.layout.activity_citas_v2);

        drawerLayout = findViewById(R.id.drawer_layout);

        cita = new Cita();
        myListView = (ListView) findViewById(R.id.ListViewCitas);
        database = FirebaseDatabase.getInstance();
        mref = database.getReference("citas");

        myArrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.cita_info,R.id.nombre_cita, myArrayList);

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    cita = ds.getValue(Cita.class);
                    myArrayList.add(cita.getMateria().toString() + " " + cita.getHorario() + " " + cita.getProfesor());
                    //myArrayList.add(materia.getCarrera().toString());
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
        MenuActivity.redirectActivity(this,ProfileActivity.class);
        //Toast.makeText(this, "Boton Perfil", Toast.LENGTH_LONG).show();

    }

    public void ClickAgendar(View view){
        //Redireccionar
        MenuActivity.redirectActivity(this, AgendarActivity.class);
        //recreate();
    }

    public void ClickCitas(View view){
        //Redireccionar
        MenuActivity.redirectActivity(this,CitasV2.class);
        //Toast.makeText(this, "Boton Citas", Toast.LENGTH_LONG).show();

    }

    public void ClickLogout(View view){
        //Cerrar app
        MenuActivity.logout(this);
    }

    public void ClickAyuda(View View){
        MenuActivity.redirectActivity(this,AyudaActivity.class);

    }
    public void ClickContacto(View View){
        MenuActivity.redirectActivity(this,ContactoActivity.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Cerrar drawer
        MenuActivity.closeDrawer(drawerLayout);
    }

    public void ClickNombre_Cita(View view){
        Intent i = new Intent( this, QRLector.class);
        startActivity(i);
    }



}
