package com.shoppinglist.dao

import com.shoppinglist.model.ListClass
import com.shoppinglist.model.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

var listSize = 1
var personSize = 1

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.postgresql.Driver"
        val jdbcURL = "jdbc:postgresql://localhost:5433/cheese"
        var user = "Blue"
        var password = "XCGT8Fdj"
        val database = Database.connect(jdbcURL, driverClassName, user, password)
        transaction(database) {
            SchemaUtils.create(Persons)
            SchemaUtils.create(Lists)
            SchemaUtils.create(Accesses)
            SchemaUtils.create(TypeOfAccesses)
        }
    }
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

}