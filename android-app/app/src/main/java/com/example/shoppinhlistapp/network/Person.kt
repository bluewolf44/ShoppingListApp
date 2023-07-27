package com.example.shoppinhlistapp.network

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val firstName: String,
    val lastName: String,
    val email: String,
    val userName: String,
    val password:String)

