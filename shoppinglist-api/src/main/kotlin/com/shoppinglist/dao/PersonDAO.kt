package com.shoppinglist.dao

import com.shoppinglist.model.*

interface PersonDAO {
    suspend fun allPeople(): List<Person>
    suspend fun getPerson(username: String,password:String): Person?
    suspend fun addPerson(person:Person): Person?
}