package com.example.shoppinhlistapp.network

import kotlinx.serialization.Serializable
@Serializable
data class ListCreate(
    val listDescription:String,
    val listName:String,
)
