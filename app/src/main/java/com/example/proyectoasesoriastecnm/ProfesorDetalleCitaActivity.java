package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import net.glxn.qrgen.android.QRCode;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfesorDetalleCitaActivity extends AppCompatActivity {

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
}