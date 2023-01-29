package com.example.chatc.data

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class firestoreInstance(
    private val imageURL : String,
    private val name: String,
    private val email : String,
    private val password : String,
    private val confirmPassword : String){
    fun addInstance(){
        val db = Firebase.firestore
        val user = hashMapOf(
            "imageurl" to imageURL,
            "name" to name,
            "email" to email,
            "password" to password,
            "confirmPassword" to confirmPassword
        )
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.i("addedDetails","${documentReference.id}")
            }
            .addOnFailureListener{ error ->
                Log.d("error","${error}")
            }
    }
}