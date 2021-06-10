package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ProfesorMenuActivity extends AppCompatActivity {

    //Inicializar variable
    DrawerLayout drawerLayoutP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_menu);

        //Asignar la variable drawerLayout
        drawerLayoutP = findViewById(R.id.profesor_drawer_layout);
    }

    public void ClickMenuP(View view){
        //Abrir Drawer
        openDrawer(drawerLayoutP);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Abrir drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogoP(View view){
        //Cerrar drawer
        closeDrawer(drawerLayoutP);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Cerrar drawer layout
        //Verificar condicion
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //Cuando el drawer esta abierto
            //Se cierra el drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //Opciones del menu
    public void ClickHomeP(View view){
        //Recrear la activity
        recreate();
    }
    public void ClickPerfilP(View view){
        //Redireccionar
        //redirectActivity(this, ProfileActivity.class);
        Toast.makeText(ProfesorMenuActivity.this, "Boton Perfil Profesor", Toast.LENGTH_LONG).show();

    }
    public void ClickDashboardP(View view){
        //Redireccionar
        redirectActivity(this, DashboardActivity.class);
        //Toast.makeText(ProfesorMenuActivity.this, "Dashboard", Toast.LENGTH_LONG).show();

    }

    public void ClickCitasP(View view){
        //Redireccionar
        //redirectActivity(this, );
        Toast.makeText(ProfesorMenuActivity.this, "Boton Citas Profesor", Toast.LENGTH_LONG).show();

    }
    public void ClickLogoutP(View view){
        logout(this);
    }

    public static void logout(Activity activity) {
        //Inicializar el dialogo de alerta
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Agregar titulo
        builder.setTitle("Logout");
        //Agregar mensaje
        builder.setMessage("Estas seguro que quieres cerrar sesion?");
        //Boton "si"
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cerrar sesion
                FirebaseAuth.getInstance().signOut();
                //Terminar actividad
                activity.finishAffinity();
                //Salir de la app
                System.exit(0);
            }
        });

        //Boton no
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cerrar mensaje
                dialog.dismiss();
            }
        });
        //Mostrar dialogo
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //Inicializar el Intent
        Intent intent = new Intent(activity, aClass);

        //Bandera
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Start Acitivty
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Cerramos drawer
        closeDrawer(drawerLayoutP);
    }
}