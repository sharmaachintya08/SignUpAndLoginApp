package com.example.chatc.data

import com.google.firebase.storage.StorageReference

data class ProfileData(
    val imageRef : StorageReference?,
    val name : String,
    val message : String
)

object dummyData{
    fun getDummData(): ArrayList<ProfileData>{
        val item1 = ProfileData(null,"a","message a")
        val item2 = ProfileData(null,"b","message b")
        val item3 = ProfileData(null,"c","message c")
        val item4 = ProfileData(null,"d","message d")
        val item5 = ProfileData(null,"e","message e")
        val item6 = ProfileData(null,"f","message f")
        val item7 = ProfileData(null,"g","message g")
        val item8 = ProfileData(null,"h","message h")
        val item9 = ProfileData(null,"i","message i")
        val item10 = ProfileData(null,"j","message j")
        val item11 = ProfileData(null,"k","message k")
        val item12 = ProfileData(null,"l","message l")

        val dataList : ArrayList<ProfileData> = ArrayList()
        dataList.add(item1)
        dataList.add(item2)
        dataList.add(item3)
        dataList.add(item4)
        dataList.add(item5)
        dataList.add(item6)
        dataList.add(item7)
        dataList.add(item8)
        dataList.add(item9)
        dataList.add(item10)
        dataList.add(item11)
        dataList.add(item12)

        return dataList
    }
}