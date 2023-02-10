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
import com.example.chatc.data.Data

open class messageBox : AppCompatActivity() {
    private lateinit var messageEditText : EditText
    private lateinit var sendButton : ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageBoxAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_box)
        recyclerViewReference()
        noteText()
    }
    fun recyclerViewReference(){
        recyclerView = findViewById<RecyclerView>(R.id.textBox)
        recyclerView.layoutManager = LinearLayoutManager(this@messageBox,
            RecyclerView.VERTICAL,true)
        adapter = MessageBoxAdapter(Data.getData())
        recyclerView.adapter = adapter
    }
    fun noteText(){
        getReference()
        sendButton.setOnClickListener(View.OnClickListener { view ->
            val text = messageEditText.text.toString()
            val intent = intent
            val email = intent.getStringExtra("email").toString()
            Data.setData(text,email)
            adapter = MessageBoxAdapter(Data.getData())
        })
    }
    fun getReference(){
        messageEditText = findViewById(R.id.sendMessage)
        sendButton = findViewById(R.id.sendMessageButton)
    }
}