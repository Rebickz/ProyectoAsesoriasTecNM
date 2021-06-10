package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CitaActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    private EditText tv1;
    private EditText materiA;
    private EditText EditHora;
    private EditText Profesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);

        drawerLayout = findViewById(R.id.drawer_layout);

        tv1 = (EditText) findViewById(R.id.EditTextV1);
         String dato = getIntent().getStringExtra("dato");
         tv1.setText(dato);

         materiA = (EditText) findViewById(R.id.EditTextMateria);
        String materia = getIntent().getStringExtra("materia");
        materiA.setText(materia);

        EditHora = (EditText) findViewById(R.id.EditTextHora);
        String horaAgendada = getIntent().getStringExtra("horaAgendada");
        EditHora.setText(horaAgendada);

        Profesor = (EditText) findViewById(R.id.EditTextProfesor);
        String profesor = getIntent().getStringExtra("profesor");
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
        //Recrear actividad
        recreate();
    }

    public void ClickAgendar(View view){//Agregar
        //Redireccionar
        //redirectActivity(this, );
        Toast.makeText(CitaActivity.this, "Boton Agendar", Toast.LENGTH_LONG).show();

    }

    public void ClickCitas(View view){//Agregar
        //Redireccionar
        //redirectActivity(this, );
        Toast.makeText(CitaActivity.this, "Boton Citas", Toast.LENGTH_LONG).show();

    }

    public void ClickLogout(View view){//Agregar
        //Cerrar app
        MenuActivity.logout(this);
    }

    @Override
    protected void onPause() {//Agregar
        super.onPause();
        //Cerrar drawer
        MenuActivity.closeDrawer(drawerLayout);
    }
}