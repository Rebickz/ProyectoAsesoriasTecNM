package com.example.proyectoasesoriastecnm;

import android.app.NotificationChannel;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;


import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;



import java.util.Random;


public class Notification extends FirebaseMessagingService {
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size() > 0){
            String titulo = remoteMessage.getData().get("titulo");
            String detalle = remoteMessage.getData().get("detalle");
            crearNotificacion(titulo,detalle);

        }

    }

    public  void crearNotificacion(String titulo, String detalle){

        String id = "mensaje";

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder constructorNotification = new NotificationCompat.Builder(this,id);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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
