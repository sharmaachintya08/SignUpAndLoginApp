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
    private val imageRef = storageRef.child("userImages")
    fun storageReference(){
        userImage.isDrawingCacheEnabled = true
        userImage.buildDrawingCache()
        val bitmap = userImage.getDrawingCache()
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val data = baos.toByteArray()
        var uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener{ exception ->
            Log.d("imageUploadError","${exception}")
        }.addOnSuccessListener { taskSnapshot ->
            Log.i("imageUploadSuccess","${taskSnapshot}")
        }
    }
}