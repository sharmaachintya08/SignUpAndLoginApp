package com.example.chatc.Message

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatc.R
import com.example.chatc.data.dummyData

open class messageBox : AppCompatActivity() {
    private lateinit var messageEditText : EditText
    private lateinit var sendButton : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_box)
        recyclerViewReference()
        noteText()
        getData()
    }
    fun recyclerViewReference(){
        val recyclerView = findViewById<RecyclerView>(R.id.textBox)
        recyclerView.layoutManager = LinearLayoutManager(this@messageBox,
            RecyclerView.VERTICAL,true)
        val adapter = MessageBoxAdapter(dummyData.getDummData())
        recyclerView.adapter = adapter
    }
    fun noteText(){
        getReference()
        sendButton.setOnClickListener(View.OnClickListener { view ->
            val text = messageEditText.text.toString()
            Log.d("text",text)
        })
    }
    fun getReference(){
        messageEditText = findViewById(R.id.sendMessage)
        sendButton = findViewById(R.id.sendMessageButton)
    }
    fun getData(){
        val intent = Intent()
        val email = intent.getCharArrayExtra("email").toString()
        Log.i("getData",email)
    }
}