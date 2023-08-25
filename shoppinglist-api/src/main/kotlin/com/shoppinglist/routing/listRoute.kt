package com.shoppinglist.routing
//
//import com.shoppinglist.dao.listSize
//import com.shoppinglist.model.*
//import io.ktor.http.*
//import io.ktor.server.application.*
//import io.ktor.server.request.*
//import io.ktor.server.response.*
//import io.ktor.server.routing.*
//import java.sql.Connection
//import java.sql.DriverManager
//import java.sql.PreparedStatement
//
//fun Route.ListRounting()
//{
//    val dbConnection: Connection = DriverManager.getConnection(
//        "jdbc:postgresql://localhost:5432/postgres",
//        "postgres", "XCGT8Fdj"
//    )
//    //var listSize = 1
//
//    route("/List")
//    {
//        get("{username}/{password}"){
//            val username = call.parameters["username"] ?: return@get call.respondText(
//                "Missing username",
//                status = HttpStatusCode.BadRequest
//            )
//            val password = call.parameters["password"] ?: return@get call.respondText(
//                "Missing password",
//                status = HttpStatusCode.BadRequest
//            )
//
//            val statement: PreparedStatement = dbConnection.prepareStatement(
//                "Select list.listid,accesstype,listname,listdescription,lastupdated,datecreated from Person " +
//                        "INNER JOIN Access on Person.UserName = Access.UserName " +
//                        "INNER JOIN List on Access.ListID = List.ListID " +
//                        "where person.username=? and password =?").apply {
//                setString(1,  username)
//                setString(2, password)
//            }
//
//            val resultSet = statement.executeQuery()
//            val lists = mutableListOf<ListClass>()
//            while (resultSet.next()) {
//                lists.add(ListClass(
//                    resultSet.getInt("listid"),
//                    resultSet.getString("accesstype"),
//                    resultSet.getString("listname"),
//                    resultSet.getString("listdescription"),
//                    resultSet.getString("lastupdated"),
//                    resultSet.getString("datecreated"),
//                ))
//            }
//
//            call.respond(lists)
//
//        }
//        get("{username}/{password}/{listId}") {
//            val username = call.parameters["username"] ?: return@get call.respondText(
//                "Missing username",
//                status = HttpStatusCode.BadRequest
//            )
//            val password = call.parameters["password"] ?: return@get call.respondText(
//                "Missing password",
//                status = HttpStatusCode.BadRequest
//            )
//            val listId = call.parameters["listId"] ?: return@get call.respondText(
//                "Missing listId",
//                status = HttpStatusCode.BadRequest
//            )
//
//            val statement: PreparedStatement = dbConnection.prepareStatement(
//                "Select ListText from Person " +
//                        "INNER JOIN Access on Person.UserName = Access.UserName " +
//                        "INNER JOIN List on Access.ListID = List.ListID " +
//                        "where person.username=? and password =? and list.listid =?"
//            ).apply {
//                setString(1, username)
//                setString(2, password)
//                setInt(3, listId.toInt())
//            }
//
//            val resultSet = statement.executeQuery()
//            resultSet.next()
//
//            call.respond(TextClass(resultSet.getString("ListText")))
//        }
//        patch ("{username}/{password}/{listId}") {
//            val username = call.parameters["username"] ?: return@patch call.respondText(
//                "Missing username",
//                status = HttpStatusCode.BadRequest
//            )
//            val password = call.parameters["password"] ?: return@patch call.respondText(
//                "Missing password",
//                status = HttpStatusCode.BadRequest
//            )
//            val listId = call.parameters["listId"] ?: return@patch call.respondText(
//                "Missing listId",
//                status = HttpStatusCode.BadRequest
//            )
//
//            val statement: PreparedStatement = dbConnection.prepareStatement(
//                "UPDATE List SET listtext =? " +
//                        "where listid in (" +
//                        "Select list.listid from Person " +
//                        "INNER JOIN Access on Person.UserName = Access.UserName " +
//                        "INNER JOIN List on Access.ListID = List.ListID " +
//                        "where person.username=? and password =? and list.listid = ?)"
//            ).apply {
//                setString(1,call.receive<TextClass>().text)
//                setString(2, username)
//                setString(3, password)
//                setInt(4, listId.toInt())
//            }
//            statement.executeUpdate()
//
//            call.respondText("Text updated correctly", status = HttpStatusCode.Created)
//        }
//        post("{username}/{password}") {
//            val username = call.parameters["username"] ?: return@post call.respondText(
//                "Missing username",
//                status = HttpStatusCode.BadRequest
//            )
//            val password = call.parameters["password"] ?: return@post call.respondText(
//                "Missing password",
//                status = HttpStatusCode.BadRequest
//            )
//
//
//            val list = call.receive<ListCreate>()
//
//            val statement: PreparedStatement = dbConnection.prepareStatement(
//                "Insert into list (ListID,ListName,ListDescription) " +
//                        "values (?,?,?)").apply {
//                setInt(1, listSize)
//                setString(2, list.listName)
//                setString(3, list.listDescription)
//            }
//            statement.executeUpdate()
//
//            val statement2: PreparedStatement = dbConnection.prepareStatement(
//                "Insert into Access (UserName,ListID,AccessType) " +
//                        "values ((Select UserName from Person where person.username=? and password =?),?,'own')").apply {
//                setString(1, username)
//                setString(2, password)
//                setInt(3, listSize++)
//            }
//            statement2.executeUpdate()
//
//            call.respondText("List stored correctly", status = HttpStatusCode.Created)
//        }
//        post("{username}/{password}/{otherUsername}/{listId}") {
//            val username = call.parameters["username"] ?: return@post call.respondText(
//                "Missing username",
//                status = HttpStatusCode.BadRequest
//            )
//            val password = call.parameters["password"] ?: return@post call.respondText(
//                "Missing password",
//                status = HttpStatusCode.BadRequest
//            )
//            val otherUsername = call.parameters["otherUsername"] ?: return@post call.respondText(
//                "Missing otherUsername",
//                status = HttpStatusCode.BadRequest
//            )
//            val listId = call.parameters["listId"] ?: return@post call.respondText(
//                "Missing listId",
//                status = HttpStatusCode.BadRequest
//            )
//            val statement: PreparedStatement = dbConnection.prepareStatement(
//                "Select * from Person " +
//                        "INNER JOIN Access on Person.UserName = Access.UserName " +
//                        "INNER JOIN List on Access.ListID = List.ListID " +
//                        "where person.username=? and password =? and list.listid =?"
//            ).apply {
//                setString(1, username)
//                setString(2, password)
//                setInt(3, listId.toInt())
//            }
//            val resultSet = statement.executeQuery()
//            if(!resultSet.next())
//            {
//                call.respondText(
//                    "Error someWhere",
//                    status = HttpStatusCode.NotFound
//                )
//            }
//
//            val statement2: PreparedStatement = dbConnection.prepareStatement(
//                "Insert into Access (UserName,ListID,AccessType) " +
//                        "values (?,?,'vie')").apply {
//                setString(1, otherUsername)
//                setInt(2, listId.toInt())
//            }
//            statement2.executeUpdate()
//
//            call.respondText("ListAccess stored correctly", status = HttpStatusCode.Created)
//        }
//
//        delete("{username}/{password}/{listId}")
//        {
//            val username = call.parameters["username"] ?: return@delete call.respondText(
//                "Missing username",
//                status = HttpStatusCode.BadRequest
//            )
//            val password = call.parameters["password"] ?: return@delete call.respondText(
//                "Missing password",
//                status = HttpStatusCode.BadRequest
//            )
//            val listId = call.parameters["listId"] ?: return@delete call.respondText(
//                "Missing listId",
//                status = HttpStatusCode.BadRequest
//            )
//            val statement: PreparedStatement = dbConnection.prepareStatement(
//                "delete from access " +
//                        "where listId in (" +
//                        "Select list.listId from Person " +
//                        "INNER JOIN Access on Person.username = Access.username " +
//                        "INNER JOIN List on Access.ListID = List.ListID " +
//                        "where person.username=? and password =? and list.listid=?" +
//                        ")"
//            ).apply {
//                setString(1, username)
//                setString(2, password)
//                setInt(3, listId.toInt())
//            }
//            statement.executeQuery()
//
//            val statement2: PreparedStatement = dbConnection.prepareStatement(
//                "delete from list " +
//                        "where listId in (" +
//                        "Select list.listId from Person " +
//                        "INNER JOIN Access on Person.username = Access.username " +
//                        "INNER JOIN List on Access.ListID = List.ListID " +
//                        "where person.username=? and password =? and list.listid=?" +
//                        ")"
//            ).apply {
//                setString(1, username)
//                setString(2, password)
//                setInt(3, listId.toInt())
//            }
//            statement2.executeQuery()
//
//            call.respondText("ListAccess stored correctly", status = HttpStatusCode.Created)
//        }
//    }
//}