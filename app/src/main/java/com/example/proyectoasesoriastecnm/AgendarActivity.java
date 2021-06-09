package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class AgendarActivity extends AppCompatActivity {

    ImageView carrera;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        drawerLayout = findViewById(R.id.drawer_layout);


      /*  carrera = (ImageView) findViewById(R.id.ticsimageView);



        carrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.ticsimageView: //id de ImageView.

                        //realiza operación al dar clic al imageView.
                        Intent Materias = new Intent(AgendarActivity.this, MateriasActivity.class);
                        startActivity(Materias);

                        break;
                    default:
                        break;
                }
            }
        });*/

    }

    public  void  ClickTics(View view){

        MenuActivity.redirectActivity(this,MateriasActivity.class);

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
        Toast.makeText(this, "Boton Agendar", Toast.LENGTH_LONG).show();

    }

    public void ClickAgendar(View view){
        //Redireccionar
        //redirectActivity(this, );
        recreate();
    }

    public void ClickCitas(View view){
        //Redireccionar
        //redirectActivity(this, );
        Toast.makeText(this, "Boton Citas", Toast.LENGTH_LONG).show();
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