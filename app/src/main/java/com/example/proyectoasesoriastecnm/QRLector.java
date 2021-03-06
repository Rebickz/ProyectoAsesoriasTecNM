package com.example.proyectoasesoriastecnm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;


public class QRLector extends AppCompatActivity  {

    DrawerLayout drawerLayout;
    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private String token = "";
    private String tokenanterior = "";
    private SurfaceHolder surfaceholder;
    private Camera camara;
    private boolean encendida;
    private Button boton;
    private TextView txt;
    FirebaseDatabase database;
    DatabaseReference mref;
    String carrera;
    String email;
    String fecha;
    String horaAgendada;
    String lugar;
    String materia;
    String profesor;
    String semestre;
    String status;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrlector2);
        drawerLayout = findViewById(R.id.drawer_layout);

        txt = (TextView)findViewById(R.id.texto);
        boton = (Button)findViewById(R.id.boton);


        //cameraView = (SurfaceView)findViewById(R.id.camera_view);

        //surfaceholder = cameraView.getHolder();

        try {
            startCamera();
        } catch (Exception e) {
            e.printStackTrace();
        }

        encendida=false;
/*        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //startCamera();
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });
*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Cita c = new Cita();

        super.onActivityResult(requestCode, resultCode, data);
        carrera = getIntent().getStringExtra("carrera");
        email = getIntent().getStringExtra("email");
        fecha = getIntent().getStringExtra("fecha");
        horaAgendada = getIntent().getStringExtra("horaAgendada");
        lugar = getIntent().getStringExtra("lugar");
        materia = getIntent().getStringExtra("materia");
        profesor = getIntent().getStringExtra("profesor");
        semestre = getIntent().getStringExtra("semestre");
        status = getIntent().getStringExtra("COMPLETADA");
        uid = getIntent().getStringExtra("uid");

        c.setCarrera(carrera);
        c.setEmail(email);
        c.setFecha(fecha);
        c.setHorario(horaAgendada);
        c.setLugar(lugar);
        c.setMateria(materia);
        c.setProfesor(profesor);
        c.setSemestre(semestre);
        c.setStatus("COMPLETADA");
        c.setUid(uid);


        database = FirebaseDatabase.getInstance();
        database.getReference().child("tablaCitas").child(uid).setValue(c);


        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        String datos =  result.getContents();
        txt.setText(datos + "Status: COMPLETADA");
    }

    private void startCamera() throws Exception{

       /* if (ActivityCompat.shouldShowRequestPermissionRationale(QRLector.this,
                Manifest.permission.CAMERA)) {

            if(!encendida){
                camara = Camera.open();
                camara.setPreviewDisplay(surfaceholder);
                camara.startPreview();
                encendida=true;
            }else{
                camara.stopPreview();
                encendida=true;
                camara.release();
                encendida=false;
            }

        } else {

            ActivityCompat.requestPermissions(QRLector.this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }

        */


        new IntentIntegrator(this).initiateScan();

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


}
