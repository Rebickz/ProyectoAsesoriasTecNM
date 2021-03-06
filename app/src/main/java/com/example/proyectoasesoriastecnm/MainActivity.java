package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.Console;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    //Lineas de codigo de prueba
    private FirebaseUser user;
    private DatabaseReference reference;
    private DatabaseReference refAn;
    private String userID;
    //Termina

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_ProyectoAsesoriasTecNM);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this,RegisterUserActivity.class));
                break;

            case R.id.signIn:
                userLogin();
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
        }

    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Falta ingresar email");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Email invalido, vuelve a intentar.");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Falta ingresar la contrase??a");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("La contrase??a debe contener minimo 6 caracteres");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    //Lineas de prueba
                    reference = FirebaseDatabase.getInstance().getReference("tablaUsuarios"); //Referenciar la tabla
                    userID = user.getUid(); //Obtener Unique ID
                    //Termina
                    Date fecha = new Date();
                    DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    String hoy = formato.format(fecha);
                    refAn = FirebaseDatabase.getInstance().getReference("Analytics").child("dailyUser");
                    refAn.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            boolean newday=true;
                            if(snapshot.exists()) {
                                for (DataSnapshot usr : snapshot.getChildren()) {
                                    int actual = Integer.parseInt(usr.getValue().toString());
                                    String key = usr.getKey();
                                    if (key.equals(hoy)) {
                                        refAn.child(hoy).setValue((actual + 1));
                                        newday = false;
                                        break;
                                    }
                                }
                                if(newday)
                                    refAn.child(hoy).setValue((1));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                    if(user.isEmailVerified()){
                        //Referenciar al perfil del usuario

                        //Lineas de prueba

                        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                User userProfile = snapshot.getValue(User.class);

                                if(userProfile != null){
                                    String name = userProfile.name;
                                    String email = userProfile.email;
                                    String lastName =userProfile.lastName;
                                    String rol = userProfile.rol;

                                    if((userProfile.rol).equals("ALUMNO")){

                                        Intent intA = new Intent(MainActivity.this, Huella.class);
                                        progressBar.setVisibility(View.INVISIBLE);
                                        intA.putExtra("rolValue",0);
                                        startActivity(intA);





                                    }else{
                                        Intent intP = new Intent(MainActivity.this, Huella.class);
                                        progressBar.setVisibility(View.INVISIBLE);
                                        intP.putExtra("rolValue",1);
                                        startActivity(intP);
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity.this, "ERROR PRUEBA",Toast.LENGTH_LONG).show();
                            }
                        });

                        // Termina


                    }else{
                        user.sendEmailVerification();
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, "Checa tu correo para verificar tu cuenta", Toast.LENGTH_LONG).show();
                    }

                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Error al iniciar sesion, verifica los datos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}