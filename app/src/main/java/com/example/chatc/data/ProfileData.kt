package com.example.chatc.data

import com.google.firebase.storage.StorageReference

data class ProfileData(
    val imageRef : StorageReference?,
    val name : String,
    val email : String,
    val password : String,
    val confirmPassword : String
)