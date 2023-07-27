package com.shoppinglist.model

import kotlinx.serialization.Serializable
@Serializable
data class Person(
    val firstName: String,
    val lastName: String,
    val email: String,
    val userName: String,
    val password:String)

