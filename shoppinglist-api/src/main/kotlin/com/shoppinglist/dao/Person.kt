package com.shoppinglist.dao


import org.jetbrains.exposed.sql.*

data class Person(val firstName: String, val lastName: String, val userName: String, val password: String, val email: String,val isVerified:Boolean)

object Persons : Table() {
    val firstName  = varchar("firstName",255)
    val lastName = varchar("lastName",255)

    val userName = varchar("userName",255)
    val password = varchar("password",255)
    val email = varchar("email",255)
    val isVerified = false

    override val primaryKey = PrimaryKey(userName)
}