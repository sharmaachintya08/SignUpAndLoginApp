package com.example.chatc.data

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.example.chatc.Validity.ifValid
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import kotlin.properties.Delegates
import kotlin.reflect.typeOf

class firestoreInstance (
    private val imageRef : StorageReference?,
    private val name: String,
    private val email : String,
    private val password : String,
    private val confirmPassword : String) {

    private var dataList : MutableList<String> = mutableListOf()
    private var fDataList : MutableList<String> = mutableListOf()
    var fValue : Boolean = false
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
    fun getData(myCallBack : MyCallBack)  {
        val db = Firebase.firestore
        db.collection("users")
            .whereEqualTo("email", "${email}")
            .whereEqualTo("password", "${password}")
            .get()
            .addOnSuccessListener { document ->
                for (i in document) {
                    for (j in i.data.values) {
                        j?.let {
                            dataList.add(j.toString())
                        }
                    }
                }
                myCallBack.onCallBack(dataList)
                Log.d("debug","after calling")
            }
            .addOnFailureListener { exception ->
                Log.d("getData", "${exception}")
            }
    }
    fun getValue(){
        getData(object : MyCallBack{
            override fun onCallBack(dataList: MutableList<String>) {
                if(dataList.contains(email)&&dataList.contains(password)){

                }
            }
        })
    }
}
interface MyCallBack{
    fun onCallBack(dataList: MutableList<String>)
}