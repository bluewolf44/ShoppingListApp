package com.shoppinglist.model

import kotlinx.serialization.Serializable
@Serializable
data class ListCreate(
    val listDescription:String,
    val listName:String,
)
