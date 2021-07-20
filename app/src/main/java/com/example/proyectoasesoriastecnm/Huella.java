package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class Huella extends AppCompatActivity {

    private TextView authStatusTv;
    private Button authBtn;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huella);

        //Iniciamos los elementos
        authStatusTv = findViewById(R.id.authStatusTv);
        authBtn = findViewById(R.id.authBtn);

        //Inicializamos la biometria

        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(Huella.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull @NotNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                authStatusTv.setText("Error"+errorCode);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull @NotNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                authStatusTv.setText("Autenticación exitosa");
                Toast.makeText(Huella.this,"Listo", Toast.LENGTH_SHORT).show();


                int valor = getIntent().getExtras().getInt("rolValue");

                if(valor==0){

                    startActivity(new Intent(Huella.this,MenuActivity.class));

                }else {

                    startActivity(new Intent(Huella.this,ProfesorMenuActivity.class));

                }



            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                authStatusTv.setText("Autenticación fallida");
                Toast.makeText(Huella.this,"Autenticación fallida",Toast.LENGTH_SHORT).show();

                finish();

            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticación con huella")
                .setSubtitle("Inicio de sesión con un toque")

                .build();






        //Clics y authenticación

        authBtn.setOnClickListener(v -> {

            biometricPrompt.authenticate(promptInfo);


        });






    }
}