package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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

public class ProfileActivity extends AppCompatActivity {
    //Inicializar variable
    DrawerLayout drawerLayout; //Agregar

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Asignar variable
        drawerLayout = findViewById(R.id.drawer_layout);//Agregar

        logout = (Button) findViewById(R.id.signOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
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

                    String Alumno = getResources().getString(R.string.Alumno);
                    String Profesor = getResources().getString(R.string.profesor_detalle);

                    if((userProfile.rol).equals("ALUMNO")){
                        greetingTextView.setText(Alumno + " : " +  name );
                    }else{
                        greetingTextView.setText(Profesor + " : " +  name );
                    }
                    nameTextView.setText(name);
                    emailTextView.setText(email);
                    lastNameTextView.setText(lastName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Ocurrió un error al cargar los datos",Toast.LENGTH_LONG).show();
            }
        });

    }

    public void ClickMenu(View view){//Agregar
        //Abrir drawer
        MenuActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){//Agregar
        //Cerrar drawer
        MenuActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){//Agregar
        //Redireccionar al Home
        MenuActivity.redirectActivity(this,MenuActivity.class);
    }

    public void ClickPerfil(View view){//Agregar
        //Recrear actividad
        recreate();
    }

    public void ClickAgendar(View view){//Agregar
        //Redireccionar
        MenuActivity.redirectActivity(this, AgendarActivity.class);

    }

    public void ClickCitas(View view){//Agregar
        //Redireccionar
        MenuActivity.redirectActivity(this,CitasV2.class);
        //Toast.makeText(ProfileActivity.this, "Boton Citas", Toast.LENGTH_LONG).show();

    }

    public void ClickLogout(View view){//Agregar
        //Cerrar app
        MenuActivity.logout(this);
    }

    public void ClickAyuda(View View){
        MenuActivity.redirectActivity(this,AyudaActivity.class);

    }
    public void ClickContacto(View View){
        MenuActivity.redirectActivity(this,ContactoActivity.class);
    }

    @Override
    protected void onPause() {//Agregar
        super.onPause();
        //Cerrar drawer
        MenuActivity.closeDrawer(drawerLayout);
    }
}