package com.shoppinglist.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.postgresql.ds.PGSimpleDataSource
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement


fun Route.PersonRouting()
{
    route("/person") {
        get("{username}/{password}"){
            val username = call.parameters["username"] ?: return@get call.respondText(
                "Missing username",
                status = HttpStatusCode.BadRequest
            )
            val password = call.parameters["password"] ?: return@get call.respondText(
                "Missing password",
                status = HttpStatusCode.BadRequest
            )

            val dbConnection: Connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres", "XCGT8Fdj"
            )

            val statement: PreparedStatement = dbConnection.prepareStatement(
                "Select * from Person where UserName =? and Password=?").apply {
                setString(1,  username)
                setString(2, password)
            }

            val resultSet = statement.executeQuery()
            val userNames = mutableListOf<String>()
            while (resultSet.next()) {
                userNames += resultSet.getString("FirstName")
            }
            if(userNames.isEmpty()){
                call.respondText(
                    "No customer with username $username",
                    status = HttpStatusCode.NotFound
                )
            }

            call.respond(userNames)
        }
    }
}