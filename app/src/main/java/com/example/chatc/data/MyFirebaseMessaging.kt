package com.example.chatc.data

import android.os.Message
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.chatc.R
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

    override fun onMessageReceived(message: RemoteMessage) {
        Log.i("message","${message.data.get("message")}")
        Data.setData(message.data.get("message").toString(),message.data.get("email").toString())
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