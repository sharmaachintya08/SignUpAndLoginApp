package com.example.chatc.data

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

object FirebaseCloudMessaging : FirebaseMessagingService() {
    val getToken = FirebaseMessaging.getInstance().token
        .addOnCompleteListener{ task ->
            if(!task.isSuccessful){
                Log.d("token","fcm registeration token cannot be retrieved ${task.exception}")
            }
            val token = task.result
            Log.i("token","$token")
        }
}