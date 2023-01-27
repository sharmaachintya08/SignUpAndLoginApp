package com.example.chatc.enter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.chatc.R
import com.google.android.material.button.MaterialButton

class signInActivity : AppCompatActivity() {
    private lateinit var signInButton : Button
    private lateinit var createNewAccountText : TextView
    private lateinit var guestContinue : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        signInButton = findViewById(R.id.buttonSignIn)
        createNewAccountText = findViewById(R.id.textCreateNewAccount)
        guestContinue = findViewById(R.id.guestContinue)
        signInButton.setOnClickListener( View.OnClickListener { view ->
            Toast.makeText(this@signInActivity,"sign in button",Toast.LENGTH_SHORT)
                .show()
        })
        createNewAccountText.setOnClickListener(View.OnClickListener { view ->
            Toast.makeText(this@signInActivity,"create new acccount button",Toast.LENGTH_SHORT)
                .show()
        })
        guestContinue.setOnClickListener(View.OnClickListener { view ->
            Toast.makeText(this@signInActivity,"guest continue button",Toast.LENGTH_SHORT)
                .show()
        })
    }

}
