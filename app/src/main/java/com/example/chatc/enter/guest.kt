package com.example.chatc.enter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.chatc.R
import com.example.chatc.Validity.ifValid

class guest : AppCompatActivity(),View.OnClickListener {
    private lateinit var continueGuestButton : Button
    private lateinit var createNewAccountButton : TextView
    private lateinit var signInButton : TextView

    private lateinit var name : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        continueGuestButton = findViewById(R.id.continueAsGuest)
        createNewAccountButton = findViewById(R.id.textCreateNewAccount)
        signInButton = findViewById(R.id.textSignIn)

        name = findViewById(R.id.inputName)

        continueGuestButton.setOnClickListener(this@guest)
        createNewAccountButton.setOnClickListener(this@guest)
        signInButton.setOnClickListener(this@guest)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.continueAsGuest -> startIntent(1)
            R.id.textCreateNewAccount -> startIntent(2)
            R.id.textSignIn -> startIntent(3)
        }
    }
    private fun startIntent(param : Int){
        lateinit var intent : Intent
        if(param == 1){
            val validity = ifValid(null,name,null,null,null)
                .guestValid(this@guest)
            if(validity == true){
                //do something
            }
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