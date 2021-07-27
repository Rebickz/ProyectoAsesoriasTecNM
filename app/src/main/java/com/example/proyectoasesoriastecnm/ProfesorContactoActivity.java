package com.example.proyectoasesoriastecnm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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

public class ProfesorContactoActivity extends AppCompatActivity {

    DrawerLayout drawerLayoutP;
    private FirebaseUser user;
    FirebaseDatabase database;
    private DatabaseReference mRef;
    private RatingBar mRating;
    private Button mSubmit;
    private String userID;
    private LinearLayout mRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_contacto);
        drawerLayoutP= findViewById(R.id.profesor_drawer_layout);

        database = FirebaseDatabase.getInstance();
        mRating = (RatingBar) findViewById(R.id.starsP);
        mSubmit = (Button) findViewById(R.id.submit_rateP);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        mRef= database.getReference().child("tablaRating");
        mRate = (LinearLayout) findViewById(R.id.rateP);

       /* List<String> Ratings = new ArrayList<>();
        List<Integer> RatingStarts = new ArrayList<>();

        ValueEventListener LoadData = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Ratings.clear();
                    for(DataSnapshot rtg:snapshot.getChildren()){
                        Ratings.add(rtg.child("uid").getValue().toString());
                        RatingStarts.add((Integer) rtg.child("rating").getValue());
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
        mRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating>0){
                    mSubmit.setEnabled(true);
                }
                else{
                    mSubmit.setEnabled(false);
                }
            }
        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate r = new rate(Float.toString(mRating.getRating()),userID);
                mRef.child(userID).setValue(r);
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
        recreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Cerrar drawer
        ProfesorMenuActivity.closeDrawer(drawerLayoutP);
    }
}