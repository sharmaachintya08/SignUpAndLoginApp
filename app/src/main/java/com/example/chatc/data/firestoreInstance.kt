package com.example.chatc.data

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference

class firestoreInstance(
    private val imageRef : StorageReference?,
    private val name: String,
    private val email : String,
    private val password : String,
    private val confirmPassword : String){
    fun addInstance(){
        val db = Firebase.firestore
        val user = hashMapOf(
            "imageref" to imageRef,
            "name" to name,
            "email" to email,
            "password" to password,
            "confirmPassword" to confirmPassword
        )
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.i("firestore","${documentReference.id}")
            }
            .addOnFailureListener{ error ->
                Log.d("firestore","${error}")
            }
    }
    fun getData() : Boolean{
        var returnVal : Boolean
        val db = Firebase.firestore
        db.collection("users")
            .whereEqualTo("email","${email}")
            .whereEqualTo("password","${password}")
            .get()
            .addOnSuccessListener { document ->
                Log.d("isPresent","inside addonsuccesslistener")
                returnVal = true
            }
            .addOnFailureListener{ exception ->
                Log.d("getData","${exception}")
                returnVal = false
            }
        Log.d("isPresent","getData :- ${returnVal}")
        return returnVal
    }
}