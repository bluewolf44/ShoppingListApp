package com.shoppinglist.plugins

import com.shoppinglist.dao.accessDao
import com.shoppinglist.dao.listDao
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.*
import io.ktor.server.application.*
import com.shoppinglist.dao.personDao
import com.shoppinglist.model.ListClass
import com.shoppinglist.model.Person
import com.shoppinglist.routing.listRounting
import com.shoppinglist.routing.personRouting
import kotlinx.datetime.*

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
                listDao.addList(ListClass(0,"Mine","Mine^2","Stuffff", Clock.System.now().toLocalDateTime(TimeZone.of("NZ")),Clock.System.now().toLocalDateTime(TimeZone.of("NZ"))))

                accessDao.createNewAccessType("own")
                accessDao.createNewAccessType("vie")
                accessDao.createNewAccessType("edi")
                accessDao.createNewAccess("Blue","Password","Blue",0,"own")
                call.respondText("set right", status = HttpStatusCode.Created)
            }
        }

        route("people") {
            get {
                call.respond(personDao.allPeople())
            }
        }
        route("lists") {
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
        listRounting()
        //AccessRounting()
    }
}
