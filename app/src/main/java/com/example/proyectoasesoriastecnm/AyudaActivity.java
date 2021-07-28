package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AyudaActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        drawerLayout = findViewById(R.id.drawer_layout);

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

    public void preguntaUno(View view) {
        Intent intent = new Intent(AyudaActivity.this, PreguntaUno.class);
        startActivity(intent);
    }

    public void preguntaDos(View view) {
        Intent intent = new Intent(AyudaActivity.this, PreguntaDos.class);
        startActivity(intent);
    }


    public void btnContacto(View view) {
        Intent intent = new Intent(AyudaActivity.this, ContactoActivity.class);
        startActivity(intent);
    }
}