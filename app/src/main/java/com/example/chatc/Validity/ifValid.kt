package com.example.chatc.Validity

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.storage.StorageReference

class ifValid(
    private val name : EditText?,
    private val email : EditText?,
    private val password : EditText?,
    private val confirmPassword : EditText?
){
    fun signUpValid(con : Context) : Boolean {
        if(name?.text.toString().trim().isEmpty()){
            Toast.makeText(con,"please fill the name field",Toast.LENGTH_SHORT)
                .show()
            return false
        }else if(email?.text.toString().trim().isEmpty()){
            Toast.makeText(con,"please fill the email field",Toast.LENGTH_SHORT)
                .show()
            return false
        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email?.text.toString()).matches()){
            Toast.makeText(con,"please fill the correct email",Toast.LENGTH_SHORT)
                .show()
            return false
        }else if(password?.text.toString().trim().isEmpty()){
            Toast.makeText(con,"please fill the password field",Toast.LENGTH_SHORT)
                .show()
            return false
        }else if(confirmPassword?.text.toString().trim().isEmpty()){
            Toast.makeText(con,"please fill the confirm password field",Toast.LENGTH_SHORT)
                .show()
            return false
        }else if(!confirmPassword?.text.toString().equals(password?.text.toString())){
            Toast.makeText(con,"password and confirm password field do not match",Toast.LENGTH_SHORT)
                .show()
            return false
        }else{
            Toast.makeText(con,"successfully signed up",Toast.LENGTH_SHORT)
                .show()
            return true
        }
    }
    fun signInValid(con : Context) : Boolean{
        if(email?.text.toString().isEmpty()){
            Toast.makeText(con,"please fill the email field",Toast.LENGTH_SHORT)
                .show()
            return false
        }else if(password?.text.toString().isEmpty()){
            Toast.makeText(con,"please fill the password field",Toast.LENGTH_SHORT)
                .show()
            return false
        }else{
            Toast.makeText(con,"successfully signed in",Toast.LENGTH_SHORT)
                .show()
            return true
        }
    }
}