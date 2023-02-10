package com.example.chatc.data

import com.google.firebase.storage.StorageReference

data class ProfileData(
    val name : String,
    val message : String
)

object Data{
    private var ftext : String = "lets chat everyone"
    private var femail : String = "bot"
    fun setData(text: String,email : String){
        ftext = text
        femail = email
    }
    fun getData() : ArrayList<ProfileData>{
        val dataList : ArrayList<ProfileData> = ArrayList()
        dataList.add(ProfileData(ftext,femail))
        return dataList
    }
}