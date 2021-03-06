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

    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        drawerLayout = findViewById(R.id.drawer_layout);

    }



    public  void  ClickElec(View view){

        Intent i = new Intent( this, MateriasActivity.class);
        i.putExtra("carrera", "Electronica");
        startActivity(i);
        //MenuActivity.redirectActivity(this,MateriasActivity.class);

    }

    public  void  ClickGestion(View view){

        Intent i = new Intent( this, MateriasActivity.class);
        i.putExtra("carrera", "Gestion Empresarial");
        startActivity(i);
        //MenuActivity.redirectActivity(this,MateriasActivity.class);

    }

    public  void  ClickIndustrial(View view){

        Intent i = new Intent( this, MateriasActivity.class);
        i.putExtra("carrera", "Ing Industrial");
        startActivity(i);
        //MenuActivity.redirectActivity(this,MateriasActivity.class);

    }

    public  void  ClickTics(View view){

        Intent i = new Intent( this, MateriasActivity.class);
        i.putExtra("carrera", "TICS");
        startActivity(i);
        //MenuActivity.redirectActivity(this,MateriasActivity.class);
    }

    public  void  ClickAdminI(View view){

        Intent i = new Intent( this, MateriasActivity.class);
        i.putExtra("carrera", "Administracion");
        startActivity(i);
        //MenuActivity.redirectActivity(this,MateriasActivity.class);

    }

    public  void  ClickMateriales(View view){

        Intent i = new Intent( this, MateriasActivity.class);
        i.putExtra("carrera", "Ing Materiales");
        startActivity(i);
        //MenuActivity.redirectActivity(this,MateriasActivity.class);

    }

    public  void  ClickMecanica(View view){

        Intent i = new Intent( this, MateriasActivity.class);
        i.putExtra("carrera", "Ing Mecanica");
        startActivity(i);
        //MenuActivity.redirectActivity(this,MateriasActivity.class);

    }

    public  void  ClickQuimica(View view){

        Intent i = new Intent( this, MateriasActivity.class);
        i.putExtra("carrera", "Ing Quimica");
        startActivity(i);
        //MenuActivity.redirectActivity(this,MateriasActivity.class);
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
        MenuActivity.redirectActivity(this,ProfileActivity.class);
        //Toast.makeText(this, "Boton Agendar", Toast.LENGTH_LONG).show();

    }

    public void ClickAgendar(View view){
        //Redireccionar
        //redirectActivity(this, );
        recreate();
    }

    public void ClickCitas(View view){
        //Redireccionar
        //redirectActivity(this, );
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


}