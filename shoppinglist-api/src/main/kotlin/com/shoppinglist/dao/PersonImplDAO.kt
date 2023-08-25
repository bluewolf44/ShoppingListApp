package com.shoppinglist.dao

import com.shoppinglist.model.*
import com.shoppinglist.dao.DatabaseFactory.dbQuery
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*

class PersonImplDAO : PersonDAO {
    private fun resultRowToPerson(row: ResultRow) = Person(
        firstName = row[Persons.firstName],
        lastName = row[Persons.lastName],
        username = row[Persons.username],
        password = row[Persons.password],
        email = row[Persons.email],
        isVerified = row[Persons.isVerified],
    )

    override suspend fun allPeople(): List<Person> = dbQuery {
        Persons.selectAll().map(::resultRowToPerson)
    }

    override suspend fun getPerson(username: String, password: String): Person? = dbQuery {
        Persons
            .select { Persons.username eq username;Persons.password eq password }
            .map(::resultRowToPerson)
            .singleOrNull()
    }

    override suspend fun addPerson(person: Person): Person? = dbQuery {
        val insertStatement = Persons.insert {
            it[Persons.firstName] = person.firstName
            it[Persons.lastName] =  person.lastName
            it[Persons.username] =  person.username
            it[Persons.password] =  person.password
            it[Persons.email] =  person.email
            it[Persons.isVerified] =  person.isVerified
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToPerson)
    }
}

val personDao: PersonDAO = PersonImplDAO().apply {
    runBlocking {
        if(allPeople().isEmpty()) {
            addPerson(Person("Dave","West","Blue","Password","@gmail",false))
            println(getPerson("Blue","Password")?.firstName);
        }
    }
}
