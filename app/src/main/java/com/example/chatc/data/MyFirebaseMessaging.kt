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
    /*private val channelId = "chatc"
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        getFirebaseMessaging(message.notification?.title.toString(),message.notification?.body.toString())
    }
    fun getFirebaseMessaging(title : String,body : String){
        Log.i("token",title)
        val builder = NotificationCompat.Builder(applicationContext,channelId)
            .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
        val manager = NotificationManagerCompat.from(applicationContext)
        manager.notify(101,builder.build())
    }*/
    val getToken = FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
        if(!task.isSuccessful){
            Log.i("token",task.exception.toString())
            return@OnCompleteListener
        }
        Log.i("token",task.result.toString())
    })
}