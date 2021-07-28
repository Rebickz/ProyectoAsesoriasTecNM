package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
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

public class CitasV2 extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ListView myListView;
    ArrayList<Cita> myArrayList;
    ArrayAdapter<Cita> adapter;
    FirebaseDatabase database;
    DatabaseReference mref;
    Cita cita;
    Cita citaSelected;

    //FILTRO
    FirebaseUser user;
    DatabaseReference uref;
    String userID;
    String emailU;
    String emailM;
    //FILTRO


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);
        //SE AGREGA SHIMMER
        ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmer(); // If auto-start is set to false

        drawerLayout = findViewById(R.id.drawer_layout);

        cita = new Cita();
        myListView = (ListView) findViewById(R.id.ListViewMaterias);
        database = FirebaseDatabase.getInstance();
        mref = database.getReference("tablaCitas");

        //FILTRO
        user = FirebaseAuth.getInstance().getCurrentUser(); //Referenciar al usuario logeado actualmente
        uref = FirebaseDatabase.getInstance().getReference("tablaUsuarios"); //Referenciar la tabla
        userID = user.getUid(); //Obtener Unique ID

        uref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                emailU = "";
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    emailU = userProfile.email;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CitasV2.this, "Ocurrió un error al cargar los datos",Toast.LENGTH_LONG).show();
            }
        });

        //FILTRO

        myArrayList = new ArrayList<>();
        adapter = new ArrayAdapter<Cita>(CitasV2.this, android.R.layout.simple_list_item_1, myArrayList);

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                emailM="";
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    Cita materia = ds.getValue(Cita.class);

                    emailM = materia.getEmail(); //
                    Toast.makeText(CitasV2.this, emailM + " " + emailU, Toast.LENGTH_LONG).show();

                    materia = ds.getValue(Cita.class);
                    //myArrayList.add(materia.getNombre() + "\n" + materia.getHorario() + "\n" + materia.getProfesor()+ "\n"+ materia.getDepartamento()+ "\n"+ materia.getLugar());
                    //IF materia
                    if(emailM.equals(emailU)){
                        myArrayList.add(materia);
                    }

                    //

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

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                user = FirebaseAuth.getInstance().getCurrentUser();
                Intent intent = new Intent(CitasV2.this, QRLector.class);
                citaSelected = (Cita) adapterView.getItemAtPosition(i);
                intent.putExtra("carrera", citaSelected.getCarrera());
                intent.putExtra("email", user.getEmail());
                intent.putExtra("fecha", citaSelected.getFecha());
                intent.putExtra("horaAgendada", citaSelected.getHorario().toString());
                intent.putExtra("lugar", citaSelected.getLugar());
                intent.putExtra("materia", citaSelected.getMateria() );
                intent.putExtra("profesor", citaSelected.getProfesor().toString());
                intent.putExtra("semestre", citaSelected.getSemestre());
                intent.putExtra("status", "COMPLETADA");
                intent.putExtra("uid", citaSelected.getUid());
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
        MenuActivity.redirectActivity(this, ProfileActivity.class);
        //Toast.makeText(this, "Boton Perfil", Toast.LENGTH_LONG).show();

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
        recreate();
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

    public void ClickNombre_Cita(View view){
        Intent i = new Intent( this, QRLector.class);
        startActivity(i);
    }
}