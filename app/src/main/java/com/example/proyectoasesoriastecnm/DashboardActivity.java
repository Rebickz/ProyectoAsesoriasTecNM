package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    //Inicializar variable
    DrawerLayout drawerLayoutP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Asignar la variable drawerLayout
        drawerLayoutP = findViewById(R.id.profesor_drawer_layout);
    }

    public void ClickUsers(View view){
        ProfesorMenuActivity.redirectActivity(this, DashboardUsuarioActivity.class);
    }

    /*public void ClickAsesorias(View view){
        ProfesorMenuActivity.redirectActivity(this, DashboardAsesoriaActivity.class);
    }*/

    public void ClickMaterias(View view){
        ProfesorMenuActivity.redirectActivity(this, DashboardMateriaActivity.class);
    }

    public void ClickMenuP(View view){
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
        //redirectActivity(this, ProfileActivity.class);
        ProfesorMenuActivity.redirectActivity(this, ProfesorProfileActivity.class);

    }
    public void ClickDashboardP(View view){
        //Redireccionar
        recreate();
        //Toast.makeText(ProfesorMenuActivity.this, "Dashboard", Toast.LENGTH_LONG).show();

    }

    public void ClickCitasP(View view){
        //Redireccionar
        //redirectActivity(this, );
        ProfesorMenuActivity.redirectActivity(this, ProfesorCitasActivity.class);

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
    }

    public void ClickStatistics(View view) {
        ProfesorMenuActivity.redirectActivity(this, DashboardEstadisticasActivity.class);
    }
}