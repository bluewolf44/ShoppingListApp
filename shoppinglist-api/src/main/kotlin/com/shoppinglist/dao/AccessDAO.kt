package com.shoppinglist.dao

import com.shoppinglist.model.*

interface AccessDAO {
    suspend fun createNewAccess(username : String, password : String, otherUsername:String,listId:Int) : AccessClass?
    suspend fun removeAccess(username : String, password : String, otherUsername:String,listId:Int) : AccessClass?
    suspend fun getAccesses(username : String, password : String, id:String) : List<AccessClass>
    suspend fun getAllAccessesForPerson(person:Person?) : List<AccessClass>
    suspend fun changeAccesses(username : String, password : String, otherUsername:String) : AccessClass?
}