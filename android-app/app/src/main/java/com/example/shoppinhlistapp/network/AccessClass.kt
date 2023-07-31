package com.example.shoppinhlistapp.network

import kotlinx.serialization.Serializable
@Serializable
data class AccessClass(
    val username:String,
    val type:String,
    val listID:Int
)


