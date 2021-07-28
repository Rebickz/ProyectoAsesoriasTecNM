 package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class DashboardMateriaActivity extends AppCompatActivity {

    //Inicializar variable
    //DrawerLayout drawerLayoutP;
    private int dia, mes, ano, hora, minutos;
    private List<Materia> listMateria = new ArrayList<Materia>();
    ArrayAdapter<Materia> arrayAdapterMateria;
    //CRUD
    EditText nomMateria, semMateria, carreraMateria, dptoMateria, fechaMateria, horaInicioMateria, horaFinMateria, lugarMateria, profesorMateria;
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
        fechaMateria = findViewById(R.id.txt_fechaMateria);
        dptoMateria = findViewById(R.id.txt_dptoMateria);
        horaInicioMateria = findViewById(R.id.txt_horaInicioMateria);
        horaFinMateria = findViewById(R.id.txt_horaFinMateria);
        lugarMateria = findViewById(R.id.txt_lugarMateria);
        profesorMateria = findViewById(R.id.txt_ProfesorMateria);


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
                dptoMateria.setText(materiaSelected.getDepartamento());
                lugarMateria.setText(materiaSelected.getLugar());
                profesorMateria.setText(materiaSelected.getProfesor());
                fechaMateria.setText(materiaSelected.getFecha());
                horaInicioMateria.setText(materiaSelected.getHorario());
                horaFinMateria.setText(materiaSelected.getHorario());
            }
        });
        //TERMINA CRUD


    }

    private void listarDatos() { //Listar
        databaseReference.child("tablaMaterias").addValueEventListener(new ValueEventListener() {
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
        String departamento = dptoMateria.getText().toString();
        String fecha = fechaMateria.getText().toString();
        String horaInicio = horaInicioMateria.getText().toString();
        String horaFin = horaFinMateria.getText().toString();
        String horario = horaInicio +" - "+horaFin + " hrs.";
        String lugar = lugarMateria.getText().toString();
        String profesor = profesorMateria.getText().toString();

        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.icon_add:
                if(nombre.equals("") || semestre.equals("") || carrera.equals("") || horario.equals("")){ //CHECAR
                    validacion();
                }else {
                    Materia m = new Materia();
                    m.setUid(UUID.randomUUID().toString());
                    m.setNombre(nombre);
                    m.setSemestre(semestre);
                    m.setCarrera(carrera);
                    m.setProfesor(profesor);
                    m.setDepartamento(departamento);
                    m.setFecha(fecha);
                    m.setHorario(horario);
                    m.setLugar(lugar);

                    databaseReference.child("tablaMaterias").child(m.getUid()).setValue(m);
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
                m.setProfesor(profesorMateria.getText().toString().trim());
                m.setDepartamento(dptoMateria.getText().toString().trim());
                m.setFecha(fechaMateria.getText().toString().trim());
                m.setHorario(horario);
                m.setLugar(lugarMateria.getText().toString().trim());
                databaseReference.child("tablaMaterias").child(m.getUid()).setValue(m);
                Toast.makeText(this,"Actualizado",Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            case R.id.icon_delete://Eliminar solo es esto
                Materia ma = new Materia();
                ma.setUid(materiaSelected.getUid());
                databaseReference.child("tablaMaterias").child(ma.getUid()).removeValue();
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
        profesorMateria.setText("");
        dptoMateria.setText("");
        fechaMateria.setText("");
        horaInicioMateria.setText("");
        horaFinMateria.setText("");
        lugarMateria.setText("");
    }

    private void validacion() {
        String nombre = nomMateria.getText().toString();
        String semestre = semMateria.getText().toString();
        String carrera = carreraMateria.getText().toString();
        //String horario = horaMateria.getText().toString();

        if(nombre.equals("")){
            nomMateria.setError("Requerido");
        }else if(semestre.equals("")){
            semMateria.setError("Requerido");
        }else if(carrera.equals("")) {
            carreraMateria.setError("Requerido");
        }/*}else if(horario.equals("")){
            horaMateria.setError("Requerido");
        }*/
    }

    public void ClickFechaMateria(View view) {
        final Calendar c = Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this
                , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fechaMateria.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },dia,mes,ano);
        datePickerDialog.show();
        Toast.makeText(this,"Fecha",Toast.LENGTH_SHORT).show();
    }

    public void ClickHoraInicio(View view) {
        final Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minutos = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(minute<10){
                    horaInicioMateria.setText(hourOfDay + ":0" +minute);
                }else{
                    horaInicioMateria.setText(hourOfDay + ":" +minute);
                }
            }
        }, hora, minutos,false);
        timePickerDialog.show();
        Toast.makeText(this,"Hora inicio",Toast.LENGTH_SHORT).show();
    }

    public void ClickHoraFinal(View view) {
        final Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minutos = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(minute<10){
                    horaFinMateria.setText(hourOfDay + ":0" +minute);
                }else{
                    horaFinMateria.setText(hourOfDay + ":" +minute);
                }
            }
        }, hora, minutos,false);
        timePickerDialog.show();
        Toast.makeText(this,"Hora fin",Toast.LENGTH_SHORT).show();

    }


    //TERMINA CRUD

    /*public void ClickMenuP(View view){
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
    }*/
}