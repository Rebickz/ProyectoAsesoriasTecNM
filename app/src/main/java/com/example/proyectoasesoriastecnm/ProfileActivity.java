package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    //Inicializar variable
    DrawerLayout drawerLayout; //Agregar

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Asignar variable
        drawerLayout = findViewById(R.id.drawer_layout);//Agregar

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
        Toast.makeText(ProfileActivity.this, "Boton Agendar", Toast.LENGTH_LONG).show();

    }

    public void ClickCitas(View view){//Agregar
        //Redireccionar
        //redirectActivity(this, );
        Toast.makeText(ProfileActivity.this, "Boton Citas", Toast.LENGTH_LONG).show();

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