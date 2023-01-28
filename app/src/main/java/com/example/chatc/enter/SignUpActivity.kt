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

class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpButton : Button
    private lateinit var signInButton : TextView
    private lateinit var guestContinueButton : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUpButton = findViewById(R.id.buttonSignUp)
        signInButton = findViewById(R.id.textSignIn)
        guestContinueButton = findViewById(R.id.guestContinue)
        signUpButton.setOnClickListener(View.OnClickListener { view ->
            startIntent(1)
        })
        signInButton.setOnClickListener(View.OnClickListener { view ->
            startIntent(2)
        })
        guestContinueButton.setOnClickListener(View.OnClickListener { view ->
            startIntent(3)
        })
    }
    private fun startIntent(param : Int){
        lateinit var intent : Intent
        if(param == 1){
            Toast.makeText(this@SignUpActivity,"sign up details stored",Toast.LENGTH_SHORT)
                .show()
        }else if(param == 2){
            intent = Intent(this@SignUpActivity,signInActivity::class.java)
            startActivity(intent)
        }else if(param == 3){
            intent = Intent(this@SignUpActivity,guest::class.java)
            startActivity(intent)
        }else{
            Log.d("debug","no button clicked")
        }
    }
}