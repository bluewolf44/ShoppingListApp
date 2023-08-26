package com.shoppinglist.dao

import com.shoppinglist.model.*

interface AccessDAO {
    suspend fun getAccess() : List<AccessClass>
    suspend fun createNewAccess(username : String, password : String, otherUsername:String,listId:Int,type: String) : AccessClass?
    suspend fun createNewAccessType(type:String) :TypeOfAccess?
    suspend fun removeAccess(username : String, password : String, otherUsername:String,listId:Int) : Boolean
    suspend fun getAccesses(username : String, password : String, listId:Int) : List<AccessClass>
    suspend fun getAllAccessesForPerson(person:Person?) : List<AccessClass>
    suspend fun changeAccesses(username : String, password : String, otherUsername:String, listId:Int) : Boolean
    suspend fun getAccessesType(otherUsername: String, listId:Int): AccessClass?
}