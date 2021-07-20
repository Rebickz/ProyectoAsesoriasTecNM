package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CitaActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;

    private EditText tv1;
    private EditText materiA;
    private EditText EditHora;
    private EditText Profesor;
    private Button BotonEnvio;
    String dato;
    String materia;
    String horaAgendada;
    String profesor;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);
        drawerLayout = findViewById(R.id.drawer_layout);
        BotonEnvio = (Button) findViewById(R.id.ButtonSend);
        //onClick(BotonEnvio);

        tv1 = (EditText) findViewById(R.id.EditTextV1);
          dato = getIntent().getStringExtra("dato");
         tv1.setText(dato);

         materiA = (EditText) findViewById(R.id.EditTextMateria);
         materia = getIntent().getStringExtra("materia");
        materiA.setText(materia);

        EditHora = (EditText) findViewById(R.id.EditTextHora);
         horaAgendada = getIntent().getStringExtra("horaAgendada");
        EditHora.setText(horaAgendada);

        Profesor = (EditText) findViewById(R.id.EditTextProfesor);
         profesor = getIntent().getStringExtra("profesor");
        Profesor.setText(profesor);

    }


    public void ClickMenu(View view){//Agregar
        //Abrir drawer
        MenuActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){//Agregar
        //Cerrar drawer
        MenuActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){//Agregar
        //Redireccionar al Home
        MenuActivity.redirectActivity(this,MenuActivity.class);
    }

    public void ClickPerfil(View view){//Agregar
        MenuActivity.redirectActivity(this,ProfileActivity.class);
        //recreate();
    }

    public void ClickAgendar(View view){//Agregar
        //Redireccionar
        MenuActivity.redirectActivity(this,AgendarActivity.class);
        //Toast.makeText(CitaActivity.this, "Boton Agendar", Toast.LENGTH_LONG).show();

    }

    public void ClickCitas(View view){//Agregar
        //Redireccionar
        MenuActivity.redirectActivity(this,CitasV2.class);
        //Toast.makeText(CitaActivity.this, "Boton Citas", Toast.LENGTH_LONG).show();

    }

    public void ClickLogout(View view){//Agregar
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
    protected void onPause() {//Agregar
        super.onPause();
        //Cerrar drawer
        MenuActivity.closeDrawer(drawerLayout);
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void ClickButtonSend(View view){//Agregar
        //Redireccionar
        //redirectActivity(this, );
        onClick(BotonEnvio);
    }


    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.ButtonSend:
                registerCita();
                break;
        }

    }

    public void registerCita(){

        iniciarFirebase();

        Cita cita = new Cita();

        cita.setEmail(dato);
        cita.setHorario(horaAgendada);
        cita.setMateria(materia);
        cita.setProfesor(profesor);

        cita.setUid(UUID.randomUUID().toString());

        databaseReference.child("citas").child(cita.getUid()).setValue(cita);
        Toast.makeText(this, R.string.solicitud_sesion, Toast.LENGTH_LONG).show();

    }




}