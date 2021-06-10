package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfesorProfileActivity extends AppCompatActivity {

    //Inicializar variable
    DrawerLayout drawerLayoutP;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_profile);

        //Asignar la variable drawerLayout
        drawerLayoutP = findViewById(R.id.profesor_drawer_layout);

        logout = (Button) findViewById(R.id.signOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfesorProfileActivity.this, MainActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser(); //Referenciar al usuario logeado actualmente
        reference = FirebaseDatabase.getInstance().getReference("Users"); //Referenciar la tabla
        userID = user.getUid(); //Obtener Unique ID

        //Crear TextView Objects con final
        //por que vamos a acceder a las variables en nuestras clases
        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView nameTextView = (TextView) findViewById(R.id.name);
        final TextView emailTextView = (TextView) findViewById(R.id.emailAddress);
        final TextView lastNameTextView = (TextView) findViewById(R.id.lastName);

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
                        greetingTextView.setText("Alumno: " + name );
                    }else{
                        greetingTextView.setText("Profesor:" + name );
                    }
                    nameTextView.setText(name);
                    emailTextView.setText(email);
                    lastNameTextView.setText(lastName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfesorProfileActivity.this, "Ocurrió un error al cargar los datos",Toast.LENGTH_LONG).show();
            }
        });

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
        Toast.makeText(this, "Boton Perfil Profesor", Toast.LENGTH_LONG).show();

    }
    public void ClickDashboardP(View view){
        //Redireccionar
        ProfesorMenuActivity.redirectActivity(this, DashboardActivity.class);
        //Toast.makeText(ProfesorMenuActivity.this, "Dashboard", Toast.LENGTH_LONG).show();

    }

    public void ClickCitasP(View view){
        //Redireccionar
        //redirectActivity(this, );
        Toast.makeText(this, "Boton Citas Profesor", Toast.LENGTH_LONG).show();

    }
    public void ClickLogoutP(View view){
        ProfesorMenuActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Cerramos drawer
        ProfesorMenuActivity.closeDrawer(drawerLayoutP);
    }
}