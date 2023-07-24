package com.shoppinglist.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.postgresql.ds.PGSimpleDataSource
import java.sql.Connection
import java.sql.DriverManager


fun Route.PersonRouting()
{
    route("/person"){
        get{
            val dbConnection: Connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres", "XCGT8Fdj"
            )

            val statement = dbConnection.createStatement()
            val resultSet = statement.executeQuery("Select FirstName from Person")
            val userNames = mutableListOf<String>()
            while (resultSet.next()) {
                userNames += resultSet.getString("FirstName")
            }
            call.respond(userNames)
            //call.respond("test")
        }
    }
}