package com.shoppinglist.routing

import com.shoppinglist.dao.personSize
import com.shoppinglist.model.Person
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.postgresql.ds.PGSimpleDataSource
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement


fun Route.PersonRouting()
{
    val dbConnection: Connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres",
        "postgres", "XCGT8Fdj"
    )

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

            val statement: PreparedStatement = dbConnection.prepareStatement(
                "Select * from Person where person.username=? and password =?").apply {
                setString(1, username)
                setString(2, password)
            }

            val resultSet = statement.executeQuery()
            if(!resultSet.next())
            {
                call.respondText(
                    "No customer with username $username",
                    status = HttpStatusCode.NotFound
                )
            }
            else {
                val person = Person(
                    firstName = resultSet.getString("FirstName"),
                    lastName = resultSet.getString("LastName"),
                    email = resultSet.getString("Email"),
                    username = resultSet.getString("UserName"),
                    password = resultSet.getString("Password"),
                    isVerified = false
                )
                call.respond(person)
            }
        }
        post {
            val person = call.receive<Person>()
            val statement: PreparedStatement = dbConnection.prepareStatement(
                "INSERT INTO Person (FirstName,LastName,UserName,Password,Email)" +
                    "Values (?,?,?,?,?)").apply {
                setString(1, person.firstName)
                setString(2, person.lastName)
                setString(3, person.username)
                setString(4, person.password)
                setString(5, person.email)
            }
            statement.executeUpdate()
            call.respondText("Person stored correctly", status = HttpStatusCode.Created)
        }
    }
}