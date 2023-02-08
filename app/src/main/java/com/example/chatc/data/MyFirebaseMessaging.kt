package com.example.chatc.data

import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.chatc.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

object MyFirebaseMessaging : FirebaseMessagingService() {
    val getToken = FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
        if(!task.isSuccessful){
            Log.i("token",task.exception.toString())
            return@OnCompleteListener
        }
        Log.i("token",task.result.toString())
    })
}