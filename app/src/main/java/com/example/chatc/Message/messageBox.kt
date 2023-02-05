package com.example.chatc.Message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatc.R
import com.example.chatc.data.dummyData

open class messageBox : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_box)
        recyclerViewReference()
    }
    fun recyclerViewReference(){
        val recyclerView = findViewById<RecyclerView>(R.id.textBox)
        recyclerView.layoutManager = LinearLayoutManager(this@messageBox,
            RecyclerView.VERTICAL,true)
        val adapter = MessageBoxAdapter(dummyData.getDummData())
        recyclerView.adapter = adapter
    }
}