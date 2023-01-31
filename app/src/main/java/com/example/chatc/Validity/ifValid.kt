package com.example.chatc.Validity

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.chatc.data.firestoreInstance
import com.google.firebase.storage.StorageReference

class ifValid(
    private val storageRef : StorageReference?,
    private val name : EditText?,
    private val email : EditText?,
    private val password : EditText?,
    private val confirmPassword : EditText?
){
    fun signUpValid(con : Context) : Boolean {
        if(isPresent()){
            Toast.makeText(con,"account is present, please Login",Toast.LENGTH_SHORT)
                .show()
            return false
        }else if(name?.text.toString().trim().isEmpty()){
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
            sendDataToFireStore()
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
            return true
        }
    }
    fun guestValid(con : Context) : Boolean{
        if(name?.text.toString().isEmpty()){
            Toast.makeText(con,"please fill the name field",Toast.LENGTH_SHORT)
                .show()
            return false
        }else{
            Toast.makeText(con,"welcome as guest",Toast.LENGTH_SHORT)
                .show()
            return true
        }
    }
    fun sendDataToFireStore(){
        firestoreInstance(
            storageRef,
            name?.text.toString(),
            email?.text.toString(),
            password?.text.toString(),
            confirmPassword?.text.toString()
        ).addInstance()
    }
    fun getDataFromFireStore() : Boolean{
        val returnVal = firestoreInstance(
            storageRef,
            name?.text.toString(),
            email?.text.toString(),
            password?.text.toString(),
            confirmPassword?.text.toString()
        ).getData()
        return returnVal
    }
    fun isPresent() : Boolean{
        val returnVal = firestoreInstance(
            storageRef,
            name?.text.toString(),
            email?.text.toString(),
            password?.text.toString(),
            confirmPassword?.text.toString()
        ).getData()
        Log.d("isPresent"," isPresent :- ${returnVal}")
        return returnVal
    }
}