package com.example.chatc.enter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.chatc.R

class guest : AppCompatActivity() {
    private lateinit var continueGuestButton : Button
    private lateinit var createNewAccountButton : TextView
    private lateinit var signInButton : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        continueGuestButton = findViewById(R.id.continueAsGuest)
        createNewAccountButton = findViewById(R.id.textCreateNewAccount)
        signInButton = findViewById(R.id.textSignIn)
        continueGuestButton.setOnClickListener(View.OnClickListener { view ->
            startIntent(1)
        })
        createNewAccountButton.setOnClickListener(View.OnClickListener { view ->
            startIntent(2)
        })
        signInButton.setOnClickListener(View.OnClickListener { view ->
            startIntent(3)
        })
    }
    private fun startIntent(param : Int){
        lateinit var intent : Intent
        if(param == 1){
            Toast.makeText(this@guest,"continueing as a guest",Toast.LENGTH_SHORT)
                .show()
        }else if(param == 2){
            intent = Intent(this@guest,SignUpActivity::class.java)
            startActivity(intent)
        }else if(param == 3){
            intent = Intent(this@guest,signInActivity::class.java)
            startActivity(intent)
        }else{
            Log.d("debug","no button clicked from guest.kt")
        }
    }
}