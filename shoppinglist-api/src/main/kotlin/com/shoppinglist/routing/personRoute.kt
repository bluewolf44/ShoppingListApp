package com.shoppinglist.routing

import com.shoppinglist.dao.personDao
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


fun Route.personRouting()
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

            val person = personDao.getPerson(username,password)
            if (person==null)
            {
                call.respondText(
                    "No customer with username $username",
                    status = HttpStatusCode.NotFound
                )
            }
            else
            {
                call.respond(person)
            }

        }
        post {
            val person = call.receive<Person>()
            if(personDao.addPerson(person) != null)
            {
                call.respondText("Person stored correctly", status = HttpStatusCode.Created)
            }
        }
    }
}