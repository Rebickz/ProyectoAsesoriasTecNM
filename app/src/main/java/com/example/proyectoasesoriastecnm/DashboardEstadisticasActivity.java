package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DashboardEstadisticasActivity extends AppCompatActivity {

    //Inicializar variable
    //DrawerLayout drawerLayoutP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_estadisticas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Asignar la variable drawerLayout
        //drawerLayoutP = findViewById(R.id.profesor_drawer_layout);
    }

    /*public void ClickMenuP(View view){
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
        ProfesorMenuActivity.redirectActivity(this, ProfesorCitasActivity.class);
        //Toast.makeText(this, "Boton Citas Profesor", Toast.LENGTH_LONG).show();

    }
    public void ClickLogoutP(View view){
        ProfesorMenuActivity.logout(this);
    }
    public void ClickAyudaP(View View){
        ProfesorMenuActivity.redirectActivity(this,ProfesorAyudaActivity.class);
    }

    public void ClickContactoP(View View){
        ProfesorMenuActivity.redirectActivity(this,ProfesorContactoActivity.class);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Cerramos drawer
        ProfesorMenuActivity.closeDrawer(drawerLayoutP);
    }*/
}