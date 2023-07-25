package com.shoppinglist.routing

import com.shoppinglist.model.ListClass
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

fun Route.ListRounting()
{
    val dbConnection: Connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres",
        "postgres", "XCGT8Fdj"
    )

    route("/List")
    {
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
                "Select list.listid,accesstype,listname,listdescription,lastupdated,datecreated from Person " +
                        "INNER JOIN Access on Person.PersonID = Access.PersonID " +
                        "INNER JOIN List on Access.ListID = List.ListID " +
                        "where username=? and password =?").apply {
                setString(1,  username)
                setString(2, password)
            }

            val resultSet = statement.executeQuery()
            val lists = mutableListOf<ListClass>()
            while (resultSet.next()) {
                lists.add(ListClass(
                    resultSet.getInt("listid"),
                    resultSet.getString("accesstype"),
                    resultSet.getString("listname"),
                    resultSet.getString("listdescription"),
                    resultSet.getString("lastupdated"),
                    resultSet.getString("datecreated"),
                ))
            }

            call.respond(lists)

        }
    }

}