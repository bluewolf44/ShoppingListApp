package com.shoppinglist.routing

import com.shoppinglist.dao.accessDao
import com.shoppinglist.dao.listDao
import com.shoppinglist.dao.listSize
import com.shoppinglist.dao.personDao
import com.shoppinglist.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

fun Route.listRounting()
{
    route("/list")
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
            if(personDao.getPerson(username,password) == null)
            {
                return@get call.respondText(
                    "No customer with username $username",
                    status = HttpStatusCode.NotFound
                )
            }
            val lists = listDao.getListForPerson(username,password)

            call.respond(lists)

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

            var list = listDao.getList(username,password, listId.toInt()) ?:return@get call.respondText("No list with id $listId", status = HttpStatusCode.NotFound)

            call.respond(list)
        }
        patch ("{username}/{password}/{listId}") {
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
            val list = call.receive<ListClass>()
            if(listDao.updateList(username,password,listId.toInt(),list.text,list.lastUpdated))
            {
                return@patch call.respondText("Text updated correctly", status = HttpStatusCode.Created)
            }
            call.respondText("No list with id $listId", status = HttpStatusCode.NotFound)
        }
        post("{username}/{password}") {
            val username = call.parameters["username"] ?: return@post call.respondText(
                "Missing username",
                status = HttpStatusCode.BadRequest
            )
            val password = call.parameters["password"] ?: return@post call.respondText(
                "Missing password",
                status = HttpStatusCode.BadRequest
            )
            if(personDao.getPerson(username,password) == null)
            {
                return@post call.respondText(
                    "No customer with username $username",
                    status = HttpStatusCode.NotFound
                )
            }

            val list = listDao.addList(call.receive<ListClass>()) ?:  return@post call.respondText("List class added wrong", status = HttpStatusCode.BadRequest)
            accessDao.createNewAccess(username,password,username,list.listID,"own")

            call.respondText("List stored correctly", status = HttpStatusCode.Created)
        }
        delete("{username}/{password}/{listId}")
        {
            val username = call.parameters["username"] ?: return@delete call.respondText(
                "Missing username",
                status = HttpStatusCode.BadRequest
            )
            val password = call.parameters["password"] ?: return@delete call.respondText(
                "Missing password",
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

            if(listDao.deleteList(username,password,listId.toInt()))
            {
                return@delete call.respondText("list deleted correctly", status = HttpStatusCode.Accepted)
            }
            call.respondText("No list with id $listId", status = HttpStatusCode.NotFound)
        }
    }
}