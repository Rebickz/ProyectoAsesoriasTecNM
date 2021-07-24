package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, registerUser;
    private EditText editTextName, editTextLastName, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        //Inicializar variables para metodo onClick
        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);
        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        //Inicializar variables para registrar usuario
        editTextName = (EditText) findViewById(R.id.name);
        editTextLastName = (EditText) findViewById(R.id.lastName);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) { //Interacción entre activities
        switch(v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }
    }

    private void registerUser() { //Validaciones registro usuario
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String rol = "ALUMNO";
        //Variables validación cuenta institucional
        Pattern p = Pattern.compile("aguascalientes.tecnm.mx", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);

        if(name.isEmpty()){
            editTextName.setError("No se ha ingresado el nombre");
            editTextName.requestFocus();
            return;
        }

        if(lastName.isEmpty()){
            editTextLastName.setError("No se ha ingresado el apellido");
            editTextLastName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("No se ha ingresado el email");
            editTextEmail.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){ //Si no cumple patrones establecidos para EMAIL ADDRESS
            editTextEmail.setError("Ingresa un email valido");
            editTextEmail.requestFocus();
            return;
        }

        if(!m.find()) { //Validación correo institucional
            Toast.makeText(RegisterUserActivity.this, "No es cuenta institucional", Toast.LENGTH_LONG).show();
            return;
        }


        if(password.isEmpty()){
            editTextPassword.setError("No se ha ingresado la contraseña");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("La contraseña no puede ser menor a 6 caracteres.");
            editTextPassword.requestFocus();
            return;
        }

        //Conexion
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            String uid = FirebaseAuth.getInstance().getUid();

                            Usuario user = new Usuario(name, lastName, email, password, rol, uid);

                            FirebaseDatabase.getInstance().getReference("tablaUsuarios")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUserActivity.this, "El usuario se registro correctamente", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        //Redireccionar a la pantalla de login
                                    }else{
                                        Toast.makeText(RegisterUserActivity.this, "Error de registro, vuelve a intentar", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                }
                            });

                        }else{
                            Toast.makeText(RegisterUserActivity.this, "Error de registro, vuelve a intentar", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



    }
}