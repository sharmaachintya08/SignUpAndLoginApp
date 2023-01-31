package com.example.chatc.enter

import android.content.ContentValues.TAG
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
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class signInActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var signInButton : Button
    private lateinit var createNewAccountText : TextView
    private lateinit var guestContinue : TextView

    private lateinit var email : EditText
    private lateinit var password : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        signInButton = findViewById(R.id.buttonSignIn)
        createNewAccountText = findViewById(R.id.textCreateNewAccount)
        guestContinue = findViewById(R.id.guestContinue)

        email = findViewById(R.id.inputEmail)
        password = findViewById(R.id.inputPassword)

        signInButton.setOnClickListener(this@signInActivity)
        createNewAccountText.setOnClickListener(this@signInActivity)
        guestContinue.setOnClickListener(this@signInActivity)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.buttonSignIn -> intentStart(1)
            R.id.textCreateNewAccount -> intentStart(2)
            R.id.guestContinue -> intentStart(3)
        }
    }
    private fun intentStart(param: Int){
        lateinit var intent: Intent
        if(param == 1){
            val validity = ifValid(null,email,password,null).signInValid(this@signInActivity)
            if(validity == true){
                //do something
            }
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
}
