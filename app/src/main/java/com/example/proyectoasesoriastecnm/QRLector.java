package com.example.proyectoasesoriastecnm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.vision.CameraSource;

public class QRLector extends AppCompatActivity {

    DrawerLayout drawerLayout;
    //private CameraSource cameraSource;
    private SurfaceView cameraView;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private String token = "";
    private String tokenanterior = "";
    private SurfaceHolder surfaceholder;
    private Camera camara;
    private boolean encendida;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrlector2);
        //drawerLayout = findViewById(R.id.drawer_layout);

        boton = (Button)findViewById(R.id.boton);
        cameraView = (SurfaceView)findViewById(R.id.camera_view);

        surfaceholder = cameraView.getHolder();

        encendida=false;
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    startCamera();
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });

    }

    private void startCamera() throws Exception{
        if (ActivityCompat.shouldShowRequestPermissionRationale(QRLector.this,
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




