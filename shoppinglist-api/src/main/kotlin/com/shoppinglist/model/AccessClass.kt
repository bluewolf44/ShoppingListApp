package com.shoppinglist.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
@Serializable
data class AccessClass(
    val username:String,
    val listID:Int,
    val accessType:String,
)

object Accesses: Table(){
    val username = varchar("firstName",255).references(Persons.username)
    val listID = integer("listId").references(Lists.listID)
    val accessType = varchar("accessType",3).references(TypeOfAccesses.accessType)

    override val primaryKey = PrimaryKey(username,listID)
}