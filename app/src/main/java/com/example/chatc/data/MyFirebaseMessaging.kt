package com.example.chatc.data

import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.chatc.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage

object MyFirebaseMessaging : FirebaseMessagingService() {
    private val TAG = "firebaseservice"
    val getToken = FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
        if(!task.isSuccessful){
            Log.i(TAG,task.exception.toString())
            return@OnCompleteListener
        }
        Log.i("token",task.result.toString())
    })

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(TAG,"From : ${message.from}")
        if(message.data.isNotEmpty()){
            Log.d(TAG,"Message data payload : ${message.data}")
        }
        message.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }
    }
}