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
                "Select * from Person INNER JOIN Access on Person.PersonID = Access.PersonID where username=? and password =?").apply {
                setString(1,  username)
                setString(2, password)
            }

            val resultSet = statement.executeQuery()
            val userNames = mutableListOf<String>()
            while (resultSet.next()) {
                userNames += resultSet.getString("ListID")
            }
            if(userNames.isEmpty()){
                call.respondText(
                    "No customer with username $username",
                    status = HttpStatusCode.NotFound
                )
            }
            call.respond(userNames)
        }
        post {
            val person = call.receive<Person>()
            val statement: PreparedStatement = dbConnection.prepareStatement(
                "INSERT INTO Person (PersonID,FirstName,LastName,UserName,Password,Email)" +
                    "Values (?,?,?,?,?,?)").apply {
                setInt(1, personSize++)
                setString(2, person.firstName)
                setString(3, person.lastName)
                setString(4, person.userName)
                setString(5, person.password)
                setString(6, person.email)
            }
            statement.executeUpdate()
            call.respondText("Person stored correctly", status = HttpStatusCode.Created)
        }
    }
}