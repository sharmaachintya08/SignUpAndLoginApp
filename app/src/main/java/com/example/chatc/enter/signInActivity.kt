package com.example.chatc.enter

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.chatc.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class signInActivity : AppCompatActivity() {
    private lateinit var signInButton : Button
    private lateinit var createNewAccountText : TextView
    private lateinit var guestContinue : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        signInButton = findViewById(R.id.buttonSignIn)
        createNewAccountText = findViewById(R.id.textCreateNewAccount)
        guestContinue = findViewById(R.id.guestContinue)
        signInButton.setOnClickListener( View.OnClickListener { view ->
            intentStart(1)
        })
        createNewAccountText.setOnClickListener(View.OnClickListener { view ->
            intentStart(2)
        })
        guestContinue.setOnClickListener(View.OnClickListener { view ->
            intentStart(3)
        })
    }
    private fun intentStart(param: Int){
        lateinit var intent: Intent
        if(param == 1){
            Toast.makeText(this@signInActivity,"sign in button",Toast.LENGTH_SHORT)
                .show()
        }else if (param == 2){
            intent = Intent(this@signInActivity,SignUpActivity::class.java)
            startActivity(intent)
        }else if ( param == 3){
            intent = Intent(this@signInActivity,guest::class.java)
            startActivity(intent)
        }else{
            Log.d("debug","no if else option selected")
        }
    }
    private fun startFireStore(){
        val db = Firebase.firestore
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}
