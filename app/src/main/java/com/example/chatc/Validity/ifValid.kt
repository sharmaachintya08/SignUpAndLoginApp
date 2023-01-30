package com.example.chatc.Validity

import com.google.firebase.storage.StorageReference

class ifValid(
    private val imageRef : StorageReference,
    private val name : String,
    private val email : String,
    private val password : String,
    private val confirmPassword : String
){
}