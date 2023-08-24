package com.shoppinglist.plugins

import com.shoppinglist.routing.AccessRounting
import com.shoppinglist.routing.ListRounting
import com.shoppinglist.routing.PersonRouting
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.*
import io.ktor.server.application.*
import java.sql.Connection
import java.sql.DriverManager
import com.shoppinglist.dao.dao

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        route("people") {
            get {
                call.respond(dao.allPeople())
            }
        }
        //PersonRouting()
        //ListRounting()
        //AccessRounting()
    }
}
