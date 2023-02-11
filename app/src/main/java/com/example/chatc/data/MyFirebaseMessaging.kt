package com.example.chatc.data

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Message
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.chatc.Message.messageBox
import com.example.chatc.R
import com.example.chatc.enter.signInActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.*
import java.net.URL
import java.util.stream.IntStream.builder
import java.util.stream.Stream.builder
import javax.net.ssl.HttpsURLConnection

object MyFirebaseMessaging : FirebaseMessagingService() {
    private val TAG = "firebaseservice"
    private val topic : String = "chatc"
    private val KEY : String = "AAAARqFuhXg:APA91bGvFadFWLDUv3jNn2JuxGVhx_2M9YizaU3sugZUzpXxzjQ3Ikgg16NtmyiWbSyn297mReu52Ja-Is8GnUCJmG3zyknMgQnmmG_Dad4oxDjbi5uQgAPTdqWvX9HCROMUu7UnnrTY"

    val getToken = FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
        if(!task.isSuccessful){
            Log.i(TAG,task.exception.toString())
            return@OnCompleteListener
        }
        Log.i("token",task.result.toString())
    })

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.e("onMessageReceived: ", p0.data.toString())

        val title = p0.data.get("email")
        val content = p0.data.get("message")
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val intent = Intent(this,messageBox::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent = PendingIntent.getActivity(applicationContext,0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(applicationContext,"1")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(content)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(defaultSound)

        val notificationManager : NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1,notification.build())

    }

    fun sendMessage(message : String , email : String){
        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch{
            val endpoint = "https://fcm.googleapis.com/fcm/send"
            try{
                val url = URL(endpoint)
                val httpsURLConnection : HttpsURLConnection = url.openConnection() as HttpsURLConnection
                httpsURLConnection.readTimeout = 10000
                httpsURLConnection.connectTimeout = 15000
                httpsURLConnection.requestMethod = "POST"
                httpsURLConnection.doInput = true
                httpsURLConnection.doOutput = true

                httpsURLConnection.setRequestProperty("authorization","key=$KEY")
                httpsURLConnection.setRequestProperty("Content-Type","application/json")

                val body = JSONObject()
                val data = JSONObject()
                data.put("email",email)
                data.put("message",message)

                body.put("to","$topic")

                val outputStream: OutputStream = BufferedOutputStream(httpsURLConnection.outputStream)
                val writer = BufferedWriter(OutputStreamWriter(outputStream, "utf-8"))
                writer.write(body.toString())
                writer.flush()
                writer.close()
                outputStream.close()
                val responseCode: Int = httpsURLConnection.responseCode
                val responseMessage: String = httpsURLConnection.responseMessage
                Log.d("Response:", "$responseCode $responseMessage")
                var result = String()
                var inputStream: InputStream? = null
                inputStream = if (responseCode in 400..499) {
                    httpsURLConnection.errorStream
                } else {
                    httpsURLConnection.inputStream
                }

                if (responseCode == 200) {
                    Log.e("Success:", "notification sent $email \n $message")
                    // The details of the user can be obtained from the result variable in JSON format
                } else {
                    Log.e("Error", "Error Response")
                }
            }catch( e : Exception){
                e.printStackTrace()
            }
        }
    }
}