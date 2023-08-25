package com.shoppinglist.dao

import com.shoppinglist.model.AccessClass
import com.shoppinglist.model.Person
import kotlinx.coroutines.runBlocking

class AccessImpIDAO:AccessDAO {
    override suspend fun createNewAccess(
        username: String,
        password: String,
        otherUsername: String,
        listId: Int
    ): AccessClass? {
        TODO("Not yet implemented")
    }

    override suspend fun removeAccess(
        username: String,
        password: String,
        otherUsername: String,
        listId: Int
    ): AccessClass? {
        TODO("Not yet implemented")
    }

    override suspend fun getAccesses(username: String, password: String, otherUsername: String): List<AccessClass> {
        TODO("Not yet implemented")
    }

    override suspend fun changeAccesses(username: String, password: String, otherUsername: String): AccessClass? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllAccessesForPerson(person: Person?): List<AccessClass> {
        TODO("Not yet implemented")
    }
}
val accessDao: AccessDAO = AccessImpIDAO().apply {
    runBlocking {
    }
}