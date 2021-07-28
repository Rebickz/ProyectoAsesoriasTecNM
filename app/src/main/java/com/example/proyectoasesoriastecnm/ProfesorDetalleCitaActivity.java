package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import net.glxn.qrgen.android.QRCode;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfesorDetalleCitaActivity extends AppCompatActivity {

    DrawerLayout drawerLayoutP;

    TextView datos;
    String carrera;
    String email;
    String fecha;
    String horaAgendada;
    String lugar;
    String materia;
    String profesor;
    String semestre;
    String status;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_detalle_cita);

        datos = (TextView) findViewById(R.id.datos_Cita);
        drawerLayoutP= findViewById(R.id.profesor_drawer_layout);


        carrera = getIntent().getStringExtra("carrera");
        email = getIntent().getStringExtra("email");
        fecha = getIntent().getStringExtra("fecha");
        horaAgendada = getIntent().getStringExtra("horaAgendada");
        lugar = getIntent().getStringExtra("lugar");
        materia = getIntent().getStringExtra("materia");
        profesor = getIntent().getStringExtra("profesor");
        semestre = getIntent().getStringExtra("semestre");
        status = getIntent().getStringExtra("status");
        uid = getIntent().getStringExtra("uid");
        datos.setText("Datos de la cita:" +"\nMateria: "+materia  +"\nLugar: "+ lugar +"\nProfesor: "+ profesor);
        String texto = "Datos de la cita:" +"\nMateria: "+materia  +"\nLugar: "+ lugar +"\nProfesor: "+ profesor;
        Bitmap bitmap = QRCode.from(texto).bitmap();

        ImageView imagenCodigo = findViewById(R.id.qr);
        imagenCodigo.setImageBitmap(bitmap);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmacion de pase");
        builder.setMessage(texto);
        builder.setPositiveButton("Aceptar", null);

        AlertDialog dialog = builder.create();
        //dialog.show();
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
        ProfesorMenuActivity.redirectActivity(this, ProfesorProfileActivity.class);

    }
    public void ClickDashboardP(View view){
        //Redireccionar
        ProfesorMenuActivity.redirectActivity(this, DashboardActivity.class);
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
        recreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Cerrar drawer
        ProfesorMenuActivity.closeDrawer(drawerLayoutP);
    }

}