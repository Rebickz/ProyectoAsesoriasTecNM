package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class DashboardUsuarioActivity extends AppCompatActivity {

    //Inicializar variable
    DrawerLayout drawerLayoutP;

    //CRUD
    EditText nomUser, apeUser, emailUser, passUser, rolUser;
    ListView listV_User;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //TERMINA CRUD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_usuario);

        //Asignar la variable drawerLayout
        drawerLayoutP = findViewById(R.id.profesor_drawer_layout);

        //CRUD
        nomUser = findViewById(R.id.txt_nameUsuario);
        apeUser = findViewById(R.id.txt_lastNameUsuario);
        emailUser = findViewById(R.id.txt_emailUsuario);
        passUser = findViewById(R.id.txt_passwordUsuario);
        rolUser = findViewById(R.id.txt_rolUsuario);
        listV_User = findViewById(R.id.lv_datosUsuario);
        inicializarFirebase();
        //TERMINA CRUD

    }
    //CRUD
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String nombre = nomUser.getText().toString();
        String apellidos = apeUser.getText().toString();
        String correo = emailUser.getText().toString();
        String password = passUser.getText().toString();
        String rol = rolUser.getText().toString();

        switch(item.getItemId()){
            case R.id.icon_add:
                if(nombre.equals("") || apellidos.equals("") || correo.equals("") || password.equals("") || rol.equals("")){
                    validacion();
                }else {
                    Usuario u = new Usuario();
                    u.setUid(UUID.randomUUID().toString());
                    u.setName(nombre);
                    u.setLastName(apellidos);
                    u.setEmail(correo);
                    u.setPassword(password);
                    u.setRol(rol);
                    databaseReference.child("Users").child(u.getUid()).setValue(u);
                    Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }
                break;
            case R.id.icon_save:
                Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show();
                break;
            case R.id.icon_delete:
                Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void limpiarCajas() {
        nomUser.setText("");
        apeUser.setText("");
        emailUser.setText("");
        passUser.setText("");
        rolUser.setText("");
    }

    private void validacion() {
        String nombre = nomUser.getText().toString();
        String apellidos = apeUser.getText().toString();
        String correo = emailUser.getText().toString();
        String password = passUser.getText().toString();
        String rol = rolUser.getText().toString();

        if(nombre.equals("")){
            nomUser.setError("Requerido");
        }else if(apellidos.equals("")){
            apeUser.setError("Requerido");
        }else if(correo.equals("")){
            emailUser.setError("Requerido");
        }else if(password.equals("")){
            passUser.setError("Requerido");
        }else if(rol.equals("")){
            rolUser.setError("Requerido");

        }
    }


    //TERMINA CRUD

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