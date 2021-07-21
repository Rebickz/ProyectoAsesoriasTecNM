package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import net.glxn.qrgen.android.QRCode;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class ProfesorDetalleCitaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_detalle_cita);
        String texto = "Datos cita\nMateria:Fundamentos de programación\nCarrera:TICS\nDepartamento:Sistemas y computacion\nHorario:12:00-13:00hrs\nLugar:Aula 45\n\n\nSesion registrada\nESTATUS DE LA SESION: COMPLETADA\n\n";
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