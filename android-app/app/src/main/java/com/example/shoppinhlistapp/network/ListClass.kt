package com.example.shoppinhlistapp.network

import kotlinx.serialization.Serializable
@Serializable
data class ListClass(
    val listID:Int,
    val accessType:String,
    val listName:String,
    val listDescription:String,
    val lastUpdated:String,
    val dateCreated:String)

