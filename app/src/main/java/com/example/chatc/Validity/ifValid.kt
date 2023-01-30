package com.example.chatc.Validity

import android.content.Context
import android.widget.Toast
import com.google.firebase.storage.StorageReference

//bugs in if and else
class ifValid(
    private val imageRef : StorageReference ,
    private val name : String,
    private val email : String,
    private val password : String,
    private val confirmPassword : String
){
    fun signUpValid(con : Context) : Int {
        if(name.trim() == ""){
            Toast.makeText(con,"please fill the name field",Toast.LENGTH_SHORT)
                .show()
            return 1
        }else if(email.trim().equals("")){
            Toast.makeText(con,"please fill the email field",Toast.LENGTH_SHORT)
                .show()
            return 1
        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(con,"please fill the correct email",Toast.LENGTH_SHORT)
                .show()
            return 1
        }else if(password.trim().equals("")){
            Toast.makeText(con,"please fill the password field",Toast.LENGTH_SHORT)
                .show()
            return 1
        }else if(confirmPassword.trim().equals("")){
            Toast.makeText(con,"please fill the confirm password field",Toast.LENGTH_SHORT)
                .show()
            return 1
        }else if(!password.trim().equals(confirmPassword)){
            Toast.makeText(con,"password and confirm password field does not match",Toast.LENGTH_SHORT)
                .show()
            return 1
        }else{
            Toast.makeText(con,"successfully signed up",Toast.LENGTH_SHORT)
                .show()
            return 0
        }
    }
}