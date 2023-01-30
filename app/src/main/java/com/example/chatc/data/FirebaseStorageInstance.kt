package com.example.chatc.data

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class FirebaseStorageInstance(private val userImage : ImageView) {
    init{
        Log.d("storage","${userImage}")
    }
    private val storage = Firebase.storage
    private val storageRef = storage.reference
    fun storageReference(){
        Log.d("storage","inside storage reference")

        //some problem in this part


        //this reference can be the cause of java.lang.nullpointerException
        val imageRef = storageRef.child("userImages")

        Log.d("storage","${imageRef}")

        //get the data from the imageview as bytes
        userImage.isDrawingCacheEnabled = true


        userImage.buildDrawingCache()
        val bitmap = userImage.getDrawingCache()

        //-----------here is the problem--------------
        //val bitmap = (userImage.drawable as BitmapDrawable).bitmap

        Log.d("storage","${bitmap}")

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val data = baos.toByteArray()

        Log.d("storage","${data}")

        var uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener{ exception ->
            Log.d("imageUploadError","${exception}")
        }.addOnSuccessListener { taskSnapshot ->
            Log.i("imageUploadSuccess","${taskSnapshot}")
        }
    }
}