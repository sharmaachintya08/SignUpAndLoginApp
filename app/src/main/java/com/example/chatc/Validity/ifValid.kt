package com.example.chatc.Validity

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.chatc.data.MyCallBack
import com.example.chatc.data.firestoreInstance
import com.google.firebase.storage.StorageReference

class ifValid (
    private val con : Context,
    private val name : EditText?,
    private val email : EditText?,
    private val password : EditText?,
    private val confirmPassword : EditText?
) {
    private val fInstance = firestoreInstance(con, name?.text.toString(), email?.text.toString(), password?.text.toString(), confirmPassword?.text.toString())
    fun signUpValid(con: Context): Boolean {
        if (name?.text.toString().trim().isEmpty()) {
            Toast.makeText(con, "please fill the name field", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (email?.text.toString().trim().isEmpty()) {
            Toast.makeText(con, "please fill the email field", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email?.text.toString()).matches()) {
            Toast.makeText(con, "please fill the correct email", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (password?.text.toString().trim().isEmpty()) {
            Toast.makeText(con, "please fill the password field", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (confirmPassword?.text.toString().trim().isEmpty()) {
            Toast.makeText(con, "please fill the confirm password field", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (!confirmPassword?.text.toString().equals(password?.text.toString())) {
            Toast.makeText(
                con,
                "password and confirm password field do not match",
                Toast.LENGTH_SHORT
            )
                .show()
            return false
        } else {
            return true
        }
    }

    fun signInValid(con: Context): Boolean {
        if (email?.text.toString().isEmpty()) {
            Toast.makeText(con, "please fill the email field", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (password?.text.toString().isEmpty()) {
            Toast.makeText(con, "please fill the password field", Toast.LENGTH_SHORT)
                .show()
            return false
        } else {
            return true
        }
    }

    fun guestValid(con: Context): Boolean {
        if (name?.text.toString().isEmpty()) {
            Toast.makeText(con, "please fill the name field", Toast.LENGTH_SHORT)
                .show()
            return false
        } else {
            Toast.makeText(con, "welcome as guest", Toast.LENGTH_SHORT)
                .show()
            return true
        }
    }
    fun returnVal(){
        fInstance.getValue()
    }
}