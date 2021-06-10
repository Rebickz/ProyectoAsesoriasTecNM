package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CitaActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    private EditText tv1;
    private EditText materiA;
    private EditText EditHora;

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

    }
}