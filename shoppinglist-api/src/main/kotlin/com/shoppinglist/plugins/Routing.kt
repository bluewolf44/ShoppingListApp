package com.shoppinglist.plugins

import com.shoppinglist.dao.accessDao
import com.shoppinglist.dao.listDao
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.*
import io.ktor.server.application.*
import com.shoppinglist.dao.personDao
import com.shoppinglist.model.Person
import com.shoppinglist.routing.PersonRouting

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        route("setup")
        {
            post{
                personDao.addPerson(Person("Dave","West","Blue","Password","@gmail",false))
                accessDao.createNewAccess("Blue","Password","Blue",0,"own")
                accessDao.createNewAccessType("own")
                accessDao.createNewAccessType("vie")
                accessDao.createNewAccessType("edi")
                accessDao.createNewAccess("Blue","Password","Blue",0,"own")
            }
        }

        route("people") {
            get {
                call.respond(personDao.allPeople())
            }
        }
        route("list") {
            get {
                call.respond(listDao.allList())
            }
        }
        route("access") {
            get {
                call.respond(accessDao.getAccess())
            }
        }
        personRouting()
        //ListRounting()
        //AccessRounting()
    }
}
