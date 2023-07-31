package com.shoppinglist.routing

import com.shoppinglist.dao.listSize
import com.shoppinglist.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

fun Route.AccessRounting()
{
    val dbConnection: Connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres",
        "postgres", "XCGT8Fdj"
    )

    route("/Access")
    {
        post("{username}/{password}/{otherUsername}/{listId}") {
            val username = call.parameters["username"] ?: return@post call.respondText(
                "Missing username",
                status = HttpStatusCode.BadRequest
            )
            val password = call.parameters["password"] ?: return@post call.respondText(
                "Missing password",
                status = HttpStatusCode.BadRequest
            )
            val otherUsername = call.parameters["otherUsername"] ?: return@post call.respondText(
                "Missing otherUsername",
                status = HttpStatusCode.BadRequest
            )
            val listId = call.parameters["listId"] ?: return@post call.respondText(
                "Missing listId",
                status = HttpStatusCode.BadRequest
            )
            val statement: PreparedStatement = dbConnection.prepareStatement(
                "Select * from Person " +
                        "INNER JOIN Access on Person.UserName = Access.UserName " +
                        "INNER JOIN List on Access.ListID = List.ListID " +
                        "where person.username=? and password =? and list.listid =?"
            ).apply {
                setString(1, username)
                setString(2, password)
                setInt(3, listId.toInt())
            }
            val resultSet = statement.executeQuery()
            if(!resultSet.next())
            {
                call.respondText(
                    "Error someWhere",
                    status = HttpStatusCode.NotFound
                )
            }

            val statement2: PreparedStatement = dbConnection.prepareStatement(
                "Insert into Access (UserName,ListID,AccessType) " +
                        "values (?,?,'vie')").apply {
                setString(1, otherUsername)
                setInt(2, listId.toInt())
            }
            statement2.executeUpdate()

            call.respondText("ListAccess stored correctly", status = HttpStatusCode.Created)
        }

        get("{username}/{password}/{listId}") {
            val username = call.parameters["username"] ?: return@get call.respondText(
                "Missing username",
                status = HttpStatusCode.BadRequest
            )
            val password = call.parameters["password"] ?: return@get call.respondText(
                "Missing password",
                status = HttpStatusCode.BadRequest
            )
            val listId = call.parameters["listId"] ?: return@get call.respondText(
                "Missing listId",
                status = HttpStatusCode.BadRequest
            )

            val statement: PreparedStatement = dbConnection.prepareStatement(
                "select * from access where listid in " +
                        "(Select list.listID from Person INNER JOIN Access on Person.username = Access.username " +
                        "INNER JOIN List on Access.ListID = List.ListID " +
                        "where person.username=? and password =? and list.listID=?)"
            ).apply {
                setString(1, username)
                setString(2, password)
                setInt(3, listId.toInt())
            }

            val resultSet = statement.executeQuery()

            val accesses = mutableListOf<AccessClass>()

            while(resultSet.next())
            {
                accesses.add(AccessClass(resultSet.getString("Username"),resultSet.getString("accessType"),resultSet.getInt("listID")))
            }

            call.respond(accesses)
        }

        patch ("{username}/{password}/{listId}")
        {
            val username = call.parameters["username"] ?: return@patch call.respondText(
                "Missing username",
                status = HttpStatusCode.BadRequest
            )
            val password = call.parameters["password"] ?: return@patch call.respondText(
                "Missing password",
                status = HttpStatusCode.BadRequest
            )
            val listId = call.parameters["listId"] ?: return@patch call.respondText(
                "Missing listId",
                status = HttpStatusCode.BadRequest
            )
            val access = call.receive<AccessClass>()

            val statement: PreparedStatement = dbConnection.prepareStatement(
                "update Access set AccessType=? " +
                        "where listid in (" +
                        "Select list.listID from Person " +
                        "INNER JOIN Access on Person.username = Access.username " +
                        "INNER JOIN List on Access.ListID = List.ListID " +
                        "where person.username=? and password =? and list.listID=?) " +
                        "and username = ?").apply {
                setString(1, when(access.type)
                {
                    "vie"-> "edi"
                    "edi"-> "vie"
                    else -> {"vie"}
                })
                setString(2, username)
                setString(3, password)
                setInt(4, listId.toInt())
                setString(5,access.username)
            }
            statement.executeUpdate()

            call.respondText("ListAccess stored correctly", status = HttpStatusCode.Created)
        }
        delete("{username}/{password}/{otherUsername}/{listId}") {
            val username = call.parameters["username"] ?: return@delete call.respondText(
                "Missing username",
                status = HttpStatusCode.BadRequest
            )
            val password = call.parameters["password"] ?: return@delete call.respondText(
                "Missing password",
                status = HttpStatusCode.BadRequest
            )
            val otherUsername = call.parameters["otherUsername"] ?: return@delete call.respondText(
                "Missing otherUsername",
                status = HttpStatusCode.BadRequest
            )
            val listId = call.parameters["listId"] ?: return@delete call.respondText(
                "Missing listId",
                status = HttpStatusCode.BadRequest
            )

            val statement: PreparedStatement = dbConnection.prepareStatement(
                "delete from Access " +
                        "where listid in (" +
                        "Select list.listID from Person " +
                        "INNER JOIN Access on Person.username = Access.username " +
                        "INNER JOIN List on Access.ListID = List.ListID " +
                        "where person.username=? and password =? and list.listID=?) " +
                        "and username = ?").apply {
                setString(1, username)
                setString(2, password)
                setInt(3, listId.toInt())
                setString(4,otherUsername)
            }
            statement.executeUpdate()

            call.respondText("ListAccess stored correctly", status = HttpStatusCode.Created)
        }
    }
}