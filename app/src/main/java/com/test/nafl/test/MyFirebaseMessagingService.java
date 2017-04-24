package com.test.nafl.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;



//Esta clase no se usa//
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG="NumerologiaDominicana";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
       // String from = "";
        String from = remoteMessage.getFrom();
        Log.e(TAG, "Mensaje recivido de: "+ from);


        if (remoteMessage.getNotification()!=null)
        {
            Log.d(TAG,"Notificaciones: "+remoteMessage.getNotification().getBody());
        }
        if (remoteMessage.getData().size()>0)
        {
            Log.d(TAG,"Data: "+ remoteMessage.getData());
        }

    }
}
