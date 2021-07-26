package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class MateriasActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ListView myListView;
    ArrayList<Materia> myArrayList;
    ArrayAdapter<Materia> adapter;
    FirebaseDatabase database;
    DatabaseReference mref;
    Materia materia;
    Materia materiaSelected;

    private FirebaseUser user;


    private List<Materia> listMateria = new ArrayList<Materia>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        //SE AGREGA SHIMMER
        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmer(); // If auto-start is set to false



        drawerLayout = findViewById(R.id.drawer_layout);

        materia = new Materia();
        myListView = (ListView) findViewById(R.id.ListViewMaterias);
        database = FirebaseDatabase.getInstance();
        mref = database.getReference("tablaMaterias");

        myArrayList = new ArrayList<Materia>();
        //adapter = new ArrayAdapter<String>(this, R.layout.materia_info,R.id.nombre_materia, myArrayList);
        adapter = new ArrayAdapter<Materia>(MateriasActivity.this, android.R.layout.simple_list_item_1, myArrayList);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    materia = ds.getValue(Materia.class);
                    //myArrayList.add(materia.getNombre() + "\n" + materia.getHorario() + "\n" + materia.getProfesor()+ "\n"+ materia.getDepartamento()+ "\n"+ materia.getLugar());
                    myArrayList.add(materia);
                }

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        container.hideShimmer();
                    }
                }, 500);
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
                Intent intent = new Intent(MateriasActivity.this, CitaActivity.class);
                materiaSelected = (Materia) adapterView.getItemAtPosition(i);
                intent.putExtra("carrera", myListView.getItemAtPosition(i).toString());
                intent.putExtra("dato", user.getEmail());
                intent.putExtra("fecha", materiaSelected.getFecha());
                intent.putExtra("horaAgendada", materiaSelected.getHorario().toString());
                intent.putExtra("lugar", materiaSelected.getLugar());
                intent.putExtra("materia", materiaSelected.getNombre() );
                intent.putExtra("profesor", materiaSelected.getProfesor().toString());
                intent.putExtra("semestre", materiaSelected.getSemestre());
                intent.putExtra("status", "PENDIENTE");
                intent.putExtra("uid", materiaSelected.getUid());
                startActivity(intent);

            }
        });
    }

    public void ClickMenu(View view){
        //Abrir drawer
        MenuActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        //Cerrar drawer
        MenuActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        //Redireccionar al Home
        MenuActivity.redirectActivity(this,MenuActivity.class);
    }

    public void ClickPerfil(View view){
        //Recrear actividad
        //Toast.makeText(this, "Boton Perfil", Toast.LENGTH_LONG).show();
        MenuActivity.redirectActivity(this,ProfileActivity.class);
    }

    public void ClickAgendar(View view){
        //Redireccionar
        MenuActivity.redirectActivity(this, AgendarActivity.class);
        //recreate();


    }

    public void ClickCitas(View view){
        //Redireccionar
        //redirectActivity(this, );
        //Toast.makeText(this, "Boton Citas", Toast.LENGTH_LONG).show();
        MenuActivity.redirectActivity(this,CitasV2.class);
    }

    public void ClickLogout(View view){
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
    protected void onPause() {
        super.onPause();
        //Cerrar drawer
        MenuActivity.closeDrawer(drawerLayout);
    }

    public void ClickNombre_Materia(View view){

        user = FirebaseAuth.getInstance().getCurrentUser();

        Intent i = new Intent( this, CitaActivity.class);


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                materiaSelected = (Materia) parent.getItemAtPosition(position);
                i.putExtra("carrera", materiaSelected.getCarrera());
                i.putExtra("dato", user.getEmail());
                i.putExtra("fecha", materiaSelected.getFecha());
                i.putExtra("horaAgendada", materiaSelected.getHorario().toString());
                i.putExtra("lugar", materiaSelected.getLugar());
                i.putExtra("materia", materiaSelected.getNombre().toString());
                i.putExtra("profesor", materiaSelected.getProfesor().toString());
                i.putExtra("semestre", materiaSelected.getSemestre());
                i.putExtra("status", "PENDIENTE");
                i.putExtra("uid", materiaSelected.getUid());

            }
        });

        /*i.putExtra("carrera", materia.getCarrera());
        i.putExtra("dato", user.getEmail());
        i.putExtra("fecha", materia.getFecha());
        i.putExtra("horaAgendada", materia.getHorario().toString());
        i.putExtra("lugar", materia.getLugar());
        i.putExtra("materia", materia.getNombre().toString());
        i.putExtra("profesor", materia.getProfesor().toString());
        i.putExtra("semestre", materia.getSemestre());
        i.putExtra("status", "PENDIENTE");
        i.putExtra("uid", materia.getUid());
        //MenuActivity.redirectActivity(this, CitaActivity.class);
*/
        startActivity(i);

        //myArrayList.indexOf( materia.getNombre().toString());

    }
}