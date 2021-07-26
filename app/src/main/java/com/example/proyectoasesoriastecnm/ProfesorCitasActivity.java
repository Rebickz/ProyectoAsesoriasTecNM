package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProfesorCitasActivity extends AppCompatActivity {
    /*DrawerLayout drawerLayoutP;
    ListView myListView;
    ArrayList<String> myArrayList;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database;
    DatabaseReference mref;
    Cita cita;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_citas);

        //Asignar la variable drawerLayout
        drawerLayoutP = findViewById(R.id.profesor_drawer_layout);

        cita = new Cita();
        myListView = (ListView) findViewById(R.id.ListViewMaterias);
        database = FirebaseDatabase.getInstance();
        mref = database.getReference("tablaCitas");

        myArrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.profesor_cita_info,R.id.Profesornombre_cita, myArrayList);

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    cita = ds.getValue(Cita.class);
                    myArrayList.add("Datos cita" + "\n" + cita.getMateria().toString() + "\n" + cita.getProfesor() + "\n" + cita.getHorario() + "\n" +  cita.getStatus());
                }

                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }*/

    DrawerLayout drawerLayoutP;
    ListView myListView;
    ArrayList<Cita> myArrayList;
    ArrayAdapter<Cita> adapter;
    FirebaseDatabase database;
    DatabaseReference mref;
    Cita materia;
    Cita materiaSelected;

    private FirebaseUser user;


    private List<Cita> listMateria = new ArrayList<Cita>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_citas);

        //SE AGREGA SHIMMER
        /*ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmer(); // If auto-start is set to false
        */


        drawerLayoutP = findViewById(R.id.profesor_drawer_layout);

        materia = new Cita();
        myListView = (ListView) findViewById(R.id.ListViewMaterias);
        database = FirebaseDatabase.getInstance();
        mref = database.getReference("tablaCitas");

        myArrayList = new ArrayList<Cita>();
        //adapter = new ArrayAdapter<String>(this, R.layout.materia_info,R.id.nombre_materia, myArrayList);
        adapter = new ArrayAdapter<Cita>(ProfesorCitasActivity.this, android.R.layout.simple_list_item_1, myArrayList);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    materia = ds.getValue(Cita.class);
                    //myArrayList.add(materia.getNombre() + "\n" + materia.getHorario() + "\n" + materia.getProfesor()+ "\n"+ materia.getDepartamento()+ "\n"+ materia.getLugar());
                    myArrayList.add(materia);
                }

                /*final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        container.hideShimmer();
                    }
                }, 500);*/
                myListView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        //***********************INICIA ON CLICK LISTENER//***********************



        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                user = FirebaseAuth.getInstance().getCurrentUser();
                Intent intent = new Intent(ProfesorCitasActivity.this, ProfesorDetalleCitaActivity.class);
                materiaSelected = (Cita) adapterView.getItemAtPosition(i);
                intent.putExtra("carrera", myListView.getItemAtPosition(i).toString());
                intent.putExtra("email", user.getEmail());
                intent.putExtra("fecha", materiaSelected.getFecha());
                intent.putExtra("horaAgendada", materiaSelected.getHorario().toString());
                intent.putExtra("lugar", materiaSelected.getLugar());
                intent.putExtra("materia", materiaSelected.getMateria() );
                intent.putExtra("profesor", materiaSelected.getProfesor().toString());
                intent.putExtra("semestre", materiaSelected.getSemestre());
                intent.putExtra("status", materiaSelected.getStatus());
                intent.putExtra("uid", materiaSelected.getUid());
                startActivity(intent);

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
        ProfesorMenuActivity.redirectActivity(this, ProfesorProfileActivity.class);

        //Toast.makeText(this, "Boton Perfil Profesor", Toast.LENGTH_LONG).show();

    }
    public void ClickDashboardP(View view){
        //Redireccionar
        ProfesorMenuActivity.redirectActivity(this, DashboardActivity.class);
        //Toast.makeText(ProfesorMenuActivity.this, "Dashboard", Toast.LENGTH_LONG).show();

    }

    public void ClickCitasP(View view){
        //Redireccionar
        //redirectActivity(this, );
        recreate();
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