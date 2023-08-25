package com.shoppinglist

import com.shoppinglist.dao.DatabaseFactory
import com.shoppinglist.dao.PersonDAO
import com.shoppinglist.dao.PersonImplDAO
import com.shoppinglist.model.Person
import io.ktor.server.application.*
import com.shoppinglist.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.init()
    configureSerialization()
    //configureRouting()
}
