package com.shoppinglist.model


import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
@Serializable
data class Person(val firstName: String, val lastName: String, val username: String, val password: String, val email: String,val isVerified:Boolean)

object Persons : Table() {
    val firstName  = varchar("firstName",255)
    val lastName = varchar("lastName",255)

    val username = varchar("username",255)
    val password = varchar("password",255)
    val email = varchar("email",255)
    val isVerified = bool("isVerified")

    override val primaryKey = PrimaryKey(username)
}