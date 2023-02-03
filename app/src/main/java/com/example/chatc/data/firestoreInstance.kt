package com.example.chatc.data

import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.chatc.Message.messageBox
import com.example.chatc.Validity.ifValid
import com.example.chatc.enter.SignUpActivity
import com.example.chatc.enter.signInActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import kotlin.properties.Delegates
import kotlin.reflect.typeOf

class firestoreInstance (
    private val con : Context,
    private val imageRef : StorageReference?,
    private val name: String,
    private val email : String,
    private val password : String,
    private val confirmPassword : String) {

    private var dataList : MutableList<String> = mutableListOf()
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
                if(con is signInActivity){
                    if(dataList.contains(email)&&dataList.contains(password)){
                        var intent = Intent(con,messageBox::class.java)
                        con.startActivity(intent)

                    }else{
                        Toast.makeText(con,"id not present please signup",Toast.LENGTH_SHORT)
                            .show()
                    }
                }else if(con is SignUpActivity){
                    if(dataList.contains(email)&&dataList.contains(password)){
                        Toast.makeText(con,"id present please login",Toast.LENGTH_SHORT)
                            .show()
                        var intent = Intent(con,signInActivity::class.java)
                        con.startActivity(intent)
                    }else{
                        addInstance()
                        var intent = Intent(con,messageBox::class.java)
                        con.startActivity(intent)
                    }
                }
            }
        })
    }
}
interface MyCallBack{
    fun onCallBack(dataList: MutableList<String>)
}