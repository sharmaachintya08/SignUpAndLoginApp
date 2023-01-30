package com.example.chatc.enter

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.chatc.R
import com.example.chatc.data.FirebaseStorageInstance
import java.net.URI

class SignUpActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var profileImage : ImageView
    private lateinit var signUpButton : Button
    private lateinit var signInButton : TextView
    private lateinit var guestContinueButton : TextView

    private val SELECT_IMAGE_CODE : Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        profileImage = findViewById(R.id.imageProfile)
        signUpButton = findViewById(R.id.buttonSignUp)
        signInButton = findViewById(R.id.textSignIn)
        guestContinueButton = findViewById(R.id.guestContinue)

        profileImage.setOnClickListener(this@SignUpActivity)
        signUpButton.setOnClickListener(this@SignUpActivity)
        signInButton.setOnClickListener(this@SignUpActivity)
        guestContinueButton.setOnClickListener(this@SignUpActivity)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.imageProfile -> startIntent(1)
            R.id.buttonSignUp -> startIntent(2)
            R.id.textSignIn -> startIntent(3)
            R.id.guestContinue -> startIntent(4)
        }
    }
    private fun startIntent(param : Int){
        lateinit var intent : Intent
        if(param == 1){
            getImage()
            //java.lang.nullpointerException
            Log.d("storage","${profileImage}")
            FirebaseStorageInstance(profileImage).storageReference()
        }
        else if(param == 2){
            Toast.makeText(this@SignUpActivity,"sign up details stored",Toast.LENGTH_SHORT)
                .show()
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
    private fun getImage(){
        val intent : Intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent,"title"),SELECT_IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            val uri = data?.data
            profileImage.setImageURI(uri)
        }
    }
}