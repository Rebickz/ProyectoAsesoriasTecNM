package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    //Inicializar variable
    DrawerLayout drawerLayoutP;
    Button btnUsers, btnMaterias,btnStatistics, btnMensajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnUsers =(Button) findViewById(R.id.Usuarios);
        btnUsers.setOnClickListener(this);

        btnMaterias =(Button) findViewById(R.id.Materias);
        btnMaterias.setOnClickListener(this);

        btnStatistics =(Button) findViewById(R.id.Estadisticas);
        btnStatistics.setOnClickListener(this);

        btnMensajes = (Button) findViewById(R.id.Mensajes);
        btnMensajes.setOnClickListener(this);

        //Asignar la variable drawerLayout
        drawerLayoutP = findViewById(R.id.profesor_drawer_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Estadisticas:
                startActivity(new Intent(this, DashboardEstadisticasActivity.class));
                break;
            case R.id.Usuarios:
                startActivity(new Intent(this,DashboardUsuarioActivity.class));
                break;

            case R.id.Materias:
                startActivity(new Intent(this,DashboardMateriaActivity.class));
                break;

            case R.id.Mensajes:
                startActivity(new Intent(this,MensajesActivity.class));
                break;

        }
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


}