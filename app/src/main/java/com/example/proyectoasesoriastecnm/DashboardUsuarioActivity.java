package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DashboardUsuarioActivity extends AppCompatActivity {

    //Inicializar variable
    DrawerLayout drawerLayoutP;
    //Listar
    private List<Usuario> listUsuario = new ArrayList<Usuario>();
    ArrayAdapter<Usuario> arrayAdapterUsuario;

    //CRUD
    EditText nomUser, apeUser, emailUser, passUser, rolUser;
    ListView listV_User;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Usuario usuarioSelected; //Actualizar

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
        listarDatos(); //Listar
        listV_User.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Actualizar
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                usuarioSelected = (Usuario) parent.getItemAtPosition(position);
                nomUser.setText(usuarioSelected.getName());
                apeUser.setText(usuarioSelected.getLastName());
                emailUser.setText(usuarioSelected.getEmail());
                passUser.setText(usuarioSelected.getPassword());
                rolUser.setText(usuarioSelected.getRol());
            }
        });
        //TERMINA CRUD

    }

    private void listarDatos() { //Listar
        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listUsuario.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    Usuario u = objSnapshot.getValue(Usuario.class);
                    listUsuario.add(u);

                    arrayAdapterUsuario = new ArrayAdapter<Usuario>(DashboardUsuarioActivity.this, android.R.layout.simple_list_item_1,listUsuario);
                    listV_User.setAdapter(arrayAdapterUsuario);
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    //CRUD
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true); //PERSISTENCIA DE DATOS
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
                Usuario u = new Usuario();
                u.setUid(usuarioSelected.getUid());
                u.setName(nomUser.getText().toString().trim());
                u.setLastName(apeUser.getText().toString().trim());
                u.setEmail(emailUser.getText().toString().trim());
                u.setPassword(passUser.getText().toString().trim());
                u.setRol(rolUser.getText().toString().trim());
                databaseReference.child("Users").child(u.getUid()).setValue(u);
                Toast.makeText(this,"Actualizado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            case R.id.icon_delete://Eliminar solo es esto
                Usuario us = new Usuario();
                us.setUid(usuarioSelected.getUid());
                databaseReference.child("Users").child(us.getUid()).removeValue();
                Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
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
        ProfesorMenuActivity.redirectActivity(this,ProfesorContactoActivity.class);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //Cerramos drawer
        ProfesorMenuActivity.closeDrawer(drawerLayoutP);
    }
}