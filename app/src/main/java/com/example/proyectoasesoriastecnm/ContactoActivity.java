package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
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

public class ContactoActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Button mSubmitRate;
    RatingBar mStarts;
    LinearLayout mRate;
    private FirebaseUser user;
    FirebaseDatabase database;
    private DatabaseReference mRef;
    private String userID;
    TextInputEditText contactmssg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        drawerLayout = findViewById(R.id.drawer_layout);

        contactmssg = (TextInputEditText) findViewById(R.id.CommentEdit_Text);




;
        mStarts = (RatingBar) findViewById(R.id.stars);
        mSubmitRate= (Button) findViewById(R.id.submit_rate);
        mRate = (LinearLayout) findViewById(R.id.rate);
        database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        mRef= database.getReference().child("tablaRating");

        List<String> Ratings = new ArrayList<>();
        List<Integer> RatingStarts = new ArrayList<>();

   /*     ValueEventListener LoadData = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Ratings.clear();
                    for(DataSnapshot rtg:snapshot.getChildren()){
                        Ratings.add(rtg.child("uid").getValue().toString());
                        RatingStarts.add(Integer.parseInt(rtg.child("rating").getValue().toString()));
                    }
                    for(int i=0;i<Ratings.size();i++){
                        Toast.makeText(ContactoActivity.this,Ratings.get(i),Toast.LENGTH_LONG);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        };
        mRef.addValueEventListener(LoadData);
        if(!Ratings.contains(userID)){
            mRate.setVisibility(View.VISIBLE);
        }
        */
        mStarts.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating>0){
                    mSubmitRate.setEnabled(true);
                }
                else{
                    mSubmitRate.setEnabled(false);
                }
            }
        });

        mSubmitRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate r = new rate(Float.toString(mStarts.getRating()),userID);
                mRef.child(userID).setValue(r);
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
        MenuActivity.redirectActivity(this,ProfileActivity.class);
        //Toast.makeText(this, "Boton Agendar", Toast.LENGTH_LONG).show();

    }

    public void ClickAgendar(View view){
        //Redireccionar
        //redirectActivity(this, );
        recreate();
    }

    public void ClickCitas(View view){
        //Redireccionar
        //redirectActivity(this, );
        MenuActivity.redirectActivity(this,CitasV2.class);
        //Toast.makeText(this, "Boton Citas", Toast.LENGTH_LONG).show();
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

    public void ClickButton(View View){

        String mensaje_cont = contactmssg.getText().toString().trim();
        iniciarFirebase();

        Mensaje mensaje = new Mensaje();

        mensaje.setMensaje(mensaje_cont);
        mensaje.setUid(user.getUid());
        mensaje.setEmail(user.getEmail());

        mRef.child("tablaMensajes").child(mensaje.getUid()).setValue(mensaje);

        Toast.makeText(this, R.string.mensaje_contacto, Toast.LENGTH_LONG).show();
        MenuActivity.redirectActivity(this,MenuActivity.class);

    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference();
    }




}
class rate{

    String raten;
    String uid;

    public String getUid() {
        return uid;
    }
    public String getRaten() {
        return raten;
    }

    public void setRaten(String raten) {
        this.raten = raten;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public rate(String raten, String uid){
        this.raten = raten;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "{" +
                "raten='" + raten + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}