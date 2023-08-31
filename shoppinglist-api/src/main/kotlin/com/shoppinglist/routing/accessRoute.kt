package com.shoppinglist.routing

import com.shoppinglist.dao.accessDao
import com.shoppinglist.dao.listSize
import com.shoppinglist.dao.personDao
import com.shoppinglist.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

fun Route.accessRounting()
{

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
            if(personDao.getPerson(username,password) == null)
            {
                return@post call.respondText(
                    "No customer with username $username",
                    status = HttpStatusCode.NotFound
                )
            }
            if(accessDao.createNewAccess(username,password,otherUsername,listId.toInt(),"vie")== null) {
                return@post call.respondText(
                    "No customer with username $otherUsername",
                    status = HttpStatusCode.NotFound
                )
            }
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
            if(personDao.getPerson(username,password) == null)
            {
                return@get call.respondText(
                    "No customer with username $username",
                    status = HttpStatusCode.NotFound
                )
            }
            call.respond(accessDao.getAccesses(username,password,listId.toInt()))
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
            if(personDao.getPerson(username,password) == null)
            {
                return@patch call.respondText(
                    "No customer with username $username",
                    status = HttpStatusCode.NotFound
                )
            }
            val access = call.receive<AccessClass>()
            accessDao.changeAccesses(username,password,access.username,listId.toInt())
            call.respondText("ListAccess updated correctly", status = HttpStatusCode.Created)
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
            if(personDao.getPerson(username,password) == null)
            {
                return@delete call.respondText(
                    "No customer with username $username",
                    status = HttpStatusCode.NotFound
                )
            }
            if(accessDao.removeAccess(username,password,otherUsername,listId.toInt()))
            {
                return@delete call.respondText("Access deleted correctly", status = HttpStatusCode.Created)
            }
            call.respondText("Access deltedion failed", status = HttpStatusCode.BadRequest)
        }
    }
}