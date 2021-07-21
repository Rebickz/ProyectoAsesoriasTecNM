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
        String texto = "Pase de lista confirmado";
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