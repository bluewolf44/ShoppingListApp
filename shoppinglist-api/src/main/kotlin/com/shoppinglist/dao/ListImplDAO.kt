package com.shoppinglist.dao

import com.shoppinglist.model.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import com.shoppinglist.dao.DatabaseFactory.dbQuery
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select


class ListImplDAO : ListDAO {
    private fun resultRowToList(row: ResultRow) = ListClass(
        listID = row[Lists.listID],
        accessType = row[Lists.accessType],
        listName = row[Lists.listName],
        listDescription = row[Lists.listDescription],
        lastUpdated = row[Lists.lastUpdated].toLocalDateTime(TimeZone.of("NZ")),
        dateCreated = row[Lists.dateCreated].toLocalDateTime(TimeZone.of("NZ")),
    )

    override suspend fun allList(): List<ListClass> = dbQuery {
        Lists.selectAll().map(::resultRowToList)
    }

    override suspend fun getListForPerson(username: String, password: String): List<ListClass> = dbQuery{
        Lists
            .select { Lists.listID inList accessDao.getAllAccessesForPerson(personDao.getPerson(username,password)).map{accessClass -> accessClass.listID } }
            .map(::resultRowToList)
    }

    override suspend fun getList(username: String, password: String, listId: Int): ListClass? {
         val iterable = listDao.getListForPerson(username,password).listIterator()
        while(iterable.hasNext())
        {
            val listClass = iterable.next()
            if (listClass.listID == listId)
            {
                return listClass;
            }
        }
        return null;
    }

    override suspend fun addList(list: ListClass): ListClass? = dbQuery{
        val insertStatement = Lists.insert {
            it[Lists.listID] = list.listID
            it[Lists.accessType] =  list.accessType
            it[Lists.listName] =  list.listName
            it[Lists.listDescription] =  list.listDescription
            it[Lists.lastUpdated] =  list.lastUpdated.toInstant(TimeZone.of("NZ"))
            it[Lists.dateCreated] =   list.dateCreated.toInstant(TimeZone.of("NZ"))
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToList)
    }

    override suspend fun deleteList(username: String, password: String, listId: Int) = dbQuery{
        TODO("Not yet implemented")
    }
}

val listDao: ListDAO = ListImplDAO().apply {
    runBlocking {
    }
}