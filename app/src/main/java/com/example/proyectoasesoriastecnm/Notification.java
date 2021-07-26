package com.example.proyectoasesoriastecnm;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;




import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.NotNull;

import java.util.Random;


public class Notification extends FirebaseMessagingService {
/*
    @Override
    public void onNewToken(String tokenUsuario) {
        super.onNewToken(tokenUsuario);

    }

    private void saveToken(String token){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tablaUsuarios").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.child("token").setValue(token);
    }
*/
    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size() > 0){

            String titulo = remoteMessage.getData().get("titulo");
            String detalle = remoteMessage.getData().get("detalle");

            afterOreo(titulo,detalle);




        }




    }

    public  void  afterOreo(String titulo, String detalle){

        String id = "mensaje";

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder constructorNotification = new NotificationCompat.Builder(this,id);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(id,"nueva", NotificationManager.IMPORTANCE_HIGH);
            nc.setShowBadge(true);
            assert  nm!= null;
            nm.createNotificationChannel(nc);
        }

        constructorNotification.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(titulo)
                .setSmallIcon(R.drawable.tecnm2)
                .setContentText(detalle)
                .setContentInfo("Nuevo")
                .setContentIntent(clickNotify());
        Random random = new Random();
        int idNotify = random.nextInt(8000);
        assert  nm != null;
        nm.notify(idNotify,constructorNotification.build());
    }

    public PendingIntent clickNotify(){
        Intent intentNoti = new Intent(this,MainActivity.class);
        intentNoti.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this,0,intentNoti,0);
    }
}
