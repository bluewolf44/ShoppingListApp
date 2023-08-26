package com.shoppinglist.dao

import com.shoppinglist.dao.DatabaseFactory.dbQuery
import com.shoppinglist.model.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList

class AccessImpIDAO:AccessDAO {
    override suspend fun getAccess(): List<AccessClass> = dbQuery{
        Accesses.selectAll().map(::resultRowToAccess)
    }

    private fun resultRowToAccess(row: ResultRow) = AccessClass(
        username = row[Accesses.username],
        listID = row[Accesses.listID],
        accessType = row[Accesses.accessType],
    )

    private fun resultRowToTypeOfAccess(row: ResultRow) = TypeOfAccess(
        accessType = row[TypeOfAccesses.accessType],
    )

    override suspend fun createNewAccess(username: String, password: String, otherUsername: String, listId: Int,type:String): AccessClass? = dbQuery {
            if(listDao.getList(username,password,listId)!= null || type == "own") {
                val insertStatement = Accesses.insert {
                    it[Accesses.username] = otherUsername
                    it[Accesses.listID] = listId
                    it[Accesses.accessType] = type
                }
                insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToAccess)
            }
            else
            {
                null
            }
        }

    override suspend fun removeAccess(username: String, password: String, otherUsername: String, listId: Int): Boolean = dbQuery{
        if (listDao.getList(username, password, listId) != null) {
            Accesses.deleteWhere { Accesses.listID eq listId;Accesses.username eq otherUsername } > 0
        } else
        {
            false
        }
    }

    override suspend fun getAccesses(username: String, password: String, listId: Int): List<AccessClass> = dbQuery{
        if (listDao.getList(username, password, listId) != null) {
            Accesses
                .select { Accesses.listID eq listId }
                .map(::resultRowToAccess)
        }
        else
        {
            ArrayList<AccessClass>(0)
        }
    }

    override suspend fun changeAccesses(username: String, password: String, otherUsername: String, listId:Int): Boolean = dbQuery{
        if (listDao.getList(username, password, listId) != null) {
            var typeOfAccess = accessDao.getAccessesType(otherUsername,listId)?.accessType
            Accesses.update({ Accesses.username eq username; Accesses.listID eq listId; Accesses.accessType eq "vie"}) {
                it[Accesses.accessType] = when(typeOfAccess)
                {
                    "vie"-> "edi"
                    "edi"-> "vie"
                    else -> {"vie"}
                }
            } > 0
        }else
        {
            false
        }

    }

     override suspend fun getAccessesType(otherUsername: String, listId:Int): AccessClass? = dbQuery{
         Accesses
             .select {Accesses.username eq otherUsername;Accesses.listID eq listId}
             .map (::resultRowToAccess)
             .singleOrNull()
    }

    override suspend fun getAllAccessesForPerson(person: Person?): List<AccessClass> = dbQuery{
        Accesses
            .select {Accesses.username eq (person?.username ?: "") }
            .map(::resultRowToAccess)
    }

    override suspend fun createNewAccessType(type: String): TypeOfAccess? = dbQuery{
        val insertStatement = TypeOfAccesses.insert {
            it[TypeOfAccesses.accessType] =  type
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToTypeOfAccess)
    }
}
val accessDao: AccessDAO = AccessImpIDAO().apply {
}