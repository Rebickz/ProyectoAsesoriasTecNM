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

public class DashboardMateriaActivity extends AppCompatActivity {

    //Inicializar variable
    //DrawerLayout drawerLayoutP;
    private List<Materia> listMateria = new ArrayList<Materia>();
    ArrayAdapter<Materia> arrayAdapterMateria;

    //CRUD
    EditText nomMateria, semMateria, carreraMateria, horaMateria;
    ListView listV_Materia;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Materia materiaSelected; //Actualizar

    //TERMINA CRUD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_materia);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Asignar la variable drawerLayout
        //drawerLayoutP = findViewById(R.id.profesor_drawer_layout);

        //CRUD
        nomMateria = findViewById(R.id.txt_nombreMateria);
        semMateria = findViewById(R.id.txt_semestreMateria);
        carreraMateria = findViewById(R.id.txt_carreraMateria);
        horaMateria = findViewById(R.id.txt_horarioMateria);
        listV_Materia = findViewById(R.id.lv_datosMateria);
        inicializarFirebase();
        listarDatos(); //Listar
        listV_Materia.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Actualizar
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                materiaSelected = (Materia) parent.getItemAtPosition(position);
                nomMateria.setText(materiaSelected.getNombre());
                semMateria.setText(materiaSelected.getSemestre());
                carreraMateria.setText(materiaSelected.getCarrera());
                horaMateria.setText(materiaSelected.getHorario());
            }
        });
        //TERMINA CRUD

    }

    private void listarDatos() { //Listar
        databaseReference.child("Materias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listMateria.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    Materia m = objSnapshot.getValue(Materia.class);
                    listMateria.add(m);

                    arrayAdapterMateria = new ArrayAdapter<Materia>(DashboardMateriaActivity.this, android.R.layout.simple_list_item_1,listMateria);
                    listV_Materia.setAdapter(arrayAdapterMateria);
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
        String nombre = nomMateria.getText().toString();
        String semestre = semMateria.getText().toString();
        String carrera = carreraMateria.getText().toString();
        String horario = horaMateria.getText().toString();

        switch(item.getItemId()){
            case R.id.icon_add:
                if(nombre.equals("") || semestre.equals("") || carrera.equals("") || horario.equals("")){
                    validacion();
                }else {
                    Materia m = new Materia();
                    m.setUid(UUID.randomUUID().toString());
                    m.setNombre(nombre);
                    m.setSemestre(semestre);
                    m.setCarrera(carrera);
                    m.setHorario(horario);

                    databaseReference.child("Materias").child(m.getUid()).setValue(m);
                    Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }
                break;
            case R.id.icon_save:
                Materia m = new Materia();
                m.setUid(materiaSelected.getUid());
                m.setNombre(nomMateria.getText().toString().trim());
                m.setSemestre(semMateria.getText().toString().trim());
                m.setCarrera(carreraMateria.getText().toString().trim());
                m.setHorario(horaMateria.getText().toString().trim());
                databaseReference.child("Materias").child(m.getUid()).setValue(m);
                Toast.makeText(this,"Actualizado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            case R.id.icon_delete://Eliminar solo es esto
                Materia ma = new Materia();
                ma.setUid(materiaSelected.getUid());
                databaseReference.child("Materias").child(ma.getUid()).removeValue();
                Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
        }
        return true;
    }

    private void limpiarCajas() {
        nomMateria.setText("");
        semMateria.setText("");
        carreraMateria.setText("");
        horaMateria.setText("");
    }

    private void validacion() {
        String nombre = nomMateria.getText().toString();
        String semestre = semMateria.getText().toString();
        String carrera = carreraMateria.getText().toString();
        String horario = horaMateria.getText().toString();

        if(nombre.equals("")){
            nomMateria.setError("Requerido");
        }else if(semestre.equals("")){
            semMateria.setError("Requerido");
        }else if(carrera.equals("")){
            carreraMateria.setError("Requerido");
        }else if(horario.equals("")){
            horaMateria.setError("Requerido");
        }
    }


    //TERMINA CRUD

    public void ClickMenuP(View view){
        //Abrir Drawer
        //ProfesorMenuActivity.openDrawer(drawerLayoutP);
    }

    public void ClickLogoP(View view){
        //Cerrar drawer
        //ProfesorMenuActivity.closeDrawer(drawerLayoutP);
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
        //ProfesorMenuActivity.closeDrawer(drawerLayoutP);
    }
}