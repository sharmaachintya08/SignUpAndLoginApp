package com.example.chatc.enter

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.chatc.R
import com.example.chatc.Validity.ifValid
import com.google.firebase.storage.StorageReference
import java.net.URI

class SignUpActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var signUpButton : Button
    private lateinit var signInButton : TextView
    private lateinit var guestContinueButton : TextView

    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var confirmPassword : EditText

    private val SELECT_IMAGE_CODE : Int = 1
    private var storageRef : StorageReference? = null
    private lateinit var validity : ifValid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUpButton = findViewById(R.id.buttonSignUp)
        signInButton = findViewById(R.id.textSignIn)
        guestContinueButton = findViewById(R.id.guestContinue)

        name = findViewById(R.id.inputName)
        email = findViewById(R.id.inputEmail)
        password = findViewById(R.id.inputPassword)
        confirmPassword = findViewById(R.id.inputConfirmPassword)

        signUpButton.setOnClickListener(this@SignUpActivity)
        signInButton.setOnClickListener(this@SignUpActivity)
        guestContinueButton.setOnClickListener(this@SignUpActivity)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.buttonSignUp -> startIntent(2)
            R.id.textSignIn -> startIntent(3)
            R.id.guestContinue -> startIntent(4)
        }
    }
    private fun startIntent(param : Int){
        lateinit var intent : Intent
        if(param == 2){
            validity = ifValid(this@SignUpActivity,storageRef,name,email,password,confirmPassword)
            if(validity.signUpValid(this@SignUpActivity)){
                validity.returnVal()
            }
        }else if(param == 3){
            intent = Intent(this@SignUpActivity,signInActivity::class.java)
            startActivity(intent)
        }else if(param == 4){
            intent = Intent(this@SignUpActivity,guest::class.java)
            startActivity(intent)
        }else{
            Log.d("debug","no button clicked")
        }
    }

}